global n
global count
n = int(input())
count = 0

def yeji(m):
    global n
    global count
    if m < n-1:
        yeji(m+2)
        bomin(m+1)
    elif m == n-1:
        count += 1
        return
    else:
        return
    
def bomin(m):
    global n
    global count
    if m < n-1:
        yeji(m+1)
        yeji(m+2)
    elif m == n-1:
        count += 1
        return
    else:
        return
    

yeji(0)

print(count)