# n: 사다리꼴 윗변 길이
# 1<=n<=100,000
# tops[i] == 1이면 i+1번째 위쪽에 정삼각형


# n == 1 -> 3가지


def solution(n, tops):
    
    #i번째 아래방향 삼각형을 오른쪽 삼각형을 포함한 마름모로 채우는 경우
    a = [0] * (n + 1)
    #a 경우가 아닌 경우
    b = [0] * (n + 1)
    
    a[1] = 1
    if tops[0] == 1:
        b[1] = 3
    else:
        b[1] = 2
    
    for i in range(2, n+1):
        a[i] = (a[i-1] + b[i-1])%10007
        if tops[i-1] == 1:
            b[i] = (2*a[i-1] + 3*b[i-1])%10007
        else:
            b[i] = (a[i-1] + 2*b[i-1])%10007
    
    answer = (a[n] + b[n])%10007
    return answer