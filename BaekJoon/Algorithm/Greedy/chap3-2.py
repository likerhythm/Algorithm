# 큰 수의 법칙
n, m, k = map(int, input().split());
arr = list(map(int, input().split()));

arr.sort()

a = arr[-1] # 가장 큰 수
b = arr[-2] # 두 번재로로 크거나 같은 수

sum = 0
temp = 0
for _ in range (m):
  if temp == k:
     sum += b
     temp = 0
     continue
  else:
     sum += a
  temp += 1

print(sum)