
import mysql.connector
import csv
import os

#select "species.csv folder"
h = os.getcwd()

h += "/species.csv"

#select "planets.csv folder"
k = os.getcwd()

k += "/planets.csv"

#edit for your own server
db = mysql.connector.connect(host = "localhost", user = "root", passwd = "0000HerbertBAilbonds0987", database = "testdatabase")

mycursor = db.cursor()



# # Create Tables
# mycursor.execute('''CREATE TABLE species (name VARCHAR(50) PRIMARY KEY, classification VARCHAR(50), designation VARCHAR(50),
# average_height smallint UNSIGNED, skin_colors VARCHAR(50), hair_colors VARCHAR(50), eye_colors VARCHAR(50), average_lifespan VARCHAR(50),
# language VARCHAR(50), homeworld VARCHAR(50))''')


# mycursor.execute('''CREATE TABLE planets ( name VARCHAR(50) PRIMARY KEY, rotation_period VARCHAR(50), orbital_period VARCHAR(50),
# diameter VARCHAR(50), climate VARCHAR(50), gravity VARCHAR(50), terrain VARCHAR(50), surface_water VARCHAR(50), 
# population VARCHAR(50))''')


# species table insert data

# sql_statement ='''INSERT INTO species (name, classification, designation, average_height, skin_colors,
# hair_colors, eye_colors, average_lifespan, language, homeworld) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'''

# with open(h) as csvfile:
#     reader = csv.DictReader(csvfile, delimiter = ",")
#     for row in reader:
#         if row["average_height"] == "NA":
#             row["average_height"] = None
#         mycursor.executemany(sql_statement, [(row["name"], row["classification"], row["designation"], row["average_height"],
#         row["skin_colors"], row["hair_colors"], row["eye_colors"], row["average_lifespan"], row["language"], row["homeworld"])])



# planets table insert data

# sql_statement1 = ''' INSERT INTO planets (name, rotation_period, orbital_period, diameter, climate, gravity, 
# terrain, surface_water, population) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s) '''

# with open(k) as csvfile:
#     reader = csv.DictReader(csvfile, delimiter = ",")
#     for row in reader:
#         mycursor.executemany(sql_statement1, [(row["name"], row["rotation_period"], row["orbital_period"], 
#         row["diameter"], row["climate"], row["gravity"], row["terrain"], row["surface_water"], row["population"])])



# use if a table is bad 
# mycursor.execute("DROP TABLE species")

db.commit()

# check contents of tables
mycursor.execute("SELECT name FROM planets")

k = []

for x in mycursor:
    k.append(x) 


mycursor.execute("SHOW TABLES") 
print(mycursor.fetchall())

print(k)