package assigment4;

// testing ideas 1: get certain info in an array list
public class arrayListTest {
  public static void main(String[] args) {
    /*
     * ArrayList<String> abc = new ArrayList<String>(); String a = "Milk"; String b
     * = "Litre"; String c = "10"; String d = "Egg";
     * 
     * abc.add(a); abc.add(b); abc.add(c); abc.add(d); System.out.println(abc);
     * if(abc.contains("Milk")) { Integer l = abc.indexOf("Milk");
     * System.out.println(abc.get(l) + abc.get(l + 1) + abc.get(l + 2)); }
     * 
     * //test 2 add information to the csv file
     * 
     * 
     * Scanner keyboard = new Scanner(System.in);
     * 
     * //show all ingredients File file = new File("ingredients.csv"); String name;
     * String unit; String price; ArrayList<String[]> collected = new
     * ArrayList<String[]>(); try { Scanner inputStream = new Scanner(file); while
     * (inputStream.hasNext()) { String data = inputStream.next(); String[] values =
     * data.split(","); collected.add(new String [] { values[0], values[1],
     * values[2]}); } FileWriter outputfile = new FileWriter(file); CSVWriter writer
     * = new CSVWriter(outputfile);
     * 
     * System.out.print("Enter Ingredient Name: "); name = keyboard.nextLine();
     * System.out.print("Enter Unit: "); unit = keyboard.nextLine();
     * System.out.print("Enter Price: "); price = keyboard.nextLine();
     * 
     * keyboard.close();
     * 
     * collected.add(new String[] {name, unit, price});
     * 
     * writer.writeAll(collected);
     * 
     * writer.close(); inputStream.close(); // print whole table ! :D ! for
     * (String[] strArr : collected) { System.out.println(Arrays.toString(strArr));
     * } } catch (IOException e) { e.printStackTrace(); }
     * 
     * // test 3 find by name and find by max price /* File file = new
     * File("ingredients.csv"); String Ingredientname; ArrayList<Object[]> collected
     * = new ArrayList<Object[]>(); ArrayList<String> names = new
     * ArrayList<String>(); int index; Integer Maxprice = 0;
     * 
     * try { Scanner inputStream = new Scanner(file); inputStream.next(); while
     * (inputStream.hasNext()) { String data = inputStream.next(); String[] values =
     * data.split(","); String price = (values[2]); names.add(values[0]);
     * collected.add(new String [] { values[0], values[1], values[2]}); Integer
     * price1 = Integer.parseInt(price); if (price1 > Maxprice) { Maxprice = price1;
     * }
     * 
     * }
     * 
     * index = collected.indexOf((Maxprice));
     * 
     * Scanner keyboard = new Scanner(System.in);
     * 
     * System.out.print("Enter Ingredient Name: "); Ingredientname =
     * keyboard.nextLine(); System.out.println(Ingredientname);
     * System.out.println(Maxprice); System.out.println(index);
     * 
     * if (collected.contains(Ingredientname)) { System.out.println("a");
     * System.out.println(collected.indexOf(Ingredientname)); } } catch (IOException
     * e) { e.printStackTrace(); }
     */

  }
}
