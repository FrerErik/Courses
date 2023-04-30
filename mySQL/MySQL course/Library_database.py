from enum import Flag
import os
import csv
import mysql.connector
from mysql.connector import errorcode
from mysql.connector.connection import MySQLConnection

srv = mysql.connector.connect(host='localhost',
                              port='3306',
                              user='root',
                              passwd='0000HerbertBAilbonds0987',
                              )
# Database name

DB_NAME = "Library"

cursor = srv.cursor()



def create_database(cursor, DB_NAME):
    try:
        cursor.execute("CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
    except mysql.connector.Error as err:
        print("Failed creating database: {}".format(err))
        exit(1)



# Create table Books for 'Books.csv'
def create_table(cursor):
    create_table = "CREATE TABLE Books ( \
                    id SMALLINT NOT NULL, \
                    Title varchar(25), \
                    Author varchar(25), \
                    Genre varchar(50), \
                    SubGenre varchar(50), \
                    Height_in_cm smallint, \
                    Publisher varchar(50), \
                    PRIMARY KEY (id) \
                   )ENGINE=InnoDB"
    try:
        print("Creating table Books: ") 
        cursor.execute(create_table)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists")
        else:
            print(err.msg)
    else:
        print("OK")      


# Create table Book_availability for 'Book_availability.csv'
def create_table2(cursor):
    create_table2 = "CREATE TABLE Book_availability ( \
                    id SMALLINT NOT NULL, \
                    Availability varchar(25), \
                    Lended_ID varchar(25), \
                    PRIMARY KEY (id) \
                   )ENGINE=InnoDB"
    try: 
        print("Creating table Book_availability: ")
        cursor.execute(create_table2)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")

# Create a table for Lended_Person for Lended_Person.csv
def create_table3(cursor):
    create_table3 = "CREATE TABLE Lended_Person ( \
                    Lended_id varchar(50) NOT NULL, \
                    First_name varchar(25), \
                    Last_name varchar(25), \
                    Time varchar(25), \
                    Picture varchar(50), \
                    PRIMARY KEY (Lended_id) \
                   )ENGINE=InnoDB"
    try:
        print("Creating table Lended_Person: ")
        cursor.execute(create_table3)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")


try:
    cursor.execute("USE {}".format(DB_NAME)) 
except mysql.connector.Error as err:
    print("Database {} does not exist.".format(DB_NAME))
    if err.errno == errorcode.ER_BAD_DB_ERROR:
        # Create database
        create_database(cursor, DB_NAME)
        print("Database {} created successfully.".format(DB_NAME))
        srv.database = DB_NAME
        # Create three tables
        cursor.execute('DROP TABLE IF EXISTS Books')
        create_table(cursor)
        cursor.execute('DROP TABLE IF EXISTS Book_availability')
        create_table2(cursor)
        cursor.execute('DROP TABLE IF EXISTS Lended_Person')
        create_table3(cursor)
    else:
        print(err)


# Read Books.csv to the database, please put the .csv in the same directory of this .py file
with open ('Books.csv','r') as csv_file:
    csv_reader = csv.reader(csv_file)
    next(csv_reader)
    for row in csv_reader:
        id = row[0]
        Title = row[1]
        Author = row[2]
        Genre = row[3]
        SubGenre = row[4]
        Height = row[5]
        Publisher = row[6]
        if Author == "":
            Author = "NA"
        if Publisher == "":
            Publisher = "NA"
        try:
            cursor.execute("INSERT INTO Books (id, Title, Author, Genre, SubGenre, Height_in_cm, Publisher)\
                        VALUES (%s, %s, %s, %s, %s, %s, %s)",(id, Title, Author, Genre, SubGenre, Height, Publisher)) 
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Book_availability.csv to database, please put the .csv in the same directory of this .py file
with open ('Book_availability.csv','r') as csv_file2:
    csv_reader2 = csv.reader(csv_file2)
    next(csv_reader2)
    for row in csv_reader2:
        id = row[0]
        Availability = row[1]
        Lended_ID = row[2]
        try:
            cursor.execute('''INSERT INTO Book_availability (id, Availability, Lended_ID) 
            VALUES (%s, %s, %s)''',(id, Availability, Lended_ID)) 
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Lended_Person.csv to database, please put the .csv in the same directory of this .py file
with open ('Lended_Person.csv','r') as csv_file3:
    csv_reader3 = csv.reader(csv_file3)
    next(csv_reader3)
    for row in csv_reader3:
        Lended_ID = row[0]
        First_name = row[1]
        Last_name = row[2]
        Time = row[3]
        picture = row[4]
        try:
            cursor.execute('''INSERT INTO Lended_Person (Lended_id, First_name, Last_name, Time, picture) 
            VALUES (%s, %s, %s, %s, %s)''',(Lended_ID, First_name, Last_name, Time, picture)) 
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()




cursor.close()
srv.close()