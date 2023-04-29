import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns
import csv
from scipy.optimize import curve_fit

pd.options.display.max_rows = 9999

# plots csv, I used it to plot linearized versions of them


def expmod(x, a, b):
    return a*x**b


def plotCsv(csv, ifLinearized):
    test = pd.read_csv(csv)
    test = test.dropna()
    test = test.tail(17)
    if (ifLinearized):
        plt.plot((test["NumberOfUnions"]), np.log2(test["Time(s)"]))
        plt.title(
            f'Linearized graph of ' + str(csv))
        plt.xlabel('Union size')
        plt.ylabel('Time  (s)')
        plt.show()
    else:
        plt.plot((test["NumberOfUnions"]), (test["Time(s)"]))
        plt.title(
            f'graph of ' + str(csv))
        plt.xlabel('Union size')
        plt.ylabel('Time  (s)')
        plt.show()


# plotCsv("UFtest.csv", True)

def plotUnionFinds(csv):
    uf = pd.read_csv(csv)
    uf = uf.apply(pd.to_numeric, errors='coerce')

    ufs = []
    count = 0
    for i in range(0, len(uf)):
        uf_temp = uf.loc[count:i]
        uf_temp2 = uf.loc[i]
        if (np.isnan(uf_temp2["Size"])):
            count = i + 1
            ufs.append(uf_temp)

    val = 100000
    for uf_temp in ufs:
        uf_temp = uf_temp.dropna()
        (function, _) = curve_fit(
            expmod, uf_temp["NumberOfUnions"], uf_temp["Time(s)"], maxfev=100000)
        label = str(val) + " growth: a*x**b" + str(function)
        sns.lineplot(x="NumberOfUnions", y="Time(s)",
                     data=uf_temp, label=label)
        # plt.plot(uf_temp["NumberOfUnions"], expmod(
        #     uf_temp["NumberOfUnions"], *function), label=str(val) + str("Fitted Curve"))
        val += 18750

    plt.title(
        f'times it takes to perform Union() for diferent sizes in' + str(csv))
    plt.xlabel('Union size')
    plt.ylabel('Time  (s)')
    plt.show()


plotUnionFinds("UFtest.csv")


# case for first would be ~4.66x**3.11
# case for second would be ~4.66x**2.88


csvFiles = ["BruteForce3Sum3K.csv", "Better3Sum3K.csv"]


for csvFile in csvFiles:
    df = pd.read_csv(csvFile)
    df = df.apply(pd.to_numeric, errors='coerce')
    df = df.dropna()
    # if (csvFile == "BruteForce3Sum3K.csv"):
    #     df["Time(s)"] = df["Time(s)"].div(6)
    sns.lineplot(x="Size", y="Time(s)", data=df, label=f'{csvFile}')

plt.title(
    f'times it takes to obtain results  for size 3K in {csvFiles}')
plt.xlabel('Union size')
plt.ylabel('Connected time (s)')

plt.show()


for csvFile in csvFiles:
    with (open(csvFile)) as f:
        reader = csv.reader(f)
        reader = list(reader)
        headers = reader[0]

    df = pd.read_csv(csvFile)
    df = df.apply(pd.to_numeric, errors='coerce')
    df = df.dropna()
    (function, _) = curve_fit(
        expmod, df[headers[0]], df[headers[2]])
    sns.lineplot(x=headers[0], y=headers[2], data=df,
                 label="growth a*n**b = " + str(function))
    plt.title(f'time to obtain results in {csvFile}')
    plt.xlabel(headers[0])
    plt.ylabel(headers[2])
    plt.show()
    # I am not using it but to show I understand how its used:

    # case for first would be ~4.66x**3.11
    # case for second would be ~4.66x**2.88
