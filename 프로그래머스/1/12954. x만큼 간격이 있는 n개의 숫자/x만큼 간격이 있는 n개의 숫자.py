def solution(x, n):
    answer = []
    if x == 0:
        answer = [0] * n
    else:
        answer = list(range(x, (n + 1) * x, x));
    return answer