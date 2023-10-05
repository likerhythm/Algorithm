def sublist_max(profits):
    sum = 0
    list = []

    for money in profits:
        sum += money
        if sum>0:
            list.append(sum)
        else:
            sum = 0
    return max(list)