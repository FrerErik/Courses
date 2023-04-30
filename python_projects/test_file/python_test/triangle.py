a = int(input("Input positive interger")) - 1

space = " "
k = "*"
l = 1
triangle = ""
triangle_1 = ""
if a < 0:
    print("Invalid number")
    pass
else:
    while a > -1:
        space1 = a*space
        k1 = l*k
        a += -1
        l += 1
        triangle = space1 + k1
        triangle_1 += f"\n{triangle}"
print(triangle_1)
