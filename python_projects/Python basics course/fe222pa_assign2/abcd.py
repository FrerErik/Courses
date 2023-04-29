#Use a quadruple nested loop and a function get_number(a, b, c, d) that converts digits a, b, c, d into a four digit integer abcd.
#There are four different digits A, B, C, and D such that he number DCBA is equal to 4 times the number ABCD
A = 1
B = 0
C = 0
D = 1

while A < 10:
    if B == 10: 
        B = 0
    while B < 10:
        if C == 10: 
            C = 0
        while C < 10:
            if D == 10: 
                D = 0
            while D < 10:
                if (D*1000 + C*100 + B*10 + A) == 4*(A*1000 + B*100 + C*10 + D):
                    print(A, B, C, D)
                D += 1
            C += 1
        B += 1
    A += 1
