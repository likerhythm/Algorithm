def solution(n, tops):
    dp1 = [0] * (n) # dp1[i] = n번째 윗변에 삼각형이 사용된 경우 채우는 경우의 수
    dp2 = [0] * (n) # dp2[i] = n번째 윗변에 첫 번째 마름모가 사용된 경우 채우는 경우의 수
    dp3 = [0] * (n) # dp3[i] = n번째 윗변에 두 번째 ...
    dp4 = [0] * (n) # dp4[i] = n번째 윗변에 세 번째 ...
    
    dp1[0] = 1
    dp2[0] = 1
    dp3[0] = 1
    if tops[0] == 1:
        dp4[0] = 1
    for i in range(1, n):
        dp1[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007
        dp2[i] = (dp1[i - 1] + dp2[i - 1]) % 10007
        if tops[i - 1] == 1:
            dp2[i] += (dp4[i - 1]) % 10007
        dp3[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007
        if tops[i] == 1:
            dp4[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007
        else:
            dp4[i] = 0
    answer = (dp1[n - 1] + dp2[n - 1] + dp3[n - 1] + dp4[n - 1]) % 10007
    return answer