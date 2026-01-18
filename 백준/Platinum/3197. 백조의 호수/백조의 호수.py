from collections import deque
import sys

def solve():
    R, C = map(int, sys.stdin.readline().split())
    lake = [list(sys.stdin.readline().strip()) for _ in range(R)]
    
    # 백조 위치 찾기
    swans = []
    for i in range(R):
        for j in range(C):
            if lake[i][j] == 'L':
                swans.append((i, j))
    
    if len(swans) < 2:
        return 0
    
    swan1, swan2 = swans[0], swans[1]
    
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    
    # 물 위치 큐 (물과 인접한 얼음을 녹이기 위해)
    water_queue = deque()
    # 백조가 현재 탐색할 수 있는 위치 큐 (물 위에서만 이동)
    swan_queue = deque()
    # 백조가 다음날 탐색할 위치 큐 (오늘은 얼음이지만 내일은 물이 될 위치)
    next_swan_queue = deque()
    
    # visited 배열: 백조가 방문한 위치
    swan_visited = [[False] * C for _ in range(R)]
    
    # 초기화: 물 위치와 백조 시작 위치 설정
    for i in range(R):
        for j in range(C):
            if lake[i][j] == '.' or lake[i][j] == 'L':
                water_queue.append((i, j))
            if lake[i][j] == 'L' and (i, j) == swan1:
                swan_queue.append((i, j))
                swan_visited[i][j] = True
    
    day = 0
    
    while True:
        # 1. 백조가 만날 수 있는지 확인 (현재 물 위에서만 이동)
        next_swan_queue = deque()
        
        while swan_queue:
            x, y = swan_queue.popleft()
            
            # 두 번째 백조에 도달했는지 확인
            if (x, y) == swan2:
                return day
            
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < R and 0 <= ny < C and not swan_visited[nx][ny]:
                    if lake[nx][ny] == '.' or lake[nx][ny] == 'L':
                        # 물이면 바로 이동 가능
                        swan_visited[nx][ny] = True
                        swan_queue.append((nx, ny))
                    elif lake[nx][ny] == 'X':
                        # 얼음이면 다음날 탐색할 위치에 추가
                        swan_visited[nx][ny] = True
                        next_swan_queue.append((nx, ny))
        
        # 2. 물과 인접한 얼음을 녹이기
        next_water_queue = deque()
        water_visited = set()
        
        while water_queue:
            x, y = water_queue.popleft()
            
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < R and 0 <= ny < C:
                    if lake[nx][ny] == 'X' and (nx, ny) not in water_visited:
                        # 얼음을 물로 변경
                        lake[nx][ny] = '.'
                        water_visited.add((nx, ny))
                        next_water_queue.append((nx, ny))
                        # 만약 이 위치가 백조가 다음날 탐색할 위치였다면, 바로 탐색 가능하도록
                        if swan_visited[nx][ny]:
                            swan_queue.append((nx, ny))
        
        # 3. 다음날 준비
        day += 1
        swan_queue = next_swan_queue
        water_queue = next_water_queue

if __name__ == '__main__':
    print(solve())
