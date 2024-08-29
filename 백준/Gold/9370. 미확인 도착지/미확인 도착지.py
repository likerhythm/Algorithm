import sys, heapq

MAX_INT = sys.maxsize
n = 0

dxs = [-1, 0, 1, 0]
dys = [0, 1, 0, -1]

def in_range(x, y):
  return 0 <= x < n and 0 <= y < n

def dijkstra(graph, s):
  queue = []
  heapq.heappush(queue, (0, s))
  distances = [MAX_INT] * (n + 1)
  distances[s] = 0
  while queue:
    now_dist, now_node = heapq.heappop(queue)
    for next_node in range(1, n + 1):
      new_dist = now_dist + graph[now_node][next_node]
      if distances[next_node] <= new_dist:
        continue
      distances[next_node] = new_dist
      heapq.heappush(queue,(new_dist, next_node))

  return distances



T = int(input())
for _ in range(T):
  n, m, t = map(int, input().split()) # 노드, 엣지, 목적지 후보 개수
  s, g, h = map(int, input().split()) # 출발지, g, h
  graph = [[MAX_INT] * (n + 1) for _ in range(n + 1)]
  for _ in range(m):
    a, b, d = map(int, input().split())
    graph[a][b] = d
    graph[b][a] = d
  answer = []
  s_distances = dijkstra(graph, s)
  h_distances = dijkstra(graph, h)
  g_distances = dijkstra(graph, g)
  for _ in range(t):
    target = int(input())
    # s -> g + g -> h + h -> t 또는 s -> h + h -> g + g -> t가 최단 거리와 같다면
    s_to_g = s_distances[g]
    s_to_h = s_distances[h]
    g_to_h = graph[g][h]
    h_to_target = h_distances[target]
    g_to_target = g_distances[target]
    if s_to_g + g_to_h + h_to_target == s_distances[target] or s_to_h + g_to_h + g_to_target == s_distances[target]:
      answer.append(target)
  answer.sort()
  print(*answer)