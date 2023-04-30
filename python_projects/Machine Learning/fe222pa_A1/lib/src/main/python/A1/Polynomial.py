import numpy as np
import matplotlib.pyplot as plt
import operator
import os

# reasons for the program not working could be because that path in line 54 is outdated

# The reason I made many definitions its because all at once had perfomance issues, this way I managed cut off unnecesary operations or find out numpy operations
# plus makes my logic easier to follow


def getEuclideanDistanceForEveryX(x1, x2):
    d = np.sqrt(pow(x1 - x2, 2))
    return d


def getDistances(Set, xValue):
    distances = {}
    for i in range(len(Set)):
        distances[i] = getEuclideanDistanceForEveryX(xValue, Set[i][0])
    distances = sorted(distances.items(), key=operator.itemgetter(1))
    return distances


def knnFunction(Set, xValue, k):
    neighbours = []
    distances = getDistances(Set, xValue)
    for i in range(k):
        neighbours.append(distances[i][0])

    ySum = 0
    for i in range(len(neighbours)):
        ySum += Set[neighbours[i]][1]
    return ySum/k


def knnRegression(Set, xValues, k):
    regression = []
    for x in xValues:
        mean = knnFunction(Set, x, k)
        regression.append([x, mean])
    regression = np.asarray(regression)
    return regression


def knnPrediction(Set, k):
    prediction = []
    for x in Set:
        calculation = knnFunction(Set, x[0], k)
        prediction.append([x[0], calculation])
    prediction = np.asarray(prediction)
    return prediction


dataPath = os.getcwd() + "/lib/src/main/resources/polynomial200.csv"

data = np.genfromtxt(dataPath, delimiter=',')


trainingSet = np.array(data[:100])
testSet = np.array(data[100:])

plt.figure(1)
plt.subplot(1, 2, 1)
plt.title("Training set")
plt.plot(trainingSet[:, 0], trainingSet[:, 1], 'bo', markersize=3)
plt.subplot(1, 2, 2)
plt.title("Test set")
plt.plot(testSet[:, 0], testSet[:, 1], 'ro', markersize=3)

plt.show()

# take each point and compare to one another, and take MSE of k

plt.figure(2)
figureNumber = 0
for k in [1, 3, 5, 7, 9, 11]:
    figureNumber += 1
    xValues = np.arange(0, 50, 0.25)
    regression = knnRegression(trainingSet, xValues, k)
    trainingSetPredictions = knnPrediction(trainingSet, k)
    testSetPredictions = knnPrediction(testSet, k)
    MseTrainingSet = np.mean(
        pow(trainingSetPredictions[:, 1] - trainingSet[:, 1], 2))
    MseTestSet = np.mean(
        pow(testSetPredictions[:, 1] - testSet[:, 1], 2))
    plt.subplot(2, 3, figureNumber)
    plt.title(f"k = {k}" + f", MseTrainingSet={round(MseTrainingSet, 2)} ")
    plt.plot(trainingSet[:, 0], trainingSet[:, 1], 'ro', markersize='3')
    plt.plot(regression[:, 0], regression[:, 1])

    print("Mse error")
    print(f"k = {k}" + f", MseTestSet={round(MseTestSet, 2)} ")

plt.show()

# resoning for q5, since the original function is erratic by design, not all points can be reasonably considered, since the original function has random 0 to 5 if we
# create a range considering 5 to -5 then if most points lie inside the area of this range then it would be assumed that, that would be the most likely answer.
plt.figure(3)
figureNumber = 0
for k in [1, 3, 5, 7, 9, 11]:
    figureNumber += 1
    xValues = np.arange(0, 50, 0.25)
    regression = knnRegression(trainingSet, xValues, k)
    trainingSetPredictions = knnPrediction(testSet, k)
    MseTrainingSet = np.mean(
        pow(trainingSetPredictions[:, 1] - trainingSet[:, 1], 2))
    plt.subplot(2, 3, figureNumber)
    plt.title(f"k = {k}" + f", MseTrainingSet={round(MseTrainingSet, 2)} ")
    plt.plot(trainingSet[:, 0], trainingSet[:, 1], 'ro', markersize='3')
    plt.plot(regression[:, 0], regression[:, 1])
    plt.plot(regression[:, 0], regression[:, 1] - 5)
    plt.plot(regression[:, 0], regression[:, 1] + 5)

plt.show()
# by using the tables, k = 9 looks the most reasonable from there because it tries to include most of the outliers that even previous k values try to get
