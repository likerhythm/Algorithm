# 괄호
t = int(input())
flag = 0

for _ in range(t):
    arr = list(input())
    if arr[0] == ')':
        print("NO")
    elif arr[-1] == '(':
        print("NO")
    else:
        count = 0
        for i in arr:
            if count < 0:
                print("NO")
                flag = 1
                break
            if i == '(':
                count += 1
            else:
                count -= 1
        if flag == 0:
            if count == 0:
                print("YES")
            else:
                print("NO")   
        flag = 0     
        