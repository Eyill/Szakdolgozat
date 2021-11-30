package adventure.modals;

import adventure.common_files.Modal;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TradeModal implements Modal {
  Pane tradeWindow;

  public TradeModal() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/adventure/ui/fxml_files/trade_modal.fxml"));
    try {
      tradeWindow = loader.load();
      tradeWindow.setLayoutX(426);
      tradeWindow.setLayoutY(-7);

    } catch (
            IOException e) {
      e.printStackTrace();
    }
  }

  public Pane getContent(){
    return tradeWindow;
  }
}