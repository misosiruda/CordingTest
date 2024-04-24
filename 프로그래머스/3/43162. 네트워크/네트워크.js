function solution(n, computers)
{
    let queue = [];
    let visited = [];
    var answer = 0;
    visited = Array.from({ length: n }, () => true);
    let isOver = false;
    let qNow = 0;
    while (!isOver)
    {
        isOver = true;
        for (let i = 0; i < n; i++)
        {
            if (visited[i])
            {
                isOver = false;
                queue.push(i);
                visited[i] = false;
                while (queue.length > 0)
                {
                    qNow = queue.shift();
                    for (let j = 0; j < n; j++)
                    {
                        if (visited[j] && computers[qNow][j] === 1)
                        {
                            queue.push(j);
                            visited[j] = false;
                        }
                    }
                }
                answer++;
            }
        }
    }
    return answer;
}