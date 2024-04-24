public class Solution 
{
    public string solution(string s, int n) 
    {
        string answer = "";
        char c;
        foreach(char elem in s)
        {
            c = elem;
            if(elem == ' ')
            {
                answer += elem.ToString();
                continue;
            }
            for(int i=1;i<=n;i++)
            {
                if(c == 'Z') c = 'A';
                else if(c == 'z') c = 'a';
                else c = (char)(c + 1);
            }
            answer += c.ToString();
        }
        return answer;
    }
}