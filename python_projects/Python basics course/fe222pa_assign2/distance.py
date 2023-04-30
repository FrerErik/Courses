# create a function distance(x1,y1,x2,y2) which computes the distance between two points x1,y1 and x2,y2 using the formula
#distance = Sqrt( (x1-x2)^2 + (y1-y2)^2 ), written as distance =((x1-x2)**2 + (y1-y2)**2)**1/2

#Also, add program code so that a user can provide the point coordinates and get the distance
def distance(t, m, u, s):
    y = ( (t - u)**2 + (m - s)**2)**0.5
    return round(y, 3)

q = 0
while q < 4:
    if q == 0:
        x1 = float(input("x1: "))
    elif q == 1:
        y1 = float(input("y1: "))
    elif q == 2:
        x2 = float(input("x2: "))
    elif q == 3:
        y2 = float(input("y2: "))
    q += 1
k = distance(x1,y1,x2,y2)
print(f"The distance between ({round(x1, 1)}),({round(y1, 1)}) and ({round(x2, 1)}),({round(y2, 1)}) is {k}")