# reading a first name and a last name (given name and family name) as two strings. 
# The output should consist of the first letter of the first name followed by a dot and a space, 
# followed by the first four letters of the last name

a = input("First name: " )
b = input("Last name: ")
l = "."
r = len(b)
if r > 3:
    print("Short name: ",a[0]+l, b[0:4])
elif r == 3:
    print("Short name: ",a[0]+l, b[0:3])
elif r == 2:
    print("Short name: ",a[0]+l, b[0:2])
elif r == 1:
    print("Short name: ",a[0]+l, b[0:1])
else:
    print("Error")