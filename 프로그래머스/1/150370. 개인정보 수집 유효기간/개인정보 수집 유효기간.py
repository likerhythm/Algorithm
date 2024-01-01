def solution(today, terms, privacies):
    answer = []
    
    base_y, base_m, base_d = 2000, 1, 1
    today_y, today_m, today_d = int(today[:4]), int(today[5:7]), int(today[8:10])
    days = today_d + (today_m - 1)*28 + (today_y - 2000)*12*28
    
    # terms 딕셔너리화
    terms_dict = {}
    for t in terms:
        tpe, term = t.split()
        terms_dict[tpe] = int(term)*28
    
    # privacies 배열화
    privacies_arr = []
    for p in privacies:
        start_data, tpe = p.split()
        # 2000.01.01 기준 흐른 날짜
        d = (int(start_data[:4]) - 2000)*12*28 + (int(start_data[5:7])-1)*28 + int(start_data[8:10])
        privacies_arr.append([d, tpe])
    
    for i in range(len(privacies_arr)):
        d, tpe = privacies_arr[i]
        
        # 유효기간 더하기
        d += terms_dict[tpe]
        
        # 유효 날짜 계산
        if d-1 < days:
            answer.append(i + 1)
    
    return answer