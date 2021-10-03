package adventure.controller;

import adventure.common_files.CommonMenu;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;

public class MainMenu extends CommonMenu {

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
    animateButtons(newGameButton);
    animateButtons(loadGameButton);
    animateButtons(optionsButton);
    animateButtons(exitButton);

    newGameButton.setOnAction(e -> {
      changeWindow(newGameButton, "/adventure/fxml_files/create_new_game.fxml");
    });

    loadGameButton.setOnAction(e->{
      changeWindow(loadGameButton,"/adventure/fxml_files/load_game.fxml");
    });

    exitButton.setOnAction(event -> {
      Stage stage = (Stage) exitButton.getScene().getWindow();
      stage.close();
    });

  }

}