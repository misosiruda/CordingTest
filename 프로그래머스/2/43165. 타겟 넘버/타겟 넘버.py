def solution(numbers, target):
    answer = 0
    sign = dict(zip(["0", "1"], [-1, 1]))
    n = len(numbers)
    for i in range(2 ** n):
        cnt = 0
        num = bin(i)[2:]
        m = len(num)
        if m < n:
            num = '0' * (n - m) + num
        for j in range(n):
            cnt += numbers[j] * sign[num[j]]
        if cnt == target:
            answer += 1
    return answer