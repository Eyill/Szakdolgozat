package adventure.modals.controllers;

import adventure.entity.Item;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class BackpackController {

  @FXML
  private GridPane backpackGrid;

  @FXML
  void initialize() {
    loadBackpackItems();
  }

  public void loadBackpackItems() {
    if (!UserDataHandler.player.getBackpack().getPack().isEmpty()) {
      int row = 0;
      int col = 0;

      for (Map.Entry<Item, Integer> entry : UserDataHandler.player.getBackpack().getPack().entrySet()) {
        Label text = new Label(String.valueOf(entry.getValue()));
        backpackGrid.add(entry.getKey(), col, row);
        backpackGrid.add(text, col, row);

        col++;
        if (col % 6 == 0) {
          row++;
          col = 0;
        }
      }
    }

  }
}