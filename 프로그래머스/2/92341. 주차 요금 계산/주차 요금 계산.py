# 초과 시간이 단위 시간으로 나누어 떨어지지 않으면 '올림'
# 차량 번호가 작은 자동차부터 주차요금 리턴
from collections import defaultdict
import math

def calc_total_time(times):
    total_time = 0
    for i in range(1, len(times), 2):
        in_time = times[i - 1] # 입차 시간
        out_time = times[i] # 출차 시간
        in_hour, in_minute = map(int, in_time.split(':'))
        out_hour, out_minute = map(int, out_time.split(':'))
        hour_term = out_hour - in_hour
        minute_term = out_minute - in_minute
        total_time += hour_term * 60 + minute_term
    return total_time


def solution(fees, records):
    answer = []
    
    basic_time, basic_cost, unit_time, unit_cost = fees
    
    car_dict = defaultdict(list)
    for record in records:
        time, number, status = record.split()
        car_dict[number].append(time)
    sorted_car_dict = dict(sorted(car_dict.items()))
    for times in sorted_car_dict.values():
        if len(times) % 2 == 1: # 23:59분에 출차하는 경우
            times.append('23:59')
        total_time = calc_total_time(times) # 총 시간 계산
        cost = 0
        if total_time <= basic_time: # 요금 계산
            cost = basic_cost
        else:
            cost = basic_cost + math.ceil((total_time - basic_time) / unit_time) * unit_cost
        answer.append(cost)
    return answer