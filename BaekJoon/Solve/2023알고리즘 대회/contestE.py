n, k = map(int, input().split())

height = list(map(int, input().split()))

start_arr = []
finish_arr = []
arr = []

for i in range(n):
    s, f = i, i
    # 비교하고자 하는 값
    while height[i]>=height[s]:
        s -= 1
        if s < 0:
            break
    while height[i]>=height[f]:
        f += 1
        if f > n-1:
            break
    arr.append(f-s-1)

for _ in range(k):
    a, b = map(int, input().split())
    in_s, in_f = a-1, b-1
    result = []

    for i in range(in_s, in_f+1):
        if in_f-in_s <= arr[i]:
            result.append(height[i])

    result2 = 0
    for i in range(in_s, in_f + 1):
        result2 += max(result) - height[i]
    print(result2)
