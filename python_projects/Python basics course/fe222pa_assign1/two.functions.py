

def positive_int(float_lst):
    k = 0
    new_lst = []
    for i in float_lst:
        if i < 0:
            pass
        else:
            k = round(i)
            if k == i - 0.5:
                k += 1
                new_lst.append(k)
            else:
                new_lst.append(k)
    return new_lst





def largest_K(N):
    o = 0
    num = 1
    if N < 0:
        return("invalid number")
    elif N == 1:
        return 0
    else:
        for i in range(N):
            if i%2 == 0:
                pass
            else:
                o += i
                num += 2
                if N < 9:
                    if N == 2 or N ==  3:
                        num = 1
                    elif N == 4 or N == 5 or N == 6 or N == 7 or N == 8:
                        num = 3
                    break
                else:
                    if o > N:
                        num += -4
                        break
                    elif o == N:
                        num += -4
                        break
        return num



# show
lst = [1.3, 2.67, -2.25, 4.88, 40.14, 49.3, 9.2, 36.121, 37.9, 9.7, 34.5, -10]
lst_n = []
for i in positive_int(lst):
    lst_n.append(largest_K(i))


print(positive_int(lst))
print(largest_K(100))

# 1, 3, 5, 7, 9, 11, 13
# 1, 4, 9, 16, 25, 36, 49

print(lst_n)