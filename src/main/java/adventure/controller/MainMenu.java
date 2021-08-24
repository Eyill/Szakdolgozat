package adventure.controller;

import adventure.common_files.CommonMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu extends CommonMenu {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

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

    exitButton.setOnAction(event -> {
      Stage stage = (Stage) exitButton.getScene().getWindow();
      stage.close();
    });

    newGameButton.setOnAction(e -> {
      changeWindow(newGameButton, "/adventure/fxml_files/create_new_game.fxml");
    });
  }

}