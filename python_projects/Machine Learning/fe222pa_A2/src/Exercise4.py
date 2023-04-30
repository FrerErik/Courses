import os
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap
from sklearn.linear_model import LogisticRegression


# Task1 Plot the data in X and y
# Task2 Use gradient descent to find β, Print the hyper parameters α and Niterations, and produce a 1×2 plot with: 1) the cost function
# J(β) as a function over iterations, 2) the corresponding decision boundary(together with
# the X, y scatter plot), and 3) the number of training errors presented as a part of the decision boundary plot title.
# Task3 implment "mapfeatures" from the slides
# Task4  Use mapFeatures to repeat 2) but with a polynomial of degree five (d = 5) model

def sigmoid(x):
    return (np.e ** x) / ((np.e ** x) + 1)


def cost(n, y, x, beta):
    return (-1/n) * (np.dot(y.T, np.log(sigmoid(np.dot(x, beta))))+np.dot((1-y).T, np.log(1-sigmoid(np.dot(x, beta)))))


def gradientDescent(alpha, iteration, X, y):
    beta = np.array([0, 0, 0, 0, 0, 0])
    n = len(X)
    gradientDes = []
    for i in range(iteration):
        beta = beta - (np.dot(((alpha/n) * X.T),
                       ((sigmoid(np.dot(X, beta))) - y)))
        new_cost = cost(n, y, X, beta)
        gradientDes.append([i, new_cost])
    return gradientDes, beta


def mapFeatures(X1, X2, D, Ones):  # Pyton
    one = np.ones([len(X1), 1])
    if Ones:
        Xe = np.c_[one, X1, X2]  # Start with [1,X1,X2]
    else:
        Xe = np.c_[X1, X2]
    for i in range(2, D+1):
        for j in range(0, i+1):
            Xnew = X1**(i-j)*X2**j  # type (N)
            Xnew = Xnew.reshape(-1, 1)  # type (N,1) required by append
            Xe = np.append(Xe, Xnew, 1)  # axis = 1 ==> append column
    return Xe


def logreg_plot(X, y, i):
    h = .05
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))
    x1, x2 = xx.ravel(), yy.ravel()

    xy_mesh = mapFeatures(x1, x2, i, False)
    classes = logreg.predict(xy_mesh)
    clz_mesh = classes.reshape(xx.shape)

    cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF'])
    cmap_bold = ListedColormap(['#FF0000', '#00FF00', '#0000FF'])
    plt.pcolormesh(xx, yy, clz_mesh, cmap=cmap_light)
    plt.scatter(X[:, 0], X[:, 1], c=y, marker='.', cmap=cmap_bold)


# Part 1
Set = np.genfromtxt(
    os.getcwd() + '/A2_datasets_2022/microchips.csv', delimiter=',')

y = Set[:, 2]
X = Set[:, [0, 1]]

ok = Set[Set[:, 2] == 1]
fail = Set[Set[:, 2] == 0]

plt.figure(1)
plt.plot(ok[:, 0], ok[:, 1], 'go', markersize=3)
plt.plot(fail[:, 0], fail[:, 1], 'ro', markersize=3)
plt.show()

# Part 2
n = len(Set)
Xe = np.c_[np.ones((n, 1)), X[:, 0], X[:, 1], pow(
    X[:, 0], 2), X[:, 0]*X[:, 1], pow(X[:, 1], 2)]

alpha = 0.5
iterations = 10000
print(f'Hyper parameters Part 2:\n α = {alpha} , N = {iterations}')
gradientDes, beta = gradientDescent(alpha, iterations, Xe, y)
gradientDesarray = np.asarray(gradientDes)

plt.figure(2)
plt.subplot(1, 2, 1)
plt.xlabel('Iterations')
plt.ylabel('F(beta)')
plt.plot(gradientDesarray[:, 0], gradientDesarray[:, 1])

h = .01  # step size in the mesh
x_min, x_max = X[:, 0].min()-0.1, X[:, 0].max()+0.1
y_min, y_max = X[:, 1].min()-0.1, X[:, 1].max()+0.1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                     np.arange(y_min, y_max, h))  # Mesh Grid
x1, x2 = xx.ravel(), yy.ravel()  # Turn to two Nx1 arrays
XXe = mapFeatures(x1, x2, 2, True)  # Extend matrix for degree 2
p = sigmoid(np.dot(XXe, beta))  # classify mesh ==> probabilities
classes = p > 0.5  # round off probabilities
clz_mesh = classes.reshape(xx.shape)  # return to mesh format
cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF'])  # mesh plot
cmap_bold = ListedColormap(['#FF0000', '#00FF00', '#0000FF'])  # colors

plt.subplot(1, 2, 2)
plt.pcolormesh(xx, yy, clz_mesh, cmap=cmap_light)
plt.scatter(X[:, 0], X[:, 1], c=y, marker='.', cmap=cmap_bold)
plt.show()

# Part 3
# No 1-column added!
Xe = np.c_[X[:, 0], X[:, 1], pow(X[:, 0], 2), X[:, 0]*X[:, 1], pow(X[:, 1], 2)]
logreg = LogisticRegression(
    solver='lbfgs', C=1000, tol=1e-6, max_iter=100000)   # instantiate the model
logreg.fit(Xe, y)  # fit the model with data


plt.figure(3)
logreg_plot(X, y, 2)
plt.title('sklearn microchips.csv')
plt.show()

# Part 5
plt.figure(4)
D = 5
plt.subplot(1, 2, 1)
plt.xlabel('Iterations')
plt.ylabel('F(beta)')
plt.plot(gradientDesarray[:, 0], gradientDesarray[:, 1])

plt.subplot(1, 2, 2)
Xe = mapFeatures(X[:, 0], X[:, 1], D, False)
logreg.fit(Xe, y)

y_pred = logreg.predict(Xe)
errors = np.sum(y_pred != y)


logreg_plot(X, y, D)
plt.title(f"D = {D}" + f", Tr. errors = {errors}")
plt.show()
