T = int(input())
for _ in range(T):
  k = int(input())
  n = int(input())
  arr = [[i+1 for i in range(n)] for _ in range(k+1)]
  for i in range(1, k+1):
    for j in range(1, n):
      arr[i][j] = arr[i][j-1]+arr[i-1][j]
  print(arr[k][n-1])
