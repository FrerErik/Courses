import os

def read_words(inputfile):
    with open(inputfile, 'r') as f:
        content = f.read()
        words = content.split()
        lst = []
        for word in words:
            # using ASCII to identify punctuation
            for i in word:
                # 39 is "'"
                if ord( i )<39:
                    word = word.replace(i,'')
                    lst.append(word)
                # 45 is "-" in the middle of a word
                elif 39<ord( i )<45:
                    word = word.replace(i,'')
                    lst.append(word)
                # 65 is "A"
                elif 45<ord( i )<65:
                    word = word.replace(i,'')
                    lst.append(word)
                # 90 is "Z"
                # 97 is "a"
                elif 90<ord( i )<97:
                    word = word.replace(i,'')
                    lst.append(word)
                # 122 is "z"
                elif ord( i )>122:
                    word = word.replace(i,'')
                    lst.append(word)                    
                # remove "-" at the end of the word
                elif word.endswith('-'):
                    word = word.replace('-','')
                    lst.append(word)
        return lst


a = os.getcwd()
a += "/fe222pa_assign3/temp/large_texts.txt/holy_grail.txt"
print(a)
print(read_words(a))