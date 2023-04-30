import numpy as np
from sklearn.metrics.pairwise import euclidean_distances

# X must be normalized first


def sammon(X, iter, threshold, learning_rate):
    # 1. random y points
    Y = np.random.rand(np.shape(X)[0], 2)
    deltaInputSpace = euclidean_distances(X, X)

    for i in range(iter):
        OutputSpace = euclidean_distances(Y, Y)
        # 2. get sammon stress
        E = sammonStress(OutputSpace, deltaInputSpace)
        # 3. stop if E is lower than threshold and for will make sure to stop after iter
        if E < threshold:
            break
        Y = gradient(Y, X, learning_rate)
    return Y


def sammonStress(OutputSpace, deltaInputSpace):
    # gets stress
    c = np.sum(deltaInputSpace) / 2
    n = (OutputSpace - deltaInputSpace ** 2)
    d = deltaInputSpace.copy()
    d[d < 1e-4] = 1e-4

    dist = np.sum(n / d) / 2
    dist = dist / c

    return dist


def gradient(Y, X, learning_rate):
    rows = Y.shape[0]

    for i in range(rows):
        yi = np.reshape(Y[i], (1, 2))

        yj = np.delete(Y.copy(), i, axis=0)

        xi = np.reshape(X[i], (1, X.shape[1]))

        xj = np.delete(X.copy(), i, axis=0)

        outputSpace = np.reshape(euclidean_distances(yi, yj), (yj.shape[0], 1))
        inputSpace = np.reshape(euclidean_distances(xi, xj), (yj.shape[0], 1))

        n = inputSpace - outputSpace
        d = outputSpace * inputSpace
        d[d < 1e-4] = 1e-4

        div = int(xj.shape[0]/2)

        c = np.sum(euclidean_distances(xi, xj[:div]))

        yi_ij = yi - yj

        first_derivative = (-2 / c) * \
            np.sum((n / d) * yi_ij, axis=0)
        square = (yi_ij ** 2) / outputSpace
        last_part = (1 + (yi_ij / outputSpace))

        second_derivative = (-2 / c) * np.sum(1/d *
                                              (yi_ij - square * last_part), axis=0)

        gradient = first_derivative / abs(second_derivative)

        Y[i] = Y[i] - learning_rate * gradient
    return Y
