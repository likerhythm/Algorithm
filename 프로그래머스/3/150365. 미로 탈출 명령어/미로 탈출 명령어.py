# dlru(아래, 왼, 오, 위 순으로 탐색)
# 거리 k

import sys
sys.setrecursionlimit(5000)

dxs = [1, 0, 0, -1]
dys = [0, -1, 1, 0]
direction = ['d', 'l', 'r', 'u']

def solution(n, m, x, y, r, c, k):
    def in_range(x, y):
        return 1 <= x <= n and 1 <= y <= m  
    str_ans = ''
    answer = [''] * k
    if (abs(x - r) + abs(y - c)) % 2 != k % 2 or (abs(x - r) + abs(y - c)) > k:
        str_ans = 'impossible'
    else:
        found = False
        
        def f(now_x, now_y, cnt):
            nonlocal found
            if cnt == k:
                if now_x == r and now_y == c: # 도착지에 도착한 경우
                    print(*answer)
                    found = True
                return
            elif (abs(now_x - r) + abs(now_y - c)) > k - cnt: # 남은 거리를 갈 수 없는 경우
                return
            for i in range(4):
                dx, dy = dxs[i], dys[i]
                nx = now_x + dx
                ny = now_y + dy
                if in_range(nx, ny):
                    answer[cnt] = direction[i]
                    f(nx, ny, cnt + 1)
                    if found:
                        return
                    answer[cnt] = ''

        f(x, y, 0)
        for s in answer:
            str_ans += s
    return str_ans