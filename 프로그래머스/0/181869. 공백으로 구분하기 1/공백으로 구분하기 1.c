#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct info_{
    int offset;
    int count;
} info;

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
char** solution(const char* my_string) {

    info A[500] = { {0, 0}, };
    int i = 0, cnt = 0, start = 0, length = 0;
    while (my_string[i] != '\0') {
        if (my_string[i] == ' ') {
            A[cnt].offset = start;
            A[cnt].count = i - start;
            ++cnt;
            start = i + 1;
        }
        ++i;
    }
    length = i + 1;
    A[cnt].offset = start;
    A[cnt].count = length - start;
    ++cnt;

    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    char** answer = (char**)malloc(sizeof(char*) * (cnt + 1));
    if (answer != NULL) {
        memset(answer, 0x0, sizeof(char*) * (cnt + 1));
    }

    for (int i = 0; i < cnt; ++i) {
        info tmp = A[i];
        char* subStr = (char*)malloc(sizeof(char) * (tmp.count + 1));
        if (subStr != NULL) {
            memset(subStr, 0x0, sizeof(char) * (tmp.count + 1));
            for (int j = 0; j < tmp.count; ++j) {
                subStr[j] = my_string[tmp.offset + j];
            }

            answer[i] = (char*)subStr;
        }
    }

    return answer;

}