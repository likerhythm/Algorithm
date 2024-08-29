import heapq, sys

MAX_INT = sys.maxsize
N = 0

def in_range(x, y):
  return 0 <= x < N and 0 <= y < N

def dijstra(graph):
  dxs = [-1, 0, 1, 0]
  dys = [0, 1, 0, -1]

  start = (graph[0][0], (0, 0))
  queue = []
  heapq.heappush(queue, start)
  distances = [[MAX_INT] * N for _ in range(N)]
  distances[0][0] = graph[0][0]
  while queue:
    now_dist, now_node = heapq.heappop(queue)
    x, y = now_node
    for dx, dy in zip(dxs, dys):
      nx = x + dx
      ny = y + dy
      if in_range(nx, ny):
        next_dist = graph[nx][ny]
        new_dist = now_dist + next_dist
        if distances[nx][ny] > new_dist:
          distances[nx][ny] = new_dist
          heapq.heappush(queue, (new_dist, (nx, ny)))
  # print()
  # for i in range(N):
  #   print(*distances[i])
  # print()
  return distances[N - 1][N - 1]

test_case = 0
while True:
  test_case += 1
  N = int(input())
  if N == 0:
    break
  graph = [list(map(int, input().split())) for _ in range(N)]
  answer = dijstra(graph)
  print(f'Problem {test_case}: {answer}')

