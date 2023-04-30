# generate a random number in the interval [-10,10] and classifies it as odd/even and as positive/negative.
import random
a = random.randint(-10,10)

if a < 0 and a%2 == 0:
    print(a,"Is even and negative")
elif a == 0:
    print(a,"Is even and nonnegative")
elif a > 0 and a%2 != 0:
    print(a,"Is odd and positive ")
elif a > 0 and a%2 == 0:
    print(a,"Is even and positive")
elif a < 0 and a%2 != 0:
    print(a,"Is odd and negative") 
else:
    print("Error") 





