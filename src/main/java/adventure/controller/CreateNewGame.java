package adventure.controller;

import adventure.common_files.CommonMenu;
import adventure.misc.UserDataHandler;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class CreateNewGame extends CommonMenu {

  @FXML
  private AnchorPane gameWindow;

  @FXML
  private TextField playerName;

  @FXML
  private Button returnButton;

  @FXML
  private Button continueButton;

  @FXML
  void initialize() {
    animateButtons(returnButton);
    animateButtons(continueButton);

    setBackgroundImage(gameWindow, "/adventure/ui/images/main_menu.png");
    gameWindow.getChildren().add(CommonMenu.runningSprite);

    List<Button> buttonList = Arrays.asList(returnButton,continueButton);
    CommonMenu.startAnimation(buttonList);

    continueButton.setOnAction(e -> {
      if (playerName.getText().trim().isEmpty()) {
        Label errorMessage = new Label("Player's name cannot be empty!");
        errorMessage.setTextFill(Color.RED);
        errorMessage.setLayoutX(260);
        errorMessage.setLayoutY(150);

        gameWindow.getChildren().add(errorMessage);

      } else {
        UserDataHandler.createNewGame(playerName.getText());
        GamePanel gamePanel = new GamePanel();
        try {
          gamePanel.start((Stage) continueButton.getScene().getWindow());
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    });

    returnButton.setOnAction(e -> {
      changeWindow(returnButton, "/adventure/fxml_files/main_menu.fxml");
    });
  }
}