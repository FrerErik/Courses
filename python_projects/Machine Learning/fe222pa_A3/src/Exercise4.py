import matplotlib.pyplot as plt
import numpy as np
from numpy.random import default_rng
from sklearn import tree
import os


# Most info its located in lecture 9

# Goal: split data into training set and test set 5000 each and random
# need to make decision trees
# make 100 decision trees, train them with different subsets for each classifier
# combine info in a majority vote
# to create subsets use a the bootstrap technique (create new training sets for each individual decision tree this way: Sample 5000 training samples with replacement)


# Task 1 Print estimate of the generalization error using the test set
# Task 2 Print the average estimated generalization of the individual decision trees
# Task 3 make a decision boundary plot of all the models, including the ensemble model
# Task 4 ...


data = np.genfromtxt(os.getcwd() + "/dataset/bm.csv", delimiter=",")
training, test = data[:5000, :], data[5000:, :]


test_set_ensemble = tree.DecisionTreeClassifier()  # just used to create the tree
test_set_ensemble.fit(training[:, :2], training[:, 2])
rng = np.random.default_rng()

prediction_of_test = test_set_ensemble.predict(test[:, :2])

generalization_errors = np.sum(prediction_of_test != test[:, 2])
print(f"The generalization errors of test set are: {generalization_errors}")

y = []
n = 5000
r = np.zeros([n, 100], dtype=int)
XX = np.zeros([n, 2, 100])
for i in range(100):
    r[:, i] = rng.choice(n, size=n, replace=True)
    XX[:, :, i] = training[:, :2][r[:, i], :]
for group in range(100):
    l = []
    for k in XX[:, :, group]:
        for row in training:
            if k[0] == row[0] and k[1] == row[1]:
                l.append(row[2])
    y.append(l)


Forest = []
for i in range(100):
    Cltree = tree.DecisionTreeClassifier().fit(XX[:, :, i], y[i])
    Forest.append(Cltree)


error_count = []
for Cltree in Forest:
    error_count.append(np.sum(Cltree.predict(test[:, :2]) != test[:, 2]))
print(
    f"The average generalization error for the trees = {np.average(np.array(error_count))}")

x_min, x_max = np.min(data[:, 0]) - 0.1, np.max(data[:, 0]) + 0.1
y_min, y_max = np.min(data[:, 1]) - 0.1, np.max(data[:, 1]) + 0.1
x, y = np.meshgrid(np.arange(x_min, x_max, 0.01),
                   np.arange(y_min, y_max, 0.01))


for Cltree in Forest:
    predicted_mesh = Cltree.predict(np.c_[x.ravel(), y.ravel()])
    predicted_mesh = predicted_mesh.reshape(x.shape)
    ax = plt.subplot(10, 10, (Forest.index(Cltree) + 1))
    ax.set_xticks([])
    ax.set_yticks([])
    plt.contourf(x, y, predicted_mesh, alpha=0.4)

plt.xlim(x.min(), x.max())
plt.ylim(y.min(), y.max())
plt.tight_layout()
plt.show()

test_set_ensemble = tree.DecisionTreeClassifier()
test_set_ensemble.fit(training[:, :2], training[:, 2])
mesh = test_set_ensemble.predict(
    np.c_[x.ravel(), y.ravel()]).reshape(x.shape)
plt.contourf(x, y, mesh, alpha=0.4)
plt.show()
