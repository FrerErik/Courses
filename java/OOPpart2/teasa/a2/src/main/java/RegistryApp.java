import model.domain.Registry;
import model.persistence.RepeatPersistence;

/** */
public class RegistryApp {

  public static void main(String[] args) {
    Registry reg = new Registry();
    RepeatPersistence.loadHardData(reg);
  }

}
