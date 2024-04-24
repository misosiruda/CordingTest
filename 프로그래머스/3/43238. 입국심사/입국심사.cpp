#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) 
{
    long long answer = 0;
    sort(times.begin(), times.end());
    long long start = 1;
    long long end = (long long) times.back() * n;
    long long avg;
    while(start <= end)
    {
        long long cnt = 0;
        avg = (start + end) / 2;
        for(int i=0;i<times.size();i++)
        {
            cnt += avg/times[i];
        }
        
        if(cnt < n)
        {
            start = avg + 1;
        }
        else
        {
            if(avg <= end) answer = avg;
            end = avg - 1;
        }
    }
    return answer;
}