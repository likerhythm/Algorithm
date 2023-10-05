# 삼각형

arr = list(map(int, input().split()))
arr.sort()

if arr[2] < (arr[0] + arr[1]):
    print("YES")
else:
    print("NO")
   