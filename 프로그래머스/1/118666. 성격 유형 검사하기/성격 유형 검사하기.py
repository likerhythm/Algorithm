# RT
# CF
# JM
# AN

def solution(survey, choices):
    answer = ''
    
    mbti = [0] * 4
    
    for i in range(len(survey)):
        score = choices[i] - 4
        index = -1
        
        if score < 4:
            index = 0
        elif score > 4:
            index = 1
        else:
            continue
            
        if survey[i][index] in ['T', 'F', 'M', 'N']:
            score = -score
            
        if survey[i][index] in "RT":
            mbti[0] += score
        elif survey[i][index] in "CF":
            mbti[1] += score
        elif survey[i][index] in "JM":
            mbti[2] += score
        elif survey[i][index] in "AN":
            mbti[3] += score
    
    if mbti[0] < 0:
        answer += 'R'
    elif mbti[0] > 0:
        answer += 'T'
    else:
        answer += 'R'
    
    if mbti[1] < 0:
        answer += 'C'
    elif mbti[1] > 0:
        answer += 'F'
    else:
        answer += 'C'
        
    if mbti[2] < 0:
        answer += 'J'
    elif mbti[2] > 0:
        answer += 'M'
    else:
        answer += 'J'
        
    if mbti[3] < 0:
        answer += 'A'
    elif mbti[3] > 0:
        answer += 'N'
    else:
        answer += 'A'
    # print(mbti)
    return answer