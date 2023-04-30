#  for any given positive number N  prints the number of zeros, odd digits, and even digits of the integer.

text =input("Enter a large positive integer: " "")

ev, on, z = 0, 0, 0

for n in text:
    if n == "1" or n == "3" or n == "5" or n == "7" or n == "9":
        on += 1
    elif n == "2" or n == "4" or n == "6" or n == "8":
        ev += 1
    elif n == "0":
        z += 1
print("\nNumber of even numbers: ", ev)
print("Number of odd numbers: ", on)
print("Number of zeroes: ", z)
