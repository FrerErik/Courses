# Value of savings (S) after 5 years given by interest rate (P),
# I = initial Savings
# !This code rounds off the final value!
I = float(input("Input Intial savings: "))
P = 1.09  #9%
S = round(I*(P)**5)
print("Total savings are" ,S, "after 5 years")