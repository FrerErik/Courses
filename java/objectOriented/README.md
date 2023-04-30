## Unfinished, what I have done and how I would like the final product to look like.
Could not finish the assigment in time I'll sent this in gitlab and Id appreciate some feedback but is not mandatory at all

My major time sink was trying to find an external type of file that was not so awkward to use, I tried XML and didnt like it then, I tried csv and I didnt like it but I had to use it or otherwise I might not get anything done, I sticked to csv due to previous knowledge in another program so I thought I might have an easier time. 


Second I have no idea how to implement gradle tasks I think I did it here but lacked time to actually do some tests.
I would have appreciated a type of online tutorial or step by step help but this is mostly on my part I guess.


My implementations:
Everything regarding the menu for the terminal + everything related to ingredient implementation except the delete its not working but I can fix it quickly I think.

I planned on using a very similar structure for the recipes while adding special rules for the ingredient section

and I would like to add a 4th column in my ingredient table called divisible or indivisible

recipe menu would look like this

(1) Add recipe, (needs: name, portion, ingredients, ingredient amounts and comments) 
Type ingredient + number next to it to define amounts, if the ingredient is indivisible add indivisible at the end.
(2)List all recipes
(3)Search a specific recipe ( needs: name, portion)
(4) Delete recipe
(5) Search recipes with filters (by ingredient name or maximum price)
(9) back to first menu

While the user types 
this way 
Name: 
portion: 
Ingredients: 
...

then my program would take the portion and multiply that to a int.parsed version of price and if it the row contains indivisible then It would round ingredient ammounts depending on the portion added.


and in order to implement the Search recipes with filters 
for Recipe names:
I would take the all recipes table and select String[2] and create a search bar that way it only shows 
recipes where keyboard.nextLine() = String[2] in a for Arrayl : ArrayList<String[]>

for Max Price:
I would take the all recipes ArrayList with all portion prices calculated and then create a for loop that
at the beginning there would be a value of 0 and if String[prices] > value then value = String[prices] 
so at the end the terminal obtains the largest value and prints it to the user.

Thanks for your time and Sorry for Disappointing.