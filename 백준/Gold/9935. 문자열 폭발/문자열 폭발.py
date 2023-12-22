input_str = input()
boom_str = input()
n = len(boom_str)
stack = []

for s in input_str:
  stack.append(s)
  if ''.join(stack[-n:]) == boom_str:
    for _ in range(n):
      stack.pop()
if stack:
  print(''.join(stack))
else:
  print("FRULA")