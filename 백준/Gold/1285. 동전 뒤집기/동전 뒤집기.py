import sys

def main():
    n = int(sys.stdin.readline().strip())
    # 비트마스킹으로 배열 생성 (H=0, T=1)
    grid = []
    for _ in range(n):
        line = sys.stdin.readline().strip()
        row = 0
        for i, char in enumerate(line):
            if char == 'T':  # 뒷면을 1로 표시
                row |= (1 << (n - 1 - i))
        grid.append(row)
    
    # 열 기준 비트마스크 미리 계산
    # column_bits[j]: 모든 행의 j번째 비트를 모은 n비트 정수
    # 예: column_bits[0] = (행0의 0번째 비트) | (행1의 0번째 비트 << 1) | ...
    column_bits = [0] * n
    for j in range(n):
        mask = 1 << (n - 1 - j)
        for i in range(n):
            if grid[i] & mask:
                column_bits[j] |= (1 << i)
    
    # 완전 탐색: 모든 행 뒤집기 조합을 탐색
    # 각 행 조합에 대해 열을 최적으로 선택
    best_tail_count = float('inf')
    
    # 모든 행 뒤집기 조합 탐색 (2^n 가지)
    for row_mask in range(1 << n):
        tail_count = 0
        for j in range(n):
            # row_mask와 XOR: 뒤집힌 행의 비트만 반전
            # popcount: 1의 개수 = tail 개수 (Python 3.10+ bit_count 사용)
            col_tail_count = (column_bits[j] ^ row_mask).bit_count()
            
            # tail 개수가 n/2 초과이면 뒤집었을 때의 tail 개수 사용
            if col_tail_count > n // 2:
                tail_count += n - col_tail_count
            else:
                tail_count += col_tail_count
        
        best_tail_count = min(best_tail_count, tail_count)
    
    print(best_tail_count)

if __name__ == "__main__":
    main()
