package assigment4;

import java.util.Scanner;

public class deleteIngredient extends ingredients {
  public void eraseIngredient(ingredients ing) {
    ;
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter Ingredient Name(case sensitive): ");
    String Ingredients = keyboard.nextLine();
    if (ing.getNames().contains(Ingredients)) {
      Integer ingredientIndex = -1;
      for (int i = 0; i < ing.getNames().size(); i++) {
        if (ing.getNames().get(i).equals(Ingredients)) {
          ingredientIndex = i;
        }
      }
      ing.removeRow(ingredientIndex);
      System.out.println(Ingredients + " Erased");
    } else {
      System.out.println("Error, Ingredient not found");
    }
  }
}
