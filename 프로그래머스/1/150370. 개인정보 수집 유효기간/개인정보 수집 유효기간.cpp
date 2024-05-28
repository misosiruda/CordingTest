#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    map<char, int> mp;

    // 현재 날짜
    int year = stoi(today.substr(0, today.find(".")));
    today.erase(0, today.find(".") + 1);
    int month = stoi(today.substr(0, today.find(".")));
    today.erase(0, today.find(".") + 1);
    int day = stoi(today);


    // 맵에 약관종류 넣기
    for (string str : terms)
    {
        int pos = str.find(" ");
        char c = str[0];
        int m = stoi(str.substr(pos, str.size() - 1));
        mp[c] = m;
    }

    int idx = 1;
    for (string str : privacies)
    {
        vector<int> v;
        int pos = 0;

        // 년월 int로 넣기
        while ((pos = str.find(".")) != string::npos)
        {
            int a = stoi(str.substr(0, pos));
            v.push_back(a);
            str.erase(0, pos + 1);
        }
        // 일 넣기
        int d = stoi(str.substr(0, str.find(" ")));
        v.push_back(d);
        str.erase(0, str.find(" ") + 1);

        // 계약 기간에 해당하는 만큼 날짜 변경하기
        int m = mp[str[0]];
        // 달에 추가
        v[1] += m;

        int plus = v[1] / 12;
        int remain = v[1] % 12;
        if (remain == 0) {
            plus--;
            remain = 12;
        }
        v[0] += plus;
        v[1] = remain;

        // 현재 날짜를 기준으로 년도 비교 월 비교 일 비교하기
        if (v[0] < year)
            answer.push_back(idx);
        else if (v[0] == year && v[1] < month)
            answer.push_back(idx);
        else if (v[0] == year && v[1] == month && v[2] - 1 < day)
            answer.push_back(idx);

        idx++;
    }

    // 개인정보를 기준으로 약관 지난 기간으로 작성



    return answer;
}