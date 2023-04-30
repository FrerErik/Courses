import numpy as np
from sklearn.cluster import KMeans


def bkmeans(X, k, iter):
  # 1 start with single cluster
    totalLabels = np.zeros(X.shape[0], dtype='int64')

    clusters = 0
    largestCluster = X
    j = np.where(totalLabels == 0)
    clusterNum = 0
    while clusters < k-1:
        clusters += 1
        # divide largest cluster into two smaller sub-clusters
        kMeans = KMeans(n_clusters=2, n_init=iter).fit(largestCluster)
        labels = kMeans.labels_

        postJ = np.where(labels == 1)
        backJ = np.where(labels == 0)
        labels[backJ] = clusterNum
        labels[postJ] = clusters
        totalLabels[j] = labels

        counts = np.bincount(totalLabels)
        mostCommon = np.argmax(counts)
        j = np.where(mostCommon == totalLabels)

        largestCluster = X[j]
        clusterNum = totalLabels[j][0]

    return totalLabels
