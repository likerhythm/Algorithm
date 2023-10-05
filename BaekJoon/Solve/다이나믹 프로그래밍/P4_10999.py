# 구간 합 구하기 2
import sys
input = sys.stdin.readline

N, M, K = map(int, input().split())

arr = []
for _ in range(N):
    arr.append(int(input()))

tree = [0]*(N*4)

def setTree(start, end, treeNode):
    if start == end:
        tree[treeNode] = arr[start]
        return tree[treeNode]
    mid = (start+end)//2
    tree[treeNode] = setTree(start, mid, treeNode*2)+setTree(mid+1, end, treeNode*2+1)
    return tree[treeNode]

# start, end: 트리의 노드가 가진 합의 구간
# left, right: 합을 구하고자 하는 구간
def getSum(start, end, left, right, treeNode):
    add_lazy(start, end, treeNode)
    # 두 구간이 겹치지 않는 경우
    if start>right or end<left:
        return 0
    # 구하고자 하는 구간이 더 크거나 같은 경우
    if start>=left and end<=right:
        return tree[treeNode]
    mid = (start+end)//2
    return getSum(start, mid, left, right, treeNode*2)+getSum(mid+1, end, left, right, treeNode*2+1)

def update(start, end, left, right, diff, treeNode):
    add_lazy(start, end, treeNode)

    # 구간이 겹치지 않는 경우
    if right<start and end<left:
        return 0
    
    # update 하고자 하는 구간이 노드의 구간을 포함하는 경우
    if left<=start and end<=right:
        tree[treeNode] += (end-start+1)*diff
        # leaf 노드가 아니면 하위 노드에 lazy 추가
        # 추가된 lazy는 해당 노드를 방문할 때 적용됨.
        # lazy에 이미 추가된 값 이후에 적용되는 lazy 값임.
        if start!=end:
            lazy[treeNode*2] += diff
            lazy[treeNode*2+1] += diff
        return tree[treeNode]
    
    if start!=end:
        # 일부만 겹치는 경우
        mid = (start+end)//2
        tree[treeNode] = update(start, mid, left, right, diff, treeNode*2)+update(mid+1, end, left, right, diff, treeNode*2+1)
    return tree[treeNode]
    

# 방문한 노드에 대해서 반영되지 않은 lazy가 있으면 반영하는 함수
def add_lazy(start, end, treeNode):
    if lazy[treeNode] != 0:
        # lazy 반영
        tree[treeNode] += (end - start + 1)*lazy[treeNode]
        # 하위 노드는 아직 lazy가 반영되지 않았으므로 이제 반영
        if start != end:
            lazy[treeNode*2] += lazy[treeNode]
            lazy[treeNode*2+1] += lazy[treeNode]
        # 현재 노드는 lazy 반영 되었으므로 초기화
        lazy[treeNode] = 0

setTree(0,N-1,1)

lazy = [0]*(N*4)
for _ in range(M+K):
    params = list(map(int, input().split()))
    a, b, c = params[0], params[1], params[2]
    if a == 1:
        d = params[3]
        update(0, N-1, b, c, d, 1)
    if a == 2:
        print(getSum(0, N-1, b-1, c-1, 1))