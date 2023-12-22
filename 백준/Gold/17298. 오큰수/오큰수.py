n = int(input())

arr = list(map(int, input().split()))

j = 1
top = 0
stack = [0]
# arr의 모든 원소가 stack에 들어갈 때까지 반복
while(j < n):
  # stack top과 다음에 stack에 넣을 arr 원소를 비교
  if top < 0:
    stack.append(j)
    top += 1
    j += 1
  else:
    stack_top = stack[top]
    # stack top이 더 작다면 꺼내고 해당 arr 값을 수정
    # 이 과정을 stack top이 더 크거나 같을 때까지 혹은 stack이 빌 때까지 반복
    while arr[stack_top] < arr[j]:
      arr[stack.pop()] = arr[j]
      top -= 1
      if top == -1:
        break
      stack_top = stack[top]
    stack.append(j)
    top += 1
    j += 1

while stack:
  arr[stack.pop()] = -1

print(*arr)