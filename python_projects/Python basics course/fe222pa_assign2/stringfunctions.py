def concat(s, n):
    y = s*n
    return y

def count(s, x):
    y = 0
    r = 0
    for y in s:
        if y == x:
            r += 1
    return r


def reverse(s):
    rev =""
    for c in s:
        rev = c + rev
    return rev

def first_last(s):
    h = len(s)
    x = 0
    if  s[h-1] == " ":
        while s[h-1] == " ":
            h += -1
            u = s[h-1]
            l = (s[0], u)
            return l
    elif s[x] == " ":
        while s[x] == " ":
            x += 1
            v = s[x]
            g = (v, s[h-1])
            return g
    else:
        o = (s[0], s[h-1])
        return o


def has_two_X(s):
    y = len(s)
    x = 0
    while y > 0:
        y += -1
        if s[y-1] == "X":
            x += 1
    if x == 2:
        return "True"
    else:
        return "False"
       

def has_duplicates(s):
    n = 0
    y = len(s)
    x = 0
    j = 0
    while n < y and j < y:
        u = s[n-1]
        n += 1
        k = s[j]
        if k == u:
            x += 1
        j += 1
    if x >= 1:
        return "True"
    else:
        return "False"