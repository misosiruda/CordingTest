let queue = [];//현재 단어, 현재 이동수

function isPossibleChanging(word1, word2)
{
    let count = 0;
    for (let i = 0; i < word1.length; i++)
    {
        if (word1[i] != word2[i])
        {
            count++;
        }
    }
    if (count > 1) return false;
    else return true;
}

function solution(begin, target, words)
{
    let exceptionCheck = true;
    for (e of words)
    {
        if (e == target) exceptionCheck = false;
    }
    if (exceptionCheck) return 0;
    queue.push([begin, 0]);
    let qNow = [];
    let wordNow = '';
    let countNow = 0;
    while (queue.length > 0)
    {
        qNow = queue.shift();
        wordNow = qNow[0];
        countNow = qNow[1];
        if (wordNow == target) break;
        for (e of words)
        {
            if (isPossibleChanging(wordNow, e))
            {
                queue.push([e, countNow + 1]);
            }
        }
    }
    return countNow;
}