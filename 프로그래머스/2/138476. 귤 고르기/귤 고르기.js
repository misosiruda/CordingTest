function solution(k, tangerine) {
    const sorted = tangerine.sort((a,b) => a-b);
    const counts = new Map();
    let maxCount = 0;

    for (const size of sorted){
        counts.set(size, (counts.get(size) || 0)+1);
        maxCount = Math.max(maxCount, counts.get(size));
    }

    let result = 0;
    let remainingCount = k;
    for(const count of [...counts.values()].sort((a,b) => b - a)){
        if(remainingCount >= count){
            remainingCount -= count;
            result++;
        } else if(remainingCount > 0) {
            result++;
            break;
        } else {
            break;
        }
    }

    return result;
}