from collections import deque

N = int(input()) #3이상 3000이하의 정수
graph = [[] for _ in range(N + 1)]
cycle = [False] * (N + 1)

for _ in range(N):
  s, f = map(int, input().split())
  graph[s].append(f)
  graph[f].append(s)


#순환선 찾아서 집합으로 저장
def find_cycle(start, graph):
  # print("======find_cycle======")
  visited = [False] * (N + 1)
  stack = deque([(start, 1)])
  while stack:
    node, cnt = stack.pop()
    
    if not visited[node]:
      visited[node] = True
      # print(f"visited: {visited}")
      for next in graph[node]:
        if not visited[next]:
          stack.append((next, cnt + 1))
        elif next == start and cnt > 2:
          # print("순환 발견")
          return True
  return False

def bfs(start, graph, cycle):
  if cycle[start]:
    return 0
  q = deque([(start, 0)])
  visited = [False] * (N + 1)
  while q:
    node, cnt = q.pop()
    if cycle[node]:
      return cnt
    if not visited[node]:
      visited[node] = True
    for next in graph[node]:
      if not visited[next]:
        q.append((next, cnt + 1))

for i in range(1, N + 1):
  # 각 역마다 cycle 존재하는지 찾으면서 존재하면 cycle[] = True
  if find_cycle(i, graph):
    cycle[i] = True

#각 역부터 순환선까지의 최소 거리 구하기
for i in range(1, N + 1):
  print(bfs(i, graph, cycle), end=" ")