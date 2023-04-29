package view;

import model.domain.Member;
import model.domain.Boat;
import model.domain.BoatType;

import java.io.IOException;
import java.util.ArrayList;

public interface MemberBoatUI {

  // Member related getters regarding input
  public int getMemberId();

  public String getFirstName();

  public String getLastName();

  public String getPersonNum();

  public ArrayList<Boat> getBoats();

  public model.domain.Member showComposeList(Iterable<Member> members) throws IOException;

  public model.domain.Member showVerboseList(Iterable<Member> members) throws IOException;

  MemberOption getMemberOptions() throws IOException;

  BoatOption getBoatOptions(model.domain.Member selected_Member) throws IOException;

  void onAddNewMember(Iterable<Member> members, Member newMember);

  void onAddNewBoat(Iterable<Boat> boats, Boat newBoat);

  void onDeleteMember(Iterable<Member> members, Member deleteMember);

  void onDeleteBoat(Iterable<Boat> boats, Boat deleteBoat);

  void onChangeCurrentMember(Member newMember);

  void onChangeCurrentBoat(Boat newBoat);
}
