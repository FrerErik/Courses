package view;

import controller.RegistryController;
import model.persistence.RepeatPersistence;

import java.io.IOException;

/** */
public class Ui {
  public static void main(String[] args) throws IOException {

    controller.RegistryController rc = new RegistryController();
    model.domain.Registry m = new model.domain.Registry();
    RepeatPersistence.loadHardData(m);

    view.MemberBoatUI IUI = new Console();
    rc.handleMembers(m, IUI);
  }
}
