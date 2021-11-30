package adventure.controller;

import adventure.common_files.CommonMenu;
import adventure.entity.Enemy;
import adventure.misc.UserDataHandler;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends CommonMenu {
  @FXML
  private AnchorPane gameWindow;

  @FXML
  private Button newGameButton;

  @FXML
  private Button loadGameButton;

  @FXML
  private Button optionsButton;

  @FXML
  private Button exitButton;

  @FXML
  void initialize() {
    setBackgroundImage(gameWindow, "/adventure/ui/images/main_menu.png");
    animateButtons(newGameButton);
    animateButtons(loadGameButton);
    animateButtons(optionsButton);
    animateButtons(exitButton);

    gameWindow.getChildren().add(CommonMenu.runningSprite);
    CommonMenu.runningSprite.setLayoutY(50);

    List<Button> buttonList = Arrays.asList(newGameButton,loadGameButton,exitButton);
    CommonMenu.startAnimation(buttonList);

    newGameButton.setOnAction(e -> {
      changeWindow(newGameButton, "/adventure/fxml_files/create_new_game.fxml");
    });

    loadGameButton.setOnAction(e -> {
      changeWindow(loadGameButton, "/adventure/fxml_files/load_game.fxml");
    });

    exitButton.setOnAction(event -> {
      Stage stage = (Stage) exitButton.getScene().getWindow();
      stage.close();
    });
  }


}