from collections import deque

M, N = map(int, input().split())
graph = [list(input()) for _ in range(N)]
broken = [[float('inf')] * M for _ in range(N)]
broken[0][0] = 0

move = [(-1, 0), (0, 1), (1, 0), (0, -1)]
q = deque([(0, 0)])

while q:
  n, m = q.popleft()
  for i in range(4):
    dn, dm = move[i]
    nxtN, nxtM = n + dn, m + dm
    if 0 <= nxtN < N and 0 <= nxtM < M:
      #벽을 만난 경우
      if graph[nxtN][nxtM] == '1':
        if broken[nxtN][nxtM] <= broken[n][m] + 1:
          continue
        else:
          broken[nxtN][nxtM] = broken[n][m] + 1
          q.append((nxtN, nxtM))
      #빈 방인 경우
      else:
        if broken[nxtN][nxtM] <= broken[n][m]:
          continue
        else:
          broken[nxtN][nxtM] = broken[n][m]
          q.append((nxtN, nxtM))

# for i in range(N):
#   print(broken[i])
print(broken[N-1][M-1])