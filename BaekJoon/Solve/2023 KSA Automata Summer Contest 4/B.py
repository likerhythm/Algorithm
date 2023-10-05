n = int(input())

count = set()

for _ in range(n):
    x, y = map(int, input().split())
    rate = y/x

    count.add(rate)

print(len(count))
