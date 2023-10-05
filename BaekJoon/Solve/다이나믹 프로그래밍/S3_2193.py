N = int(input())

# 1로 끝나는 이친수
one = [0 for _ in range(91)]
# 0으로 끝나는 이친수
zero = [0 for _ in range(91)]

one[1], one[3] = 1, 1
zero[2], zero[3] = 1, 1

if N > 3:
    for i in range(4, N+1):
        one[i] = zero[i-1]
        zero[i] = one[i-1]+zero[i-1]
print(one[N]+zero[N])