import os
try:
    fil = os.getcwd()

    fil += "/lab1/Fredric_Eriksson_Sepulveda.txt"


    paragraph = open(fil, "r", encoding='utf-8')
    sentence = ""
    text = []
    k = ""
    for i in paragraph:
        k += i


    for z in k:
        sentence += z.upper()
    
    key = int(input("Select the amount of shifts desired: "))#I will use 7 in case
    j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    print(KeyC)
    while key > 0:
        KeyC.append(KeyC[0])
        KeyC.remove(KeyC[0])
        key += -1 
    print(KeyC)
    sentence2 = ""
    p = 0
    for i in sentence:
        if i.isalpha():
            while p < len(j) - 1:
                if j[p] == i:
                    sentence2 += KeyC[p]
                p += 1
        else:
            sentence2 += i
        p = 0
    # key 4213
    x = 1
    g = 0
    final_sentence = ""
    while g < round(len(sentence2)/4):
        final_sentence += sentence2[3 + g*4]
        final_sentence += sentence2[1 + g*4]
        final_sentence += sentence2[0 + g*4]
        final_sentence += sentence2[2 + g*4]
        if g == round(len(sentence2)/4) - 1: 
            g += 1              
            if len(sentence2)%4 == 0:
                g += -1
            if len(sentence2)%4 == 1:
                final_sentence += sentence2[0 + g*4]
            elif len(sentence2)%4 == 2:
                final_sentence += sentence2[1 + g*4]                
                final_sentence += sentence2[0 + g*4]
            elif len(sentence2)%4 == 3:
                final_sentence += sentence2[1 + g*4]
                final_sentence += sentence2[0 + g*4]
                final_sentence += sentence2[2 + g*4]
            else:
                pass
        g += 1
    
    paragraph.close()


    with open(fil, "w", encoding='utf-8') as encrypted:
        encrypted.writelines(final_sentence)
        encrypted.close()
except FileNotFoundError:
    print("Please select path to be the desired Encrypted file")
except Exception as f:
    print(f)