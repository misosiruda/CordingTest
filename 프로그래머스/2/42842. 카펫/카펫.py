def solution(brown, yellow):
    answer = []
    # yellow가 1이면 3x3만 가능
    if yellow == 1:
        return [3, 3]

    # 곱해서 yellow가 되는 수의 짝
    yellow_divisor = myDivisor(yellow) 
    for i in range(0, len(yellow_divisor), 2):
        height = yellow_divisor[i] # 작은 부분이 세로
        width = yellow_divisor[i + 1] # 큰 부분이 가로
        # 해당 조합으로 brown수를 만들 수 있다면
        if ((height + width) * 2) + 4 == brown:
            answer = [width + 2, height + 2]
            break
    return answer

def myDivisor(number):
    divisor = []
    for i in range(1, int(number**(1/2)) + 1):
        if number % i == 0:
            divisor.append(i)
            # 1, 24 / 2, 12 / 3, 8 / 4, 6
            divisor.append(number // i)
    return divisor