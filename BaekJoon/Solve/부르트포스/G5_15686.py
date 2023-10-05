# 치킨 거리

import itertools

N, M = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]

home = []
chicken = []

for i in range(N):
  for j in range(N):
    if arr[i][j] == 1:
      home.append((i, j))
    elif arr[i][j] == 2:
      chicken.append((i, j))

# a행 b열은 b번째 집으로부터 a번째 치킨집까지의 거리
dist = [[0]*len(home) for _ in range(len(chicken))]

# 거리 계산
for i in range(len(chicken)):
  chicken_i, chicken_j = chicken[i]
  for j in range(len(home)):
    home_i, home_j = home[j]
    dist[i][j] = abs(chicken_i - home_i) + abs(chicken_j - home_j)

# 치킨집의 모든 조합 리스트
nCr = list(itertools.combinations(list(i for i in range(len(chicken))), M))

answer = 100000

for j in nCr:
  temp = 0
  for i in range(len(home)):
    temp_min = 10000
    for k in j:
      # temp_min과 i번째 home과 k번째 치킨집 사이의 거리 중 최소값
      temp_min = min(temp_min, dist[k][i])
    temp += temp_min
  answer = min(temp, answer)

print(answer)