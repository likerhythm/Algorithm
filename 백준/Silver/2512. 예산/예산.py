N = int(input())
budgets = list(map(int, input().split()))
M = int(input())

if sum(budgets) <= M:
  print(max(budgets))
else:
  start = 1
  end = M
  answer = 0
  while start <= end:
    mid = (start + end) // 2
    calc_budget = 0
    for budget in budgets:
      if mid > budget:
        calc_budget += budget
      else:
        calc_budget += mid
    if calc_budget > M: # 계산된 총 예산이 주어진 예산보다 큰 경우
      end = mid - 1
    elif calc_budget <= M:
      start = mid + 1
      answer = max(answer, mid)
  print(answer)