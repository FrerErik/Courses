#reading a positive odd integer n from the keyboard, and then prints two triangles. First a right-angled triangle, then isosceles
s ="*"
r = " "
tr = int(input("Enter an odd positive integer: "))
f = 0
g = 1
kl = 0
tr2 = tr
y = round(tr/2)
if ( (tr < 0) or (tr%2 == 0)):
    print("ERROR use acceptable numbers") 
else:
    print("right-angled triangle: x")
    while tr > f:
        print(r*kl + s*tr)
        tr += -1
        kl += 1 
        
    print("\nIsosceles Triangle: ")
    while g <= tr2: 
        print( y*r + s*g)
        g += +2
        y += -1