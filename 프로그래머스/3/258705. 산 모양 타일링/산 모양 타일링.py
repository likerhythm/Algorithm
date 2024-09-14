def solution(n, tops):
    dp1 = [0] * n # 삼각형
    dp2 = [0] * n # 1번 마름모
    dp3 = [0] * n # 2번 마름모
    dp4 = [0] * n # 3번 마름모
    
    dp1[0] = 1
    dp2[0] = 1
    dp3[0] = 1
    if tops[0] == 1:
        dp4[0] = 1
    
    for i in range(1, n):
        dp2[i] = (dp1[i - 1] + dp2[i - 1] + dp4[i - 1]) % 10007
        dp3[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007
        if tops[i] == 1:
            dp4[i] = dp3[i]
        else:
            dp4[i] = 0
        dp1[i] = dp3[i]
    answer = (dp1[n - 1] + dp2[n - 1] + dp3[n - 1] + dp4[n - 1]) % 10007
    return answer