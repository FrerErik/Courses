

import matplotlib.pyplot as plt
import os
import random
try:
    fil = os.getcwd()
    fil += "/lab1/sample_text.txt"

    string = ""

    file = open(fil, "r", encoding='utf-8')

    for i in file:
        for z in i:
            string += z.strip()



    def bit_creator(string):
        values = 0
        x = len(string)
        h = 0
        while h < x:
            values += ord(string[h])/x
            h += 1
        values = round(values)
        if values < 100:
            values = values*10**5
            for i in string:
                values += 3*ord(i)**x
        elif values > 100:
            values = values*10**4
            for i in string:
                values += 3*ord(i)*x
        bit = "0"
        values = round(values)
        values = str(values)
        b = 2
        for i in values:
            if int(i)%2 == 0:
                bit += "0"
            else:
                bit += "1"
            if b == 8:
                break
            b += 1
        return bit


    # B) part 1
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


    plt.xlabel("bite values")
    plt.ylabel("frequency")


    bins = []

    for i in values_uniformity:
        if i not in bins:
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


    plt.ylabel("bite values")
    plt.xlabel("Bits added")

    plt.title("2nd_property_graph")
    plt.plot(x_values, values_2ndproperty)
    plt.show()
except FileNotFoundError as f:
    print(f)
except Exception as f:
    print(f)