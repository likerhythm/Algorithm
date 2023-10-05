from collections import deque

n = int(input())

start = list(map(int, input().split()))
finish = list(map(int, input().split()))

#미로
arr = [list(map(int, input().split())) for _ in range(n)]
visit = [[False for _ in range(n)] for _ in range(n)] 

global find
find = False

def search(i, j):
    queue = deque([[i, j]])
    visit[i][j] = True
    while queue:
        v = queue.popleft()
        a, b = v[0], v[1]
        if a == finish[0]-1 and b == finish[1]-1:
            global find
            find = True
            break
        if a+1<n and not visit[a+1][b] and arr[a+1][b] != 1:
            queue.append([a+1, b])
            visit[a+1][b] = True
        if a-1>-1 and not visit[a-1][b] and arr[a-1][b] != 1:
            queue.append([a-1, b])
            visit[a-1][b] = True
        if b+1<n and not visit[a][b+1] and arr[a][b+1] != 1:
            queue.append([a, b+1])
            visit[a][b+1] = True
        if b-1>-1 and not visit[a][b-1] and arr[a][b-1] != 1:
            queue.append([a, b-1])
            visit[a][b-1] = True
        

search(start[0]-1, start[1]-1)

if find == True:
    print("YES")
else:
    print("NO")
