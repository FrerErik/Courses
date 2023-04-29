#A string is a palindrome if it contains the same sequence of letters when read backwards. 
# We make no difference between upper and lower case letters.
# I want to turn all sentences into the same structure 
# I want to first get rid of ".", "!", and "?"
# 2nd make everything in caps
# 3rd get rid of all spaces
# so it looks like this Was it a Rat I saw? to WASITARATISAW in order to just reverse it and if they equal each other then we are set

def reverse(s):
    rev =""
    for c in s:
        rev = c + rev
    return rev

 
def is_palindrome(s):
    for i in s:
        if i == "?" or i == "!" or i == ".":
            s = s.replace("?", '')
            s = s.replace("!", '')
            s = s.replace(".", '')
            e = s.upper()
            i = e.replace( " ","" )
            l = reverse(i)
        else:
            e = s.upper()
            i = e.replace( " ","" )
            l = reverse(i)
    if l == i:
        return "True"
    else:
        return "False"

k = "A nut for a jar of tuna??!!."
t = is_palindrome(k)
print(t)
    