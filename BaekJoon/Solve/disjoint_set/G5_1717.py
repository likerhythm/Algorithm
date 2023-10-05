# 분리집합

def find(node):
    if parent[node] >= 0:
        parent[node] = find(parent[node])
        return parent[node]
    return node

def union(node1, node2):
    pNode1, pNode2 = find(node1), find(node2)

    if pNode1 == pNode2:
        return
    if parent[pNode1] < parent[pNode2]:
        parent[pNode1] += parent[pNode2]
        parent[pNode2] = pNode1
    else:
        parent[pNode2] += parent[pNode1]
        parent[pNode1] = pNode2
    return



n, m = map(int, input().split())

parent = [-1]*(n+1)

for _ in range(m):
    n, a, b = map(int, input().split())
    if n == 0:
        union(a, b)
    elif n == 1:
        if find(a) == find(b):
            print("yes")
        else:
            print("no")
    