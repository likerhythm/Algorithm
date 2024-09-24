N = int(input())
graph = [[] for _ in range(N + 1)]

for _ in range(N - 1):
  n1, n2 = map(int, input().split())
  graph[n1].append(n2)
  graph[n2].append(n1)

parent = [0] * (N + 1)
parent[1] = 1

def dfs():
  stack = [1]
  visited = [False] * (N + 1)
  visited[1] = True
  while stack:
    v = stack.pop()
    for next_node in graph[v]:
      if not visited[next_node]:
        visited[next_node] = True
        stack.append(next_node)
        parent[next_node] = v

dfs()
for i in range(2, N + 1):
  print(parent[i])