package model.domain;

import java.util.ArrayList;

/** */
public class Member {
  private int memberId;
  private String firstName;
  private String lastName;
  private String personNum;
  private ArrayList<Boat> boats;

  /**
   * @param memberId
   * @param firstName
   * @param lastName
   * @param personNum
   * @param boats
   */
  public Member(int memberId, String firstName, String lastName, String personNum, ArrayList<Boat> boats) {
    this.memberId = memberId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.personNum = personNum;
    this.boats = boats;
  }

  public Member() {

  }

  public int getMemberId() {
    return memberId;
  }

  public void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPersonNum() {
    return personNum;
  }

  public void setPersonNum(String personNum) {
    this.personNum = personNum;
  }

  public ArrayList<Boat> getBoats() {
    return boats;
  }

  public void setBoats(ArrayList<Boat> boats) {
    this.boats = boats;
  }

}
