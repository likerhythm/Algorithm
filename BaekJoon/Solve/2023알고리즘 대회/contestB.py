# 이진수 369

n = int(input())

arr = [bin(i) for i in range(1, 100)]
no = [3, 6, 9]

count = 0

for i in range(n):
    if ((i+1)%10 != 3 and 
    (i+1)%10 != 6 and
    (i+1)%10 != 9 and
    (i+1)//10 != 3 and
    (i+1)//10 != 6 and
    (i+1)//10 != 9):
        for c in arr[i]:
            if c == '1':
                count += 1

print(count)
