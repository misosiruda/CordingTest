import sys
sys.setrecursionlimit(10**6)

def dfs(x,y,visited,answer,maps,res):
    dx=[-1,1,0,0]
    dy=[0,0,1,-1]
    visited[x][y] = True
    res1=int(maps[x][y])

    for i in range(4):

        nx=x+dx[i]
        ny=y+dy[i]
        if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and not visited[nx][ny] and maps[nx][ny] != "X":
            res1+=dfs(nx,ny,visited,answer,maps,res)

    return res1

def solution(maps):
    answer = []
    res=0
    visited = [[False for _ in range(len(maps[0]))] for _ in range(len(maps))]
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if maps[i][j] != "X" and not visited[i][j]:
                answer.append(dfs(i,j,visited,answer,maps,res))

    if len(answer)==0:
        return [-1]
    else:
        answer.sort()
        return answer