# 트리의 부모 찾기

def check_cycle(node):
  if parent[node] - 100000 > 0:
    parent[parent[node] - 100000] = node
    parent[node] = node


N = int(input())

# 부모 노드
parent = [i for i in range(N+1)]
parent[1] = 0

# 부모를 가지고 있는지 판별
has_cycle = [False for _ in range(N+1)]
has_cycle[1] = True

for _ in range(N-1):
  a, b = map(int, input().split())
  if parent[a] != a and parent[b] != b:
    if has_cycle[a]:
      parent[a] = b
    else
  
for i in range(2, N+1):
  print(parent[i])