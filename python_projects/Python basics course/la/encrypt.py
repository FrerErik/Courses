import os



typ = int(input("Choose operation (1)Encryption, (2)Decryption (select by selecting a number): "))
if typ == 1:
    try:
        # file encrypt_example.txt
        read = os.getcwd()
        read += "/Fredric_Eriksson_Assigment_1/"
        read += input("please type the file name(must be in the same directory as this program): ")

        paragraph = open(read, "r", encoding='utf-8')
        sentence = ""
        k = ""
        for i in paragraph:
            k += i
        for z in k:
            sentence += z.upper()
        f = int(input("Select encryption method: (1)Substitution (2)Transposition: "))
        if f == 1:
            password = input("Type your password: ")
            val = 0
            for i in password:
                val += ord(i)
            val = val%256   
            j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
            KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
            while val > 0:
                KeyC.append(KeyC[0])
                KeyC.remove(KeyC[0])
                val += -1 
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
            paragraph.close()
            with open(read, "w", encoding='utf-8') as encrypted:
                encrypted.writelines(sentence2)
                encrypted.close()
            print("Encrypted")
        elif f == 2:
            paragraph.close()
            paragraph = open(read, "r", encoding='utf-8')
            sentence = ""
            k = ""
            for i in paragraph:
                k += i
            for z in k:
                sentence += z
            pin = input("Insert a 4 number pin code (make sure the values do not repeat): ")  # for example 7203 
            o = 0
            k = 0
            x = 0
            txt = ""
            while o < 4:
                while x < 4:
                    if pin[o] == pin[x]:
                        x += 1
                    else:
                        k += 1
                        x += 1
                    if k == 3:
                        k = 0
                        txt += pin[o]
                k = 0
                x = 0
                o += 1
            x = 0
            l = 0
            k = 0
            pin = ""
            while l < 4:
                while x < 4:
                    if int(txt[l]) > int(txt[x]):
                        k += 1
                    x += 1
                x = 0
                l += 1
                pin += str(k)
                k = 0
            if len(txt) < 4:
                print("Error")
            final_sentence = ""
            g = 0
            # key 0123 ----> 2310
            while g < round(len(sentence)/4):
                final_sentence += sentence[int(pin[0]) + g*4] #0
                final_sentence += sentence[int(pin[1]) + g*4] #1
                final_sentence += sentence[int(pin[2]) + g*4] #2
                final_sentence += sentence[int(pin[3]) + g*4] #3
                if g == round(len(sentence)/4) - 1: 
                    g += 1              
                    if len(sentence)%4 == 0: #last 3, 2, 1 letters, # 0 works
                        g += -1
                    elif len(sentence)%4 == 1:  #0 --> 3 ----> 0 # number 1 works
                        final_sentence += sentence[0 + g*4]
                    elif len(sentence)%4 == 2: #01 ---> 31 ---> 10 #number 2 works
                        final_sentence += sentence[1 + g*4]                
                        final_sentence += sentence[0 + g*4]
                elif  g == round(int(len(sentence)/4)) - 1: # n 3 works
                        g += 1
                        final_sentence += sentence[0 + g*4]
                        final_sentence += sentence[1 + g*4]
                        final_sentence += sentence[2 + g*4]
                g += 1
            paragraph.close()
            with open(read, "w", encoding='utf-8') as encrypted:
                encrypted.writelines(final_sentence)
                encrypted.close()
            print("Encrypted")
        else:
            print("Error restart program")
    except FileNotFoundError:
        print("File not found Error: Please select a file in this directory")
    except Exception as j:
        print(j)
elif typ == 2:
    try:
        read = os.getcwd()
        read += "/Fredric_Eriksson_Assigment_1/"
        read += input("please type the file name(must be in the same directory as this program): ")

        paragraph = open(read, "r", encoding='utf-8')
        sentence = ""
        k = ""
        for i in paragraph:
            k += i
        for z in k:
            sentence += z.upper()
        f = int(input("Select decryption method: (1)Substitution (2)Transposition: "))      
        if f == 1:
            password = input("Type your password: ")
            val = 0
            for i in password:
                val += ord(i)
            val = val%256   
            j = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
            KeyC = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
            while val > 0:
                KeyC.append(KeyC[0])
                KeyC.remove(KeyC[0])
                val += -1 
            ortext = ""
            p = 0
            for i in sentence:
                if i.isalpha():
                    while p < len(j) - 1:
                        if KeyC[p] == i:
                            ortext += j[p]
                        p += 1
                else:
                    ortext += i
                p = 0
            paragraph.close()
            with open(read, "w", encoding='utf-8') as encrypted:
                encrypted.writelines(ortext)
                encrypted.close()  
            print("Decrypted")          
        elif f == 2:
            paragraph.close()
            paragraph = open(read, "r", encoding='utf-8')
            sentence = ""
            k = ""
            for i in paragraph:
                k += i
            for z in k:
                sentence += z
            pin = input("Insert a 4 number pin code (make sure the values do not repeat): ")  # for example 7203 
            o = 0
            k = 0
            x = 0
            txt = ""
            while o < 4:
                while x < 4:
                    if pin[o] == pin[x]:
                        x += 1
                    else:
                        k += 1
                        x += 1
                    if k == 3:
                        k = 0
                        txt += pin[o]
                k = 0
                x = 0
                o += 1
            x = 0
            l = 0
            k = 0
            pin = ""
            while l < 4:
                while x < 4:
                    if int(txt[l]) > int(txt[x]):
                        k += 1
                    x += 1
                x = 0
                l += 1
                pin += str(k)
                k = 0
            if len(txt) < 4:
                print("Error")
            # finding out the right pattern
            a = 2
            b = 2
            c = 2
            d = 2
            if int(pin[0]) == 0:
                a = 0
            if int(pin[0]) == 1:
                b = 0
            if int(pin[0]) == 2:
                c = 0
            if int(pin[0]) == 3:
                d = 0

            if int(pin[1]) == 0:
                a = 1
            if int(pin[1]) == 1:
                b = 1
            if int(pin[1]) == 2:
                c = 1
            if int(pin[1]) == 3:
                d = 1

            if int(pin[2]) == 0:
                a = 2
            if int(pin[2]) == 1:
                b = 2
            if int(pin[2]) == 2:
                c = 2
            if int(pin[2]) == 3:
                d = 2

            if int(pin[3]) == 0:
                a = 3
            if int(pin[3]) == 1:
                b = 3
            if int(pin[3]) == 2:
                c = 3
            if int(pin[3]) == 3:
                d = 3

            txt = ""
            p = 0
            while p < round(len(sentence)/4): #key 2310 ---> 0123
                txt += sentence[a + p*4]#0
                txt += sentence[b + p*4]#1  
                txt += sentence[c + p*4]#2    
                txt += sentence[d + p*4]#3
                if p == round(len(sentence)/4) - 1:
                    p += 1
                    if len(sentence)%4 == 0: #last 3, 2, 1 letters
                        p += -1
                    elif len(sentence)%4 == 1:
                        txt += sentence[0 + p*4]
                    elif len(sentence)%4 == 2:
                        txt += sentence[1 + p*4]                
                        txt += sentence[0 + p*4]
                elif  p == round(int(len(sentence)/4)) - 1:
                        p += 1
                        txt += sentence[0 + p*4]
                        txt += sentence[1 + p*4]
                        txt += sentence[2 + p*4]
                p += 1
            paragraph.close()
            with open(read, "w", encoding='utf-8') as encrypted:
                encrypted.writelines(txt)
                encrypted.close()
            print("Done")
        else:
            print("Error please select between 1 and 2")
    except FileNotFoundError:
        print("File not found Error:  Please select a file in this directory")
    except Exception as j:
        print(j)
else:
    print("Please follow instructions")
