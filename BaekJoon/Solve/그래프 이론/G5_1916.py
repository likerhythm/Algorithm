# 최소비용 구하기

import sys
import heapq
INF = int(1e9)
input = sys.stdin.readline

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]
distance = [INF]*(n+1)

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))

start, finish = map(int, input().split())

def dijkstra():
    h = []
    distance[start] = 0
    heapq.heappush(h, (start, 0))
    while h:
        now_node, now_dist = heapq.heappop(h)

        if distance[now_node] < now_dist:
            continue
        
        for next_node, next_dist in graph[now_node]:
            if distance[next_node] > next_dist+now_dist:
                dist = next_dist+now_dist
                distance[next_node] = dist
                heapq.heappush(h, (next_node, dist))

dijkstra()
print(distance[finish])

