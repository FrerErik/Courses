
import matplotlib.pyplot as plt
import os
import random
try:
    fil = os.getcwd()
    fil += "/Fredric_Eriksson_Assigment_1/"
    fil += input("Please type the name of the txt file: ")

    string = ""

    file = open(fil, "r", encoding='utf-8')

    for i in file:
        for z in i:
            string += z.strip()

    def bit_creator(text):
        val = 0
        for i in text:
            val += ord(i)
        key = val%256
        return key

    print(bit_creator(string))
      
    # task 7 part B)
    values_uniformity = []
    g = 0
    while g < 1000:
        d = random.randint(3, 1000)
        text = ""
        while d > 0:
            h = random.randint(32, 126)
            text += chr(h)
            d += -1
        values_uniformity.append(bit_creator(text))
        g += 1


    plt.xlabel("8-bit values")
    plt.ylabel("frequency")


    bins = []
    for i in range(0, 257):
        bins.append(i)


    plt.hist(values_uniformity, bins=bins, edgecolor="black")
    plt.title("Collision_frequency_uniformity_graph")
    plt.show()


    # B) part 2

    values_2ndproperty = [bit_creator(string)]

    d = 1
    base = 32
    add = "AA"
    k = 3
    while d < 1000:
        if base == 126:
            base = 32
            add = add + chr(base)
            k += 1
        string += add + chr(base)
        values_2ndproperty.append(bit_creator(string))
        string = string[:-k]
        base += 1
        d += 1



    x_values = []
    for i in range(0, 1000):
        x_values.append(i)


    plt.ylabel("8-bit values")
    plt.xlabel("Bits added")

    plt.title("2nd_property_graph")
    plt.plot(x_values, values_2ndproperty)
    plt.show()
except FileNotFoundError as f:
    print(f)
except Exception as f:
    print(f)