# 스택 수열

n = int(input())
arr = [i+1 for i in range(n)]
cursor_a = 1

sequence = []
for _ in range(n):
    sequence.append(int(input()))

result = ['+']

stack = [0, 1]
top = 1


# i: 
for i in range(n):
    print("i: ", i)
    while sequence[i] != stack[top]:
        stack.append(arr[cursor_a])
        cursor_a += 1
        print("while")
    if cursor_a == n and (sequence[i] != stack[top]):
        print("NO")
        break
    else:
        stack.pop(top)
        top -= 1
        i += 1
        print('-')

