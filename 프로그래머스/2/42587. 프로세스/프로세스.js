function solution(priorities, location) {
    const runIdx = [];  // 프로세스 실행순으로 넣을 배열
    let currIdx = 0;
    const len = priorities.length;
    let maxProcess = Math.max(...priorities);
    let dict = [];
    for(let i = 0; i < priorities.length; i++) {
        dict.push([priorities[i], i]);
    }

    while (dict.length) {
        let [currProcess, currIdx]= dict.shift();

        if (currProcess === maxProcess) {
            runIdx.push(currIdx);
            maxProcess = 0;
            for(let i = 0; i < dict.length; i++) {
                maxProcess = Math.max(maxProcess, dict[i][0]);
            }
        } else {
            dict.push([currProcess, currIdx]);
        }
    }
    console.log(runIdx)
    return runIdx.indexOf(location) + 1;
}