def solution(jobs:list) -> int :
    import heapq as hq
    hq.heapify(jobs)
    len_ = len(jobs)
    time = 0 
    answer = 0

    heap = []
    while jobs :

        if time == 0 :
            request_time, required_time = hq.heappop(jobs)
            time += request_time+required_time
            answer += required_time

        while jobs and time >= jobs[0][0]:
            a,b = hq.heappop(jobs)
            hq.heappush(heap, [b,a])
            if not jobs or jobs[0][0] > time :
                break
        if heap:
            required_time, request_time = hq.heappop(heap)
            answer += (time - request_time) + required_time
            time += required_time
        else :
            time += 1

    while heap:
        required_time, request_time = hq.heappop(heap)
        answer += (time - request_time) + required_time
        time += required_time

    return answer // len_