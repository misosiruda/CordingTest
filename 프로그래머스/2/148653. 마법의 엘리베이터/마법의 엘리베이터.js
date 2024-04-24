function solution(storey) 
{
    let answer = 0;
    let str = String(storey);
    let upper = 0;
    let num = 0;

    for(let i=str.length - 1;i>=0;i--)
    {
        num = Number(str[i]) + upper;
        if(num == 5)
        {
            if(Number(str[i-1]) < 5)
            {
                answer += num;
                upper = 0;
            }
            else
            {
                answer += 10 - num;
                upper = 1;
            }
        }
        else if(num < 5)
        {
            answer += num;
            upper = 0;
        }
        else
        {
            answer += 10 - num;
            upper = 1;
        }
    }
    if(num != 5)
    {
        answer += upper;
    }
    return answer;
}