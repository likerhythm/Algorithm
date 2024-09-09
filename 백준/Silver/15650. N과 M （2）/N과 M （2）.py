N, M = map(int, input().split())

arr = [0] * M

def f(start, index):
  if index == M:
    print(*arr)
    return
  for i in range(start, N + 1):
    arr[index] = i
    f(i + 1, index + 1)

f(1, 0)