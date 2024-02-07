#Ai: i번칸 내구도
#"올리는 위치"에서 로봇을 올릴 수 있다.
#로봇은 벨트 위에서 스스로 이동할 수 있다.
#로봇을 "올리는 위치"에 올리거나 어떤 칸으로 이동하면 그 칸의 내구도는 1만큼 감소
#로봇이 "내리는 위치"에 도달하면 그 즉시 내린다.

#로봇이 이동 가능한 환경: 이동하려는 칸에 로봇이 없고 그 칸의 내구도가 1 이상인 경우
#로봇은 이동가능하다면 벨트 한칸 회전 후 이동한다.

#내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
#몇 단계에서 종료되는지 출력
#N, K
#Ai
def countDurability(dura):
  # print("countDurability")
  count = 0
  for d in dura:
    if d == 0:
      count += 1
  return count


N, K = map(int, input().split())
belt = [0] * (2 * N)
durability = list(map(int, input().split()))

step = 0

while True:
  #벨트 한 칸 이동
  step += 1
  for i in range(2*N - 2, -1, -1):
    belt[i + 1] = belt[i]
  belt[0] = 0
  #내구성 회전
  temp = durability[2*N - 1]
  for i in range(2*N - 2, -1, -1):
    durability[i + 1] = durability[i]
  durability[0] = temp

  #내리는 위치의 로봇 제거
  if belt[N - 1] == 1:
    belt[N - 1] = 0

  #로봇 이동
  #내구도 감소, K와 비교
  for i in range(N - 2, -1, -1):
    j = i
    # while belt[j] == 1 and belt[j + 1] == 0 and durability[j + 1] >= 1:
    if belt[j] == 1 and belt[j + 1] == 0 and durability[j + 1] >= 1:
      # print("durability: ", durability) 
      # print("belt: ", belt)
      # print("로봇 이동")
      belt[j + 1] = 1
      belt[j] = 0
      durability[j + 1] -= 1
      #로봇 내림
      if j + 1 == N - 1:
        # print("로봇 내림")
        belt[j + 1] = 0
      #내구도 비교
      if countDurability(durability) == K:
        print(step)
        exit(0)
      #로봇을 내린 경우 다음 로봇으로
      # if j == N - 2:
      #   break
      # j += 1

  #올리는 위치에 로봇 올림
  #내구도 감소, K와 비교
  if durability[0] >= 1:
    # print("로봇 올림")
    belt[0] = 1
    durability[0] -= 1
  if countDurability(durability) == K:
    print(step)
    exit(0)
