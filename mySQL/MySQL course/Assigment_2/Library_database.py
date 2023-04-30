from enum import Flag
import os
import csv
from re import T
import mysql.connector
from mysql.connector import errorcode
from mysql.connector.connection import MySQLConnection

srv = mysql.connector.connect(host='localhost',
                              port='3306',
                              user='root',
                              passwd='root',
                              )
# Database name

DB_NAME = "Library"

cursor = srv.cursor()


def create_database(cursor, DB_NAME):
    try:
        cursor.execute(
            "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
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
with open('Books.csv', 'r') as csv_file:
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
                        VALUES (%s, %s, %s, %s, %s, %s, %s)", (id, Title, Author, Genre, SubGenre, Height, Publisher))
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Book_availability.csv to database, please put the .csv in the same directory of this .py file
with open('Book_availability.csv', 'r') as csv_file2:
    csv_reader2 = csv.reader(csv_file2)
    next(csv_reader2)
    for row in csv_reader2:
        id = row[0]
        Availability = row[1]
        Lended_ID = row[2]
        try:
            cursor.execute('''INSERT INTO Book_availability (id, Availability, Lended_ID)
            VALUES (%s, %s, %s)''', (id, Availability, Lended_ID))
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# Read Lended_Person.csv to database, please put the .csv in the same directory of this .py file
with open('Lended_Person.csv', 'r') as csv_file3:
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
            VALUES (%s, %s, %s, %s, %s)''', (Lended_ID, First_name, Last_name, Time, picture))
            srv.commit()
        except mysql.connector.Error as err:
            srv.rollback()

# creating views
query = '''Create View Book_Storage as
    Select books.Title, book_availability.availability, book_availability.lended_ID
    From books
    Inner Join book_availability On books.id = book_availability.id'''
try:
    cursor.execute(query)
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
        pass
    else:
        print(err.msg)
srv.commit()
query = '''Create View Customers_data as
        Select book_storage.title, lended_person.First_name, lended_person.Last_name, lended_person.Picture
        from book_storage
        inner Join lended_person
        On book_storage.Lended_id = lended_person.Lended_id'''
try:
    cursor.execute(query)
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
        pass
    else:
        print(err.msg)
srv.commit()

query = '''Create View Deadlines as
        Select lended_person.Time, book_storage.Title from lended_person
        Inner Join book_storage
        On book_storage.Lended_ID = lended_person.Lended_ID'''
try:
    cursor.execute(query)
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
        pass
    else:
        print(err.msg)

srv.commit()


# Main menu
flag = True
while (flag):
    print("press (1) Check how many books a library has for a certain genre")
    print("press (2) Check if a desired book is available")
    print("press (3) Check when a book should be returned")
    print("press (4) Check which customer currently holds a book")
    print("press (5) Check amount of deliveries will happen in a day")
    print("press (6) to Show all unavailable books")
    print("press (7) to leave")

    Selection = int(input("Enter Selection: "))
    if Selection == 1:
        Genre = input("Input a genre: ")  # science as an example
        query = f'''Select count(Title) as Amount_of_Books_per_Genre, Genre 
        From Books 
        where Genre = "{Genre}"
        Group by Genre '''
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("invalid Input or library lacks that Genre")
        else:
            tuple = data[0]
            amount = tuple[0]
            print(f"amount of books for genre {Genre} is: {amount}")
        print("")
    elif Selection == 2:
        book_Title = input("Type book name: ")  # manasa is an example
        query = f'''Select * from book_storage 
        Where Title = "{book_Title}"'''
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("invalid input or book does not exist")
        else:
            tuple = data[0]
            available = tuple[1]
            print(f"Book {book_Title} availability: {available}")
        print("")
    elif Selection == 3:

        book_Title = input("Type book name: ")  # manasa is an example
        query = f'''Select * from deadlines 
        Where Title = "{book_Title}"'''
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("Invalid input or book does not exist")
        else:
            tuple = data[0]
            date = tuple[0]
            print(f"The date to return book {book_Title} is: {date}")
        print("")
    elif Selection == 4:
        book_Title = input("Type book name: ")  # manasa is an example
        query = f'''Select * from Customers_data
        Where Title = "{book_Title}"'''
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("invalid input or book does not exist")
        else:
            tuple = data[0]
            firstName = tuple[1]
            lastName = tuple[2]
            picture = tuple[3]
            print(
                f'The book {book_Title} is currently lended to: {firstName} {lastName}')
            print(f'The customers ID is: {picture}')
        print("")
    elif Selection == 5:  # example date would be 3/25/2021
        date = input("Input date (format MM-DD-YYYY): ")
        query = f'''select count(Lended_id) as 
        Total_deliveries_in_date, Time
        From lended_person 
        Where Time = "{date}"
        group by Time'''
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("no deliveries in that date or invalid Input")
        else:
            tuple = data[0]
            amount = tuple[0]
            print(f"Amount of deliveries for date {date} is: {amount}")
            print("")
            query = f'''Select Title from deadlines where Time = "{date}"'''
            cursor.execute(query)
            data = cursor.fetchall()
            print("The books are: ")
            for i in range(len(data)):
                print(data[i])

    elif Selection == 6:
        query = "Select Title from book_storage where availability = 'No'"
        cursor.execute(query)
        data = cursor.fetchall()
        if data == []:
            print("all books are available")
        else:
            print("Books currently un available:")
            for i in range(len(data)):
                print(data[i])
    elif Selection == 7:
        print("Thanks for using this program")
        flag = False
    srv.commit()


cursor.close()
srv.close()
