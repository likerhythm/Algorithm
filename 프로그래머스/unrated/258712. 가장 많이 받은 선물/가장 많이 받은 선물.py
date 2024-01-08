# 선물지수 = (준 선물 갯수) - (받은 선물 갯수)
# 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 하나를 받음
# 주고받은 선물 수와 선물지수가 같다면 다음 달에 주고받지 않음
## 다음 달에 가장 많은 선물을 받을 친구의 선물 수?

import numpy as np

def solution(friends, gifts):
    
    N = len(friends)
    
    friendEnum = {}
    for i, f in enumerate(friends):
        friendEnum[f] = i
    
    # 선물을 준 사람과 받은 사람 표
    table = [[0 for _ in range(N)] for _ in range(N)]
    for str in gifts:
        sender, reciever = str.split()
        s = friendEnum[sender]
        r = friendEnum[reciever]
        table[s][r] += 1

    # 선물지수 계산
    score = [0 for _ in range(N)]
    for i in range(N):
        send = 0
        for j in range(N):
            send += table[j][i]
        recieve = 0
        for j in range(N):
            recieve += table[i][j]
        score[i] = recieve - send
    # print("score: ", score)
    
    # 모든 조합 고려하여 각각이 받을 선물의 개수 계산
    # 서로가 주고받은 선물 수와 선물지수 모두 고려해야
    result = [0 for _ in range(N)]
    for i in range(N-1):
        for j in range(i+1, N):
            # j가 준 것보다 받은 게 많은 경우
            if table[i][j] > table[j][i]:
                result[i] += 1
            elif table[i][j] < table[j][i]:
                result[j] += 1
            else:
                if score[i] > score[j]:
                    result[i] += 1
                elif score[i] < score[j]:
                    result[j] += 1
    # print("result: ", result)
    answer = max(result)
    return answer