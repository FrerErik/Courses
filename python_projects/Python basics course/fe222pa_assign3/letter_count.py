import os

def only_english_letters(stri):
    stri = stri.lower()
    letters = "abcdefghijklmnopqrstuvwxyz"
    q = ""
    for i in letters:
        for l in stri:
            if l == i:
                q += i
    return q

# input the direct path to the txt document!
dir_path = os.getcwd()
dir_path += "/fe222pa_assign3/temp/large_texts.txt/holy_grail.txt"
try:
    k = "*"
    p = "X"
    file = open(dir_path,encoding='utf-8')
    full_text = ""
    for i in file:
        full_text += i
    file.close()
    mi = 100000

    full_text = only_english_letters(full_text)
 # limiting factor:
    minimum = {}
    for kl in full_text:
        if kl not in minimum:
            minimum[kl] = 0
        minimum[kl] += 1

    tot = 0
    for pa,va in minimum.items():
        tot += va
        if va < mi:
            mi = va
    if tot == 0:
        print("ERROR: Empty text, or non english-letters used")
    else:
        count = {}
        for i in full_text:
            if i not in count:
                count[i] = ""
            count[i] += p
            if count[i].endswith((2*mi)*p):
                count[i] = count[i].replace("X", "")
                count[i] += k

        mi2 = round(int(mi/65)) #65 is the most optimal I believe


        print(f"Reading from text file: {dir_path}")
        print("Total number of letters: ", tot )
        if mi2 <= 1: # no point in having - if its lower or equal to 1 ("X")
            print(f"each '*' is equal to: {2*mi} letters and each 'X' is equal to 1 letter")
        else:
            print(f"each '*' is equal to: {2*mi} letters, each '-' is equal to {mi2} letters and each 'X' is equal to 1 letter")

        for k,v in count.items():
            if (mi2 <= 1)  and (v.endswith(mi2*p)):
                print(f"{k} |\t{v}")
            else:
                if v.endswith(mi2*p):
                    v = v.replace(mi2*p,"-")
                    print(f"{k} |\t{v}")
except FileNotFoundError as j:
    print (j)
except Exception as j:
    print(j)

# I added a 3rd variable ("-") because it would look terrible and unreadable otherwise 
# and I refuse to show only some parts so it would look convenient.

# the limiting factor (mi) value is dictated by the lowest values of letters in the file times 2(at my best effort to fit all in 1 line).
# the value for mi2 has been obtained through try and error.
# I counted and it takes around 3 mins to print out the 100K text.

# to answer the question: They do resemble quite a lot, many of the "*" look rather similar in both files 
# which means that the limiting factor is rather similar for an old novel, and 100K english newspaper titles, Crazy.