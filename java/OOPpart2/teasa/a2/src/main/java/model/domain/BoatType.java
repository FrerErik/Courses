package model.domain;

/** */
public enum BoatType {
  SAILBOAT, MOTORSAILER, KAYAK, No_unit;

  /**
   * @param type
   * @return
   */
  public static BoatType fromString(String type) {
    if (type == null || type.trim().equals("")) {
      return No_unit;
    }
    for (BoatType boatType : BoatType.values()) {
      if (boatType.name().equalsIgnoreCase(type)) {
        return boatType;
      }
    }
    throw new IllegalStateException("Unknown boat type, please add it to enum: " + type);
  }
}
