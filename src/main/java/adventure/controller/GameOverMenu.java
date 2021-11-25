package adventure.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameOverMenu extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/adventure/fxml_files/game_over.fxml"));
    Scene scene = new Scene(root, 700, 400);
    Image image = new Image(getClass().getResource("/adventure/ui/images/game_cursor.png").toString());
    scene.setCursor(new ImageCursor(image));
    stage.setScene(scene);
    stage.show();
  }
}