# 연속합

n = int(input())

arr = list(map(int, input().split()))
dp = [0] * len(arr)


dp[0] = arr[0]

for i in range(1, n):
    if dp[i-1]+arr[i]<arr[i]:
        dp[i] = arr[i]
    else:
        dp[i] = dp[i-1] + arr[i]

print(max(dp))