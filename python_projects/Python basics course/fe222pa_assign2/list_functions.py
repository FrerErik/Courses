import random

def random_list(s):
    f = []
    for _ in range(s):
        g = random.randint(1,100)
        f.append( g )
    return f
         
def average(lst):
    x = len(lst)
    z = 0
    k = 0
    while z < x:
        k += lst[z]
        z += 1
        if z == x:
            return round(k/x)

def only_odd(lst):
    x = len(lst)
    g = 0
    f = []
    while g < x:
        if lst[g]%2 != 0:
            f.append( lst[g] )
        g += 1
    return f

def to_string(lst):
    x = len(lst)
    g = 0
    u = ""
    while g < x:
        k = f"{lst[g]},"
        y = f"{lst[g]}]"
        h = f"[{lst[g]},"        
        if g == 0:
            u += h
        elif g < x -1:
            u += k
        elif g == x - 1:
            u += y
        g += 1
    return u


def contains(lst,a,b):
    x = len(lst)
    o = 0
    d = 0
    f = 0
    while o < x:
        p = lst[o]
        if p == a:
            d += 1
        elif p == b:
            f += 1
        o += 1
    if f >= 1 and d >= 1:
        return "True"
    else:
        return "False"




def has_duplicates(lst):
    x = len(lst)
    f = []
    g = 0
    h = 0
    k = 0
    q = 0
    lst.sort()
    while g < x-1 and k < x-1:
        h = lst[g+1]
        for _ in range(1):
            f.append(h)
        if f[k] == lst[g]:
            q += 1
        g += 1
        k += 1
    if q == 0:
        return "False"
    else:
        return "True"