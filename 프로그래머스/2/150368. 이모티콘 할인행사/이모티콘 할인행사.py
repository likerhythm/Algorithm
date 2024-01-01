from itertools import product
# users: [비율, 가격]
# emoticons: [가격]
def solution(users, emoticons):
    answer = []
    # 각 경우에 대한 결과(플러스 가입자, 판매액)
    result = []
    
    r = [10, 20, 30, 40]
    
    # 할인 적용
    sailed_price = [[emo*0.9, emo*0.8, emo*0.7, emo*0.6] for emo in emoticons]
    
    # 이모티콘 금액의 모든 경우의 수
    sailed_prices = list(product(*sailed_price))
    # print("sailed_prices: ", sailed_prices)
    
    
    result = []
    for prices in sailed_prices:
        plus_service = 0
        sales = 0
        for rate, limit_price in users:
            sum_price = 0
            for i, p in enumerate(prices):
                # 적용된 할인율
                sailed_rate = round((1 - p / emoticons[i]) * 100)
                # print(round(sailed_rate))
                if sailed_rate >= rate:
                    sum_price += p
            # 이모티콘 플러스 서비스 구매
            if sum_price >= limit_price:
                plus_service += 1
            # 이모티콘 구매
            else:
                sales += sum_price
        result.append([plus_service, sales])
    
    result = sorted(result, key=lambda x: x[1])
    result = sorted(result, key=lambda x: x[0])
    # print(result)
    answer = result[-1]
    
    return answer