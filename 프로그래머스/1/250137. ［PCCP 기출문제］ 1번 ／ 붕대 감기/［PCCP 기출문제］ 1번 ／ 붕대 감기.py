def solution(bandage, health, attacks):
    lasAtt = attacks[-1]
    answer = health
    continuity = 0

    for time in range(1, lasAtt[0] + 1, 1):
        isAttacked = False
        
        for att in attacks:
            if att[0] == time :
                isAttacked = True
                continuity = 0
                answer -= att[1]
                if answer <= 0 :
                    return -1

        if isAttacked :
            continue

        answer += bandage[1]

        continuity += 1
        if continuity == bandage[0] :
            answer += bandage[2]
            continuity = 0
            
        if(answer > health) :
            answer = health

    return answer