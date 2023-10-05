#소수 구하기
m, n = map(int, input().split())

arr = [True] * (n + 1)

def find_prime(arr, n, m):
    arr[0], arr[1] == False, False
    for i in range(2, n + 1):
        if arr[i] == True:
            if i >= m:
                print(i)
            j = 2
            while i*j <= n:
                arr[i*j] = False
                j += 1

find_prime(arr, n, m)