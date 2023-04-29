#choose between the largest 3 

a = int(input("select first digit: "))
b = int(input("select second digit: "))
c = int(input("select third digit: "))

if a > b and a > c:
    print(a)
elif b > a and b > c: 
    print(b)
elif c > a and c > b:
    print(c)
else:
    print("Error")

