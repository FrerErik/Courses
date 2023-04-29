
import os
import visualkeras
import numpy as np
from matplotlib import pyplot as plt
from numpy.random import default_rng
import tensorflow as tf
from tensorflow import keras
from sklearn.metrics import confusion_matrix
from random import choice
import seaborn as sb


# since I am using keras tensorflow your device would require to have the GPU Set up properly

trainDataset = np.loadtxt(
    os.getcwd() + "/dataset/fashion-mnist_train.csv", delimiter=",", skiprows=1)
testDataset = np.loadtxt(
    os.getcwd() + "/dataset/fashion-mnist_test.csv", delimiter=",",  skiprows=1)


labels = ["T-shirt", "Trouser", "Pullover", "Dress",
          "Coat", "Sandal", "Shirt", "Sneaker", "Bag", "Boot"]

Ytrain = trainDataset[:, 0]
Xtrain = trainDataset[:, 1:]


Ytest = testDataset[:, 0]
Xtest = testDataset[:, 1:]


rng = np.random.default_rng()

sequence = [i for i in range(len(Ytrain))]
fig1, ax1 = plt.subplots(nrows=4, ncols=4)
for row in ax1:
    for col in row:
        selection = rng.choice(sequence)

        label = labels[(Ytrain[selection]).astype(int)]

        pixels = Xtrain[selection].reshape(28, 28)

        col.title.set_text('{label}'.format(label=label))

        col.imshow(pixels, cmap='gray')
        col.set_axis_off()
plt.tight_layout()
plt.show()


model = keras.models.Sequential()
model.add(tf.keras.layers.Dense(256, input_shape=(
    Xtrain.shape[1],), activation='sigmoid'))
model.add(keras.layers.Flatten(input_shape=[28, 28]))
model.add(keras.layers.Dense(300, activation="relu"))
model.add(keras.layers.Dense(100, activation="relu"))
model.add(keras.layers.Dense(10, activation="softmax"))

model.compile(loss="sparse_categorical_crossentropy",
              optimizer="sgd",
              metrics=["accuracy"])


history = model.fit(Xtrain, Ytrain, epochs=40,
                    validation_data=(Xtest, Ytest))

visualkeras.layered_view(model)

val_loss, val_acc = model.evaluate(Xtest, Ytest)

y_pred = (model.predict(Xtest, verbose=1) > 0.5)*1
y_pred = np.argmax(y_pred, axis=1)

cm = confusion_matrix(Ytest, y_pred)


fig, ax = plt.subplots(figsize=(12, 9))
sb.heatmap(cm, annot=True, ax=ax, cmap='Blues', fmt="d",
           linewidths=.5)
sb.set(font_scale=1.4)
ax.set_xlabel('Predicted labels', )
ax.set_ylabel('True labels')
ax.set_title('Confusion Matrix Fashion')
ax.xaxis.set_ticklabels(labels)
ax.yaxis.set_ticklabels(labels)
ax.xaxis.set_tick_params(labelsize=12)
ax.yaxis.set_tick_params(labelsize=11)
plt.tight_layout()

plt.show()
