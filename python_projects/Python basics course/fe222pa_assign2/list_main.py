
import list_functions

n = 10
y = list_functions.random_list(n) #provides n amount of random numbers in a list.
print(y)


lst = [1,3,5,7,9,10,10]

x = list_functions.average(lst)
print(x) # should show the average of the values in lst (5.8 or 6 since its rounded in this case)


h = list_functions.only_odd(lst)
print(h) # should show all numbers in lst except 10

g = list_functions.to_string(lst)
print(g, type(g)) # should say the values in lst but turned into a str class.

m = list_functions.contains(lst, 1,10)
print(m) # should say true since 1 and 10 are in lst

j = list_functions.has_duplicates(lst)
print(j) #should say True since 10 is mentioned twice in lst.