package assigment4;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class terminal {
  private csvReader s;
  private ingredients ing;
  private addIngredient addI;
  private deleteIngredient remI;
  private recipes rep;
  private addRecipe AR;

  // splashable function

  ArrayList<Integer> list = new ArrayList<>();

  public int idSorter(ArrayList<Integer> list) {
    int id = 1;

    for (int i : list) {
      if (i == id) {
        id += 1;
      } else {
        return id;
      }
    }

    return id;
  }

  public void menu() {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(4);

    int id = 0;

    id = idSorter(list);

    System.out.println(id);

    s = new csvReader();
    ing = new ingredients();
    addI = new addIngredient();
    remI = new deleteIngredient();
    rep = new recipes();
    AR = new addRecipe();

    // s.readCsvFile("ingredients.csv");
    // s.readCsvFile("Recipes.csv");
    Scanner keyboard = new Scanner(System.in);
    Boolean menu = true;
    ing.setIngredients();
    while (menu == true) {
      ArrayList<String[]> Allrecipes = s.readCsvFile("recipes.csv");
      System.out.println("Select From the menu (type the number)");
      System.out.println("(1) Ingredients");
      System.out.println("(2) Recipes ");
      System.out.println("(3) Save changes and close application");

      System.out.print("Select: ");
      Integer UserInput = keyboard.nextInt();
      System.out.println("");

      if (UserInput == 1) {
        System.out.println("Select From the menu (type the number)");
        System.out.println("(1) Add ingredient(needs: Name, Unit, Price, Type)");
        System.out.println("(2) List all ingredients");
        System.out.println("(3) Search specific ingredient");
        System.out.println("(4) Delete ingredient");
        System.out.println("(9) back to first menu");
        System.out.print("Select: ");
        UserInput = keyboard.nextInt();
        System.out.println("");

        if (UserInput == 1) { // add Ingredient
          addI.addIngredients(ing);
          System.out.println("");
          UserInput = 9;

        } else if (UserInput == 2) { // List all ingredients
          System.out.println("All available Ingredients");
          System.out.println("");
          for (int i = 0; i < ing.getNames().size(); ++i) {
            System.out.println(ing.getNames().get(i));
          }
          UserInput = 9;
          System.out.println("");
        } else if (UserInput == 3) {// display searched ingredient
          ing.searchIngredient();
          System.out.println("");
          UserInput = 9;
        } else if (UserInput == 4) {// delete selected ingredient
          remI.eraseIngredient(ing);
          System.out.println("");
        } else if (UserInput == 9) {
          System.out.println("(9) back to first menu");
          System.out.println("");
        } else {
          System.out.println("Error");
        }
      } else if (UserInput == 2) {
        System.out.println("Select From the menu (type the number)");
        System.out.println("(1) Add recipe, (needs: name, portion, ingredient_Name, ingredient amounts and comments) ");
        System.out.println("(2) List all recipes");
        System.out.println("(3) Search specific Recipe");
        System.out.println("(4) Delete Recipe");
        System.out.println("(9) back to first menu");
        System.out.print("Select: ");
        UserInput = keyboard.nextInt();
        System.out.println("");
        if (UserInput == 1) {
          // AR.addrecipe(Allrecipes, rep);

          System.out.println("Done!");
          System.out.println("");
          UserInput = 9;
        } else if (UserInput == 2) { // list recipes
          System.out.println("Recipe Names: ");
          System.out.println("");
          for (String[] strArr : Allrecipes) {
            System.out.println(strArr[0]); // strArr[0], Arrays.toString(strArr)
          }
          System.out.println("");
        } else if (UserInput == 3) {
          ;
        }
      } else if (UserInput == 3) {
        s.saveCsvChanges("ingredients.csv", ing);
        System.out.println("exit");
        keyboard.close();
        menu = false;
      } else {
        System.out.println("Main menu");
      }
    }
  }

  public static void main(String[] args) {
    terminal a = new terminal();
    a.menu();
  }
}