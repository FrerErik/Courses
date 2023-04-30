
# a, b, c, d, e, f, g, h, a = odd b = even ... 
# 1, 2, 3, 4, 5, 6, 7, 8
s = input("Enter a chess square identifier (e.g. e5): ")

p = int(s[1]) # takes the number
t = s[0] # picks the letter

if p > 8:
    print("Error")
elif p < 1:
    print("Error")
else:
    if(("a" == t or "c" == t or "e" == t or "g" == t) and (p%2 == 0)):
        print(s, "Is White")
    elif(("b" == t or "d" == t or "f" == t or "h" == t) and (p%2 == 0)):
        print(s, "Is Black")
    elif(("a" == t or "c" == t or "e" == t or "g" == t) and (p%2 != 0)):
        print(s, "Is Black")
    elif(("b" == t or "d" == t or "f" == t or "h" == t) and (p%2 != 0)):
        print(s, "Is White")
    else:
        print("Error")

