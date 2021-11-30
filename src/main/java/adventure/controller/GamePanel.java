package adventure.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GamePanel extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/adventure/fxml_files/running_game.fxml"));
    Scene scene = new Scene(root, 700, 400);
    stage.setScene(scene);
    stage.show();
  }
}