import sys
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[float('inf')] * (N + 1) for _ in range(N + 1)]

for _ in range(M):
  a, b = map(int, input().split())
  graph[a][b] = 1
  graph[b][a] = 1

for i in range(1, N + 1):
  graph[i][i] = 0

for k in range(1, N + 1):
  for i in range(1, N + 1):
    for j in range(1, N + 1):
      graph[i][j] = min(graph[i][k] + graph[k][j], graph[i][j])

sum_arr = [0] * N
for i in range(1, N + 1):
  for j in range(1, N + 1):
    sum_arr[i - 1] += graph[i][j]

min_value = min(sum_arr)
print(sum_arr.index(min_value) + 1)