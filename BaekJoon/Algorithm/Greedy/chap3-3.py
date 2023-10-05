# 숫자 카드 게임

# 내가 짠 코드(배열 slicing 사용 => 너무 복잡)
n, m = map(int, input().split())
cards = [list(map(int, input().split())) for _ in range(n)]
for c in cards:
    c.sort()
cards = [row[0:1] for row in cards[0:n]]
cards.sort()

print(cards[-1][0])

# 교재 코드(min, max 사용)
result = 0
for _ in range(n):
    cards = list(map(int, input().split()))
    result = max(result, min(cards))
print(result)

