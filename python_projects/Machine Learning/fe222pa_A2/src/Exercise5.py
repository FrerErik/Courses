from matplotlib.colors import ListedColormap
from sklearn.model_selection import cross_val_predict
import os
from sklearn.linear_model import LogisticRegression
import numpy as np
from matplotlib import pyplot as plt

# Task 1 Use Logistic regression and mapFeatures from the previous exercise to construct nine different classifiers
# Task 2 Redo 1 now use the regularization parameter C = 1. What is different than from the step in 1
# Task 3 you should use cross-validation (in sklearn) to see which of the regularized and unregularized models performs best


def mapFeatures(X1, X2, D, Ones=True):  # Pyton
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


def logisticRegSklearn(Xe, y, C, tol=1e-6, max_iter=10000):
    logreg = LogisticRegression(
        solver='lbfgs', C=C, tol=tol, max_iter=max_iter)
    logreg.fit(Xe, y)
    y_pred = logreg.predict(Xe)
    errors = np.sum(y_pred != y)
    return logreg, errors


def computeMesh(X, Y, h):
    x_min, x_max = X.min()-0.2, X.max()+0.2
    y_min, y_max = Y.min()-0.2, Y.max()+0.2
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))
    x1, x2 = xx.ravel(), yy.ravel()

    return (x1, x2, xx, yy)


Set = np.genfromtxt(
    os.getcwd() + "/A2_datasets_2022/microchips.csv", delimiter=",")

X = Set[:, :-1]
y = Set[:, -1]

cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA'])
cmap_bold = ListedColormap(['#FF0000', '#00FF00'])


validationErrorsNonReg = []
C = 10000
k = 10
i = 1
fig1, ax1 = plt.subplots(nrows=3, ncols=3)
for row in ax1:
    for col in row:
        Xe = mapFeatures(X[:, 0], X[:, 1], i, Ones=False)
        (clf, error) = logisticRegSklearn(Xe, y, C)

        prediction = cross_val_predict(clf, Xe, y, cv=k)
        errors = np.sum(prediction != y)
        validationErrorsNonReg.append(errors)

        x1, x2, xx, yy = computeMesh(X[:, 0], X[:, 1], 0.01)
        XXe = mapFeatures(x1, x2, i, Ones=False)

        p = clf.predict(XXe)

        classes = p > 0.5
        clzmesh = classes.reshape(xx.shape)

        col.pcolormesh(xx, yy, clzmesh, cmap=cmap_light)
        col.scatter(X[:, 0], X[:, 1], c=y, cmap=cmap_bold)
        col.set_title(f'Tr-errors = {error}, Deg ={i}')
        i += 1
fig1.suptitle('Non Regularized logistic regression')
plt.tight_layout()
plt.show()

validationErrorsReg = []


i = 1
C = 1
fig2, ax2 = plt.subplots(nrows=3, ncols=3)
for row in ax2:
    for col in row:
        Xe = mapFeatures(X[:, 0], X[:, 1], i, Ones=False)
        (clf, error) = logisticRegSklearn(Xe, y, C)

        prediction = cross_val_predict(clf, Xe, y, cv=k)
        errors = np.sum(prediction != y)
        validationErrorsReg.append(errors)

        x1, x2, xx, yy = computeMesh(X[:, 0], X[:, 1], 0.01)
        XXe = mapFeatures(x1, x2, i, Ones=False)

        p = clf.predict(XXe)

        classes = p > 0.5
        clzmesh = classes.reshape(xx.shape)

        col.pcolormesh(xx, yy, clzmesh, cmap=cmap_light)
        col.scatter(X[:, 0], X[:, 1], c=y, cmap=cmap_bold)
        col.set_title(f'Tr-errors = {error}, Deg ={i}')
        i += 1
fig2.suptitle('Regularized logistic regression')
plt.tight_layout()
plt.show()

# What is different: There are less training errors in the regularized chart compared to the unregularized one.

# Part 3
fig, fig3 = plt.subplots()
fig3.set_xlabel('Degrees')

fig3.set_ylabel('Validation errors')

fig3.plot(list(range(1, 10)), validationErrorsReg, 'r', label='Regularized')
fig3.plot(list(range(1, 10)), validationErrorsNonReg,
          '-', label='Non Regularized')

fig3.legend()
fig.suptitle(f'CrossValidation k = {k}')
plt.show()
