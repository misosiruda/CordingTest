function solution(a, b, n)
{
    var answer = 0;
    let num = n;
    let newCola = 0;
    while (num > 1)
    {
        if (num < a) break;
        newCola = Math.floor(num / a) * b;
        answer += newCola;
        num = num % a + newCola;
        console.log(num);
    }
    return answer;
}