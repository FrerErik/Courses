#Write a program highlow.py, implementing the game High and Low. 
# The computer chooses a random integer between 1 and 100 and lets the user guess the value.
#  After each guess, the user is given a clue of the type higher or lower.

import random
a = random.randint(1,100)
n = 0
#print(a)
while n < 10:
    guess = int(input("Please add a number between 1 and 100: "))
    if guess > a:
        print("Lower")
    elif guess < a:
        print("Higher")
    elif guess == a:
        print("Congratulations correct answer after only",(n+1),"guesses- excellent" )
        n += 100
    n += 1

    if n == 10 :
            print("Sorry the number was",a,"more luck next time!")