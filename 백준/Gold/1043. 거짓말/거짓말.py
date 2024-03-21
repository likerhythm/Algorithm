N, M = map(int, input().split())

parent = [-1] * (N+1)

#진실을 아는 사람 집합
know = set(input().split()[1:])

parties = []
#파티
for _ in range(M):
  parties.append(set(input().split()[1:]))
# print(f"parties: {parties}")

for _ in range(M):
  for party in parties:
    if party & know:
      know = know.union(party)

# print(f"know: {know}")  

cnt = 0
for party in parties:
  # print(f"party: {party}")
  if party & know:
    continue
  cnt+= 1

print(cnt)