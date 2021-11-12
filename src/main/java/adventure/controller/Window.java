package adventure.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Window extends Application {

  //Class for creating application window
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/adventure/fxml_files/main_menu.fxml"));
    primaryStage.setTitle("My First Adventure");
    Image image = new Image(getClass().getResource("/adventure/ui/images/game_cursor.png").toString());
    Scene scene = new Scene(root,700, 400);
    scene.setCursor(new ImageCursor(image));
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}