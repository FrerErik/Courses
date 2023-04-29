# code that Generates and prints (in a single line) n random numbers in the interval [1,100]
# Prints the average value (with two decimals), the smallest number (min), and the largest number (max).

import random
n =int(input("Please input the amount of random numbers desired: "))
s = 0
i = 0
ma = 0
mi = 100
if n < 1:
    print("Use positive values!")
else:
    while i < n:
        a = random.randint(1,100)
        print(a, end=' ')
        i += 1
        s += a
        if a > ma:
            ma = a
        if  a < mi:
            mi = a   
    #print(i, a, s)

    print(f"\nAverage, min, and max are: {(s/n)}, {mi}, and {ma}.")