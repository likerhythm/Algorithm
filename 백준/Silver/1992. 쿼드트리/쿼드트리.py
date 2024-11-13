N = int(input()) # 2의 제곱수

arr = [list(map(int, input())) for _ in range(N)]

def make_string(start_x, start_y, search_range):
  start_num = arr[start_x][start_y]

  if search_range == 1:
    return str(start_num)
  for i in range(start_x, start_x + search_range):
    for j in range(start_y, start_y + search_range):
      if arr[i][j] != start_num:
        next_search_range = search_range // 2
        string = '(' + (
                  make_string(start_x, start_y, next_search_range)
                + make_string(start_x, start_y + next_search_range, next_search_range)
                + make_string(start_x + next_search_range, start_y, next_search_range)
                + make_string(start_x + next_search_range, start_y + next_search_range, next_search_range)
        ) + ')'
        return string
  return str(start_num)

answer = make_string(0, 0, N)
print(answer)