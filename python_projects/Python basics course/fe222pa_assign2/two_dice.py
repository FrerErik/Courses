import random


s =[2,3,4,5,6,7,8,9,10,11,12]
j =[0,0,0,0,0,0,0,0,0,0,0]
x = 0 
p = 0
h = 0
while x < 10000:
    if h == 11:
        h = 0
    y = random.randint(2,12)
    while h < 11:
        if y == s[0 + h]:
            j[0 + h] += 1      
        h += 1
    x += 1

print("Frequency table (sum,count) for rolling two dices 10000 times:")
while p < 11:
    print( s[p] , j[p] )
    p += 1