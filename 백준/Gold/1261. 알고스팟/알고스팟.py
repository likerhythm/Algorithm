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
      if broken[nxtN][nxtM] <= broken[n][m] + int(graph[nxtN][nxtM]):
        continue
      else:
        broken[nxtN][nxtM] = broken[n][m] + int(graph[nxtN][nxtM])
        q.append((nxtN, nxtM))

# for i in range(N):
#   print(broken[i])
print(broken[N-1][M-1])