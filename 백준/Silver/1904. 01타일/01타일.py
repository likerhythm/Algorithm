n = int(input())
dp00 = list(range(n + 1))
dp1 = list(range(n + 1))
dp = list(range(n + 1))

dp00[1] = 0
dp1[1] = 1
if n == 1:
  print(dp00[1] + dp1[1])
else:
  dp00[2] = 1
  dp1[2] = 1
  if n == 2:
    print(dp00[2] + dp1[2])
  else:
    for i in range(3, n+1):
      dp00[i] = (dp00[i-2] + dp1[i-2]) % 15746
      dp1[i] = (dp1[i-1] + dp00[i-1]) % 15746
    print((dp00[n] + dp1[n]) % 15746)
  