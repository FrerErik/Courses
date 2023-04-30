# median salary, average salary, salary gap
# all rounded

# gap means difference between highest salary and lowest 

def turn_str_into_lst(st):
    k = []
    f = ""
    st += " "
    for i in st:
        f += i
        if i == " " and f != " ":
            k.append(int(f))
            f = ""
    return k

def median(lst):
    x = 0
    for _ in lst:
        x += 1
    lst.sort()
    if x%2 != 0:
        return (lst[round(int((x/2)))])
    else:
        o = (lst[round(x/2)] + lst[round(x/2) - 1])/2
        return (round(o))

def average(lst):
    x = 0
    g = 0
    for i in lst:
        x += i
        g += 1
    return round(x/g)

def gap(lst):
    x = lst[0]*10
    y = 0
    for i in lst:
        if i > y:
            y = i
        if x > i:
            x = i
    return round(y - x)


            

o = []
a = input("Provide Salaries: ")
if a == "" or a == " ":
    print("Error no salaries found")
else:
    a = turn_str_into_lst(a)
    o.append(a[0])
    if a == o:
        print("Error more than one Salary is needed")
    else:
        print(f"Median: {median(a)}\nAverage: {average(a)} \nGap: {gap(a)}")

