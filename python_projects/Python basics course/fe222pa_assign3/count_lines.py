import os
def count_py_files(dir_path):
    x = 0
    lst = os.listdir(dir_path)
    for s in lst:
        if s.endswith('.py'):    
            x += 1
    return x

def last_py_file(dir_path):
    x = 0
    lst = os.listdir(dir_path)
    a = count_py_files(dir_path)
    for s in lst:
        if s.endswith('.py'):    
            x += 1
        if x == a:
            return s

def first_folder_in_1DV501(dir_path):
    lst = os.listdir(dir_path)
    x = 0
    for i in lst:
        x += 1
        if x == 1:
            return f"/{i}/"
def last_folder_in_1DV501(dir_path):
    lst = os.listdir(dir_path)
    for i in lst:
        pass
    return f"/{i}/"



def count_py_lines(dir_path):
    dir_path += first_folder_in_1DV501(dir_path)
    x = 0
    i = 0
    q = dir_path
    h  = 0
    b = os.listdir(dir_path)
    k = count_py_files(dir_path)
    p = last_py_file(dir_path)
    t = []
    while x < k:
        q += b[x]
        if q.endswith(".py"):
            file = open(q,"r")
            for o in file:
                y = o.split()    # I make a list of each line, if the list in the line is empty([]) then It ignores it
                if y == t:
                    pass
                else:
                    h += 1
        if( (x == k - 1) and (q.endswith(p)) and (i == 0) and first_folder_in_1DV501(dir_path) != last_folder_in_1DV501(dir_path)):
            dir_path = dir_path.replace("assign1","assign2")
            k = count_py_files(dir_path)
            p = last_py_file(dir_path)
            b = os.listdir(dir_path)
            q = dir_path
            i += 1
            x = 0
        elif( (x == k - 1) and (q.endswith(p)) and (i == 1) and first_folder_in_1DV501(dir_path) != last_folder_in_1DV501(dir_path)):
            dir_path = dir_path.replace("assign2","assign3")
            k = count_py_files(dir_path)
            p = last_py_file(dir_path)
            b = os.listdir(dir_path) 
            q = dir_path
            x = 0
            i += 1
        else:
            pass
        q = dir_path
        x += 1
        file.close()
    if h == 0:
        return "Error no Lines found in py files!"
    else:
        return h

#This program assumes that the path starts in a 1DV501 folder, hence the inside folders must a have ..._assign#.
# regardless if there is only 1 file then it will ignore the rest of the code
path_fold = os.getcwd()
try:
    print("Python Line Count: ",count_py_lines(path_fold))
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)