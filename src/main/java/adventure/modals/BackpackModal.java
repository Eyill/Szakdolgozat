package adventure.modals;

import adventure.entity.Backpack;
import adventure.misc.UserDataHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class BackpackModal {
  GridPane backpack;

  public BackpackModal() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/adventure/ui/fxml_files/backpack_modal_2.fxml"));
    try {
      backpack = loader.load();
      setBackgroundImage("/adventure/ui/images/backpack.png");
      backpack.setLayoutX(423);
      backpack.setLayoutY(200);
      loadBackpackItems();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public GridPane getBackpack() {
    return backpack;
  }

  public void setBackgroundImage(String resourceLocation) {
    BackgroundImage myBI = new BackgroundImage(
            new Image(getClass().getResource(resourceLocation).toString()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    backpack.setBackground(new Background(myBI));
  }

  public void loadBackpackItems() {
  //  ImageView imageView = new ImageView(UserDataHandler.class.getResource("/adventure/entities/player/player.gif").toString());
   // backpack.add(imageView, 0, 0);
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 6; col++) {
        ImageView imageView = new ImageView(UserDataHandler.class.getResource("/adventure/entities/player/player.gif").toString());
        backpack.add(imageView, col, row);
      }
    }
  }

}