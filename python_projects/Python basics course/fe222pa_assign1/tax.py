# 30% on all income below 38,000kr/month, + 5% if around 38,000kr/month and 50,000 kr/month + 5%  if above 50,000kr/month
a = int(input("Please input monthy income: "))

b = round(a*0.3)
c = round(b + (a - 38000)*0.05)
d = round(b + 12000*0.05 + (a - 50000)*0.1)
if a < 38000 and a >= 0:
    print(b)
elif a >= 38000 and a <= 50000: 
    print(c)
elif a > 50000:
    print(d)
else:
    print("Error")


    