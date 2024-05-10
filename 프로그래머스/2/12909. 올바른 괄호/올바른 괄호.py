def solution(s):
    answer = True
    count = 0
    for e in s:
        if e == '(':
            count += 1
        else:
            count -= 1
        if count < 0:
            answer = False
            break;
    return False if count != 0 else answer