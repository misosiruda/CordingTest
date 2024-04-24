using System;

public class Solution 
{
    public int[] solution(int rows, int columns, int[,] queries) 
    {
        int[,] table = new int[rows, columns];
        int num = 1;
        int[] answer = new int[queries.GetLength(0)];
        for(int i=0;i<rows;i++)
        {
            for(int k=0;k<columns;k++)
            {
                table[i,k] = num;
                num++;
            }
        }        
        for(int i=0;i<queries.GetLength(0);i++)
        {
            int qurow = queries[i,0] - 1, qucol = queries[i,1] - 1;
            int temp = table[qurow, qucol], min = 10001;
            for(int k=0;k<queries[i,2] - queries[i,0];k++)
            {
                if(min > table[qurow, qucol]) min = table[qurow, qucol];
                table[qurow, qucol] = table[qurow+1, qucol];
                qurow++;
            }
            for(int k=0;k<queries[i,3] - queries[i,1];k++)
            {
                if(min > table[qurow, qucol]) min = table[qurow, qucol];
                table[qurow, qucol] = table[qurow, qucol+1];
                qucol++;
            }
            for(int k=0;k<queries[i,2] - queries[i,0];k++)
            {
                if(min > table[qurow, qucol]) min = table[qurow, qucol];
                table[qurow, qucol] = table[qurow-1, qucol];
                qurow--;
            }
            for(int k=0;k<queries[i,3] - queries[i,1] - 1;k++)
            {
                if(min > table[qurow, qucol]) min = table[qurow, qucol];
                table[qurow, qucol] = table[qurow, qucol-1];
                qucol--;
            }
            if(min > table[qurow, qucol]) min = table[qurow, qucol];
            table[qurow, qucol] = temp;
            answer[i] = min;
        }
        return answer;
    }
}