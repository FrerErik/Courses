import os

fil = os.getcwd()
fil += "/lab1/Fredric_Eriksson_Sepulveda.txt"

# This program is meant to decode "encryption_program.py"

file = open(fil, "r", encoding='utf-8')


text = ""
x = 1
for i in file: #key 4213
    for z in i:
        text += z

p = 0
tr = ""
print(len(text)/4)
while p < round(len(text)/4): #key 3102 ---> 1234
    tr += text[2 + p*4]
    tr += text[1 + p*4]    
    tr += text[3 + p*4]    
    tr += text[0 + p*4]
    if p == round(len(text)/4) - 2:
        p += 1
        if len(text)%4 == 0:
            tr += text[2 + p*4]
            tr += text[1 + p*4]    
            tr += text[3 + p*4]    
            tr += text[0 + p*4] 
        elif len(text)%4 == 1:
            tr += text[0 + p*4]
        elif len(text)%4 == 2:
            tr += text[1 + p*4]                
            tr += text[0 + p*4]
        elif len(text)%4 == 3:
            tr += text[2 + p*4]            
            tr += text[1 + p*4]
            tr += text[0 + p*4]
    p += 1


key = int(input("Select the amount of shifts used: "))#I will use 7 in case
j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
while key > 0:
    KeyC.append(KeyC[0])
    KeyC.remove(KeyC[0])
    key += -1 
print(KeyC)
ortext = ""
p = 0
for i in tr:
    if i.isalpha():
        while p < len(j) - 1:
            if KeyC[p] == i:
                ortext += j[p]
            p += 1
    else:
        ortext += i
    p = 0

print(ortext)
