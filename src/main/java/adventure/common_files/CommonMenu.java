package adventure.common_files;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class CommonMenu {
  public static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: white";
  public static final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-text-fill: black ";

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
    Image image = new Image(getClass().getResource("/adventure/ui/game_cursor.png").toString());
    Scene scene = new Scene(root,700, 400);
    scene.setCursor(new ImageCursor(image));
    stage.setScene(scene);
  }

  public void setBackgroundImage(AnchorPane anchorPane, String resourceLocation){
    BackgroundImage myBI= new BackgroundImage(
            new Image(resourceLocation),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    anchorPane.setBackground(new Background(myBI));
  }

  public void initKeyActions(Scene scene){
    scene.setOnKeyPressed(keyAction ->{
      System.out.println(keyAction.getCode());
    });
  }

  public void getCurrentScene(Node node){
    System.out.println(node.getScene());
  }
}