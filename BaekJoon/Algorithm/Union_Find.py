"""
Union-Find Algorithm이란 Disjoint Set을 표현할 때 사용하는 알고리즘이다.
Disjoint Set이란 서로 중복되지 않는 부분 집합들로 나눠진 원소들에 대한 정보를 저장하고 조작하는 자료구조이다.
Union-Find Algorithm은 Union(합집합)과 Find(찾기)의 두 가지 연산으로 이루어져 있다.
Union(합집합) 연산은 두 개의 원소가 포함된 집합을 하나의 집합으로 합치는 연산이다.
Find(찾기) 연산은 특정 원소가 속한 집합이 어떤 집합인지 찾는 연산이다.
Union-Find Algorithm은 Union 연산을 통해 서로 연결된 노드들을 찾거나, 노드들의 연결 여부를 확인할 때 사용할 수 있다.
"""

def find_parent(parent, node):
    if parent[node] > 0:
        parent[node] = find_parent(parent, parent[node]) # 경로 압축 기법
    return parent[node]

def union_parent(parent, node1, node2):
    pNode1 = find_parent(parent, node1)
    pNode2 = find_parent(parent, node2)
    if pNode1 == pNode2:
        return 
    if pNode1 < pNode2:
        parent[node2] = node1
        parent[node1] += pNode2

    else:
        parent[node1] = node2
        parent[node2] += pNode1