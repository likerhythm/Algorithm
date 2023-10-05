# N-Queen

import copy

N = int(input())

if N == 1:
  print(1)
  exit()

arr = [[0]*N for _ in range(N)]
global count
count = 0

arr_list = [0]*N
arr_list[0] = copy.deepcopy(arr)
print(arr_list)

def set_queen(i, arr2):
  print("set_queen")
  global count
  arr_list[i] = copy.deepcopy(arr2)
  if i == N:
    count += 1
    print("FIND!")
    return
  for j in range(N):
    print(i, " FOR")
    if arr_list[i][i][j] == 0:
      set_range(i, j)
      set_queen(i+1, arr_list[i])

def set_range(i, j):
  # 12시 부터 시계 방향
  print(i, "번째 줄")
  di = [-1, -1, 0, 1, 1, 1, 0, -1]
  dj = [0, 1, 1, 1, 0, -1, -1, -1]
  print("전: ", arr_list[i])
  arr_list[i][i][j] = 1
  for k in range(8):
    temp_i = i
    temp_j = j
    while True:
      temp_i = temp_i + di[k]
      temp_j = temp_j + dj[k]
      if 0<=temp_i<N and 0<=temp_j<N:
        arr_list[i][temp_i][temp_j] = 1
      else:
        break
  print("후: ", arr_list[i])

for a in range(N):
  temp_arr = copy.deepcopy(arr)
  set_range(0, a)
  set_queen(1, arr_list[0])

print(count)
