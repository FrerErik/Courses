package assigment4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class addRecipe {
  public void addrecipe(ArrayList<String[]> csvFile, recipes rep) {
    String Rname;
    String Ingredient_Names = "", Ingredient_Amount = "", Ingredient_Comments = "", Step_by_step = "";
    String portion;
    Boolean x = true;
    Integer counter = 0;

    Scanner keyboard = new Scanner(System.in);

    System.out.print("Recipe Name: ");
    Rname = keyboard.nextLine();
    rep.addRecipe_name(Rname);

    System.out.print("portion(type in numbers): ");
    portion = keyboard.nextLine();
    rep.addPortions(portion);
    System.out.print("");

    System.out.println("Ingredients, Type 'Done'(Case sensitive) when all ingredients are listed");
    while (x.equals(true)) {
      counter++;
      System.out.print("Ingredient " + counter + ": ");
      String Temp = keyboard.nextLine();
      if (Temp.equals("Done")) {
        System.out.println("Ingredients added");
        x = false;
      } else {
        System.out.print("Ingredient amount for " + Temp + ": ");
        String number = keyboard.nextLine();
        System.out.print("");
        System.out.print("Ingredient Comments: ");
        String comment = keyboard.nextLine();
        Ingredient_Names += Temp + ", ";
        Ingredient_Amount += number + ", ";
        Ingredient_Comments += comment + ", ";
        System.out.println("Added");
      }
    }
    counter = 0;
    System.out.println("Type Steps Type 'Done'(Case sensitive) when all steps are listed");
    while (x.equals(false)) {
      counter++;
      System.out.print("Step " + counter + ": ");
      String Temp = "Step" + counter.toString() + ": ";
      Temp += keyboard.nextLine() + "." + ", ";
      if (Temp.equals("Step" + counter.toString() + ": " + "Done" + "." + ", ")) {

        System.out.println("Steps added");
        x = true;

      } else {
        Step_by_step += Temp;
      }
    }
    Ingredient_Names = Ingredient_Names.substring(0, Ingredient_Names.length() - 2);
    Ingredient_Amount = Ingredient_Amount.substring(0, Ingredient_Amount.length() - 2);
    Ingredient_Comments = Ingredient_Comments.substring(0, Ingredient_Comments.length() - 2);
    Step_by_step = Step_by_step.substring(0, Step_by_step.length() - 3);
    rep.setIngredients(Ingredient_Names);
    rep.setIngredient_amount(Ingredient_Amount);
    rep.setComments(Ingredient_Comments);
    rep.setSteps(Step_by_step);

    String fileName = "recipes.csv";
    FileWriter outputfile;
    try {
      outputfile = new FileWriter(fileName);

      CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

      Boolean empty = csvFile.isEmpty();
      if (empty) {
        ;
      } else {
        for (String[] strArr : csvFile) {
          strArr[0] = strArr[0].replaceAll("^\"|\"$", "");
          strArr[1] = strArr[1].replaceAll("^\"|\"$", "");
          strArr[2] = strArr[2].replaceAll("^\"|\"$", "");
          strArr[3] = strArr[3].replaceAll("^\"|\"$", "");
          strArr[4] = strArr[4].replaceAll("^\"|\"$", "");
          strArr[5] = strArr[5].replaceAll("^\"|\"$", "");
          writer.writeNext(strArr);
        }
      }
      String[] nextLine = { rep.getRecipe_name(), rep.getPortion(), rep.getIngredients(), rep.getIngredient_amount(),
          rep.getComments(), rep.getSteps() };

      writer.writeNext(nextLine);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}