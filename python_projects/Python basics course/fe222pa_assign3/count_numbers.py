import os

#counts the different integers in the list (lst) using a set.
def count_different(lst):
    st = set(lst)
    return f"{lst}\ntotal unique numbers in list: {len(st)}"

def turn_file_A_into_lst(dir_path):
    file = open(dir_path,"r")
    full_text = ""
    for line in file:
        full_text += line
    lst1 = full_text.replace(",", "")
    lst1 = lst1.split()
    for _ in range(0, len(lst1)):
        lst1[_] = int(lst1[_])
    file.close()
    return lst1

def turn_file_B_into_lst(dir_path):
    full_text1 = ""
    file = open(dir_path, "r")
    for line1 in file:
        full_text1 += line1
    lst2 = full_text1.replace(":", " ")
    lst2 = lst2.split()
    for _ in range(0, len(lst2)):
        lst2[_] = int(lst2[_])
    file.close()
    return lst2    

# returns a dictionary (number,count) maximum values in count
def count_occurrences(lst):
    count = {}
    ma = 0
    km = 0
    for i in lst:
        if i not in count:
            count[i] = 0
        count[i] += 1
    for k,v in count.items():
        if v > ma:
            ma = v
            km = k
    return  f"{count.items()} \nthe highest values repeated are located at key: {km} and values repeated: {ma}"





# Input the direct file path for file_10000integers_A.txt in A
# also the -1 in the result is moved because there are more negative values than positive ones 
# I checked here for instance: st = {4,3,2,1,-6,-5,-4,-3,-1,-2,-10,-21}
# if printed it shows: {1, 2, 3, 4, -21, -2, -10, -6, -5, -4, -3, -1} I have no idea why it does that but it still presents the right values, 
# just in different positions, of course I cannot modify the values in the sets so I think ill leave it like that



# read for each integer line 
A = os.getcwd()
A += "/fe222pa_assign3/temp/10000_integers/file_10000integers_A.txt"
B = os.getcwd()
B += "/fe222pa_assign3/temp/10000_integers/file_10000integers_B.txt"
try:
    a = turn_file_A_into_lst(A) 
    print(count_different(a))
    print(count_occurrences(a))
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)

try:
    b = turn_file_B_into_lst(B)
    print(count_different(b))
    print(count_occurrences(b))
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)


# It might look messy but Im just following the instructions