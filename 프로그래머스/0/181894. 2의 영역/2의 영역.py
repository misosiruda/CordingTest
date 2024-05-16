def solution(arr):
    l = [idx for idx, n in enumerate(arr) if n == 2]
    if len(l) == 0:
        return [-1]
    else:
        answer = arr[min(l):max(l)+1]
    return answer