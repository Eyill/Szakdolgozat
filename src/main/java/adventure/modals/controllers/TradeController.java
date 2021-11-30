package adventure.modals.controllers;

import adventure.entity.Item;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class TradeController {

  @FXML
  private GridPane NPCbackpack;

  @FXML
  private GridPane playerBackpack;

  @FXML
  void initialize() {
    loadBackpackItems();
  }

  public void loadBackpackItems() {
    if (!UserDataHandler.player.getBackpack().getPack().isEmpty()) {
      if (UserDataHandler.targetNPC != null && !UserDataHandler.targetNPC.getBackpack().getPack().isEmpty()) {
        int row = 0;
        int col = 0;

        for (Map.Entry<Item, Integer> entry : UserDataHandler.targetNPC.getBackpack().getPack().entrySet()) {
          Label text = new Label(String.valueOf(entry.getValue()));
          NPCbackpack.add(entry.getKey(), col, row);
          NPCbackpack.add(text, col, row);

          col++;
          if (col % 6 == 0) {
            row++;
            col = 0;
          }
        }
      }
    }
  }
}