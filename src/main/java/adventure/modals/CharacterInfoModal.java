package adventure.modals;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CharacterInfoModal {

  @FXML
  private Pane characterMenu;

  public CharacterInfoModal() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/adventure/ui/fxml_files/character_info_modal.fxml"));
    try {
      characterMenu = loader.load();
      characterMenu.setLayoutX(240);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Pane getCharacterMenu() {
    return characterMenu;
  }

}