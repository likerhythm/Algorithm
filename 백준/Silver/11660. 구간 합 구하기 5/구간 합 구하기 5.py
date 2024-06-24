import sys
input = sys.stdin.readline

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

#누적합 계산
dp = [[0] * (N + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
  line_sum = 0
  for j in range(1, N + 1):
    line_sum += board[i - 1][j - 1]
    dp[i][j] = line_sum + dp[i - 1][j]

for _ in range(M):
  x1, y1, x2, y2 = map(int, input().split()) #x1<=x2, y1<=y2
  sum = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]
  print(sum)