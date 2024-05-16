def solution(keymap, targets):
    answer = []         
    for string in targets:
        cnt = 0
        for s in string:
            check = []
            for key in keymap:
                if key.find(s) != -1:
                    check.append(key.find(s)+1)
            if len(check) > 0:
                check.sort()
                cnt += check[0]
            else:
                cnt = -1
                break
        answer.append(cnt)
    return answer