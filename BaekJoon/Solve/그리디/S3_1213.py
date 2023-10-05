# 팬린드롬 만들기

arr = list(input())
alphabet = [0]*26

for a in arr:
    alphabet[ord(a)-ord('A')] += 1

# 개수가 홀수인 알파벳 수
count = 0
# 개수가 홀수인 알파벳
#즉 가운데에 출력될 알파벳 인덱스
middle = 0
for i in range(26):
    if alphabet[i]%2 == 1:
        count += 1
        middle = i

if count > 1:
    print("I'm Sorry Hansoo")
elif count == 0:
    str = []
    for i in range(26):
        for _ in range(alphabet[i]//2):
            str.append(chr(i+65))
    reversed_str = str[::-1]
    string = ''.join(str)+''.join(reversed_str)
    print(string)
elif count == 1:
    alphabet[middle] -= 1
    str = []
    for i in range(26):
        for _ in range(alphabet[i]//2):
            str.append(chr(i+65))
    reversed_str = str[::-1]
    string = ''.join(str) + chr(middle+65) + ''.join(reversed_str)
    print(string)