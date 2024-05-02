using System;

public class Solution 
{
    public int solution(int n, int[] lost, int[] reserve) 
    {
        int[] count = new int[n];
        for(int i=0;i<n;i++)
        {
            count[i] = 1;
        }
        for(int i=0;i<lost.Length;i++)
        {
            count[lost[i]-1]--;
        }
        for(int i=0;i<reserve.Length;i++)
        {
            count[reserve[i]-1]++;
        }
        
        for(int i=0;i<n;i++)
        {
            if(count[i] == 0)
            {
                if(i - 1 > - 1 && i + 1 < n)
                {
                    if(count[i-1] == 2)
                    {
                        count[i-1] = 1;
                        count[i] = 1;
                    }
                    else if(count[i+1] == 2)
                    {
                        count[i+1] = 1;
                        count[i] = 1;
                    }
                    else
                    {
                        continue;
                    }
                }
                else if(i == 0)
                {
                    if(count[i+1] == 2)
                    {
                        count[i+1] = 1;
                        count[i] = 1;
                    }
                    else
                    {
                        continue;
                    }
                }
                else if(i == n - 1)
                {
                    if(count[i-1] == 2)
                    {
                        count[i-1] = 1;
                        count[i] = 1;
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        }
        int answer = 0;
        for(int i=0;i<n;i++)
        {
            if(count[i] > 0)
            {
                answer++;
            }
        }
        return answer;
    }
}