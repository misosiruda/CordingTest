#include <string>
#include <vector>

using namespace std;

string solution(string bin1, string bin2) {
    string answer = "";

    int result = stoi(bin1, 0, 2) + stoi(bin2, 0, 2);

    while(result != 0)
    {
        answer = (result % 2 == 0 ? "0" : "1") + answer;
        result /= 2;
    }
    return answer == "" ? "0" : answer;
}