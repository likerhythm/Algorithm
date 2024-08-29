import sys, heapq

MAX_INT = sys.maxsize

N, M, X = map(int, input().split())

graph = [[MAX_INT] * (N + 1) for _ in range(N + 1)]

for _ in range(M):
  s, e, t = map(int, input().split())
  graph[s][e] = t

to_X_times = [0] * (N + 1)

def dijkstra(start):
  distances = [MAX_INT] * (N + 1)
  distances[start] = 0
  queue = [(0, start)]
  while queue:
    now_dist, now_node = heapq.heappop(queue)
    if now_dist > distances[now_node]:
      continue
    for next_node in range(1, N + 1):
      next_dist = graph[now_node][next_node]
      new_dist = now_dist + next_dist
      if distances[next_node] > new_dist:
        heapq.heappush(queue,(new_dist, next_node))
        distances[next_node] = new_dist
  return distances

def reverse_graph():
  for i in range(1, N + 1):
    for j in range(i, N + 1):
      graph[i][j], graph[j][i] = graph[j][i], graph[i][j]

to_home_times = dijkstra(X)
reverse_graph()
to_X_times = dijkstra(X)
max_time = 0
for to_home, to_X in zip(to_home_times[1:], to_X_times[1:]):
  time = to_home + to_X
  max_time = max(max_time, time)

print(max_time)