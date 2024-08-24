def binary_search(start, end, array, target):
  while start <= end:
    mid = (start + end) // 2
    if array[mid] == target:
      return 1
    elif array[mid] < target:
      start = mid + 1
    else:
      end = mid - 1
  return 0



N = int(input())
array = list(map(int, input().split()))
M = int(input())
targets = list(map(int, input().split()))

array.sort()

for target in targets:
  print(binary_search(0, N - 1, array, target))