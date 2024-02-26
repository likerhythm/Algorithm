#구슬 탈출 2
#빨간구슬1, 파란구슬1
#기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지

from collections import deque

N, M = map(int, input().split())
board = []
rn, rm, bn, bm = 0, 0, 0, 0
for i in range(N):
  temp = list(input())
  if 'R' in temp:
    rn, rm = i, temp.index('R')
  if 'B' in temp:
    bn, bm = i, temp.index('B')
  board.append(temp)

# print("rn, rm, bn, bm:", rn, rm, bn, bm)
move = [(0, -1), (0, 1), (-1, 0), (1, 0)]
visited = [[rn, rm, bn, bm]]
queue = deque()
queue.append([0, rn, rm, bn, bm])
board[rn][rm] = '.'
board[bn][bm] = '.'

while queue:
  cnt, rn, rm, bn, bm = queue.popleft()
  # print(rn, rm, bn, bm)
  for i in range(4):
    dn, dm = move[i]
    b_nextN, b_nextM = bn, bm
    r_nextN, r_nextM = rn, rm

    while board[b_nextN+dn][b_nextM+dm] == '.':
      b_nextN, b_nextM = b_nextN+dn, b_nextM+dm
    
    if board[b_nextN+dn][b_nextM+dm] == 'O':
      continue

    while board[r_nextN+dn][r_nextM+dm] == '.':
      r_nextN, r_nextM = r_nextN+dn, r_nextM+dm
      
    if board[r_nextN+dn][r_nextM+dm] == 'O':
      print(cnt+1)
      exit(0)
    if r_nextN == b_nextN and r_nextM == b_nextM:
      if abs(r_nextN - rn) + abs(r_nextM - rm) > abs(b_nextN - bn) + abs(b_nextM - bm):
        r_nextN -= dn
        r_nextM -= dm
      else:
        b_nextN -= dn
        b_nextM -= dm
    if [r_nextN, r_nextM, b_nextN, b_nextM] not in visited:
      queue.append([cnt+1, r_nextN, r_nextM, b_nextN, b_nextM])
      # print("cnt", cnt+1, "move", i, "append", r_nextN, r_nextM, b_nextN, b_nextM)
      visited.append([r_nextN, r_nextM, b_nextN, b_nextM])
  if cnt + 1 > 10:
    print(-1)
    exit(0)
print(-1)
