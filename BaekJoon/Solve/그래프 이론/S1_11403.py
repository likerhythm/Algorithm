from collections import deque

def find(i, j):
    for a in range(n):
        # 간선으로 연결되어 있지만 아직 방문하지 않은 경우
        if arr[j][a] == 1 and result[i][a] == 0:
            result[i][a] = 1
            queue.append(a)
            find(i,a)
        



n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]
result = [[0]*n for _ in range(n)]

queue = deque()

for i in range(n):
    for j in range(n):
        if arr[i][j] == 1:
            result[i][j] = 1
            find(i, j)

# 출력
for i in range(n):
    print(' '.join(str(s) for s in result[i]))