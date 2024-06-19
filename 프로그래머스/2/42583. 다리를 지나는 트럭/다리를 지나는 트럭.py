def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge_list=[0 for i in range(bridge_length)]
    bridge_w = 0
    while truck_weights != []:
        bridge_w -= bridge_list.pop()
        bridge_list.insert(0,0)
        if bridge_w+truck_weights[0]<=weight:
            bridge_list[0] = truck_weights.pop(0)
            bridge_w+=bridge_list[0]
        answer+=1
    answer+=bridge_length
    return answer