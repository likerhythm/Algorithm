import heapq

def dijstra(graph, X):
  record = [float('inf')] * (N+1)
  record[X] = 0
  queue = []
  heapq.heappush(queue, [0, X])

  while queue:
    curDist, curNode = heapq.heappop(queue)

    if record[curNode] < curDist:
      continue
    for nextNode, nextDist in graph[curNode]:
      newDist = curDist + nextDist
      if newDist < record[nextNode]:
        record[nextNode] = newDist
        heapq.heappush(queue, [newDist, nextNode])
  
  return record


N, E = map(int, input().split())
graph = [[] for _ in range(N + 1)]
for _ in range(E):
  a, b, c = map(int, input().split())
  graph[a].append((b, c))
  graph[b].append((a, c))

v1, v2 = map(int, input().split())

record1 = dijstra(graph, 1)
recordV1 = dijstra(graph, v1)
recordV2 = dijstra(graph, v2)
 
answer = min(record1[v1] + recordV1[v2] + recordV2[N]
             , record1[v2] + recordV2[v1] + recordV1[N])

if answer == float('inf'):
  print(-1)
else:
  print(answer)