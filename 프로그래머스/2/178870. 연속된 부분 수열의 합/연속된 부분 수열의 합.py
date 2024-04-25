def solution(sequence, k):
    first = 1; second = 1000001
    end, interval_sum, n = 0, 0, len(sequence)
    for start in range(n):
        while end < n and interval_sum < k:
            interval_sum += sequence[end]
            end += 1
        if interval_sum == k:
            if (end-1) - start < second - first:
                first, second = start, end-1
        interval_sum -= sequence[start]

    return [first, second]