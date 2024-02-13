# 출입구, 쉼터, 산봉우리
# 양방향, weight = 시간
# 쉼터, 산봉우리에서는 휴식 가능
# 출입구1 - ... - 산봉우리 - ... - 출입구1
# intensity: 등산코스에서 한 번에 이동하는 시간 중 가장 긴 시간
# intensity가 가장 작은 등산 코스

# n: 지점 수
# paths 2차원 배열: 등산로 정보
# gates 배열: 출입구 번호
# summits 배열: 산봉우리 배열
# [산봉우리 번호, intensity 최솟값] 반환

import heapq

def dijstra(linkedList, gate, summits):
    
    n = len(linkedList) - 1 # 노드 수(1 ~ len(linkedList))
    intensities = [float('inf')] * (n + 1)
    queue = []
    heapq.heappush(queue, [0, gate])
    
    minIntensity = float('inf')
    minSummit = 0
    
    while queue:
        currentIntensity, currentNode = heapq.heappop(queue)
        
        if intensities[currentNode] < currentIntensity:
            continue
        
        for node, w in linkedList[currentNode]:
            
            if w > minIntensity:
                continue

            # intens: node까지의 intensity
            intens = max(currentIntensity, w)
            
            if intens < intensities[node]:
                intensities[node] = intens
                # 산봉우리가 아닌 경우에만 추가
                if node not in summits:
                    heapq.heappush(queue, [intens, node])
                # 산봉우리인 경우
                else:
                    if minIntensity > intens:
                        minIntensity = intens
                        minSummit = node
                    elif minIntensity == intens:
                        minSummit = min(minSummit, node)
    
    result = [minSummit, minIntensity]
    return result
    
def solution(n, paths, gates, summits):
    
    summits = set(summits)
    gates = set(gates)
    
    # 그래프 구성
    linkedList = [[] for _ in range(n + 1)]
    for s, f, w in paths:
        if s in gates or f in summits:
            linkedList[s].append((f, w))
        else:
            linkedList[s].append((f, w))
            linkedList[f].append((s, w))
            
    for gate in gates:
        linkedList[0].append((gate, 0))
    
    answer = dijstra(linkedList, 0, summits)
            
    return answer