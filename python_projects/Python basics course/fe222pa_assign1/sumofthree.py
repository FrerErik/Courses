#Compute the sum of three digits
a = int(input("Provide a three digit number: "))
if a < 100:
    print("Error")
elif a >= 1000:
    print("Error")
else:  
    b = round(int(a/100))

    temp = b*10

    c = round(int(a/10 - temp))

    d = a - b*100 - c*10

    s = print("sum of digits:",b + c + d)

