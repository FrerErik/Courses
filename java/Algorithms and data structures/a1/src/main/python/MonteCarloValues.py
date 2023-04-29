import statistics as st
import pandas as pd

csvFiles = ["percolationRateRawDataN10.csv", "percolationRateRawData.csv"]

df = pd.read_csv(csvFiles[1])

df = df = df.apply(pd.to_numeric, errors='coerce')
df = df.dropna()

average_of_percolationValues = []
average_of_stdev = []

for i in range(0, len(df), 10):
    df_n = df.loc[i: i + 9]
    df_n["NumberOfOpenComponents"] = df_n["NumberOfOpenComponents"].div(
        df_n["Total"])
    df_n["Percolation Rate"] = 0
    average_of_percolationValues.append(df_n["NumberOfOpenComponents"].mean())
    average_of_stdev.append(df_n["NumberOfOpenComponents"].std())


print("less specific but overall oriented test")

print(
    f"average overall: {st.mean(average_of_percolationValues)} and average of stdev: {st.mean(average_of_stdev)}")
df = pd.read_csv(csvFiles[0])
df = df = df.apply(pd.to_numeric, errors='coerce')
df = df.dropna()

df["NumberOfOpenComponents"] = df["NumberOfOpenComponents"].div(
    df["Total"])
df["Percolation Rate"] = 0

a = df["NumberOfOpenComponents"].mean()
b = df["NumberOfOpenComponents"].std()
print("hyper specific test")

print(f"average of n = 10: {a}")

print(f"standard deviation of n = 10 is: {b}")
