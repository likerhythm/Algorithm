# 연구소
from collections import deque

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
queue = deque()
virus = []

# 바이러스 위치 찾기
for i in range(N):
    for j in range(M):
        if arr[i][j] == 2:
            queue.append([i, j])
            virus.append([i, j])

dn = [-1, 0, 0, 1]
dm = [0, -1, 1, 0]


def setVirus():
    while queue:
        n, m = queue.popleft()
        for i in range(4):
            newN, newM = n+dn[i], m+dm[i]
            if 0<=newN<N and 0<=newM<M and arr[newN][newM]==0:
                queue.append([newN, newM])
                arr[newN][newM] = 2

def reset():
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 2:
                arr[i][j] = 0
    for v1, v2 in virus:
        arr[v1][v2] = 2
        queue.append([v1, v2])

# 빈 공간 세기
def count():
    count = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                count += 1
    return count

# def printArr():
#     for i in range(N):
#         print(*arr[i])
#     print("\n")

def setWall():
    result = -1
    for i in range(2, N*M):
        if arr[i//M][i%M]==0:
            arr[i//M][i%M]=1
        else: continue
        for j in range(1, i):
            if arr[j//M][j%M]==0:
                arr[j//M][j%M]=1
            else: continue
            for k in range(j):
                if arr[k//M][k%M]==0:                 
                    arr[k//M][k%M]=1
                    setVirus()
                    result = max(result, count())
                    reset()
                    arr[k//M][k%M]=0
            arr[j//M][j%M]=0
        arr[i//M][i%M]=0
    return result

print(setWall())
                    