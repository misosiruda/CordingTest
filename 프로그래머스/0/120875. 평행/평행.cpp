#include <string>
#include <vector>
#include <map>
using namespace std;

int solution(vector<vector<int>> dots) {
    int answer = 0;
    vector<int> f_key;

    for(int i = 0; i < dots.size()-1; i++)
    {
        map<int, int> temp = {{0,0}, {1,0}, {2,0}, {3,0}};
        temp[i] = 1;
        temp[i+1] = 1;

        for(const auto& c : temp)
        {
            if(c.second == 0)
            {
                f_key.push_back(c.first);
            }
        }



        if((dots[i + 1][0] - dots[i][0]) != 0 && (dots[f_key[1]][0] - dots[f_key[0]][0]) != 0 && (dots[f_key[1]][1] - dots[f_key[0]][1]) != 0 && (dots[f_key[1]][0] - dots[f_key[0]][0]) != 0)
        {
            double a = (double)(dots[i + 1][1] - dots[i][1]) / (double)(dots[i + 1][0] - dots[i][0]);
            double b = (double)(dots[f_key[1]][1] - dots[f_key[0]][1]) / (double)(dots[f_key[1]][0] - dots[f_key[0]][0]);
            if (a == b)
            {
                answer = 1;
                break;
            }
            else
            {
                f_key.clear();
            } 
        }

    }
    return answer;
}