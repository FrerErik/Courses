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

DB_NAME = "Highschool_X_Students"

cursor = srv.cursor()



def create_database(cursor, DB_NAME):
    try:
        cursor.execute("CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
    except mysql.connector.Error as err:
        print("Failed creating database: {}".format(err))
        exit(1)



# Create table Second_Year for 'Second_Year.csv'
def create_table_First_Year(cursor):
    create_table = "CREATE TABLE First_year ( \
                    id SMALLINT NOT NULL, \
                    First_name varchar(25), \
                    Last_name varchar(25), \
                    Email varchar(50), \
                    Year varchar(50), \
                    Picture varchar(50), \
                    Attendance varchar(50), \
                    PRIMARY KEY (id) \
                   )ENGINE=InnoDB"
    try:
        print("Creating table First_year: ") 
        cursor.execute(create_table)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists! ")
        else:
            print(err.msg)
    else:
        print("OK")      


# Create table Second_Year for 'Second_Year.csv'
def create_table_Second_Year(cursor):
    create_table2 = "CREATE TABLE Second_Year ( \
                    id SMALLINT NOT NULL, \
                    First_name varchar(25), \
                    Last_name varchar(25), \
                    Email varchar(50), \
                    Year varchar(50), \
                    Picture varchar(50), \
                    Attendance varchar(50), \
                    PRIMARY KEY (id) \
                   )ENGINE=InnoDB"
    try: 
        print("Creating table Second_Year: ")
        cursor.execute(create_table2)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")

# Create a table for Third_Year for Third_Year.csv
def create_table_Third_Year(cursor):
    create_table3 = "CREATE TABLE Third_Year ( \
                    id SMALLINT NOT NULL, \
                    First_name varchar(25), \
                    Last_name varchar(25), \
                    Email varchar(50), \
                    Year varchar(50), \
                    Picture varchar(50), \
                    Attendance varchar(50), \
                    PRIMARY KEY (id) \
                   )ENGINE=InnoDB"
    try:
        print("Creating table Third_Year: ")
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
        cursor.execute('DROP TABLE IF EXISTS planets')
        create_table_First_Year(cursor)
        cursor.execute('DROP TABLE IF EXISTS species')
        create_table_Second_Year(cursor)
        cursor.execute('DROP TABLE IF EXISTS calcu')
        create_table_Third_Year(cursor)
    else:
        print(err)


# Read first_year.csv to the database, please put the .csv in the same directory of this .py file
with open ('First_Year.csv','r') as csv_file:
    csv_reader = csv.reader(csv_file)
    next(csv_reader)
    for row in csv_reader:
        id = row[0]
        firstname = row[1]
        lastname = row[2]
        email = row[3]
        year = row[4]
        picture = row[5]
        attendance = row[6]
        try:
            cursor.execute("INSERT INTO First_Year (id, First_name, Last_name, email, Year, picture, attendance)\
                        VALUES (%s, %s, %s, %s, %s, %s, %s)",(id, firstname, lastname, email, year, picture, attendance)) 
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Second_Year.csv to database, please put the .csv in the same directory of this .py file
with open ('Second_Year.csv','r') as csv_file2:
    csv_reader2 = csv.reader(csv_file2)
    next(csv_reader2)
    for row in csv_reader2:
        id = row[0]
        firstname = row[1]
        lastname = row[2]
        email = row[3]
        year = row[4]
        picture = row[5]
        attendance = row[6]
        try:
            cursor.execute('''INSERT INTO Second_Year (id, First_name, Last_name, email, Year, picture, attendance) 
            VALUES (%s, %s, %s, %s, %s, %s, %s)''',(id, firstname, lastname, email, year, picture, attendance)) 
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Third_Year.csv to database, please put the .csv in the same directory of this .py file
with open ('Third_Year.csv','r') as csv_file3:
    csv_reader3 = csv.reader(csv_file3)
    next(csv_reader3)
    for row in csv_reader3:
        id = row[0]
        firstname = row[1]
        lastname = row[2]
        email = row[3]
        year = row[4]
        picture = row[5]
        attendance = row[6]
        try:
            cursor.execute('''INSERT INTO Third_Year (id, First_name, Last_name, email, Year, picture, attendance) 
            VALUES (%s, %s, %s, %s, %s, %s, %s)''',(id, firstname, lastname, email, year, picture, attendance)) 
            srv.commit()
        except mysql.connector.Error as err:
            print(err)
            srv.rollback()




cursor.close()
srv.close()