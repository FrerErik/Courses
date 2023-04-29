#Assume a unit circle centred around origin inside a square with sides 2 like in the figure above.
#  Assume also that we randomly generate N points (x,y) where both x and y are within the range [- 1,1]. 
# The proportion of points inside the circle should then approximately be the same as the ratio between the circle area
# pi*R*R (which equals pi since R=1) and the square area 4. This relation can be used to compute an approximation of pi. 
# Write a program pi_approx.py that computes (and prints) 
# a pi approximation for N=100, N=10000, and N=1000000. Print also the error (i.e. the absolut value of pi_actual - pi_approx).




# A = pi since r = 1   and the area of a square would be 4
import random
import math

n = 1
p = False
l = 0

    
while p == False:
    q = random.randint(-10,10) # x value
    g = random.randint(-10,10)
    if g == 0 or q == 0:
        q = random.randint(-10,10)
        g = random.randint(-10,10)
    else:
        q = q/10
        g = g/10
        r = 4/q*g
        l += abs(r)
        if n == 1000000:
            p = True
        else:
            n += 1
print(l/2000000)
print(math.pi - l/2000000)
