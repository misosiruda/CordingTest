function solution(arr)
{
    let tailMax = 0, tailMin = 0;
    let i = arr.length - 1;
    let arrTemp = [];
    while (i >= 0)
    {
        arrTemp = [];
        while (i >= 0)
        {
            if (i % 2 === 0)
            {
                arrTemp.unshift(Number(arr[i]));
            }
            else
            {
                if (arr[i] === '-')
                {
                    break;
                }
            }
            i--;
        }
        let sum = arrTemp.reduce((p, v) => p += v, 0);
        if (i < 0)
        {
            return sum + tailMax;
        }
        let compareArr = [
            sum - (arrTemp[0] * 2) + tailMax,
            sum - (arrTemp[0] * 2) + tailMin,
            -sum + tailMax,
            -sum + tailMin,
            -sum - tailMax,
            -sum - tailMin
        ];
        tailMax = Math.max(...compareArr);
        tailMin = Math.min(...compareArr);
        i--;
    }
}