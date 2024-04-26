#include <string>
#include <vector>

using namespace std;

int solution(string myString, string pat) {
    string tmp = "";

    // Pat 수정
    for(int i = 0; i < pat.size(); i++){
        if(pat[i] == 'A'){
            tmp += 'B';
        }
        else{
            tmp += 'A';
        }
    }

    // 같은게 있는지 확인
    bool is_same = true;
    if (myString.size() < tmp.size()){
        return 0;
    }
    for(int i = 0; i <= myString.size() - tmp.size(); i++){
        for(int j = 0; j < tmp.size(); j++){
            is_same = true;
            if(myString[i + j] != tmp[j]){
                is_same = false;
                break;
            }
        }

        if(is_same){
            return 1;
        }
    }

    return 0;
}