from collections import defaultdict
import heapq, sys

MAX_INT = sys.maxsize

N, D = map(int, input().split()) # N: 지름길 개수, D: 고속도로 길이
fast_ways = defaultdict(lambda: defaultdict(lambda: -1))
for _ in range(N):
  s, e, dist = map(int, input().split())
  if fast_ways[e][s] != -1: # 시작점과 도착점이 같은 지름길이 존재하는 경우
    now_dist = fast_ways[e][s]
    if now_dist > dist:
      fast_ways[e][s] = dist
  else:
    fast_ways[e][s] = dist


queue = []
heapq.heappush(queue, (0, 0)) # 우선순위(현재 거리), 현재 노드 순
distances = [i for i in range(D + 1)]

for now_node in range(1, D + 1):
  if fast_ways[now_node] != {}: # 현재 노드를 도착지로 하는 지름길이 있는 경우
    for pre_node, fast_dist in fast_ways[now_node].items():
      new_dist = distances[pre_node] + fast_dist
      distances[now_node] = min(new_dist, distances[now_node], distances[now_node - 1] + 1)
  else:
    distances[now_node] = distances[now_node - 1] + 1

print(distances[-1])