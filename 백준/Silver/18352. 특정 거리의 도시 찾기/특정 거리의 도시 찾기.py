import sys
import heapq
input = sys.stdin.readline

INF = float('inf')

N, M, K, X = map(int, input().split()) #도시 개수, 도로 개수, 거리 정보, 출발 도시 번호
graph = [[] * N for _ in range(N)]

for _ in range(M):
  A, B = map(int, input().split())
  graph[A - 1].append((B - 1, 1))

distance = [INF] * N
distance[X - 1] = 0

def dijkstra(start):
  q = []
  heapq.heappush(q, (start, 0))
  while q:
    now_node, now_dist = heapq.heappop(q)
    if distance[now_node] < now_dist:
      continue
    for next_node, next_dist in graph[now_node]:
      new_dist = distance[now_node] + next_dist
      if new_dist < distance[next_node]:
        distance[next_node] = new_dist
        heapq.heappush(q, (next_node, new_dist))

dijkstra(X - 1)
result = [i for i, x in enumerate(distance) if x == K]
if len(result) == 0:
  print(-1)
else:
  for r in result:
    print(r + 1)