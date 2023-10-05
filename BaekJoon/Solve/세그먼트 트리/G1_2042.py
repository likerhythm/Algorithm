def segment_tree(start, end, node):
    if start == end:
        tree[node] = arr[start]
        return tree[node]
    mid = (start + end) // 2
    tree[node] = segment_tree(start, mid, node*2) + segment_tree(mid + 1, end, node*2+1)
    return tree[node]

# start, end: tree의 각 노드가 담당하는 구간 [start, end]
# left, right: 합을 구하고자 하는 구간 [left, right]
def subSum(start, end, left, right, node):
    # 두 구간이 겹치지 않는 경우
    if end < left or start > right:
        return 0
    
    # 합을 구하고자하는 구간이 노드의 구간보다 크거나 같은 경우
    if left <= start and right >= end:
        return tree[node]
    
    # 두 구간의 일부가 겹치는 경우
    mid = (start + end) // 2
    return subSum(start, mid, left, right, node*2) + subSum(mid+1, end, left, right, node*2+1)


# start, end: tree의 각 노드가 담당하는 구간 [start, end]
# left, right: 합을 구하고자 하는 구간 [left, right]
def update(start, end, index, diff, node):

    if start > index or end < index:
        return
    
    tree[node] += diff
    mid = (start + end)//2
    if start != end:
        update(start, mid, index, diff, node*2)
        update(mid+1, end, index, diff, node*2+1)


N, M, K = map(int, input().split())
arr = []
for _ in range(N):
    arr.append(int(input()))

tree = [0] * (N * 4)
start, end = 0, N-1

segment_tree(start, end, 1)

for _ in range(M+K):
    a, b, c = map(int, input().split())
    # update
    if a == 1:
        diff = c-arr[b-1]
        arr[b-1] = c # 이게 왜 있어야 하는지 모르겠음
        update(start, end, b-1, diff, 1)
    # sum
    elif a == 2:
        result = subSum(start, end, b-1, c-1, 1)
        print(result)