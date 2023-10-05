# 소수 찾기
n = int(input())
num = list(map(int, input().split()))
arr = [True] * 1001

def makePrime(arr):
    arr[0], arr[1] = False, False
    
    for i in range(2,1001):
        if arr[i] == True: # i가 소수라면
            j = 2
            while i*j <= 1000:
                arr[i*j] = False
                j += 1

makePrime(arr)

count = 0
for m in num:
    if arr[m] == True:
        count += 1

print(count)