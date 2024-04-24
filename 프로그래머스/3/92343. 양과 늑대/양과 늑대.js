let answers = [];
let infoArr = [];
let edgeArr = [];


function DFS(now, arr, sheep, wolf)
{
    let sheepNow = sheep;
    let wolfNow = wolf;
    let arrCopy = arr.filter(() => true);
    if (infoArr[now] == 0) sheepNow++;
    else wolfNow++;
    if (sheepNow - wolfNow <= 0)
    {
        answers.push(sheepNow);
        return;
    }

    for (e of edgeArr)
    {
        if (now == e[0])
        {
            arrCopy.push(e[1]);
        }
    }

    let arrTemp = [];
    let next = 0;
    for (let i = 0; i < arrCopy.length; i++)
    {
        arrTemp = arrCopy.filter(() => true);
        next = arrTemp.splice(i, 1);
        DFS(next, arrTemp, sheepNow, wolfNow);
    }
    answers.push(sheepNow);
}

function solution(info, edges)
{
    infoArr = info;
    edgeArr = edges;
    DFS(0, [], 0, 0);

    return Math.max(...answers);
}