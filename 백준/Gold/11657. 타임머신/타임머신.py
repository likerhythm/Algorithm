import sys
MAX_INT = sys.maxsize

N, M = map(int, input().split())

edges = []
for _ in range(M):
  A, B, C = map(int, input().split())
  edges.append([A, B, C])

distances = [MAX_INT] * (N + 1)

def bellman_ford(start):
  distances[start] = 0
  for i in range(N):
    for s, e, d in edges:
      if distances[s] != MAX_INT and distances[e] > distances[s] + d:
        distances[e] = distances[s] + d
        if i == N - 1:
          return True
  return False

has_negative_cycle = bellman_ford(1)

if has_negative_cycle:
  print(-1)
else:
  for i in range(2, N + 1):
    print(distances[i] if distances[i] != MAX_INT else -1)