package model.domain;

/** */
public class Boat {
  private String name;
  private int id;
  private BoatType type;
  private double length;

  /**
   * @param name
   * @param id
   * @param type
   * @param length
   */
  public Boat(String name, int id, BoatType type, double length) {
    this.name = name;
    this.id = id;
    this.type = type;
    this.length = length;
  }

  public Boat() {

  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public BoatType getType() {
    return type;
  }

  public void setType(BoatType type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
