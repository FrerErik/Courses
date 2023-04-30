package assigment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.opencsv.CSVWriter;

public class csvReader {

  public ArrayList<String[]> readCsvFile(String fileName) {
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
    return Total;
  }

  public void saveCsvChanges(String filename, ingredients ing) {
    FileWriter outputfile;
    try {
      outputfile = new FileWriter(filename);

      CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

      if (!ing.getNames().isEmpty()) {
        Integer counter = 0;
        for (String s : ing.getNames()) {
          String[] strArr = { ing.getNames().get(counter), ing.getUnit().get(counter), ing.getPrice().get(counter),
              ing.getType().get(counter) };
          strArr[0] = strArr[0].replaceAll("^\"|\"$", "");
          strArr[1] = strArr[1].replaceAll("^\"|\"$", "");
          strArr[2] = strArr[2].replaceAll("^\"|\"$", "");
          strArr[3] = strArr[3].replaceAll("^\"|\"$", "");
          writer.writeNext(strArr);
          counter += 1;
        }
      }

      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
