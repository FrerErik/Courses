# how many boxes of candles a person needs to buy each year for his birthday cake. 
# You can assume that the person reaches an age of 100, the number of candles used each year is the same as the age, 
# that you save non-used candles from one year to another, and that each box contains 24 candles. 
# Also print the total number of boxes one has to buy, and how many candles that are available after having celebrated the 100th birthday.
# I want to print when it is needed to buy a new box for now we look for at 1, 7 10
# in order to do that we need to keep track of the number of candles and how many are spent according to the year of the individual
# at the start we buy 1 box that contains 24 candles 
# hence we start at 1 years old, 1 box and 24, candles 
# when box + 1 so candles + 24 opposite is true
x = 1 # years of the individual
d = 24 # candles
k = 1 # boxes bought
l = 1 # counts how many times the while loop has gone through
while x < 101:
    if x == 1:
        print(f"Before birtday {x}, buy {l} box(es)")
    elif d < 1:
        print(f"Before birtday {x-1}, buy {l} box(es)")
        l = 0
        while d < 1:
            d = d + 24
            k += 1
            l += 1
    if x == 100:
        print(f"Before birtday {x}, buy {l} box(es)")
    x += 1  
    d += -x
print(f"\nTotal number of boxes: {k}, Remainding candles: {d + x - 1}")