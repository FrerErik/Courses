from sklearn.linear_model import LinearRegression
from sklearn.model_selection import cross_val_predict
from matplotlib import pyplot as plt
import os
import numpy as np


# Task 1 implement forward selection algorithm, use tMSE to find the best model.
# Task 2 Apply your forward selection on the GPUbenchmark.csv

Set = np.genfromtxt(
    os.getcwd() + "/A2_datasets_2022/GPUbenchmark.csv", delimiter=',')

X = Set[:, :-1]
y = Set[:, -1]


models = np.ones((X.shape[0], 1))
traningErr = []
order = []
selection = [0, 1, 2, 3, 4, 5]
l = X.shape[1]
for i in range(l):
    mse = np.array([])
    X_test = np.copy(models)
    for t in selection:
        newSelection = np.copy(X[:, t])
        X_test = np.c_[X_test, newSelection]
        reg = LinearRegression(fit_intercept=False,
                               ).fit(X_test, y)
        y_pred = reg.predict(X_test)
        X_test = np.delete(X_test, [X_test.shape[1]-1], 1)
        sum = (y_pred - y) ** 2
        sum = (np.sum(sum)) / len(y_pred)
        mse = np.append(mse, sum)
    argmin = mse.argmin()
    models = np.c_[models, X[:, selection[argmin]]]
    traningErr.append(mse[argmin])
    order.append(selection[argmin])

    selection = np.delete(selection, [argmin], 0)


validationErrors = []
for i in range(l):
    X_t = models[:, :i+2]
    Lreg = LinearRegression(fit_intercept=False)
    prediction = cross_val_predict(Lreg, X_t, y, cv=3)
    sum = (prediction - y) ** 2
    sum = (np.sum(sum)) / len(prediction)
    validationErrors.append(sum)

validationErrors = np.array(validationErrors)
min = validationErrors.argmin()


fig, fig1 = plt.subplots()
fig1.axvline(x=list(range(1, 7))[min], linestyle='--', label='best fit')

fig1.plot(list(range(1, 7)), traningErr, 'r', label='training sample')

fig1.plot(list(range(1, 7)), validationErrors, 'b', label='Set sample')

fig1.set_xlabel('Model Complexity)')
fig1.set_ylabel('MSE')
fig1.legend()
plt.show()

string = 'Part 2\nBest model is Y = B0 + '
for k in range(min):
    if k == (min - 1):
        string += f"B{k+1}*X{order[k]+1}"
    else:
        string += f"B{k+1}*X{order[k]+1} + "


print(string)
print(f"The most Important feature is X{order[0]+1}")
