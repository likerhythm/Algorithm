# 최솟값

from heapq import *
import sys
input = sys.stdin.readline


def init(start, end, node):
    arr2 = arr[start:end+1]
    tree[node] = min(arr2)

    if start == end:
        return

    mid = (start + end)//2
    init(start, mid, node*2)
    init(mid+1, end, node*2+1)

def find_min(start, end, section1, section2, node):
    # 구간이 겹치지 않는 경우
    if start > section2 or end < section1:
        return
    # 구간을 포함하는 경우
    if start >= section1 and end <= section2:
        heappush(heap, tree[node])
        return
    #구간의 일부가 겹치는 경우
    mid = (start + end)//2
    find_min(start, mid, section1, section2, node*2),
    find_min(mid+1, end, section1, section2, node*2+1)
    return


n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(int(input()))
tree = [0] * (len(arr)*4)
start, end = 0, len(arr)-1
init(start, end, 1)
for _ in range(m):
    a, b = map(int, input().split())
    
    heap = []
    find_min(start, end, a-1, b-1, 1)
    print(heap[0])

