package adventure.controller;

import adventure.common_files.CommonMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOver extends CommonMenu{

  @FXML
  private Button loadGame;

  @FXML
  private Button mainMenu;

  @FXML
  void initialize(){
    animateButtons(loadGame);
    animateButtons(mainMenu);

    loadGame.setFocusTraversable(false);
    mainMenu.setFocusTraversable(false);

    loadGame.setOnAction(e -> {
      changeWindow(loadGame, "/adventure/fxml_files/load_game.fxml");
    });

    mainMenu.setOnAction(e -> {
      changeWindow(mainMenu, "/adventure/fxml_files/main_menu.fxml");
    });
  }
}