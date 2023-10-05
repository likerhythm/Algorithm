# 거스름돈
n = 1260
m = [500, 100, 50, 10]
count = 0

for i in m:
    count += n//i
    n = n%i

print(count)