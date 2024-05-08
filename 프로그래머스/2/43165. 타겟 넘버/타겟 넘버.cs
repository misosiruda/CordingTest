using System;

public class Solution 
{
    static int des,tar;
    static int answer = 0;
    public void search(int sum, int n, int[] arr)
    {
        if(n < des)
        {
            search(sum + arr[n], n+1, arr);
            search(sum - arr[n], n+1, arr);
        }
        else if(sum == tar)
            answer++;
            
    }
    public int solution(int[] numbers, int target) 
    {
        des = numbers.Length;
        tar = target;
        search(0,0,numbers);
        return answer;
    }
}