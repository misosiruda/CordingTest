function solution(k, num, links)
{
    const checkLimit = function (limit)
    {
        while (copyStack.length > 0)
        {
            let index = copyStack.pop();
            let [cl, cr] = links[index];
            let cur = num[index];
            if (cl === -1 && cr === -1)//자식이 없을때
            {
                dp[index][0] = cur;
            }
            else if (cl !== -1 && cr !== -1)//자식이 모두 있을 때
            {
                let clNum = dp[cl][0];
                let crNum = dp[cr][0];
                let curSum = cur + clNum + crNum;
                if (curSum <= limit)
                {
                    dp[index][0] = curSum;
                    dp[index][1] = dp[cl][1] + dp[cr][1] - 1;
                }
                else if (cur + Math.min(clNum, crNum) <= limit)
                {
                    dp[index][0] = cur + Math.min(clNum, crNum);
                    dp[index][1] = dp[cl][1] + dp[cr][1];
                }
                else
                {
                    dp[index][0] = cur;
                    dp[index][1] = dp[cl][1] + dp[cr][1] + 1;
                }
            }
            else//자식이 한쪽만 있을 때
            {
                let c = 0;
                cl === -1 ? c = cr : c = cl;
                let cNum = dp[c][0];
                let curSum = cur + cNum;
                if (curSum <= limit)
                {
                    dp[index][0] = curSum;
                    dp[index][1] = dp[c][1];
                }
                else
                {
                    dp[index][0] = cur;
                    dp[index][1] = dp[c][1] + 1;
                }
            }
        }
        return dp[start][1];
    };

    let start = (num.length) * (num.length - 1) / 2;
    let dp = []; // [[같은 그룹인 자식들과 현재노드의 인원수, 현재까지 그룹의 수], ] //초기값은 [0,1]
    dp = links.map(a =>
    {
        let [l, r] = a;
        l !== -1 ? start -= l : 0;
        r !== -1 ? start -= r : 0;
        return [0, 1];
    });
    let stack = [start];
    let sel = 0;
    while (stack.length < links.length)
    {
        let i = stack[sel];
        let [l, r] = links[i];
        l !== -1 ? stack.push(l) : 0;
        r !== -1 ? stack.push(r) : 0;
        sel++;
    }
    let minavg = Math.max(...num), maxavg = 100000000;
    let copyStack = [];
    while (minavg < maxavg)
    {
        copyStack = stack.filter(a => true);
        let avg = Math.floor((maxavg + minavg) / 2);
        let count = checkLimit(avg);
        count > k ? minavg = avg + 1 : maxavg = avg;
    }
    return minavg;
}