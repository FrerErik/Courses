package com.a1.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class MonteCarloSim {

  public MonteCarloSim(int n) {
    this.n = n;
    this.total = n * n;
    this.qF = new QuickFind(total + 2);
    this.numberOfOpenComponents = 0;
    this.sideValues = new ArrayList<Integer>();
    this.sideValues2 = new ArrayList<Integer>();
    this.state = new ArrayList<Boolean>();
    Start();
  }

  private int total;

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  private int n;
  private QuickFind qF;
  private int numberOfOpenComponents;
  private List<Integer> sideValues;
  private List<Integer> sideValues2;
  private List<Boolean> state;

  public void setN(int n) {
    this.n = n;
  }

  public QuickFind getQF() {
    return this.qF;
  }

  public void setQF(QuickFind qF) {
    this.qF = qF;
  }

  public int getNumberOfOpenComponents() {
    return this.numberOfOpenComponents;
  }

  public boolean isConnected(int a, int b) {
    return this.qF.isConnected(a, b);
  }

  public void normalConnect(int value) {

    List<Boolean> closeStates = checkCloseStates(value);

    if (value < (total - n) && value > n) { // {12 --- 19}, ..., {82 --- 89}
      if (closeStates.get(0)) {
        qF.union(value, value + 1);
      }
      if (closeStates.get(1)) {
        qF.union(value, value - 1);

      }
      if (closeStates.get(2)) {
        qF.union(value, value + n);
      }
      if (closeStates.get(3)) {
        qF.union(value, value - n);

      }
    } else if (value < n) { // {2 --- 9}
      if (closeStates.get(0)) {
        qF.union(value, value + 1);

      }
      if (closeStates.get(1)) {
        qF.union(value, value - 1);
      }

      if (closeStates.get(2)) {
        qF.union(value, value + n);
      }
      qF.union(value, 0);
    } else if (value > (total - n)) { // {92 --- 99}
      if (closeStates.get(0)) {
        qF.union(value, value + 1);
      }
      if (closeStates.get(1)) {
        qF.union(value, value - 1);
      }
      if (closeStates.get(2)) {
        qF.union(value, value - n);
      }
      qF.union(value, total + 1);
    }
  }

  public void sideConnect(int value) {
    List<Boolean> closeStates = checkCloseStates(value);
    if (value <= (total - n) && value > n) { // {11, 20, 21, 30, 31, ..., 89, 90}
      if (sideValues.contains(value)) { // {11, 21, 31, ..., 81}
        if (closeStates.get(0)) {
          qF.union(value, value + 1);
        }
        if (closeStates.get(2)) {
          qF.union(value, value + n);
        }
        if (closeStates.get(3)) {
          qF.union(value, value - n);
        }
      } else if (sideValues2.contains(value)) { // {20, 30, ..., 90}
        if (closeStates.get(1)) {
          qF.union(value, value - 1);
        }

        if (closeStates.get(2)) {
          qF.union(value, value + n);
        }
        if (closeStates.get(3)) {
          qF.union(value, value - n);
        }
      }
    } else if (value <= n) { // {1, 10}
      if (sideValues.contains(value)) { // {1}
        if (closeStates.get(0)) {
          qF.union(value, value + 1);
        }
        if (closeStates.get(2)) {
          qF.union(value, value + n);
        }
        qF.union(value, 0);
      } else if (sideValues2.contains(value)) { // {10}
        if (closeStates.get(1)) {
          qF.union(value, value - 1);
        }
        if (closeStates.get(2)) {
          qF.union(value, value + n);
        }
        qF.union(value, 0);
      }
    } else if (value > (total - n)) { // {91, 100}
      if (sideValues.contains(value)) { // {91}
        if (closeStates.get(0)) {
          qF.union(value, value + 1);
        }
        if (closeStates.get(2)) {
          qF.union(value, value - n);
        }
        qF.union(value, total + 1);
      } else if (sideValues2.contains(value)) { // {100}
        if (closeStates.get(1)) {
          qF.union(value, value - 1);
        }
        if (closeStates.get(2)) {
          qF.union(value, value - n);
        }
        qF.union(value, total + 1);
      }
    }
  }

  public void Start() {
    for (int i = 0; i < n; i++) {
      this.sideValues.add(i * n + 1); // {1, 11, 21, 31, ..., 91}
      this.sideValues2.add((i + 1) * n); // {10, 20, 30, 40, ..., 100}
    }
    this.state.add(null);
    for (int i = 0; i < this.total + 1; i++) {
      this.state.add(false);
    }
    this.state.add(null);
  }

  public void ActivateCC(int value) {
    state.set(value, true);

    if (CheckCC(value)) {

      sideConnect(value);
    } else {
      normalConnect(value);
    }
    numberOfOpenComponents += 1;

  }

  public boolean CheckCC(int value) {
    if (sideValues.contains(Integer.valueOf(value)) || sideValues2.contains(Integer.valueOf(value))) {
      return true;
    }
    return false;
  }

  public List<Boolean> checkCloseStates(int value) {
    List<Boolean> states = new ArrayList<Boolean>();

    if (value > (total - n)) { // {
      states.add(this.state.get(value + 1));
      states.add(this.state.get(value - 1));
      states.add(this.state.get(value - n));
    } else if (value <= n) { // {
      states.add(this.state.get(value + 1));
      states.add(this.state.get(value - 1));
      states.add(this.state.get(value + n));
    } else if (value <= (total - n) && value > n) {
      states.add(this.state.get(value + 1));
      states.add(this.state.get(value - 1));
      states.add(this.state.get(value + n));
      states.add(this.state.get(value - n));
    }
    return states;
  }
}
