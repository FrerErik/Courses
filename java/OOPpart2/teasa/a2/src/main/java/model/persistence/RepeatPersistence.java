package model.persistence;

import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Registry;

import java.util.ArrayList;

public class RepeatPersistence {

  public static void loadHardData(Registry rg) {
    ArrayList<Boat> jBoats = new ArrayList<>();
    Boat jFirstBoat = new Boat("Flying Dutchman", 1, BoatType.SAILBOAT, 10);
    Boat jSecondBoat = new Boat("Black Pearl", 2, BoatType.MOTORSAILER, 6);
    jBoats.add(jFirstBoat);
    jBoats.add(jSecondBoat);

    ArrayList<Boat> oBoats = new ArrayList<>();
    Boat oFirstBoat = new Boat("US Navy Boat", 1, BoatType.MOTORSAILER, 260);
    oBoats.add(oFirstBoat);

    rg.addNewMember(1, "Jack", "Sparrow", "0403208549", jBoats);
    rg.addNewMember(2, "Barrack", "Obama", "731289371", oBoats);

  }
}
