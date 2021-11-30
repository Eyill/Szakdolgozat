package adventure.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TempPage extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    root.setPrefSize(700, 400);
    Scene scene = new Scene(root, 700, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
