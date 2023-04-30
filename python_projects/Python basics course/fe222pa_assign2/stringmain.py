import stringfunctions

a = "Hello XAXA "
b = 2
con = stringfunctions.concat(a, b)
print(con) # should show "Hello XAXA  Hello XAXA"


k = "l"
g = stringfunctions.count(a, k)
print(g) # should say 2 since 2 l's


p = stringfunctions.reverse(a)   
print(p) # should say " AXAX olleH"


y = stringfunctions.first_last(a)
print(y) #should say (H, A)

v = stringfunctions.has_two_X(a)
print(v) # Should say "True" as long as there are two and only two X's in variable a


j = stringfunctions.has_duplicates(a)
print(j) # as long as two variables repeat after each other it will say true otherwise false