package adventure.common_files;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public abstract class CommonMenu {
  public static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: white";
  public static final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-text-fill: black ";

  public static ImageView runningSprite =
          new ImageView(new Image(
                  CommonMenu.class.getResource("/adventure/entities/player/running_player_to_right.gif").toString(),
                  32,
                  32,
                  false,
                  true)
          );
  public static boolean isRunningForward = true;

  /**
   * In this function we will animate buttons when hovering
   *
   * @param button Which button we want to animate
   */
  public void animateButtons(Button button) {
    button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
    button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
  }

  /**
   * With this function we get the current button's window, and change it's scene
   *
   * @param button           The clicked button
   * @param resourceLocation FXML file location (string)
   */
  public void changeWindow(Button button, String resourceLocation) {
    Stage stage = (Stage) button.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(resourceLocation));

    try {
      loader.load();
    } catch (IOException error) {
      error.printStackTrace();
    }

    Parent root = loader.getRoot();
    Image image = new Image(getClass().getResource("/adventure/ui/images/game_cursor.png").toString());
    Scene scene = new Scene(root, 700, 400);
    scene.setCursor(new ImageCursor(image));
    stage.setScene(scene);
  }

  public void setBackgroundImage(AnchorPane pane, String path) {
    BackgroundImage myBI = new BackgroundImage(
            new Image(getClass().getResource(path).toString(),
                    700,
                    400,
                    false,
                    true
            ),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
  }

  public static void startAnimation(List<Button> buttonList) {
    new AnimationTimer() {
      @Override
      public void handle(long now) {
        animate();
        for (Button button: buttonList) {
          if (button.isPressed()) {
            this.stop();
            System.out.println("Animation stopped!");
          }
        }
      }
    }.start();
  }

  public static void animate() {
    if (runningSprite.getLayoutX() < 710 && isRunningForward) {
      runningSprite.setLayoutX(runningSprite.getLayoutX() + 1);
    }
    if (runningSprite.getLayoutX() == 710) {
      isRunningForward = false;
      changeImage("/adventure/entities/player/running_player_to_left.gif");
    }
    if (!isRunningForward) {
      runningSprite.setLayoutX(runningSprite.getLayoutX() - 1);
    }
    if (runningSprite.getLayoutX() == -30) {
      isRunningForward = true;
      changeImage("/adventure/entities/player/running_player_to_right.gif");
    }
  }

  static void changeImage(String path) {
    runningSprite.setImage(new Image(CommonMenu.class.getResource(path).toString(),
            32,
            32,
            false,
            true
    ));
  }
}