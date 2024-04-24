function solution(a, b, g, s, w, t)
{
    function isPossibleTime(time)
    {
        let n = g.length;
        let total = 0;
        let totalG = 0;
        let totalS = 0;
        for (let i = 0; i < n; i++)
        {
            let maxWeight = Math.min((Math.floor((time + t[i]) / (t[i] * 2)) * w[i]), (g[i] + s[i]));
            total += maxWeight;
            totalG += Math.min(maxWeight, g[i]);
            totalS += Math.min(maxWeight, s[i]);
        }
        return total >= a + b && totalG >= a && totalS >= b ? true : false;
    }

    let minTime = 1, maxTime = 2000000000000000;
    while (minTime < maxTime)
    {
        let sel = Math.floor((minTime + maxTime) / 2);
        isPossibleTime(sel) ? maxTime = sel : minTime = sel + 1;
    }
    let answer = minTime;
    return answer;
}