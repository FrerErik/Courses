#compute the change a customer should receive when she/he has paid a certain sum of money

price = float(input("Input the price (in kr) "))
pay = float(input("Input the amount payed "))
change = round(pay - price)

if change < 0:
    print("Error")
else:
    print (change)

    a = change//1000
    print("amount of 1000 kr bills:", a)
    b = (change - a*1000)//500  
    print("amount of 500 kr bills:", b)
    r = (change - a*1000 - b*500)//200
    print("amount of 200 kr bills", r)
    c = (change - a*1000 - b*500 - r*200)//100
    print("amount of 100 kr bills:", c)
    d = (change - a*1000 - b*500 - r*200 - c*100)//50
    print("amount of 50 kr bills:", d)
    f = (change - a*1000 - b*500 - r*200 - c*100 - d*50)//20
    print("amount of 20 kr bills:", f)
    e = (change - a*1000 - b*500 - r*200- c*100 - d*50 - f*20)// 10
    print("amount of 10 kr coins:", e)
    g = (change - a*1000 - b*500 - r*200- c*100 - d*50 - f*20 - e*10)//5
    print("amount of 5 kr coins:", g)
    j = (change - a*1000 - b*500 - r*200 - c*100 - d*50 - f*20 - e*10 - g*5)//2
    print("amount of 2 kr coins:", j)
    h = (change - a*1000 - b*500 - r*200 - c*100 - d*50 - f*20 - e*10 - g*5 - j*2)//1
    print("amount of 1 kr coins:", h)