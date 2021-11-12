package adventure.modals;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GamePauseModal {
  @FXML
  private Pane pauseMenu;

  public GamePauseModal() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/adventure/ui/fxml_files/pause_modal.fxml"));
    try {
      pauseMenu = loader.load();
      pauseMenu.setLayoutX(249);
      pauseMenu.setLayoutY(91);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Pane getPauseMenu() {
    return pauseMenu;
  }
}
