# 0 <= 비용 < 100,000

# 최소비용, 거치는 도시 수, 경로 출력

from heapq import *
from collections import deque, defaultdict as dfd

def dijstra():
  queue = []
  heappush(queue, [0, start])

  while queue:
    curCost, curNode = heappop(queue)
    if visit[curNode] < curCost:
      continue
    for nextNode in adj[curNode].keys():
      nextCost = adj[curNode][nextNode]
      if curCost + nextCost < visit[nextNode]:
        visit[nextNode] = curCost + nextCost
        vertex[nextNode] = curNode
        heappush(queue, [curCost + nextCost, nextNode])
  


n = int(input())
m = int(input())
# 그래프 설정
adj = [dfd(lambda: float('inf')) for _ in range(n + 1)]
for _ in range(m):
  s, f, c = map(int, input().split())
  adj[s][f] = min(adj[s][f], c)

start, finish = map(int, input().split())
visit = [float('inf')] * (n + 1)
visit[start] = 0
vertex = [0] * (n + 1)

dijstra()
# print(vertex)

print(visit[finish])

path = []
q = deque([finish])
while True:
  x = q.popleft()
  path.append(x)
  if x == start:
    break
  # print(x)
  nextNode = vertex[x]
  q.append(nextNode)
print(len(path))
print(*path[::-1])