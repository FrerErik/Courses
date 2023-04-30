import os
import csv


#make lists into csv friendly

k = []
l = []

with open ('Book_availability.csv','r') as csv_file2:
    csv_reader2 = csv.reader(csv_file2)
    next(csv_reader2)
    for row in csv_reader2:
        id = row[0]
        k.append(id)

for i in range (1, 212):
    l.append(i)

pl = []
for i in k:
    pl.append(int(i))

print(pl)


for i in range(1, 212):
    print(i)
    if i in pl:
        l.remove(i)



print(l)

def extractDigits(lst): 
    return [[el] for el in lst] 


l = extractDigits(l)
print(l)

with open("a.csv", "w+", newline="") as o:
    writer = csv.writer(o)
    writer.writerows(l)