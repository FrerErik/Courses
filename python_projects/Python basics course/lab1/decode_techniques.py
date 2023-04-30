import os
try:
    ## IMPORTANT  remove all the comments just once in order to use this program as intended.
    fil = os.getcwd()

    fil += "/lab1/Albin_Johnsson_Ciphertext.txt"


    file = open(fil, "r", encoding='utf-8')

    # use a frequency table
    fa = ""
    ast = "*"
    table = {}
    for i in file:
        for z in i:
            fa += z.upper()

    for i in fa:
        if i.isalpha():
            if i not in table:
                table[i] = ""
            table[i] += ast

    for k, v in table.items(): #(frequency graph)
        print(k, "/", v)

    # identify the largest frequencies v, k, r, y, e, f, j, u,
    # assume that v = e (due to being the largest in the frequency table)
    # there should be 17 shifts

    key = int(input("Select the amount of shifts used: "))#I will use 17 in case
    j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    while key > 0:
        KeyC.append(KeyC[0])
        KeyC.remove(KeyC[0])
        key += -1 
    ortext = ""
    p = 0

    print(KeyC)
    for i in fa.upper():
        if i.isalpha():
            while p < len(j) - 1:
                if KeyC[p] == i:
                    ortext += j[p]
                p += 1
        else:
            ortext += i
        p = 0
    print(ortext) ##(full message in here)
    file.close()
    with open(fil, "w", encoding='utf-8') as encrypted:
        encrypted.writelines(ortext)
        encrypted.close()

    #done Its not in order but its just good enough the message has been decrypted!


    ##next student
    fil = fil.replace("Albin_Johnsson_Ciphertext.txt", "")

    fil += "Chen Ningrui.txt"

    # file = open(fil, "r", encoding='utf-8')

    # fa = ""
    # ast = "*"
    # table = {}
    # for i in file:
    #     for z in i:
    #         fa += z.strip()

    # for i in fa.upper():
    #     if i.isalpha():
    #         if i not in table:
    #             table[i] = ""
    #         table[i] += ast

    # for k, v in table.items():
    #     print(k, "/", v)

    # ## in this file the most common letters are: M, Z, A, K, I, W, V, B, Q
    # ## M is the most used so we will assume M = E

    # key = int(input("Select the amount of shifts used: "))#I will use 8 in case
    # j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    # KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    # while key > 0:
    #     KeyC.append(KeyC[0])
    #     KeyC.remove(KeyC[0])
    #     key += -1 
    # ortext = ""
    # p = 0
    # print(KeyC)

    # for i in fa.upper():
    #     if i.isalpha():
    #         while p < len(j) - 1:
    #             if KeyC[p] == i:
    #                 ortext += j[p]
    #             p += 1
    #     else:
    #         ortext += i
    #     p = 0
    # print(ortext) 
except FileNotFoundError as f:
    print(f)
except Exception as f:
    print(f)