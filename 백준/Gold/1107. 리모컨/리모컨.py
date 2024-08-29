target = int(input())
M = int(input())
broken = []
if M != 0:
  broken = list(map(str, input().split()))

answer = abs(target - 100)

for num in range(1000000):
  num = str(num)
  for i in range(len(num)):
    if num[i] in broken:
      break 
    if i == len(num) - 1:
      answer = min(answer, abs(target - int(num)) + len(num))

print(answer)