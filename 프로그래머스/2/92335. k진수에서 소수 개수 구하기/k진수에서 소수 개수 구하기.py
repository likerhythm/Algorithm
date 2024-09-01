# n을 k진수로 바꾸고 이를 10진수로 바라보았을 때 소수의 개수

def is_prime_num(n):
    if n <= 1: return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0: return False
    return True

def solution(n, k):
    # if n == 1:
    #     return 0
    answer = 0
    numbers = []
    tmp_k = 1
    while n > tmp_k:
        tmp_k *= k
    tmp_k //= k
    if tmp_k == 0:
        return 0
    tmp_n = n
    while tmp_n > 0: # k진수로 바꾸기
        if tmp_n >= tmp_k:
            numbers.append(tmp_n // tmp_k)
            tmp_n = tmp_n - (tmp_n // tmp_k) * tmp_k
        else:
            numbers.append(0)
        tmp_k //= k
    numbers.append(0)
    # print(f'numbers={numbers}')
    decimal = 0
    for decimal_unit in numbers:
        if decimal_unit == 0:
            if decimal > 0 and is_prime_num(decimal):
                # print(f'decimal={decimal}')
                answer += 1
            decimal = 0
            continue
        decimal *= 10
        decimal += decimal_unit
    return answer