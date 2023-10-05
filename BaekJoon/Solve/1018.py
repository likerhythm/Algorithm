# 체스판 다시 칠하기
n, m = map(int, input().split())

chess = [input() for _ in range(n)]

result = []

for a in range(n-7):
    for b in range(n-7):
        tempW = 0 # W로 시작하는 경우
        tempB = 0 # B로 시작하는 경우
        for i in range(a, a+8):
            for j in range(b, b+8):
                if (i+j) % 2 == 0:
                    if chess[i][j] != 'W':
                        tempW += 1
                    if chess[i][j] != 'B':
                        tempB += 1
                else:
                    if chess[i][j] != 'B':
                        tempW += 1
                    if chess[i][j] != 'W':
                        tempB += 1
        result.append(min(tempW, tempB))
print(min(result))