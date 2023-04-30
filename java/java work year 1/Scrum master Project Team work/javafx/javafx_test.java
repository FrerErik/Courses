import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class javafx_test extends Application {



@Override
public void start(Stage primaryStage) throws Exception {
Scene theScene = new Scene(new Label(”Hello”), 640, 480);
primaryStage.setScene(theScene);
primaryStage.setTitle(”First example”);
primaryStage.show();
}
  public static void main(String[] args) {
  launch(args);
  }
    
}