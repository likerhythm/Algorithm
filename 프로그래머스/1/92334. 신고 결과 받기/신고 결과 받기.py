def solution(id_list, report, k):
    ban_id = []
    report_cnt = {}
    report_who = {}
    for id_ in id_list:
        report_cnt[id_] = 0
        report_who[id_] = []
    for re_str in report:
        from_id, to_id = re_str.split()
        if to_id in report_who[from_id]:
            continue
        report_cnt[to_id] += 1
        report_who[from_id].append(to_id)
    
    for id, cnt in report_cnt.items():
        if cnt >= k:
            ban_id.append(id)
            
    answer = [0] * len(id_list)
    cursor = 0
    for to_ids in report_who.values():
        for id_ in ban_id:
            if id_ in to_ids:
                answer[cursor] += 1
        cursor += 1
                
                
    return answer