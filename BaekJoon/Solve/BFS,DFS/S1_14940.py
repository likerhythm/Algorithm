# 쉬운 최단거리

from collections import deque

def search(i, j):
    queue = deque([[i, j]])
    visited[i][j] = True

    while queue:
        v = queue.popleft()
        i, j = v[0], v[1]
        if i+1<n and not visited[i+1][j] and arr[i+1][j] != 0:
            queue.append([i+1, j])
            arr[i+1][j] = arr[i][j]+1
            visited[i+1][j] = True
        if i-1>-1 and not visited[i-1][j] and arr[i-1][j] != 0:
            queue.append([i-1, j])
            arr[i-1][j] = arr[i][j]+1
            visited[i-1][j] = True
        if j+1<m and not visited[i][j+1] and arr[i][j+1] != 0:
            queue.append([i, j+1])
            arr[i][j+1] = arr[i][j]+1
            visited[i][j+1] = True
        if j-1>-1 and not visited[i][j-1] and arr[i][j-1] != 0:
            queue.append([i, j-1])
            arr[i][j-1] = arr[i][j]+1
            visited[i][j-1] = True

n, m = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(n)]
visited = [[False]*m for _ in range(n)]
start = []

for i in range(n):
    flag = False
    for j in range(m):
        if arr[i][j] == 2:
            arr[i][j] = 0
            start = [i, j]
            search(i, j)
            flag = True
            break
    if flag:
        break

for i in range(n):
    for j in range(m):
        if arr[i][j] == 1 and not visited[i][j]:
            print(-1, end=" ")
        else:
            print(arr[i][j], end=" ")
    print()

