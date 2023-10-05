# 색정이 만들기

global white
global blue

white = 0
blue = 0

def run(arr, n, i, j):
  if not same_color(arr, n, i, j):
      run(arr, n//2, i, j)
      run(arr, n//2, i+n//2, j)
      run(arr, n//2, i, j+n//2)
      run(arr, n//2, i+n//2, j+n//2)
  else:
      global white
      global blue
      if arr[i][j] == 1:
        blue += 1
        return
      else:
        white += 1
        return
         

def same_color(arr, n, i, j):
    if n == 1:
       return True
    color = arr[i][j]
    for i2 in range(i, i+n):
      for j2 in range(j, j+n):
         if arr[i2][j2] != color:
            return False
    return True

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]

run(arr, n, 0, 0)

print(white)
print(blue)

