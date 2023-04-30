#which for any given positive integer n (read from the keyboard) 
# computes the largest integer k such that 0+2+4+6+8+...+k < n

n = int(input("Please Enter a positive integer: "))

if n < 1:
    print("ERROR, Please input a positive number")
else:
    step = -2 # step = k in equation
    l = 0 # l = sum
    while l <= n:
        step += 2
        l += step
        #print(l, step)
        if n == l:
            print ( (step - 2), "Is is the largest k such that 0+2+4+6+...+k <",n)
        elif ( (n < l) and (l - step < n) ):
            print((step - 2), "Is the largest k such that 0+2+4+6+...+k <",n)