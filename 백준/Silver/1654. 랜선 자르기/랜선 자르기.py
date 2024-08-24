K, N = map(int, input().split())
lengths = [0] * K
for i in range(K):
  lengths[i] = int(input())
lengths.sort()

max_len = lengths[-1]

def binary_search(start, end):
  while start <= end:
    mid = (start + end) // 2
    cnt = 0
    for length in lengths:
      cnt += length // mid
    if cnt >= N:
      start = mid + 1
    else:
      end = mid - 1
  return end
  
  
answer = binary_search(1, max_len)
print(answer)