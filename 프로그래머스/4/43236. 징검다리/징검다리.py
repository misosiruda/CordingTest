def solution(distance, rocks, n):
    """
    (프로그래머스 43236) 징검다리

    바위를 n개 제거한 뒤, 인접한 지점(0, 남은 바위들, distance) 사이 거리의
    "최솟값"을 최대화한 값을 반환합니다.

    정석 풀이: 파라메트릭 서치(이분 탐색)
    - 어떤 최소 거리 x를 만족시키려면, 인접 거리 < x 인 바위들을 제거하면 됩니다.
    - 필요한 제거 개수가 n 이하라면 x는 가능(더 키워볼 수 있음), 초과면 불가능.

    시간 복잡도: O(k log distance), k = len(rocks)
    """
    sorted_rocks = sorted(rocks)

    def required_removals(min_gap: int) -> int:
        """모든 인접 거리 >= min_gap 을 만들기 위한 최소 제거 개수."""
        removed = 0
        prev = 0

        # 마지막 도착 지점도 '바위'처럼 포함시켜 gap 계산
        for rock in sorted_rocks + [distance]:
            gap = rock - prev
            if gap < min_gap:
                removed += 1
            else:
                prev = rock

        return removed

    lo, hi = 1, distance
    best = 0

    while lo <= hi:
        mid = (lo + hi) // 2
        if required_removals(mid) <= n:
            best = mid
            lo = mid + 1
        else:
            hi = mid - 1

    return best