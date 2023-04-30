
def is_positive_and_odd(n):
    if 0 < n and n%2 != 0:
        return "True"
    else:
        return "False"

    
i = 0
while i < 5:
    n = int(input("Provide a number that is positive and odd: "))
    l = is_positive_and_odd(n)
    i += 1
    if l == "True":
        print(f"Good {n} is positive and odd")
        i += 100
    elif  i == 5 or l == "False" :
        print("Error, please follow instructions try again")