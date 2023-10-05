import math

while True:
  taxi = int(input()) # 택시 원의 넓이
  index = [list(map(int, input().split())) for _ in range(4)]
  index1 = index[1][0] - index[0][0] # 직교좌표계 가로 길이
  index2 = index[2][1] - index[1][1] # 직교좌표계 세로 길이
  rate = math.pi/2
  uclid = taxi * rate # 유클리드 원의 넓이
  r = (uclid/math.pi)**(1/2) # 원의 반지름

  if r>index1 or r>index2:
      print("error")
  else:
      area = uclid + 2 * (index1 - 2*r) * r + 2 * (index2 - 2*r) * r + (index1 - 2*r) * (index2 - 2*r)
      print(round(area, 2))