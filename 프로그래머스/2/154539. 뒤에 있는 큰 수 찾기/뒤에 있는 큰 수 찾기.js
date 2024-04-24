function solution(numbers) 
{
    var answer = [];
    let stack = [];
    stack.push(numbers[numbers.length - 1]);
    answer.push(-1);
    let num = -1;
    for(let i=numbers.length - 2;i>=0;i--)
    {
        num = -1;
        while(stack.length > 0)
        {
            if(stack[stack.length - 1] > numbers[i])
            {
                num = stack[stack.length - 1];
                break;
            }
            else
            {
                stack.pop();
            }
        }
        answer.push(num);
        stack.push(numbers[i]);
    }
    
    return answer.reverse();
}