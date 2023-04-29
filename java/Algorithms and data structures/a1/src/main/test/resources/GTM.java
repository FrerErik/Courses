package com.test.resources;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import os;

import com.a1.StrategyPatterns.StrategyMeasurer;

/* Dead code, or just improvements that I have planned for next GTM version, Ignore it */

/* Maven implementation just to streamline CSV exporting. */
import com.opencsv.CSVWriter;
import java.util.Scanner;

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
  private boolean csvWriteDirectly;

  static JFrame f;
  static JProgressBar b;

  /* General Time Measurer */
  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease) {
    this(size, amountOfIterations, maxSize, sizeIntervalIncrease, null, false, true);
  }

  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease, String[] csvSetUp,
      boolean wantLines) {
    this(size, amountOfIterations, maxSize, sizeIntervalIncrease, csvSetUp, wantLines, true);
  }

  public GTM(int size, int amountOfIterations, int maxSize, int sizeIntervalIncrease, String[] csvSetUp,
      boolean wantLines, boolean csvWriteDirectly) {
    this.amountOfIterations = amountOfIterations;
    this.size = size;
    this.originalSize = size;
    this.maxSize = maxSize;
    this.sizeIntervalIncrease = sizeIntervalIncrease;
    this.csvSetUp = csvSetUp;
    this.wantLines = wantLines;
    this.csvWriteDirectly = csvWriteDirectly;
  }

  public void StartGTM(StrategyMeasurer strategy, String csvFileName) {
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
      ExportCSV(values, csvFileName, firstRow);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.size = this.originalSize;

  }

  public void StartGTMProgressBar(StrategyMeasurer strategy, String csvFileName) throws IOException {
    ArrayList<ArrayList<String[]>> values = new ArrayList<ArrayList<String[]>>();
    String[] firstRow;
    if (this.csvSetUp != null) {
      firstRow = this.csvSetUp;
    } else {
      firstRow = SetUpCSV();
    }

    progressBar();
    if (this.csvWriteDirectly) {
      CSVWriter writer = new CSVWriter(
          new FileWriter(
              "./" + csvFileName + ".csv"));

      writer.writeNext(firstRow);
      while (size <= maxSize) {
        size += sizeIntervalIncrease;
        long progressValue = Math.round(((double) size / (double) maxSize) * 100);
        b.setValue((int) progressValue);
        ArrayList<String[]> dataset = strategy.run(size, amountOfIterations);
        writer.writeAll(dataset, false);
        if (wantLines) {
          writer.writeNext(new String[] { "-------------------------------------------------------" });
        }
        os.fsync(writer);
      }

    } else {
      while (size <= maxSize) {
        values.add(strategy.run(size, amountOfIterations));
        size += sizeIntervalIncrease;
        long progressValue = Math.round(((double) size / (double) maxSize) * 100);
        b.setValue((int) progressValue);

      }
      try {
        b.setValue(100);
        ExportCSV(values, csvFileName, firstRow);

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    System.out.println("Done");
    this.size = this.originalSize;
    f.dispose();

  }

  public void ExportCSV(ArrayList<ArrayList<String[]>> values, String csvFileName, String[] firstRow)
      throws IOException {

    CSVWriter writer = new CSVWriter(
        new FileWriter(
            "./" + csvFileName + ".csv"));
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

}
