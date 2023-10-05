# 토마토

from collections import deque
import sys
input = sys.stdin.readline



m, n, h = map(int, input().split())

# 3차원 배열
arr = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
queue = deque()

for i in range(h):
    for j in range(n):
        for k in range(m):
            if arr[i][j][k] == 1:
                queue.append([i, j, k])

def tomato():
    while queue:
        i, j, k = queue.popleft()
        if i+1<h and arr[i+1][j][k] == 0:
            queue.append([i+1, j, k])
            arr[i+1][j][k] = arr[i][j][k] + 1
        if i-1>-1 and arr[i-1][j][k] == 0:
            queue.append([i-1, j, k])
            arr[i-1][j][k] = arr[i][j][k] + 1
        if j+1<n and arr[i][j+1][k] == 0:
            queue.append([i, j+1, k])
            arr[i][j+1][k] = arr[i][j][k] + 1
        if j-1>-1 and arr[i][j-1][k] == 0:
            queue.append([i, j-1, k])
            arr[i][j-1][k] = arr[i][j][k] + 1
        if k+1<m and arr[i][j][k+1] == 0:
            queue.append([i, j, k+1])
            arr[i][j][k+1] = arr[i][j][k] + 1
        if k-1>-1 and arr[i][j][k-1] == 0:
            queue.append([i, j, k-1])
            arr[i][j][k-1] = arr[i][j][k] + 1

tomato()
for i in range(h):
    for j in range(n):
        for k in range(m):
            if arr[i][j][k] == 0:
                print(-1)
                exit(0)
print(max(map(max, map(max, arr)))-1)  
    

