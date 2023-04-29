from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import GridSearchCV
from sklearn import svm
from matplotlib.colors import ListedColormap
import os


def computeMesh(X, Y, h):
    x_min, x_max = X.min()-0.2, X.max()+0.2
    y_min, y_max = Y.min()-0.2, Y.max()+0.2
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))  # Mesh Grid
    x1, x2 = xx.ravel(), yy.ravel()

    return (x1, x2, xx, yy)


cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF', '#ffffb3'])
cmap_bold = ListedColormap(['#FF0000', '#00FF00', '#0000FF', '#e6e600'])

dataset = np.genfromtxt(
    os.getcwd() + '/dataset/mnistsub.csv', delimiter=',')
X = dataset[:, :-1]
y = dataset[:, -1]

# Splitting the dataset into the Training set and Test set
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=20)

# Using gridsearch to find the best hyperparameters
clf = svm.SVC()
polyParameters = [{'kernel': ['poly'], 'C': [0.1, 0.5, 1, 3, 5, 7, 8, 9],
                   'gamma': [0.5, 1, 10, 'auto', 'scale'],
                   'degree': [1, 2, 3]}]

linearParameters = [{'kernel': ['linear'], 'C': [0.1, 0.5, 1, 3, 5, 7, 8, 9]}]

rbfParameters = [{'kernel': ['rbf'], 'C': [0.1, 0.5, 1, 3, 5, 7, 8, 9],
                  'gamma': [0.1, 0.5, 1, 10, 'auto', 'scale']}]

# grid_search = GridSearchCV(estimator=clf,
#                            param_grid=linearParameters,
#                            cv=5,
#                            n_jobs=-1)
# grid_search = grid_search.fit(X_train, y_train)
# lBest_score = grid_search.best_score_
# lBest_parameters = grid_search.best_params_


# print(f"Best Linear score: {lBest_score} Best parameters: {lBest_parameters}.")

# grid_search = GridSearchCV(estimator=clf,
#                            param_grid=rbfParameters,
#                            cv=5,
#                            n_jobs=-1)
# grid_search = grid_search.fit(X_train, y_train)
# rBest_score = grid_search.best_score_
# rBest_parameters = grid_search.best_params_


# print(f"Best rbf score: {rBest_score} Best parameters: {rBest_parameters}.")

# grid_search = GridSearchCV(estimator=clf,
#                            param_grid=polyParameters,
#                            cv=5,
#                            n_jobs=-1)
# grid_search = grid_search.fit(X_train, y_train)
# pBest_score = grid_search.best_score_
# pBbest_parameters = grid_search.best_params_

# print(f"Best poly score: {pBest_score} Best parameters: {pBbest_parameters}.")

# best params are listed in the pdf

clf = svm.SVC(kernel='linear', C=7)
clf.fit(X_train, y_train)

x1, x2, xx, yy = computeMesh(X_train[:, 0], X_train[:, 1], 0.02)
xy_mesh = np.c_[x1, x2]
clzmesh = clf.predict(xy_mesh)
clzmesh = clzmesh.reshape(xx.shape)
fig, ax = plt.subplots()
ax.pcolormesh(xx, yy, clzmesh, cmap=cmap_light, linewidth=10)
ax.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap_bold, s=15)

plt.show()

clf = svm.SVC(kernel='rbf', C=0.1, gamma=1)
clf.fit(X_train, y_train)


x1, x2, xx, yy = computeMesh(X_train[:, 0], X_train[:, 1], 0.02)
xy_mesh = np.c_[x1, x2]
clzmesh = clf.predict(xy_mesh)
clzmesh = clzmesh.reshape(xx.shape)
fig, ax = plt.subplots()
ax.pcolormesh(xx, yy, clzmesh, cmap=cmap_light, linewidth=10)
ax.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap_bold, s=15)


plt.show()

clf = svm.SVC(kernel='poly', C=0.5, degree=1, gamma=10)
clf.fit(X_train, y_train)

x1, x2, xx, yy = computeMesh(X_train[:, 0], X_train[:, 1], 0.02)
xy_mesh = np.c_[x1, x2]
clzmesh = clf.predict(xy_mesh)
clzmesh = clzmesh.reshape(xx.shape)

fig, ax = plt.subplots()
ax.pcolormesh(xx, yy, clzmesh, cmap=cmap_light, linewidth=10)
ax.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap_bold, s=15)

plt.show()
