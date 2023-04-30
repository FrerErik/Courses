import random
# I tried something different, how to make it go diagonal do not present.
a = random.randint(-1,1) #x-axis
b = random.randint(-1,1) #y-axis
x_axis, y_axis = 0, 0
steps = 0

k = 0
size = int(input("Enter the size: "))
last_step = int(input("Enter number of steps: "))
number_of_sailors = int(input("Enter number of sailors: "))

drowned = 0
while k < number_of_sailors:
    x = True
    x_axis, y_axis = 0, 0
    steps = 0
    while x == True:
        a = random.randint(-1, 1)
        b = random.randint(-1,1)
        if ((a == 0) or (b == 0) or (a == 0 and b == 0)):
            pass
        else:
            x_axis += a
            y_axis += b
        if ((a == 1 or a == -1) and (b == 1 or b == -1)):
            steps += 1
        if y_axis == size or y_axis == -1*size or x_axis == size or x_axis == -1*size: 
            x = False
        if steps == last_step:
            x = False
    if x_axis <= -1*size or x_axis >= size or y_axis <= -1*size or y_axis >= size:
        drowned += 1
    k += 1
print(f"Out of {number_of_sailors} drunk sailors, {drowned}  ({round(100*(drowned/number_of_sailors), 2)}%) fell into the water.")