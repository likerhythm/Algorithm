def solution(info, edges):
    visited = [False] * len(info)
    visited[0] = True
    answer = 0
    
    def dfs(sheep, wolf):
        nonlocal answer
        if sheep > wolf:
            answer = max(answer, sheep)
        else:
            return
        for p, c in edges:
            if visited[p] and not visited[c]:
                visited[c] = True
                if info[c] == 1:
                    dfs(sheep, wolf + 1)
                else:
                    dfs(sheep + 1, wolf)
                visited[c] = False
    dfs(1, 0)
    
    return answer