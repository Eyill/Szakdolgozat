package adventure.controller;

import adventure.common_files.CommonMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateNewGame extends CommonMenu {

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

    continueButton.setOnAction(e -> {
      //Player player = new Player("/resources/entities/1.png", playerName.getText(),6 , 6, 0, 0, 0, 0, 0);
      changeWindow(continueButton, "/adventure/fxml_files/running_game.fxml");
    });

    returnButton.setOnAction(e -> {
      changeWindow(returnButton, "/adventure/fxml_files/main_menu.fxml");
    });
  }
}