#Write a program read_write.py containing two functions read_file(file_path) and write_file(lines, file_path).
import os


def read_file(file_path):   
    file = open(file_path,"r")
    full_text = ""
    for line in file:
        full_text += line
    file.close()
    return full_text

def write_file(lines, file_path):
    file = open(file_path,"w")
    file.writelines(lines)
    file.close
k = os.getcwd()
k += "/fe222pa_assign3/temp/text.txt" #input exactly where the txt file is located 

try:
    print(read_file(k))
    u = input("Type what you want to be written in the txt file: ") 
    write_file(u,k)
    print(read_file(k))
except FileNotFoundError as j:
    print(j)
except Exception as j:
    print(j)


