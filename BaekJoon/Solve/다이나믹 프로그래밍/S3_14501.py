# 퇴사

N = int(input())
day = [0]*N
pay = [0]*N

for i in range(N):
  day[i], pay[i] = map(int, input().split())

dp = [0]*(N+1)

for i in range(N-1, -1, -1):
  if day[i] > N-i:
    dp[i] = dp[i+1]
  else:
    dp[i] = max(dp[day[i]+i]+pay[i], dp[i+1])

print(dp[0])
