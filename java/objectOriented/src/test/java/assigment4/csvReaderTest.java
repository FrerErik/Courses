package assigment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class csvReaderTest {
  public static void main(String[] args) {
    String fileName = "recipes.csv";
    File file = new File(fileName);
    ArrayList<String[]> Total = new ArrayList<>();
    Scanner inputStream;
    try {
      inputStream = new Scanner(file).useDelimiter("\n");
      while (inputStream.hasNext()) {
        String data = inputStream.next();
        String[] values = data.split(Pattern.quote("|"));
        Total.add(values);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    for (String[] ArrayL : Total) {
      String[] ingredients = ArrayL[2].split(",\s");
      String[] Steps = ArrayL[5].split("\\.,\s");
      String[] amount = ArrayL[3].split(",\s");
      String[] comments = ArrayL[4].split(",\s");
      System.out.println(Arrays.toString(ingredients));
      System.out.println(Arrays.toString(amount));
      System.out.println(Arrays.toString(comments));
      System.out.println(Arrays.toString(Steps));
    }
  }
}
