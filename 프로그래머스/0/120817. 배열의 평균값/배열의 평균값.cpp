#include <string>
#include <vector>
#include <iostream>
using namespace std;

double solution(vector<int> numbers) {
    int answer = 0;


    double total = 0;

    for(int i = 0; i < numbers.size(); i++){
        total += numbers[i];
    }

    total =( total / numbers.size());

    return total;

}