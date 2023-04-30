# which for any given positive integer n (read from the keyboard) 
# computes the smallest integer k such that 1+3+5+7+...+k >= n.

n = int(input("please input a positive integer "))

if n < 1:
    print("Plese input a positive value")
else:
    step = -1
    r = 0
    while r <= n:
        step += 2
        r += step
        #print(r, step) 
        if r == n:
            print ( (step), "is the smallest k such 1+3+5+7+...+k >=" ,n)
        elif ( (r > n) and (r != n + step)):
            print ( (step), "is the smallest k such 1+3+5+7+...+k >=" ,n, end='') 