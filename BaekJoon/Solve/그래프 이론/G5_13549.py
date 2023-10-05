# 숨바꼭질3

from collections import deque

arr = [-1 for _ in range(100001)]
N, K = map(int, input().split())
queue = deque()
arr[N] = 0
queue.append(N)


while queue:
    now = queue.popleft()
    if now == K:
        print(arr[K])
        break
    # now-1 if문 앞에 now+1 if문이 오면 오답이 나옴..
    if 0<=now-1<100001 and arr[now-1]==-1:
        queue.append(now-1)
        arr[now-1] = arr[now]+1
    if 0<=now*2<100001 and arr[now*2]==-1:
        queue.appendleft(now*2)
        arr[now*2] = arr[now]
    if 0<=now+1<100001 and arr[now+1]==-1:
        queue.append(now+1)
        arr[now+1] = arr[now]+1
