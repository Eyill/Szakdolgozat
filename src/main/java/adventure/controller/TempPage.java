package adventure.controller;

import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;

public class TempPage extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    root.setPrefSize(700, 400);

    ProgressBar bar = new ProgressBar();

    bar.setProgress(0.9);
    bar.setProgress(0.5);
    root.getChildren().add(bar);
    Scene scene = new Scene(root,700, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
