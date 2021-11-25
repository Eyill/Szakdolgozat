package adventure.modals;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class BackpackModal {
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

  public Pane getBackpack() {
    return backpackMenu;
  }

}