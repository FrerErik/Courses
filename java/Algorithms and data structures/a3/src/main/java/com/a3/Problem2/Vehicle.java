package com.a3.Problem2;

import java.util.concurrent.ThreadLocalRandom;

public class Vehicle {
  String licensePlateNumber;


  public Vehicle() {
    this.licensePlateNumber = generateLicense();
  }

  public Vehicle(String lPN) {
    licensePlateNumber = lPN;
  }

  public String getLicensePlateNumber() {
    return this.licensePlateNumber;
  }

  private String generateLicense() {
    String lPN = "";
    String letters = "ABCDEFGHJKLMNOPRSTUWXYZ";
    String numbers = "0123456789";

    for (int i = 0; i < 7; i++) {
      if (i < 3) {
        lPN += letters.charAt(ThreadLocalRandom.current().nextInt(letters.length()));
      } else if (i == 3) {
        lPN += " ";
      } else if (i >= 4 && i < 6 ) {
        lPN += numbers.charAt(ThreadLocalRandom.current().nextInt(numbers.length()));
      } else if (i == 6) {
        letters = "ABCDEFGHJKLMNPRSTUWXYZ";
        letters += numbers;
        lPN +=  letters.charAt(ThreadLocalRandom.current().nextInt(letters.length()));
      }
    }
    this.licensePlateNumber = lPN;
    return lPN;
  } 

  @Override
  public String toString() {
    return "{" +
        " licensePlateNumber='" + getLicensePlateNumber() + "'" +
        "}";
  }

}
