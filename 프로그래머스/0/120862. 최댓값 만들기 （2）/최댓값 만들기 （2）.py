def solution(numbers):
    big_num = -100000000
    for i in range(len(numbers)):
        for j in range(i + 1, len(numbers)):
            if (numbers[i] * numbers[j] > big_num):
                big_num = numbers[i] * numbers[j]
    answer = big_num
    return answer