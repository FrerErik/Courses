
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sb
import os
from mlxtend.data import loadlocal_mnist
from sklearn import svm
from sklearn.metrics import accuracy_score, confusion_matrix
from sklearn.model_selection import GridSearchCV


def oneVsAllClassifierFit(X, y, cl2):
    cl1 = []
    loopNumber = 0
    for n in np.unique(y):
        tempY = np.isin(y, n).astype(int)
        cl3 = cl2[loopNumber].fit(X, tempY)
        cl1.append(cl3)
        loopNumber += 1
    return cl1


def PredictOVAC(X, classifications):
    Predictions = []

    for i in range(X.shape[0]):
        l = list()
        for c in classifications:
            k = c.predict_proba(np.array([X[i]]))
            l.append(k[0][1])
        l = np.array(l)
        Predictions.append(np.argmax(l))
    return Predictions


train_X, train_y = loadlocal_mnist(images_path=os.getcwd() +
                                   "/dataset/train-images.idx3-ubyte",
                                   labels_path=os.getcwd() +
                                   "/dataset/train-labels.idx1-ubyte")
test_X, test_y = loadlocal_mnist(images_path=os.getcwd() +
                                 "/dataset/t10k-images.idx3-ubyte",
                                 labels_path=os.getcwd() + "/dataset/t10k-labels.idx1-ubyte")

categories = []
number = []

r = np.random.permutation(len(train_y))
X, y = train_X[r, :], train_y[r]
X_train, y_train = X[:10000, :], y[:10000]

r = np.random.permutation(len(test_y))
X, y = test_X[r, :], test_y[r]
X_test, y_test = X[:1000, :], y[:1000]

categories = []
number = []

for num in np.unique(y_train):
    indexes = np.where(y_train == num)[0]
    categories.append(num)
    number.append(len(indexes))

ax = sb.barplot(x=categories, y=number)
plt.title("Digits distribution (Training set)", fontsize=16)
plt.ylabel('Number of digits', fontsize=12)
plt.xlabel('Digit', fontsize=12)
plt.show()

categories = []
number = []

for num in np.unique(y_test):
    indexes = np.where(y_test == num)[0]
    categories.append(num)
    number.append(len(indexes))

ax = sb.barplot(x=categories, y=number)
plt.title("Digits distribution (Test set)", fontsize=16)
plt.ylabel('Number of digits', fontsize=12)
plt.xlabel('Digit ', fontsize=12)
plt.show()


# clf = svm.SVC(kernel='rbf', gamma='scale')
# parameters = [{'C': [0.1, 0.5, 1, 3, 5, 8, 9, 10, 12, 15]}]

# grid_search = GridSearchCV(estimator=clf,
#                            param_grid=parameters,
#                            cv=5,
#                            n_jobs=-1)
# grid_search = grid_search.fit(X_train, y_train)
# best_score = grid_search.best_score_
# best_parameters = grid_search.best_params_

# print(f"Best Score: {best_score} \n best parameters: {best_parameters}")

clf = svm.SVC(kernel='rbf', C=8, gamma='scale')
clf.fit(X_train, y_train)
y_pred = clf.predict(X_test)
print('accuracy score: %0.3f' % accuracy_score(y_test, y_pred))

categories = np.array(categories)
labeling = np.char.mod('%d', categories)
cm = confusion_matrix(y_test, y_pred, labels=categories)


fig, ax = plt.subplots(figsize=(10, 7))
sb.heatmap(cm, annot=True, ax=ax, cmap='Blues', fmt="d",
           linewidths=.5)
sb.set(font_scale=1.4)


ax.set_title('Confusion Matrix (One Vs One)')
ax.set_xlabel('Predicted labels', )
ax.set_ylabel('True labels')

ax.xaxis.set_ticklabels(labeling)
ax.yaxis.set_ticklabels(labeling)
plt.show()


classifiers = []
for num in np.unique(y_train):
    svc = svm.SVC(kernel='rbf', C=8, gamma='scale', probability=True)
    classifiers.append(svc)

classifier = oneVsAllClassifierFit(X_train, y_train, classifiers)
y_pred = PredictOVAC(X_test, classifier)
print('accuracy score: %0.3f' % accuracy_score(y_test, y_pred))

categories = np.array(categories)
labeling = np.char.mod('%d', categories)
cm = confusion_matrix(y_test, y_pred, labels=categories)


fig, ax = plt.subplots(figsize=(10, 7))
sb.heatmap(cm, annot=True, ax=ax, cmap='Blues', fmt="d",
           linewidths=.5)
sb.set(font_scale=1.4)

ax.set_xlabel('Predicted labels', )
ax.set_ylabel('True labels')
ax.set_title('Confusion Matrix (One Vs All)')
ax.xaxis.set_ticklabels(labeling)
ax.yaxis.set_ticklabels(labeling)
plt.show()

X_test[0].shape
