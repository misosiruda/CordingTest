function solution(board)
{
    let map = board;
    let min = 0;
    let max = 0;
    for(let i=1;i<map.length;i++)
    {
        for(let j=1;j<map[0].length;j++)
        {
            if(map[i][j] > 0)
            {
                min = map[i -1][j - 1];
                if(min > map[i - 1][j]) min = map[i - 1][j];
                if(min > map[i][j - 1]) min = map[i][j - 1];
                if(max < (min + 1)) max = min + 1;
                map[i][j] = min + 1;
            }
        }
    }
    if(max == 0)
    {
        for(e1 of map)
        {
            for(e2 of e1)
            {
                if(e2 == 1) return 1;
            }
        }
    }

    return Math.pow(max, 2);
}