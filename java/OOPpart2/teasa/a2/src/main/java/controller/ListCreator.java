package controller;

import java.util.ArrayList;

/** */
public class ListCreator {

  ArrayList<Integer> list = new ArrayList<>();

  /** */
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
}
