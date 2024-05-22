using System;
using System.Collections.Generic;
using System.Linq;


public class Solution 
{
    public List<int> solution(string[,] places) 
    {
        List<List<char>> colons = new List<List<char>>();
        bool isWrong = false;
        string place;
        int roomNum = places.GetLength(0);
        int rawNum = places.GetLength(1);
        int colonNum = places[0,0].Length;
        List<int> answer = new List<int>();
        
        for(int a=0;a<roomNum;a++)//대기실 번호
        {
            for(int i=0;i<rawNum;i++)//대기실 따로 때기
            {
                place = places[a,i];
                if(a==0)
                {
                    colons.Add(new List<char>());
                    for(int k=0;k<colonNum;k++)
                    {
                        colons[i].Add(place[k]);
                    }
                }
                else
                {
                    colons[i].RemoveRange(0, colonNum);
                    for(int k=0;k<colonNum;k++)
                    {
                        colons[i].Add(place[k]);
                    }
                }
            }
            for(int b=0;b<rawNum;b++)//열 번호
            {
                if(b==0)//첫번째 열 예외처리
                {
                    for(int c=0;c<colonNum-1;c++)//행 번호
                    {
                        if(c==colonNum-2)//마지막 행 예외 처리
                        {
                            if(colons[b][c]=='P'&&colons[b][c+1]=='P') isWrong = true;
                        }
                        else if(colons[b][c]=='P')
                        {
                            switch(colons[b][c+1])
                            {
                                case 'O':
                                    if(colons[b][c+2]=='P') isWrong = true;
                                    break;
                                case 'X':
                                    break;
                                case 'P':
                                    isWrong = true;
                                    break;
                            }
                        }
                        if(isWrong==true) break;
                    }
                    if(isWrong==true) break;
                }
                else
                {
                    for(int c=0;c<colonNum-1;c++)//행 번호
                    {
                        if(c==colonNum-2)//마지막 행 예외처리
                        {
                            if(colons[b][c]=='P')
                            {
                                if(colons[b][c+1]=='P') isWrong = true;
                                if(colons[b-1][c]=='P') isWrong = true;
                                if(colons[b-1][c-1]=='P')
                                {
                                    if(colons[b][c-1]=='O') isWrong = true;
                                    if(colons[b-1][c]=='O') isWrong = true;
                                }
                                if(colons[b-1][c+1]=='P')
                                {
                                    if(colons[b][c+1]=='O') isWrong = true;
                                    if(colons[b-1][c]=='O') isWrong = true;
                                }
                                if(b>1)
                                {
                                    if(colons[b-1][c]=='O'&&colons[b-2][c]=='P')isWrong = true;
                                }
                            }
                        }
                        else if(c==0)//첫 행 예외처리
                        {
                            if(colons[b][c]=='P')
                            {
                                if(colons[b][c+1]=='P') isWrong = true;
                                if(colons[b-1][c]=='P') isWrong = true;
                                if(colons[b-1][c+1]=='P')
                                {
                                    if(colons[b][c+1]=='O') isWrong = true;
                                    if(colons[b-1][c]=='O') isWrong = true;
                                }
                                if(b>1)
                                {
                                    if(colons[b-1][c]=='O'&&colons[b-2][c]=='P')isWrong = true;
                                }
                            }
                        }
                        else if(colons[b][c]=='P')
                        {
                            switch(colons[b][c+1])
                            {
                                case 'O':
                                    if(colons[b][c+2]=='P') isWrong = true;
                                    break;
                                case 'X':
                                    break;
                                case 'P':
                                    isWrong = true;
                                    break;
                            }
                            if(colons[b][c+1]=='P') isWrong = true;
                            if(colons[b-1][c]=='P') isWrong = true;
                            if(colons[b-1][c-1]=='P')
                            {
                                if(colons[b][c-1]=='O') isWrong = true;
                                if(colons[b-1][c]=='O') isWrong = true;
                            }
                            if(colons[b-1][c+1]=='P')
                            {
                                if(colons[b][c+1]=='O') isWrong = true;
                                if(colons[b-1][c]=='O') isWrong = true;
                            }
                            if(b>1)
                            {
                                if(colons[b-1][c]=='O'&&colons[b-2][c]=='P')isWrong = true;
                            }
                        }
                        if(isWrong==true) break;
                    }
                    if(colons[b][colonNum-1]=='P')
                    {
                        if(colons[b-1][colonNum-1]=='P')isWrong = true;
                        if(colons[b-1][colonNum-2]=='P')
                        {
                            if(colons[b][colonNum-2]=='O') isWrong = true;
                            if(colons[b-1][colonNum-1]=='O') isWrong = true;
                        }
                        if(b>1)
                        {
                            if(colons[b-1][colonNum-1]=='O'&&colons[b-2][colonNum-1]=='P')isWrong = true;
                        }
                    }
                    if(isWrong==true) break;
                }
            }
            if(isWrong==true) answer.Add(0);
            else answer.Add(1);
            isWrong = false;
        }
        
        return answer;
    }
}