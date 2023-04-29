package com.a1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.a1.StrategyPatterns.StrategyMeasurer;

/* Maven implementation just to streamline CSV exporting. */
import com.opencsv.CSVWriter;
import java.util.Scanner;
import java.io.File;

/*Just for creating a progress bar */
import javax.swing.*;

public class GTM extends JFrame {

  private int amountOfIterations;
  private int size;
  private int originalSize;
  private int maxSize;
  private int sizeIntervalIncrease;
  private String[] csvSetUp;
  private boolean wantLines;

  static JFrame f;
  static JProgressBar b;

  /* General Time Measurer */
  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease) {
    this(size, amountOfIterations, maxSize, sizeIntervalIncrease, null, false);
  }

  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease, String[] csvSetUp) {
    this(size, amountOfIterations, maxSize, sizeIntervalIncrease, csvSetUp, false);
  }

  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease, String[] csvSetUp,
      boolean wantLines) {
    this.amountOfIterations = amountOfIterations;
    this.size = size;
    this.originalSize = size;
    this.maxSize = maxSize;
    this.sizeIntervalIncrease = sizeIntervalIncrease;
    this.csvSetUp = csvSetUp;
    this.wantLines = wantLines;
  }

  public void StartGTMProgressBar(StrategyMeasurer strategy, String CSVFilename) {

    ArrayList<ArrayList<String[]>> values = new ArrayList<ArrayList<String[]>>();
    String[] firstRow;
    if (this.csvSetUp != null) {
      firstRow = this.csvSetUp;
    } else {
      firstRow = SetUpCSV();
    }

    progressBar();

    while (size <= maxSize) {
      values.add(strategy.run(size, amountOfIterations));

      long progressValue = Math.round(((double) size / (double) maxSize) * 100);
      b.setValue((int) progressValue);
      size += sizeIntervalIncrease;
    }
    try {
      b.setValue(100);
      b.setString("Data gathering is done!, now its just exporting");
      ExportCSV(values, CSVFilename, firstRow);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Done");
    this.size = this.originalSize;
    f.dispose();

  }

  public void StartGTMProgressBar2(StrategyMeasurer strategy, String CSVFileName) {

    ArrayList<ArrayList<String[]>> values = new ArrayList<ArrayList<String[]>>();
    String[] firstRow;
    boolean firstIter = true;
    if (this.csvSetUp != null) {
      firstRow = this.csvSetUp;
    } else {
      firstRow = SetUpCSV();
    }

    progressBar();

    while (size <= maxSize) {
      values.add(strategy.run(size, amountOfIterations));

      long progressValue = Math.round(((double) size / (double) maxSize) * 100);
      b.setValue((int) progressValue);
      size += sizeIntervalIncrease;
      try {
        if (!firstIter) {
          File f = new File("./" + CSVFileName + ".csv");
          f.delete();
        } else {
          firstIter = false;
        }
        ExportCSV(values, CSVFileName, firstRow);

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    b.setString("Data gathering is done!");
    System.out.println("Done");
    this.size = this.originalSize;
    f.dispose();

  }

  public void ExportCSV(ArrayList<ArrayList<String[]>> values, String CSVFileName, String[] firstRow)
      throws IOException {

    CSVWriter writer = new CSVWriter(
        new FileWriter(
            "./" + CSVFileName + ".csv"));
    writer.writeNext(firstRow);
    for (List<String[]> set : values) {
      writer.writeAll(set, false);
      if (wantLines) {
        writer.writeNext(new String[] { "-------------------------------------------------------" });

      }
    }
    writer.close();
  }

  public String[] SetUpCSV() {
    Scanner scanner = new Scanner(System.in);

    List<String> columnNames = new ArrayList<String>();
    int firstRowNames;
    System.out.print("Select amount of columns in first row: ");
    firstRowNames = scanner.nextInt();

    for (int i = 1; i < firstRowNames + 1; i++) {

      System.out.print("Name column " + i + ": ");
      String cName = scanner.next();
      System.out.println("Added!");
      columnNames.add(cName);

    }
    String[] firstRow = new String[columnNames.size()];
    firstRow = columnNames.toArray(firstRow);
    return firstRow;
  }

  public void progressBar() {
    f = new JFrame("ProgressBar");

    JPanel p = new JPanel();

    b = new JProgressBar();
    b.setValue(0);
    b.setStringPainted(true);

    p.add(b);

    f.add(p);
    f.setSize(200, 200);
    f.setVisible(true);

  }

  public void StartGTM(StrategyMeasurer strategy, String CSVFilename) { // old version kept in case
    ArrayList<ArrayList<String[]>> values = new ArrayList<ArrayList<String[]>>();
    String[] firstRow;
    if (this.csvSetUp != null) {
      firstRow = this.csvSetUp;
    } else {
      firstRow = SetUpCSV();
    }
    while (size <= maxSize) {
      values.add(strategy.run(size, amountOfIterations));
      size += sizeIntervalIncrease;
    }
    try {
      ExportCSV(values, CSVFilename, firstRow);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.size = this.originalSize;

  }

}
