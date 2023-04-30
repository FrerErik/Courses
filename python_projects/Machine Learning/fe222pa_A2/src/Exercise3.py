import os
import numpy as np
import matplotlib.pyplot as plt

# Task 1 Read data and shuffle rows
# Task 2 replace 2 and 4 to 1 and 0, then divide into data set and training set
# Task 3 normalize training data, then d train a linear logistic regression model using gradient descent, print α and Niter, plot cost function
# Task 4 What is the training error and training accuracy
# Task 5 What is the number of test error and the test accuracy for your model
# Task 6 different runs will provide different results, are they qualitatively the same? Do they depend on how many observations you put aside for testing? Is the
# difference between training and testing expected?


def normalize(x):
    mean = np.mean(x)
    stdev = np.std(x)
    normal = (x - mean)/stdev
    return normal


def sigmoid(x):
    return (np.e ** x) / ((np.e ** x) + 1)


def cost(n, y, x, beta):
    return (-1/n) * (np.dot(y.T, np.log(sigmoid(np.dot(x, beta))))+np.dot((1-y).T, np.log(1-sigmoid(np.dot(x, beta)))))


def gradientDescent(alpha, iteration, X, y):
    beta = np.array([0, 0, 0, 0, 0, 0, 0, 0, 0, 0])
    n = len(X)
    gradientDes = []
    for i in range(iteration):
        beta = beta - (np.dot(((alpha/n) * X.T),
                       ((sigmoid(np.dot(X, beta))) - y)))
        new_cost = cost(n, y, X, beta)
        gradientDes.append([i, new_cost])
    return gradientDes, beta


def PlotDataAndNormalize(Set, type):

    alpha = 0.01
    iterations = 5000
    n = len(Set)

    column0 = normalize(Set[:, 0])
    column1 = normalize(Set[:, 1])
    column2 = normalize(Set[:, 2])
    column3 = normalize(Set[:, 3])
    column4 = normalize(Set[:, 4])
    column5 = normalize(Set[:, 5])
    column6 = normalize(Set[:, 6])
    column7 = normalize(Set[:, 7])
    column8 = normalize(Set[:, 8])

    SetXe = np.c_[np.ones((n, 1)), column0, column1,
                  column2, column3, column4, column5, column6, column7, column8]

    print(f'α = {alpha},  N = {iterations}')
    gradientDes, beta = gradientDescent(alpha, iterations, SetXe, Set[:, 9])
    gradientDesarray = np.asarray(gradientDes)

    plt.figure(1)
    plt.xlabel('Iterations')
    plt.ylabel('J(beta)')

    if type:
        plt.plot(gradientDesarray[:, 0],
                 gradientDesarray[:, 1], label='Training data')
    else:
        plt.plot(gradientDesarray[:, 0],
                 gradientDesarray[:, 1], label='Test data')
    plt.legend()
    # Part 4
    p = sigmoid(np.dot(SetXe, beta))
    pred = np.round(p)
    dataError = np.round((np.mean(pred != Set[:, 9]) * 100), 2)
    dataAccuracy = 100 - dataError

    print("Number of non-correct classifications: " + str(
        int(np.around(len(Set[:, 9]) * np.mean(pred != Set[:, 9])))))
    print("Training accuracy: " + str(dataAccuracy) + "%")


# Part 1 shuffler
bC = np.genfromtxt(
    os.getcwd() + '/A2_datasets_2022/breast_cancer.csv', delimiter=',')
np.random.shuffle(bC)


for i in bC:
    if i[9] == 2:
        i[9] = 0
    elif i[9] == 4:
        i[9] = 1

# I allocated 25% of the data for training set and the rest for dataset, I chose that because a high dataset is required for accuracy
# Also I considered that having too low of a test set might exarcerbate outliers.
Set = np.array(bC[:512])
trainingSet = np.array(bC[512:])

print("Training data:")
PlotDataAndNormalize(Set, True)

print("\nTest data:")
PlotDataAndNormalize(trainingSet, False)

plt.show()

# Part 6
# After multiple tests most percentages fall between 95% - 99%
