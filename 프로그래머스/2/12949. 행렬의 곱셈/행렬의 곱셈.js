function solution(arr1, arr2)
{
    var answer = [];
    let n = arr2.length;
    for (let i = 0; i < arr1.length; i++)
    {
        answer.push([]);
        for (let j = 0; j < arr2[0].length; j++)
        {
            answer[i][j] = 0;
            for (let r = 0; r < n; r++)
            {
                answer[i][j] += arr1[i][r] * arr2[r][j];
            }
        }
    }
    return answer;
}