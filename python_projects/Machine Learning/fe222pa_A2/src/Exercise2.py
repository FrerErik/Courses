import numpy as np
import matplotlib.pyplot as plt
import os

# Function that plots and fits data to a desired polynomial of selected degree

# Task 1 Plot the data in the matrix housing_price_index
# Task 2 Fit polynomial models f(X) = β0 + β1X + β2X2 + ... + βdXd, then print which one is best fit
# Task 3 Calculate selling price for Jonas house in 2022 using "best fit model".


def polyFit(X, beta, degree):
    poly = beta[0] + X*beta[1]
    for i in range(degree + 1):
        if (i <= 1):
            pass
        else:
            poly += pow(X, i)*beta[i]
    plt.plot(X, poly, 'r')


def getPoly(X, beta, degree):
    poly = beta[0] + X*beta[1]
    for i in range(degree + 1):
        if (i <= 1):
            pass
        else:
            poly += pow(X, i)*beta[i]
    return poly


def normalizeData(data, x):
    mean = np.mean(x)
    stdev = np.std(x)
    normal = (data - mean)/stdev
    return normal


housing_price_index = np.genfromtxt(
    os.getcwd() + '/A2_datasets_2022/housing_price_index.csv', delimiter=',')


plt.figure(1)
plt.plot(housing_price_index[:, 0],
         housing_price_index[:, 1], 'bo', markersize=2)
plt.show()

X = housing_price_index[:, [0]]
y = housing_price_index[:, 1]


Xe = np.c_[np.ones((len(X), 1)), X[:, 0], pow(
    X[:, 0], 2), pow(X[:, 0], 3), pow(X[:, 0], 4)]
beta = np.linalg.inv(Xe.T.dot(Xe)).dot(Xe.T).dot(y)


for i in range(5):
    if i == 0:
        pass
    else:
        plt.subplot(2, 2, i)
        plt.title(f"Degree {i}")
        plt.plot(housing_price_index[:, 0],
                 housing_price_index[:, 1], 'bo', markersize=2)
        polyFit(X, beta, i)

plt.show()

print("I think the fourth degree is the most accurate polynomial, also it does not seem to try to overfit the results so also it can be considered reasonable in that regard")

index2022 = 2022 - 1975
index2015 = 2015 - 1975

indexPrice2015 = (beta[0] + beta[1]*index2015 + beta[2]
                  * (index2015**2) + beta[3]*(index2015**3) + beta[4]*(index2015**4))

indexPrice2022 = (beta[0] + beta[1]*index2022 + beta[2]
                  * (index2022**2) + beta[3]*(index2022**3) + beta[4]*(index2022**4))


housePrice = 2.3*10**6


print((housePrice) * indexPrice2022/indexPrice2015)
# Part 3

# find out when x is 2022 by using that we know the index value, then we compare to the index value in 2015
# by accounting the index of 2015 to 2022 we multiply the house price to it.
# Ultimately the price is around 3.2 * 10**6, I think that the answer is realistic only if we are completely sure that nothing would impact the housing prices
# Which is a risky assumption to make, so probably not however its the closest we can make for a prediction.
