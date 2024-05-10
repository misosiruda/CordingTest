#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    int tYY = stoi(today.substr(0, 4));
    int tMM = stoi(today.substr(5, 2));
    int tDD = stoi(today.substr(8, 2));

    for(int i = 0; i < privacies.size(); i++){
        int YY = stoi(privacies[i].substr(0, 4));
        int MM = stoi(privacies[i].substr(5, 2));
        int DD = stoi(privacies[i].substr(8, 2));

        // 약관 종류와 유효기간을 파악합니다.
        char privacyTerm = privacies[i][11];
        int termMonths = 0;
        for (string term : terms) {
            if (term[0] == privacyTerm) {
                if (term.size() == 3) {
                    termMonths = stoi(term.substr(2, 1));
                } else if (term.size() == 4) {
                    termMonths = stoi(term.substr(2, 2));
                } else {
                    termMonths = stoi(term.substr(2, 3));
                }
                break;
            }
        }

        // 유효기간을 계산합니다.
        YY += (MM + termMonths - 1) / 12;
        MM += (termMonths) % 12;
        MM = MM > 12 ? MM - 12 : MM;
        cout << YY << "." << MM << "." << DD << endl;
        // 현재 날짜와 비교하여 파기해야 할 개인정보를 찾습니다.
        if (tYY > YY || (tYY == YY && tMM > MM) || (tYY == YY && tMM == MM && tDD >= DD)) {
            answer.push_back(i + 1);
        }
    }
    return answer;
}