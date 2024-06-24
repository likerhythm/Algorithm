N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

#누적합 계산
for i in range(N):
  line_sum = 0
  for j in range(N):
    line_sum += board[i][j]
    if i == 0:
      board[i][j] = line_sum
    elif i > 0:
      board[i][j] = line_sum + board[i - 1][j]

for _ in range(M):
  x1, y1, x2, y2 = map(int, input().split()) #x1<=x2, y1<=y2
  x1, y1, x2, y2 = x1 - 1, y1 - 1, x2 - 1, y2 - 1
  if x1 > 0 and y1 > 0:
    sum = board[x2][y2] - (board[x1 - 1][y2]) - (board[x2][y1 - 1] - board[x1 - 1][y1 - 1])
  elif x1 > 0 and y1 == 0:
    sum = board[x2][y2] - (board[x1 - 1][y2])
  elif x1 == 0 and y1 >0:
    sum = board[x2][y2] - board[x2][y1 - 1]
  elif x1 == 0 and y1 == 0:
    sum = board[x2][y2]
  print(sum)