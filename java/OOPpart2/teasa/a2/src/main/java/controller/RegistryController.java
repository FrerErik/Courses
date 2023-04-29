package controller;

import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;
import model.domain.Registry;
import view.Console;
import view.MemberBoatUI;
import view.MemberOption;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * */
public class RegistryController implements Registry.IEventListener {
  MemberBoatUI uI;
  Registry reg;
  Member curMember;
  Boat curBoat;

  public void handleMembers(Registry a_reg, MemberBoatUI a_uI) throws IOException {
    reg = a_reg;
    uI = a_uI;
    reg.addEventListener(this);
    boolean done = false;
    while (!done) {
      MemberOption choice = a_uI.getMemberOptions();
      switch (choice) {
        case AddNewMember:
          addNewMember(a_reg, a_uI);

          break;

        case Quit:

        default:
          done = true;
          break;
      }
    }
    a_reg.removeEventListener(this);
  }

  // Will search in registry and return whether it found it or not
  public boolean setCurMember(int memberID) {
    for (Member mem : reg.getMembers()) {
      if (mem.getMemberId() == memberID) {
        curMember = mem;
        return true;
      }
    }

    return false;
  }

  public boolean setCurBoat(int boatID) {
    for (Boat boat : curMember.getBoats()) {
      if (boat.getId() == boatID) {
        curBoat = boat;
        return true;
      }
    }

    return false;
  }

  public void clearCurMember() {
    curMember = null;
  }

  public void clearCurBoat() {
    curBoat = null;
  }

  public void addNewMember(Registry a_registry, MemberBoatUI a_ui) throws IOException {
    a_registry.addNewMember(a_ui.getMemberId(), a_ui.getFirstName(), a_ui.getLastName(), a_ui.getPersonNum(),
        new ArrayList<Boat>());
    for (Member ms : a_registry.getMembers()) {
      System.out.println("Name is : " + ms.getFirstName());
    }
  }

  public void addBoat(String name, int id, BoatType type, double length) {
    curMember.getBoats().add(new Boat(name, id, type, length));
  }

  public void deleteCurMember() {
    // reg.getMembers().remove(curMember);
  }

  public void deleteCurBoat() {
    curMember.getBoats().remove(curBoat);
  }

  public void changeMemberInformation(int memberId, String firstName, String lastName, String personNum) {
    curMember.setMemberId(memberId);
    curMember.setFirstName(firstName);
    curMember.setLastName(lastName);
    curMember.setPersonNum(personNum);
  }

  public void changeBoatInformation(String name, int id, BoatType type, double length) {
    curBoat.setName(name);
    curBoat.setId(id);
    curBoat.setType(type);
    curBoat.setLength(length);
  }

  @Override
  public void onAddNewMember(Iterable<Member> members, Member member) {
    uI.onAddNewMember(members, member);
  }

  @Override
  public void onAddNewBoat(Iterable<Boat> a_allBoats, Boat b) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateMember(Member a_oldMember, Member a_newMember) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateBoat(Member m, Boat a_newBoat) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onDeleteMember(Member a_deleteMember) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onDeleteBoat(Member m, Boat a_deleteboat) {
    // TODO Auto-generated method stub
    int boatNum = 10;

    while (boatNum > 0) {
      boatNum--;
    }
  }

}