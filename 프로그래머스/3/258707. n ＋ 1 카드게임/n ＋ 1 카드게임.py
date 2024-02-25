def solution(coin, cards):
    n = len(cards)
    deq = {x:0 for x in range(1, n+1)}
    
    #처음 덱
    #deq = set(cards[:n//3])
    for card in cards[:n//3]:
        deq[card] = 1
    pick = {x:0 for x in range(1, n+1)}
        
    #뽑은 카드
    c1, c2 = n//3 - 2, n//3 - 1
    
    round = 1
    flag = True
    while True:
        found = False
        c1 += 2
        c2 += 2
        if c1 >= n:
            break
        pick[cards[c1]] = 1
        pick[cards[c2]] = 1
        for key in deq:
            if deq[key] == 1 and deq[n+1-key] == 1:
                deq[key] = 0
                deq[n+1-key] = 0
                round += 1
                found = True
                break
        if not found:
            for key in deq:
                if deq[key] == 1 and pick[n+1-key] == 1 and coin >= 1:
                    deq[key] = 0
                    pick[n+1-key] = 0
                    coin -= 1
                    round += 1
                    found = True
                    break
        if not found:
            for key in pick:
                if pick[key] == 1 and pick[n+1-key] == 1 and coin >= 2:
                    pick[key] = 0
                    pick[n+1-key] = 0
                    coin -= 2
                    round += 1
                    found = True
                    break
        if not found:
            break
            
    return round