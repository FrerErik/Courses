package view;

import java.util.Scanner;

import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;

import java.io.IOException;
import java.util.ArrayList;

public class Console implements MemberBoatUI {
  // Member variables
  private String firstName;
  private String lastName;
  private String personNum;
  private MemberOption chosen;

  // Boat variables
  private String name;
  private BoatType type;
  private double length;

  public Console() {
    firstName = lastName = personNum = "";
  }


  public void AddMemberOption() {
      System.out.print("Input member's name: ");
      firstName = String.valueOf(GetInput());
      System.out.print("Input member's last name: ");
      lastName = String.valueOf(GetInput());
      System.out.print("Input member's personal number: ");
      personNum = String.valueOf(GetInput());
  }

    public boolean addBoatConfirmation() {
      // used after creating a Member, or while editing a member and adding a boat
      // from there
      System.out.print("Press y to add a boat other keys will do nothing: ");

      return GetInput() == "y";
    }

    public void addBoat() {
      System.out.print("Input boat name: ");
      name = String.valueOf(GetInput());
      System.out.println("Boat length: ");
      length = Double.valueOf(GetInput());
    }

    public void returnBoatType() {

    }

    /**public ListOption ShowMemberListChoice() {
      System.out.print("Press 1 to display simple member list");
      System.out.println("Press 2 to display verbose member list");
      String option = GetInput();
      if (option.equals("1")) {
        Lchoice = ListOption.SimpleList;
      } else if (option.equals("2")) {
        Lchoice = ListOption.VerboseList;
      } else {
        Lchoice = ListOption.Error;
      }
      return Lchoice;
    }*/

    public void DisplayVerboseList() {

    }

    public void DisplaySimpleList() {

    }

    public void PickAMember() {

    }

    public void Quit() {
      System.out.println("Thanks for using this program");
    }

    public void Error() {
      System.out.println("Invalid Input");
    }

    private String GetInput() {
      Scanner scanner = new Scanner(System.in);
      String input = scanner.next();
      return input;
  }

  public int getMemberId() {
    return 50;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPersonNum() {
    return personNum;
  }

  public ArrayList<Boat> getBoats() {
    return null;
  }

  @Override
  public Member showComposeList(Iterable<Member> members) throws IOException {
    return null;
  }

  @Override
  public Member showVerboseList(Iterable<Member> members) throws IOException {
    return null;
  }

  @Override
  public MemberOption getMemberOptions() throws IOException {
    System.out.println("Press 1 to create a new member");
    System.out.println("Press 2 to show a list of all members");
    System.out.println("Press 3 to pick a member");
    System.out.println("Press 4 to close the program");

    // I feel that what is below belongs in another method.

    String option = GetInput();

    if (option.equals("1")) {
      AddMemberOption();
      chosen = MemberOption.AddNewMember;
    } else if (option.equals("2")) {
      //chosen = MemberOption.ShowMemberList;
    } else if (option.equals("3")) {
      //chosen = MemberOption.PickAMember;
    } else if (option.equals("4")) {
      chosen = MemberOption.Quit;
    } else {
      chosen = MemberOption.None;
    }
    return chosen;
  }

  @Override
  public BoatOption getBoatOptions(Member selected_Member) throws IOException {
    return null;
  }

  @Override
  public void onAddNewMember(Iterable<Member> members, Member newMember) {

  }

  @Override
  public void onAddNewBoat(Iterable<Boat> boats, Boat newBoat) {

  }

  @Override
  public void onDeleteMember(Iterable<Member> members, Member deleteMember) {

  }

  @Override
  public void onDeleteBoat(Iterable<Boat> boats, Boat deleteBoat) {

  }

  @Override
  public void onChangeCurrentMember(Member newMember) {

  }

  @Override
  public void onChangeCurrentBoat(Boat newBoat) {

  }
}