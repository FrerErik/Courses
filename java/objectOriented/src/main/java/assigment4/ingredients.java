package assigment4;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import com.opencsv.CSVWriter;
import java.io.IOException;

public class ingredients {
  private csvReader s = new csvReader();

  private ArrayList<String[]> allIngredients = s.readCsvFile("ingredients.csv");
  private ArrayList<String> names = new ArrayList<String>();
  private ArrayList<String> unit = new ArrayList<String>();
  private ArrayList<String> price = new ArrayList<String>();
  private ArrayList<String> type = new ArrayList<String>();

  public ArrayList<String> getNames() {
    return this.names;
  }

  public void setNames(ArrayList<String> names) {
    this.names = names;
  }

  public ArrayList<String> getUnit() {
    return this.unit;
  }

  public void setUnit(ArrayList<String> unit) {
    this.unit = unit;
  }

  public ArrayList<String> getPrice() {
    return this.price;
  }

  public void setPrice(ArrayList<String> price) {
    this.price = price;
  }

  public ArrayList<String> getType() {
    return this.type;
  }

  public void setType(ArrayList<String> type) {
    this.type = type;
  }

  public boolean addRow(String name, String unit, String price, String type) {
    if (!this.names.contains(name) && !name.isEmpty()) {
      if (!unit.isEmpty()) {
        if (!price.isEmpty()) {
          if (type.equals("y") || type.equals("n")) {
            this.names.add(name);
            this.unit.add(unit);
            this.price.add(price);
            this.type.add(type);
            return true;
          } else {
            return false;
          }
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public void removeRow(int index) {
    this.names.remove(index);
    this.unit.remove(index);
    this.price.remove(index);
    this.type.remove(index);
  }

  public void setIngredients() {
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> unit = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();

    for (String[] strArr : this.allIngredients) {
      names.add(strArr[0]);
      unit.add(strArr[1]);
      price.add(strArr[2]);
      type.add(strArr[3]);
    }
    setNames(names);
    setUnit(unit);
    setPrice(price);
    setType(type);
  }

  public void searchIngredient() {
    Scanner keyboard = new Scanner(System.in);

    System.out.print("Enter Ingredient Name(case sensitive): ");
    String ingredients = keyboard.nextLine();
    System.out.println("");
    Integer counter = 0;
    for (Integer i = 0; i < getNames().size(); i++) {
      if (getNames().get(i).equals(ingredients)) {
        counter++;
        System.out.println("Name: " + getNames().get(i) + "\n" + "Unit: " + getUnit().get(i) + "\n" + "Price(kr): "
            + getPrice().get(i) + "\n" + "divisible?: " + getType().get(i));
      }
    }
    if (counter == 0) {
      System.out.println("Ingredient not found");
    }
  }
}