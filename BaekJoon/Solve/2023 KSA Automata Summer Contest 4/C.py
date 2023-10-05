n, m, k = map(int, input().split())

string_arr = []

for _ in range(n):
    string = input()
    string_arr.append(string)

    string_arr.sort()

result = ""
for i in range(k):
    result += string_arr[i]
print(''.join(sorted(result)))