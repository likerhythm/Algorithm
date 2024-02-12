# 아이디어는 맞지만 시간을 고려하여 구현해야
# 처음 구현 방법은 queue1과 queue2를 하나의 배열로 합치고 
# 칸막이를 놓을 수 있는 모든 경우의 수를 고려 => 시간 초과

# pop, insert를 작업 1회 수행으로 간주
# 배열 앞에 있는게 먼저 넣은 원소(pop 할 원소)
# 최소 작업 횟수? 만들 수 없는 경우 -1

# idea
# 원소 중에 절반보다 큰 원소가 있으면 불가능

# from itertools import combinations

# def solution(queue1, queue2):
#     answer = -1
    
#     # 0 ~ l-1 인덱스 존재
#     queue = queue1 + queue2
#     l = len(queue1)
    
#     start = 0
#     finish = l
#     count = 0
    
#     while sum(queue[start:finish]) != sum(queue[finish:]) and count < 4*l:
#         if sum(queue[start:finish]) > sum(queue[finish:]):
#             queue.append(queue[0])
#             start += 1
#         else:
#             finish += 1
#         count += 1
#     if count == 4*l:
#         answer = -1
#     else:
#         answer = count
    
#     return answer

from collections import deque

def solution(queue1, queue2):
    
    answer = 0
    
    q1 = deque(queue1)
    q2 = deque(queue2)
    sum1 = sum(q1)
    sum2 = sum(q2)
    l = len(q1)
    
    while True:
        if sum1 > sum2:
            tmp = q1.popleft()
            q2.append(tmp)
            sum1 -= tmp
            sum2 += tmp
            answer += 1
        elif sum1 < sum2:
            tmp = q2.popleft()
            q1.append(tmp)
            sum1 += tmp
            sum2 -= tmp
            answer += 1
        else:
            break
        
        if answer == l * 4:
            answer = -1
            break
    
    return answer





