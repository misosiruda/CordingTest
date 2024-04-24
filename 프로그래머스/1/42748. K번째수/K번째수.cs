using System;
using System.Collections.Generic;

public class Solution 
{
    public int[] solution(int[] array, int[,] commands) 
    {
        List<int> result = new List<int>();
        int[] command = new int[3];
        for(int i = 0; i < commands.GetLength(0); i ++)
        {
            command[0] = commands[i, 0];
            command[1] = commands[i, 1];
            command[2] = commands[i, 2];
            result.Add(Result(array, command));
        }
        
        int[] answer = result.ToArray();
        return answer;
    }
    private int Result(int[] array, int[] command)
    {
        List<int> cutList = new List<int>();
        for(int i = 0; i < command[1] - command[0] + 1; i++)
        {
            cutList.Add(array[command[0] + i - 1]);
        }
        cutList.Sort();
        return cutList[command[2] - 1];
    }
}