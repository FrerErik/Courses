
import numpy as np
import matplotlib.pyplot as plt
import os

# Task 1 Normalize X (X are the first six rows)

# Task 2 use Subplot(2, 1, i) to fit six plots into a single figure

# Task 3 Compute beta (β = (XTe Xe)**-1 (XTe y))

# Task 4 print J(b)

# Task 5 find and print hyperparameters (alpha, N) 1% of the final cost, find the predicted benchmark result


def normalizeData(data, x):
    mean = np.mean(x)
    stdev = np.std(x)
    normal = (data - mean)/stdev
    return normal


def calculateJ(Xe, y, beta):
    j = np.dot(Xe, beta)-y
    J = (j.T.dot(j))/n
    return J


def gradientDescent(Xe, y, alpha):
    beta = np.array([0, 0, 0, 0, 0, 0, 0])
    gradientDes = []
    iteration = 1
    while True:
        beta = beta - np.dot((alpha * Xe.T), ((np.dot(Xe, beta)) - y))
        newCost = calculateJ(Xe, y, beta)
        gradientDes.append([iteration, newCost])
        percentageDiff = np.round(cost/newCost * 100, 2)
        if percentageDiff == 99:
            break
        iteration += 1
    return gradientDes, beta, iteration


Gpus = np.genfromtxt(
    os.getcwd() + "/A2_datasets_2022/GPUbenchmark.csv", delimiter=",")

X = Gpus[:, [0, 1, 2, 3, 4, 5]]
y = Gpus[:, 6]

normalizedXList = []

for i in range(6):
    column = np.array(X[:, i])
    normalizedColumn = normalizeData(column, column)
    normalizedXList.append(normalizedColumn)

Xplot = np.c_[normalizedXList[0], normalizedXList[1], normalizedXList[2],
              normalizedXList[3], normalizedXList[4], normalizedXList[5]]

plt.figure(1)


for i in range(6):
    plt.subplot(2, 3, i+1)
    plt.plot(Xplot[:, i], y, 'bo', markersize=2)


Xe = np.c_[np.ones((len(X), 1)), Xplot]
beta = np.linalg.inv(Xe.T.dot(Xe)).dot(Xe.T).dot(y)
n = len(Gpus)
predictList = [2432, 1607, 1683, 8, 8, 256]

testList = [1]
for i in range(6):
    column = np.array(X[:, i])
    normalizedTest = normalizeData(predictList[i], column)
    testList.append(normalizedTest)

plt.show()
prediction = np.dot(beta, testList)
print(f"Prediction: {prediction}")


# Part 4
cost = calculateJ(Xe, y, beta)
print(f"Cost: {cost}")


# Part 5 gradient descent
gradientDes, beta, iterations = gradientDescent(Xe, y, 0.01)

print(f"It took {iterations} iterations to reach 1% of the original cost from starting from origin")
gradientDesArray = np.asarray(gradientDes)
plt.figure(2)
plt.plot(gradientDesArray[:, 0], gradientDesArray[:, 1])
plt.show()


yPredictionGrad = np.dot(beta, testList)
print(f"predicted benchmark result: {yPredictionGrad}")
