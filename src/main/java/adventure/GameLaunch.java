package adventure;

import adventure.controller.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameLaunch extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Window gameWindow = new Window();
    gameWindow.start(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
