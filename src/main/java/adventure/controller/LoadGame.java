package adventure.controller;

import adventure.common_files.CommonMenu;
import adventure.misc.UserDataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadGame extends CommonMenu {

  @FXML
  private Button returnButton;

  @FXML
  private ListView<String> gameSaves = new ListView<String>();

  @FXML
  void initialize() {
    animateButtons(returnButton);

    ObservableList<String> items = FXCollections.observableArrayList(UserDataHandler.gamePlayDAO.findAllGameSaves());
    gameSaves.setItems(items);

    gameSaves.setOnMouseClicked(click -> {
      if (gameSaves.getSelectionModel().getSelectedItem() != null && click.getClickCount() == 2) {
        String currentItemSelected = gameSaves.getSelectionModel().getSelectedItem();
        int gamePlayId = Integer.parseInt(currentItemSelected.substring(0, currentItemSelected.indexOf(" ")));

        UserDataHandler.loadGame(gamePlayId);
        GamePanel gamePanel = new GamePanel();

        try {
          gamePanel.start((Stage) returnButton.getScene().getWindow());
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