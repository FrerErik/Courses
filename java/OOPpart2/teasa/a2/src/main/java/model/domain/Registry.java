package model.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.persistence.Persistence;

/**
 * Will reference Tobias' lectures regarding the setup of the registry
 */
public class Registry implements Persistence {
  private ArrayList<Member> members;
  private ArrayList<Boat> boats;

  /**
   * 
   */
  public Registry() {
    // Creating the arraylist for boats owned by the member
    boats = new ArrayList<>();
    members = new ArrayList<>();
  }

  public interface IEventListener {
    void onAddNewMember(Iterable<Member> members, Member a_member);

    void onAddNewBoat(Iterable<Boat> boats, Boat b);

    void onUpdateMember(Member a_oldMember, Member a_newMember);

    void onUpdateBoat(Member m, Boat a_newBoat);

    void onDeleteMember(Member a_deleteMember);

    void onDeleteBoat(Member m, Boat a_deleteboat);
  }

  Set<IEventListener> m_eventListeners = new HashSet<>();

  public void addEventListener(IEventListener a_listener) {
    m_eventListeners.add(a_listener);
  }

  public void removeEventListener(IEventListener a_listener) {
    m_eventListeners.remove(a_listener);
  }

  public void addNewMember(int memberId, String firstName, String lastName, String personNum,
      ArrayList<Boat> ownerBoatList) {
    Member m = new Member(memberId, firstName, lastName, personNum, ownerBoatList);
    members.add(m);
    m_eventListeners.forEach(el -> el.onAddNewMember(members, m));
  }

  public Boat addNewBoat(Member m, String name, int id, BoatType type, double length) {
    Boat b = new Boat(name, id, type, length);
    m.getBoats().add(b);
    m_eventListeners.forEach(el -> el.onAddNewBoat(m.getBoats(), b));
    return b;
  }

  public Boat updateBoat(Member a_selectedMember, Boat oldBoat, String name, int id, BoatType type, double length) {
    Boat updatedBoat = new Boat(name, id, type, length);
    removeBoat(a_selectedMember, oldBoat);
    m_eventListeners.forEach(el -> el.onUpdateBoat(a_selectedMember, updatedBoat));
    return updatedBoat;
  }

  public Member updateMember(Member a_selectedMember, int memberId, String firstName, String lastName, String personNum,
      ArrayList<Boat> ownerBoatList) {
    removeMember(a_selectedMember);
    Member updatedMember = new Member(memberId, firstName, lastName, personNum, ownerBoatList);
    m_eventListeners.forEach(el -> el.onUpdateMember(a_selectedMember, updatedMember));
    return updatedMember;
  }

  public void removeMember(Member a_selectedMember) {
    members.removeIf(member -> member.getMemberId() == (a_selectedMember.getMemberId()));
    m_eventListeners.forEach(el -> el.onDeleteMember(a_selectedMember));
  }

  public void removeBoat(Member m, Boat a_selectedBoat) {
    m.getBoats().removeIf(boat -> boat.getName().equalsIgnoreCase(a_selectedBoat.getName()));
    m_eventListeners.forEach(el -> el.onDeleteBoat(m, a_selectedBoat));
  }

  public Iterable<Member> getMembers() {
    return members;
  }

  public Iterable<Boat> getBoats() {
    return boats;
  }

  @Override
  public void load() {
  }

  @Override
  public void save() {
  }

}