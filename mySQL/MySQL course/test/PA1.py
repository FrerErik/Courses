import csv
import mysql.connector
from mysql.connector import errorcode
from os import system, name

# Connection details
cnx = mysql.connector.connect(
    host='localhost',
    user='root',
    password='0000HerbertBAilbonds0987',
)
DB_NAME = 'Chowdhurry_Bataa'
cursor = cnx.cursor()

# Using provided database name new database is Born into this world.


def create_database(cursor, DB_NAME):
    try:
        cursor.execute(
            "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
        cursor.execute('SET GLOBAL sql_mode =""')
        cursor.execute('SET SESSION sql_mode =""')
    except mysql.connector.Error as err:
        print("Failed creating database: {}".format(err))
        exit(1)

# Newly born database acquires its first table...A 'planets'.


def create_table_planets(cursor):
    create_planets = "CREATE TABLE `planets` ("\
        "  `name`            varchar(15),"\
        "  `rotation_period` int,"\
        "  `orbital_period`  int,"\
        "  `diameter`        int,"\
        "  `climate`         varchar(30),"\
        "  `gravity`         varchar(20),"\
        "  `terrain`         varchar(45),"\
        "  `surface_water`   int,"\
        "  `population`      int" \
        ") ENGINE=InnoDB"
    try:
        print("Creating table planets: ")
        cursor.execute(create_planets)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")

# Secondly A 'species' table.


def create_table_species(cursor):
    create_species = "CREATE TABLE `species` (" \
        "  `name` varchar(15)," \
        "  `classification` varchar(14)," \
        "  `designation` varchar(14)," \
        "  `average_height` int," \
        "  `skin_colors` varchar(25)," \
        "  `hair_colors` varchar(14)," \
        "  `eye_colors` varchar(24)," \
        "  `average_lifespan` int," \
        "  `language` varchar(20)," \
        "  `homeworld` varchar(20)" \
        ") ENGINE=InnoDB"
    try:
        print("Creating table species: ")
        cursor.execute(create_species)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")

# In case of abnormal null values.


def na_to_null(row):
    value = 'None'
    for i in range(len(row)):
        if(row[i] == "NA" or row[i] == "N/A"):
            row[i] = value

# Populating 'planets' table.


def insert_into_planets(cursor):
    print("------------------PLANETS------------------")
    try:
        with open('planets.csv') as csv_file:
            reader = csv.reader(csv_file)
            cnt = 0
            for row in reader:
                if cnt == 0:
                    cnt += 1
                else:
                    na_to_null(row)
                    cursor.execute(f"INSERT INTO planets(\
                        name,\
                        rotation_period,\
                        orbital_period,\
                        diameter,\
                        climate,\
                        gravity,\
                        terrain,\
                        surface_water,\
                        population)\
                        VALUES\
                        {row[0] ,  row[1] , row[2] , row[3] , row[4] , row[5] , row[6] , row[7] , row[8]};")
    except mysql.connector.Error as err:
        print(err.msg)
    else:
        # Make sure data is committed to the database
        cnx.commit()
        print("OK")

# Populating 'species' table.


def insert_into_species(cursor):
    print("------------------SPECIES------------------")
    try:
        with open('species.csv') as csv_file:
            reader = csv.reader(csv_file)
            cnt = 0
            for row in reader:
                na_to_null(row)
                if cnt == 0:
                    cnt += 1
                else:
                    cursor.execute(f"INSERT INTO SPECIES (\
                        name,\
                        classification,\
                        designation,\
                        average_height,\
                        skin_colors,\
                        hair_colors,\
                        eye_colors,\
                        average_lifespan,\
                        language,\
                        homeworld)\
                        VALUES\
                        {row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9]};")
    except mysql.connector.Error as err:
        print(err.msg)
    else:
        # Make sure data is committed to the database
        cnx.commit()
        print("OK")

# Clear terminal page


def clear():
    if name == 'nt':
        _ = system('cls')
    else:
        _ = system('clear')

# Main menu


def menu(cursor):
    menu = 0
    while(menu != 6):
        print("\t    1. List all planets.\n\
            2. Search for planet details.\n\
            3. Search for species with height higher than given number.\n\
            4. What is the most likely desired climate of the given species?\n\
            5. What is the average lifespan per species classification?\n\
            6. Quit.\n")
        menu = int(input("Menu(1-6): "))

        if(menu == 1):
            query = "SELECT name FROM planets"
        elif(menu == 2):
            planeName = str(input("\t    Planet name: "))
            query = f"SELECT * from planets WHERE name = '{planeName}'"
        elif(menu == 3):
            testHeight = int(input("\t    Standard height: "))
            query = f"SELECT name from species WHERE average_height > '{testHeight}'"
        elif(menu == 4):
            specieName = input("\t    Specie name: ")
            query = f"SELECT climate from planets WHERE name = (SELECT homeworld from species WHERE name = '{specieName}')"
        elif(menu == 5):
            query = "SELECT classification, avg(average_lifespan) as Average from species GROUP BY classification"
        elif(menu == 6):
            print("Closing connection...")
            cursor.close()
            print("Have a nice day!")
            continue
        else:
            print("Please choose from the provided options.")
            continue

        # Clear window before showing result.
        clear()
        print_results(query, menu, cursor)

        more = input("\t<<< Press 'Enter' key to go back <<<")
        clear()

# Once correct query for chosen task is selected this fucntion prints the results.


def print_results(query, number, cursor):
    cursor.execute(query)
    if(number == 2):
        for tupl in cursor:
            print(f"\t    Rotation period: \t{tupl[1]}\n\
            Orbital period: \t{tupl[2]}\n\
            Diameter:       \t{tupl[3]}\n\
            Climate:        \t{tupl[4]}\n\
            Gravity:        \t{tupl[5]}\n\
            Terrain:        \t{tupl[6]}\n\
            Surface water:  \t{tupl[7]}\n\
            Population:     \t{tupl[8]}")
    elif(number == 5):
        for (classification, Average) in cursor:
            print(
                f"\t    Classification: {classification}\t\tAverage lifespan: {Average}")
    else:
        cnt = 0
        for tupl in cursor:
            cnt += 1
            for info in tupl:
                print(f"\t    {cnt}) {info}")


# Main
try:
    cursor.execute("USE {}".format(DB_NAME))
    clear()
    menu(cursor)

except mysql.connector.Error as err:
    print("Database {} does not exists.".format(DB_NAME))
    if err.errno == errorcode.ER_BAD_DB_ERROR:
        create_database(cursor, DB_NAME)
        print("Database {} created successfully.".format(DB_NAME))
        cnx.database = DB_NAME
        create_table_planets(cursor)
        create_table_species(cursor)
        print("Tables created successfully.\n")
        insert_into_planets(cursor)
        insert_into_species(cursor)
    else:
        print(err)
