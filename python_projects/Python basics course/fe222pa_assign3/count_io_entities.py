import os
# y = 0
#path = os.getcwd()
#print(path)

# lst = os.listdir(path)
# for s in lst:
#     print(s)
#     y += 1
# print(y)


#count how many subdirectories are in a file : in 1DV501 there are 3 since fe222pa1, ...2 and ...3 = gives 3
def count_directories(dir_path):
    x = 0
    lst = os.listdir(dir_path)
    for _ in lst:
        x += 1
    return x


#count the amount of py files in a directory, for instance in my fe222pa_assign1 file it contains 15 py files
def count_py_files(dir_path):
    x = 0
    lst = os.listdir(dir_path)
    for s in lst:
        if s.endswith('.py'):    
            x += 1
    return x

try:
    path = os.getcwd() #choose a directory to count its files/sub directories, cwd will be used by default.
    print("Dir Count:", count_directories(path))    
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)

try:
    path = os.getcwd() #choose a file that contains the py files
    path += "/fe222pa_assign3" #choose a file that contains the py files, assign3 is used by default.
    print("Py-file Count:", count_py_files(path))
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)
