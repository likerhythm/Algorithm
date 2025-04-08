# 나무자르기

import sys
input = sys.stdin.readline

n, m = map(int, input().split())

arr = []
arr = list(map(int, input().split()))
start, end = 1, max(arr)

while start <= end:
  sum = 0
  mid = (start+end)//2

  for a in arr:
    if a>mid:
      sum += a-mid
  
  if sum<m:
    end = mid - 1
  else:
    start = mid + 1

print(end)