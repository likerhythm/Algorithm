# 24.08.19 11시 20분 시작

# 이미 파괴된 건물도 공격을 받으면 내구도 하락

# 시간복잡도 계산
# board: 1000 X 1000 = 100만
# skill 25만개, 1000 X 1000 = 100만

def solution(board, skill):
    N = len(board)
    M = len(board[0])
    temp = [[0] * (M + 1) for _ in range(N + 1)]
    for type, r1, c1, r2, c2, degree in skill:
        if type == 1:
            degree = -degree
        temp[r1][c1] += degree
        temp[r1][c2 + 1] += -degree
        temp[r2 + 1][c1] += -degree
        temp[r2 + 1][c2 + 1] += degree
    for i in range(N):
        for j in range(M):
            temp[i][j + 1] = temp[i][j] + temp[i][j + 1]
    for i in range(N):
        for j in range(M):
            temp[i + 1][j] = temp[i][j] + temp[i + 1][j]
    for i in range(N):
        for j in range(M):
            board[i][j] += temp[i][j]
    
    answer = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] > 0:
                answer += 1
    return answer