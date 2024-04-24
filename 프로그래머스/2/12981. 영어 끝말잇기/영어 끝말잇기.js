
function solution(n, words)
{
    let set = new Set();
    let setLen = 0;
    for (let i = 0; i < words.length; i++)
    {
        set.add(words[i]);
        if (set.size === setLen)
        {
            return [i % n + 1, Math.ceil((i + 1) / n)];
        }
        else
        {
            setLen++;
        }
        if (i !== 0)
        {
            if (words[i - 1][words[i - 1].length - 1] !== words[i][0])
            {
                return [i % n + 1, Math.ceil((i + 1) / n)];
            }
        }
    }
    return [0, 0];
}