#Write a program divisible.py that prints all the numbers from 100 to 200, ten per line, that are divisible by 4 or 5, but not both. 
# The numbers in a printed line are separated by exactly one space.
x = 0
t = 100
while t <= 200:
    if t % 20 == 0:
        x = 0
    else:
        if( (t % 5 == 0) or (t % 4 == 0)):
            if t >= 104 and t <= 128:
                print(t,end=" ")
            elif t == 130: 
                print()        
                print(t,end=" ")
            elif t < 155:
                print(t,end=" ")
            elif t == 156:
                print()
                print(t,end=" ")
            elif t < 185:
                print(t, end=" ")   
            elif t == 188:
                print()
                print(t, end=" ")
            elif t <= 196:
                print(t,end=" ")
    t += 1
print()    


