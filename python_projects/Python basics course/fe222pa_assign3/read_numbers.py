# calculate the mean and standard deviation in both txt files
# mean = all numbers + each other/all numbers(in this case 10000)
# standard deviation = ((indivudual numbers - mean)**2/10000)**0.5

import os
# this function reads the file and converts it to list by erasing the commas and also turns all the strings inside to int.
def read_file_A_and_convert_to_lst(file_path): 
    file = open(file_path,"r")
    full_text = ""
    for line in file:
        full_text += line
    lst = full_text.replace(",", "")
    lst = lst.split()
    for _ in range(0, len(lst)):
        lst[_] = int(lst[_])
    file.close()
    return lst

def mean(lst):
    total = 0
    for _ in range(0, len(lst)):
        total = total + lst[_] 
    return total/len(lst)

def std(lst):
    d = 0
    k = 0
    l = 0
    while d < len(lst):
        l = (lst[d] - mean(lst))**2
        k += l
        d += 1
    k = k/len(lst)
    k = k**0.5
    return k

# this function seeks to replace all ":" to " " in order to be able to split it and turn everything inside into into integers
def read_file_B_and_convert_to_lst(file_path):
    file = open(file_path, "r")
    full_text = ""
    p = ""
    for i in file:
        full_text += i
    for i in full_text: # I tried to separate the ":" it at once in the file itself but it gives many errors as opposed to this
        if i == ":":
            p += " "
        else:
            p += i
    lst = p.split()
    for i in range(0, len(lst)):
        lst[i] = int(lst[i])
    file.close()
    return lst


# The "A" is the path to the txt file ..._A.txt that is in my pc, its imperative to replace A to the path to where the txt file ...A.txt is located.
A = os.getcwd()
A += "/fe222pa_assign3/temp/10000_integers/file_10000integers_A.txt" 

try: # it takes some extra time with the std(lst) function but it gives results.
    q = read_file_A_and_convert_to_lst(A)
    print(f"The mean contained in '{A}' is:  {mean(q)}")
    print(f"The standard deviation in '{A}'is: {std(q)}") 
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j) 

# B follows same rules as in A.
B = os.getcwd()
B +="/fe222pa_assign3/temp/10000_integers/file_10000integers_B.txt" 
try:
    m = read_file_B_and_convert_to_lst(B)
    print(f"\nthe mean contained in '{B}' is: {mean(m)}")
    print(f"the standard deviation in '{B}' is: {std(m)}")
except FileNotFoundError as j:
    print(f"\n{j}")
except Exception as j:
    print(f"\n{j}")
