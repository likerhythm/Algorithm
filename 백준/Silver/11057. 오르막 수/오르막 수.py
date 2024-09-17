N = int(input())

# dp[i][j]: i자리수 수의 마지막 숫자가 j인 경우의 오르막 수의 개수
dp = [[0] * 10 for _ in range(N + 1)]
for i in range(10):
  dp[1][i] = 1

for i in range(2, N + 1):  # i 자리수
  for j in range(10):      # 마지막 숫자가 j인 경우
    for k in range(j + 1):
      dp[i][j] += dp[i - 1][k]
answer = sum(dp[-1])
print(answer % 10007)