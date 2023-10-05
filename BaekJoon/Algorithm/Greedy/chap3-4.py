# 1이 될 때까지

# 내가 짠 코드(n이 100억 단위까지 커지면 시간이 오래 걸림)
n, k = map(int, input().split())
count = 0
while n!=1:
  if n%k == 0:
     n /= k
     count += 1
  else:
     n -= 1
     count += 1
print(count)

#교재 코드(n>k일 때는 n을 k로 나누어 떨어질 때까지 빼주고 n<k이면 1씩 빼주기)
count = 0
while True:
   target = (n//k) * k
   count += (n - target)
   n = target
   if n < k:
      break
   count += 1
   n //= k

count += n - 1
print(count)
