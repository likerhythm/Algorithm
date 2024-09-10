from collections import deque

N = int(input())
M = int(input())

graph = [[0] * (N + 1) for _ in range(N + 1)]

for i in range(1, N + 1):
  edges = list(map(int, input().split()))
  for j in range(N):
    graph[i][j + 1] = edges[j]
    graph[j + 1][i] = edges[j]

plan = list(map(int, input().split()))
visited = [False] * (N + 1)

def bfs(start):
  q = deque([start])
  group = []
  while q:
    node = q.popleft()
    group.append(node)
    for i in range(1, N + 1):
      if graph[node][i] == 1 and not visited[i]:
        q.append(i)
        visited[i] = True
  return group

groups = [0] * (N + 1)
for i in range(1, N + 1):
  if not visited[i]:
    group = bfs(i)
    for node in group:
      groups[node] = i

root_set = set()
for node in plan:
  root_set.add(groups[node])
if len(root_set) == 1:
  print('YES')
else:
  print('NO')