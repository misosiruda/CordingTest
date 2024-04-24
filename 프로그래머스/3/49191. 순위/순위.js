function MakeGraph(n, results)
{
    let arr = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    for (e of results)
    {
        let [a, b] = e;
        a -= 1, b -= 1;
        arr[a][b] = 1;
        arr[b][a] = -1;
    }
    return arr;
}

function solution(n, results)
{
    var answer = 0;
    let graph = MakeGraph(n, results);
    let isEnd = false;
    while (!isEnd)
    {
        isEnd = true;
        for (let i = 0; i < n; i++)
        {
            for (let j = 0; j < n; j++)
            {
                if (graph[i][j] === 1)
                {
                    for (let r = 0; r < n; r++)
                    {
                        if (graph[j][r] === 1 && graph[i][r] === 0)
                        {
                            isEnd = false;
                            graph[i][r] = 1;
                            graph[r][i] = -1;
                        }
                    }
                }
            }
        }
    }
    for (e of graph)
    {
        let count = e.filter((a) => a === 0).length;
        if (count === 1) answer++;
    }
    return answer;
}