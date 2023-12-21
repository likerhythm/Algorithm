n = int(input())

item = 0
top = 0
stack = [-1]
result = []

for _ in range(n):
  target = int(input())
  # stack top이 target인 경우 pop
  if stack[top] == target:
    stack.pop()
    top -= 1
    result.append('-')
  else:
    # 그렇지 않으면 target이 나올 때까지 push.
    while(item != target and item <= n):
      item += 1
      top += 1
      stack.append(item)
      result.append('+')
      if item == target:
        stack.pop()
        top -= 1
        result.append('-')
        
    # 만약 마지막으로 push한 원소가 target이 아니면 NO 출력
    if item > n:
      print('NO')
      exit(0)

for r in result:
  print(r)