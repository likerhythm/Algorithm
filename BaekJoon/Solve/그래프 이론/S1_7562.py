# 나이트의 이동
from collections import deque

t = int(input())

for _ in range(t):
    i = int(input())
    start = list(map(int, input().split()))
    finish = list(map(int, input().split()))
    chess = [[0 for _ in range(i)] for _ in range(i)]

    queue = deque()
    queue.append(start)

    dx = [-2, -2, -1, -1, 1, 1, 2, 2]
    dy = [-1, 1, -2, 2, -2, 2, -1, 1]

    while queue:
        x, y = queue.popleft()
        if x == finish[0] and y == finish[1]:
            print(chess[x][y])
            break
        for n in range(8):
            newX, newY = x+dx[n], y+dy[n]
            if 0<=newX<i and 0<=newY<i and chess[newX][newY]==0:
                chess[newX][newY] = chess[x][y]+1
                queue.append([newX, newY])
          

