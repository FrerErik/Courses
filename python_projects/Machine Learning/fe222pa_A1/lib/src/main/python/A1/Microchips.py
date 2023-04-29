import numpy as np
import matplotlib.pyplot as plt
import operator
import os
from matplotlib.colors import ListedColormap

# reasons for the program not working properly would be because of path in line 85 being outdated


def getEucledianDistance(x1, y1, x2, y2):
    d = np.sqrt((pow(x1 - x2, 2) + pow(y1 - y2, 2)))
    return d


def getManhattanDistance(x1, y1, x2, y2):
    d = np.absolute(x1 - x2) + np.absolute(y1 - y2)
    return d


def getDistances(trainingSet, instanceSet):
    distances = {}
    for i in range(len(trainingSet)):
        d = getEucledianDistance(
            instanceSet[0], instanceSet[1], trainingSet[i][0], trainingSet[i][1])
        distances[i] = d

    distances = sorted(distances.items(), key=operator.itemgetter(1))
    return distances


def knnFunction(k, trainingSet, instanceSet):
    distances = getDistances(trainingSet, instanceSet)
    neighbours = []
    for i in range(k):
        neighbours.append(distances[i][0])

    ok = 0
    fail = 0
    for i in range(len(neighbours)):
        response = trainingSet[neighbours[i]][2]
        if (response == 1):
            ok = ok + 1
        if (response == 0):
            fail = fail + 1

    if (ok > fail):
        return True
    else:
        return False


def trainingError(k, trainingSet, y):
    y = np.asarray(y)
    errors = 0

    index = 0
    for x in trainingSet:
        distances = getDistances(trainingSet, x)

        neighbours = []
        for i in range(k):
            neighbours.append(distances[i][0])

        ok = 0
        fail = 0
        for i in range(len(neighbours)):
            response = trainingSet[neighbours[i]][2]
            if (response == 1):
                ok += 1
            if (response == 0):
                fail += 1

        if (ok > fail):
            prediction = 1
        else:
            prediction = 0

        if (y[index] != prediction):
            errors += 1

        index += 1

    return errors


microchipsPath = os.getcwd() + '/resources/microchips.csv'
microchips = np.genfromtxt(microchipsPath, delimiter=',')
predict = np.array([[-0.3, 1.0],  [-0.5, -0.1], [0.6, 0.0]])

ok = []
fail = []

for chip in microchips:
    if (chip[2] == 0):
        fail.append(chip)
    if (chip[2] == 1):
        ok.append(chip)

oArray = np.asarray(ok)
fArray = np.asarray(fail)
# 1. plotting original data
plt.figure(1)
plt.title("Microchip Classification")
plt.plot(predict[:, 0], predict[:, 1], 'ko', markersize=3)
plt.plot(oArray[:, 0], oArray[:, 1], 'bo', markersize=2)
plt.plot(fArray[:, 0], fArray[:, 1], 'ro', markersize=2)


plt.show()

# part 2 predict
for k in [1, 3, 5, 7]:
    print(f'k = {k}')
    message = ''
    for i in range(len(predict)):
        result = knnFunction(k, microchips, predict[i])
        if (result):
            message = 'OK'
        else:
            message = 'Fail'
        print(f' chip {i + 1}: {predict[i]} == > {message}')


# part 3
mColors = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF'])
pColors = ListedColormap(['#FF0000', '#00FF00', '#0000FF'])


x_min, x_max = microchips[:, 0].min()-0.1, microchips[:, 0].max()+0.1
y_min, y_max = microchips[:, 1].min()-0.1, microchips[:, 1].max()+0.1
xx, yy = np.meshgrid(np.arange(x_min, x_max, .05),
                     np.arange(y_min, y_max, .05))
xy_mesh = np.c_[xx.ravel(), yy.ravel()]


plt.figure(2)
figureNumber = 0
for k in [1, 3, 5, 7]:
    figureNumber += 1
    plt.subplot(2, 2, figureNumber)
    errors = trainingError(k, microchips, microchips[:, 2])
    plt.title(f"k={k}, training errors = {errors}")

    classes = []
    for x in xy_mesh:
        result = knnFunction(k, microchips, x)
        classes.append([result])
    classes = np.asarray(classes)

    classes = classes.reshape(xx.shape)
    plt.pcolormesh(xx, yy, classes, cmap=mColors)
    plt.scatter(microchips[:, 0], microchips[:, 1],
                c=microchips[:, 2], marker='.', cmap=pColors)


plt.show()
