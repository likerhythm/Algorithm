import sys
import copy
input = sys.stdin.readline

SCORE_TYPE_NUM = 11

max_diff = 0
lion_info = [0] * SCORE_TYPE_NUM # 10 ~ 0점 순
apich_info = [0] * SCORE_TYPE_NUM
max_lion_info = []
lion_win_flag = False

def calc_diff():
    global lion_win_flag
    lion_score = 0
    apich_score = 0
    
    for i in range(SCORE_TYPE_NUM):
        if lion_info[i] == 0 and apich_info[i] == 0:
            continue
        if lion_info[i] <= apich_info[i]:
            apich_score += 10 - i
        else:
            lion_score += 10 - i
    if lion_score > apich_score:
        lion_win_flag = True
        return abs(lion_score - apich_score)
    else:
        return -1
        

# n: 총 화살 수, cnt: 현재까지 쏜 화살 수
def gen_lion_info(n, cnt, start): # 점수가 낮은 순서대로 생성(낮은 점수를 더 많이 맞힌 경우를 return해야 하므로)
    global max_lion_info, max_diff
    if cnt == n:
        # 점수차 계산
        diff = calc_diff()
        if diff == -1:
            return
        # if # 점수차가 max_diff보다 큰 경우 교체
        else:
            if max_diff < diff:
                max_diff = diff
                # print(lion_info)
                max_lion_info = copy.deepcopy(lion_info)
        return
    for i in range(start, -1, -1):
        lion_info[i] += 1
        gen_lion_info(n, cnt + 1, i)
        lion_info[i] -= 1

def solution(n, info):
    for i in range(SCORE_TYPE_NUM):
        apich_info[i] = info[i]
    gen_lion_info(n, 0, SCORE_TYPE_NUM - 1)
    answer = []
    if not lion_win_flag:
        answer = [-1]
    else:
        answer = max_lion_info
    return answer