package adventure.controller;

import adventure.common_files.CommonMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoadGame extends CommonMenu {

  @FXML
  private Button returnButton;

  @FXML
  private AnchorPane gameWindow;

  @FXML
  void initialize(){
    animateButtons(returnButton);
    // TODO: load save options from db
    for (int i=0; i<10; i++){
      gameWindow.getChildren().add(new Button("test")) ;
      // selected box with id -> UserDataHandler.loadGame(int id)
      //  GamePanel gamePanel = new GamePanel();
      //      try {
      //        gamePanel.start((Stage)continueButton.getScene().getWindow());
      //      } catch (Exception exception) {
      //        exception.printStackTrace();
      //      }
      //    });
    }

    returnButton.setOnAction(e -> {
      changeWindow(returnButton, "/adventure/fxml_files/main_menu.fxml");
    });
  }

}