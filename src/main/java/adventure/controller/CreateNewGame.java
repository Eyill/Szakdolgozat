package adventure.controller;

import adventure.common_files.CommonMenu;
import adventure.entity.Player;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
      UserDataHandler.player = new Player(UserDataHandler.class.getResource("/adventure/entities/player/player.gif").toString(),100,6 , 6, 0, 0, 0);
      //Player player = new Player("/resources/entities/1.png",6,6 , 6, 0, 0, 0);
      GamePanel gamePanel = new GamePanel();
      try {
        gamePanel.start((Stage)continueButton.getScene().getWindow());
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });

    returnButton.setOnAction(e -> {
      changeWindow(returnButton, "/adventure/fxml_files/main_menu.fxml");
    });
  }
}