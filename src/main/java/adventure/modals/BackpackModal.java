package adventure.modals;

import adventure.common_files.Modal;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BackpackModal implements Modal {
  Pane backpackMenu;

  public BackpackModal() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/adventure/ui/fxml_files/backpack_modal.fxml"));
    try {
      backpackMenu = loader.load();
      backpackMenu.setLayoutX(423);
      backpackMenu.setLayoutY(200);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Pane getContent() {
    return backpackMenu;
  }

}