#randomly selecting seven of 35 numbers (in sequence). 
#creates a valid lotto sequence, that is seven numbers in the range from 1 to 35 without duplicates.
# 5 6 18 21 24 31 32
import random


d = 0
k = []
p = 0
g = 0
m = 0
e = 0
while p < 7:
    if p == 0:
        for i in range (1):
            rn = random.randint(1,35)
            k.append( rn )
    else:
        for i in range(1):
            rn = random.randint(1,35)
            m = rn
            if m in k:
                del(k[0:7])
                p = -1
                #e += 1
            else:
                k.append( rn )
                if p == 6:
                    k.sort()
                    print(k)                
    p += 1
#print(e)