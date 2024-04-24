using System;
using System.Linq;
using System.Collections.Generic;

class Solution
{
    public int solution(int[] nums)
    {
        int sum = 0;
        int answer = 0;
        List<int> list = new List<int>();
        for(int i=0;i<nums.Length-2;i++)
        {
            for(int n=i+1;n<nums.Length-1;n++)
            {
                if(i == n){continue;}
                for(int k=n+1;k<nums.Length;k++)
                {
                    if(i==k||n==k){continue;}
                    sum = nums[i] + nums[n] + nums[k];
                    list.Add(sum);
                    sum = 0;
                }
            }
        }
        list.Sort();
        foreach(int elem in list)
        {
            if(isDecimal(elem)){answer++;}
        }
        System.Console.WriteLine();
        return answer;
    }
    
    
    private bool isDecimal(int n)
    {
        int res = 0;
        for(int i=2;i<n;i++)
        {
            if(n%i == 0)
            {
                if(n/i <= 1)
                {
                    break;
                }
                res++;
            }
        }
        if(res == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}