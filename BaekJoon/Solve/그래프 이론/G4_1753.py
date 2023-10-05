# 최단경로

import heapq
import sys
INF = int(1e9)

input = sys.stdin.readline

v, e = map(int, input().split())
start = int(input())
graph = [[] for _ in range(v+1)]
for _ in range(e):
    a, b, c = map(int, input().split())
    graph[a].append([b,c])

distance = [INF]*(v+1)

def dijkstra():
    distance[start] = 0
    queue = []
    heapq.heappush(queue, [0, start])
    while queue:
        # 거리를 계산하고자 하는 노드
        now_dist, now_node = heapq.heappop(queue)
        # 기존 거리가 더 짧다면 유지
        if distance[now_node] < now_dist:
            continue
        # 노드와 연결된 새로운 노드들
        for next_node, next_dist in graph[now_node]:
            # 계산된 거리
            dist = next_dist+now_dist

            if distance[next_node] > dist:
                distance[next_node] = dist
                heapq.heappush(queue, [dist, next_node])

dijkstra()

for i in range(1, v+1):
    if distance[i] == INF:
        print("INF")
    else:
        print(distance[i])

           