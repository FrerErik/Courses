How to book a room 

Step 1 
Enter room number: ie 1
Choose start date: 4/16/2021
Choose end date: 4/21/2021
Create customer -----> popup [customer name: Fred
		             Customer last name: Eriksson
		             Customer phone: 017212	
                                             Customer payment method: [visa, master, cash]
		              Maybe more, 
	 	              >Done is pressed
		              If customer already exists we can create a search function and fetch his information from there] -----> from there customer name is taken and added in a hidden String
Once fields have been entered


>Done button is pressed


Step 2

Program goes to the Room database and checks if the room exists and fetches the price of the room [1, 20000]

And looks if dates are valid

Then if true:

- booking number is generated

- values will be inserted to the booking database 

finally it would look this way:

[Booking number, room number, start date, end date, customer number, current payment status, price]
[#19281,                     1,           4/16/2021, 4/21/2021, 0763029120,               not paid,                    20000]

If false

Error says room is already taken



---------------------------------------------------------------------------



maybe implement a available room button?