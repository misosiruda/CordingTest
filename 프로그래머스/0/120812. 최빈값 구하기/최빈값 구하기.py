from collections import Counter

def solution(array):
    counter = Counter(array)

    value_list = list(sorted(counter.values(),reverse=True))

    key_list = list(counter.keys())

    max_frequency= max(value_list)
    my_dict = dict(counter)


    print(value_list)
    print(max_frequency)
    
    if len(value_list) == 1:
        answer = array[0]

    elif max_frequency == value_list[1]:
        answer = -1


    else:
        target_value = max_frequency
        for key, value in my_dict.items():
            if value == target_value:

                answer = key



    return answer