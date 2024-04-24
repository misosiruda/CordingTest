function DFS(n, f, a)
{
    if(n != 1)
    {
        let value = Math.pow(4, n-1);
        let arr = [value, value, 0, value, value];
        let devine = Math.pow(5, n-1)
        let index = Math.floor(f / devine);
        let nextf = f % devine;
        for(let i=0;i<index;i++)
        {
            a += arr[i];
        }
        console.log(n, index, nextf, a);
        if(index == 2)
        {
            return a;
        }
        a = DFS(n-1, nextf, a);
    }
    else
    {
        let arr = [1, 1, 0, 1, 1];
        for(let i=0;i<f;i++)
        {
            a += arr[i];
        }
    }
    console.log(a);
    return a;
}

function solution(n, l, r) 
{
    let answer = 0;
    answer = DFS(n, r, 0) - DFS(n, l-1, 0);
    return answer;
}