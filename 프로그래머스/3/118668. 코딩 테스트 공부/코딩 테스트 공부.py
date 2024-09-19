import sys
INT_MAX = sys.maxsize

def get_max(problems):
    max_alp = 0
    max_cop = 0
    for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
        max_alp = max(max_alp, alp_req)
        max_cop = max(max_cop, cop_req)
    return max_alp, max_cop

def solution(alp, cop, problems):    
    max_alp, max_cop = get_max(problems)
    dp = [[INT_MAX] * (max_cop + 1) for _ in range(max_alp + 1)] # dp[i][j]: 알고력 i, 코딩력 j가 되기 위한 최단시간
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    dp[alp][cop] = 0

    for i in range(alp, max_alp + 1):
        for j in range(cop, max_cop + 1):
            if i == max_alp and j == max_cop:
                continue
            if i == max_alp:
                dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)
            elif j == max_cop:
                dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
            else:
                dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
                dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if i < alp_req or j < cop_req:
                    continue
                next_alp = min(i + alp_rwd, max_alp)
                next_cop = min(j + cop_rwd, max_cop)
                dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[i][j] + cost)
                
    answer = dp[max_alp][max_cop]
    return answer