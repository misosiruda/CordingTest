function solution(words)
{
    words.sort();
    words.unshift('');
    words.push('');
    var answer = 0;
    for (let i = 1; i < words.length; i++)
    {
        let j = 0;
        let prev = 0, next = 0;
        for (j = 0; j < words[i - 1].length; j++)
        {
            if (words[i - 1][j] !== words[i][j])
            {
                break;
            }
        }
        prev = j + 1;
        for (j = 0; j < words[i].length; j++)
        {
            if (words[i][j] !== words[i + 1][j])
            {
                break;
            }
        }
        next = j === words[i].length ? j : j + 1;
        answer += Math.max(prev, next);
    }
    return answer - 1;
}