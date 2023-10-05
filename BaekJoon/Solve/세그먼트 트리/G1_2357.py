from heapq import *
import sys
input = sys.stdin.readline


def init(start, end, node):
    arr2 = arr[start:end+1]
    tree[node][0] = min(arr2)
    tree[node][1] = max(arr2)

    if start == end:
        return

    mid = (start + end)//2
    init(start, mid, node*2)
    init(mid+1, end, node*2+1)

def find_min(start, end, section1, section2, node):
    # 구간이 겹치지 않는 경우
    if start > section2 or end < section1:
        return 1000000001
    # 구간을 포함하는 경우
    if start >= section1 and end <= section2:
        return tree[node][0]
    #구간의 일부가 겹치는 경우
    mid = (start + end)//2
    return min(find_min(start, mid, section1, section2, node*2), find_min(mid+1, end, section1, section2, node*2+1))
    

def find_max(start, end, section1, section2, node):
    # 구간이 겹치지 않는 경우
    if start > section2 or end < section1:
        return 0
    # 구간을 포함하는 경우
    if start >= section1 and end <= section2:
        return tree[node][1]
    #구간의 일부가 겹치는 경우
    mid = (start + end)//2
    return max(find_max(start, mid, section1, section2, node*2),
              find_max(mid+1, end, section1, section2, node*2+1))


n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(int(input()))
tree = [[0 for _ in range(2)] for _ in range(len(arr)*4)]

start, end = 0, len(arr)-1
init(start, end, 1)

for _ in range(m):
    a, b = map(int, input().split())
    
    result1 = find_min(start, end, a-1, b-1, 1)
    result2 = find_max(start, end, a-1, b-1, 1)

    print(result1, result2)

