package adventure.modals.controllers;

import adventure.common_files.CommonMenu;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePauseController extends CommonMenu {

  @FXML
  private Pane pauseMenu;

  @FXML
  private Button resumeButton;

  @FXML
  private Button saveButton;

  @FXML
  private Button loadButton;

  @FXML
  private Button exitButton;

  @FXML
  void initialize() {
    animateButtons(resumeButton);
    animateButtons(saveButton);
    animateButtons(loadButton);
    animateButtons(exitButton);

    saveButton.setOnAction(e -> {
      System.out.println("Saving game!");
      UserDataHandler.saveGame(UserDataHandler.player, UserDataHandler.gameplayId, UserDataHandler.gameMap.getMapId());
    });

    loadButton.setOnAction(e -> {
    });

    resumeButton.setOnAction(e -> {
      pauseMenu.setVisible(false);
    });

    exitButton.setOnAction(e -> {
      Stage stage = (Stage) exitButton.getScene().getWindow();
      stage.close();
    });
  }
}
