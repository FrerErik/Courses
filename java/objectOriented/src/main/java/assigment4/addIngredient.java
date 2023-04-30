package assigment4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPopupMenu.Separator;

import com.opencsv.CSVWriter;

public class addIngredient {

  public void addIngredients(ingredients ing) {

    Scanner keyboard = new Scanner(System.in);

    System.out.print("Enter Ingredient Name: ");
    String name = keyboard.nextLine();

    System.out.print("Enter Unit: ");
    String unit = keyboard.nextLine();

    System.out.print("Enter Price(kr): ");
    String price = keyboard.nextLine();
    // if (!isInteger(price)) {
    // System.out.println("Invalid Input, cancelling");
    // cancel = true;
    // }

    System.out.print("Enter if ingredient is divisible(if possible type: y,  else n)(case sensitive): ");
    String type = keyboard.nextLine();

    if (ing.addRow(name, unit, price, type)) {
      System.out.println("Successful addition");
    } else {
      System.out.println("Invalid input cancelling addition");
    }
  }
}