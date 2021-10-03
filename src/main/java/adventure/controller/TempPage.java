package adventure.controller;

import adventure.misc.UserDataHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TempPage extends Application {

  //Class for creating application window
  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    root.setPrefSize(700, 400);
    root.getChildren().add(UserDataHandler.tileManager.loadGameMap("/adventure/game_maps/map2.xml"));

    Scene scene = new Scene(root,700, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
