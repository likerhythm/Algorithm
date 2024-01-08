# 도넛모양: n개의 정점, n개의 간선, 간선 따라가면 출발지도 돌아옴
# 막대 모양 그래프: n개의 정점, n-1개의 간선
# 8자모양 그래프: 2n+1개의 정점, 2n+2개의 간선


# out 간선이 두 개 이상, in 간선이 0개이면 start
# 도넛모양 판별 방법: 간선을 따라갔을 때 출발점으로 돌아오는 경우
# 막대 모양 판별 방법: 간선을 따라갔을 때 out degree가 없는 노드를 만나는 경우
# 8자모양 판별 방법: 간선을 따라갔을 때 out degree가 두 개, in degree가 두 개인 노드가 존재하는 경우
    

def bfs(start, out_degree, visited):
    queue = [start]
    s = 0
    f = 1
    while s != f:
        v = queue[s]
        # print(v)
        visited[v] = True
        s+=1
        for out in out_degree[v]:
            if not visited[out]:
                queue.append(out)
                f += 1
    return visited
        
    


def solution(edges):
    answer = [0, 0, 0, 0]
    vertex = set()
    
    #시작 노드
    start = 0
    
    # 노드 수 구하기
    for a, b in edges:
        vertex.add(a)
        vertex.add(b)
    N = len(vertex)
    visited = [False for _ in range(N + 1)]
    
    # 엣지 연결
    # 각 노드에서 진출하는 간선
    out_degree = [[] for _ in range(N + 1)]
    # 각 노드에 진입하는 간선
    in_degree = [[] for _ in range(N + 1)]
    for a, b in edges:
        out_degree[a].append(b)
        in_degree[b].append(a)
    # print("out: ", out_degree)
    # print("in: ", in_degree)
    
    # 시작 노드 찾기
    for i in range(1, N+1):
        if len(out_degree[i]) >=2 and len(in_degree[i]) == 0:
            start = i
            answer[0] = start
            visited[i] = True
            break
            
    # out 2개, in 2개 이상이면 8자 모양, bfs 진행
    for i in range(1, N+1):
        # 8자
        if len(out_degree[i]) == 2 and len(in_degree[i]) >= 2:
            answer[3] += 1
            visited = bfs(i, out_degree, visited)\
        # 막대
        elif len(out_degree[i]) == 0:
            answer[2] += 1
            visited = bfs(i, in_degree, visited)
    
    
    # 나머지 노드들에 대해서 bfs 진행하여 도넛 개수 찾기
    for i in range(1, N+1):
        if not visited[i]:
            visited = bfs(i, out_degree, visited)
            answer[1] += 1
    # print("answer: ", answer)
    return answer