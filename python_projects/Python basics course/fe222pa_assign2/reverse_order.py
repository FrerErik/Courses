# hat reads an arbitrary number of positive integers from the keyboard and then prints them in reverse order. 
# The reading stops when the user inputs a negative number.

b = []
a = 0
y = 1
while a < y:
    n = int(input("Enter positive integers. End by giving a negative integer: "))
    for i in range(1):
        if n > 0:
            b.append( n )
    a += 1
    y += 1
    if n == 0:
        print("Error Follow instructions")
        a += 100
    elif n > 0:
        print(f"Integer {a}: {n}")
    elif n < 0:
        print(f"Integer {a}: {n}")
        print(f"\nNumber of positive integers: {a - 1}")
        y += -100
        b.sort(reverse= True)
        print(b)
    else:
        print("Error Follow instructions")