using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution 
{
    private static string answer = "";
    private static StringBuilder sb = new StringBuilder();
    public string solution(int[] numbers) 
    {
        Array.Sort(numbers, (x, y) => string.Compare(y.ToString()+x.ToString(),x.ToString()+y.ToString()));
        for (int i = 0; i < numbers.Length; i++)
        {
            sb.Append(numbers[i].ToString());
        }
        bool isZero = true;
        answer = sb.ToString();
        for(int i=0;i<answer.Length;i++)
        {
            if(answer[i]!='0') isZero = false;
        }
        if(isZero) answer = "0";
        return answer;
    }
}