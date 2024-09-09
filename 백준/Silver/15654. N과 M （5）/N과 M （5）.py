N, M = map(int, input().split())

nums = list(map(int, input().split()))
nums.sort()
l = len(nums)
arr = [0] * M

def f(index):
  if index == M:
    print(*arr)
    return
  for i in range(l):
    if nums[i] in arr:
      continue
    arr[index] = nums[i]
    f(index + 1)
    arr[index] = 0

f(0)