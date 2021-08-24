package adventure.controller;

import adventure.entity.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class RunningGame {

  @FXML
  private AnchorPane gameWindow;

  @FXML
  private ImageView playerImage;

  @FXML
  private Button questButton;

  @FXML
  private Button playerButton;

  @FXML
  private Button backpackButton;

  @FXML
  private Button menuButton;

  private Player player;

  @FXML
  public void initialize() {
    setBackgroundImage(gameWindow, "/adventure/fxml_files/game_maps/map1.png");
    gameWindow.requestFocus();
    player = new Player("/adventure/entities/1.png", "jatekos",20 , 40, 0, 0, 0, 0, 0);
    
    gameWindow.addEventFilter(KeyEvent.KEY_PRESSED, event->{
      if (event.getCode() == KeyCode.SPACE) {
        System.out.println("ATTACK"); //call player's attack method
      }
    });

    questButton.setOnAction(e->{System.out.println("Quest button clicked!");});
    playerButton.setOnAction(e->{System.out.println("Player button clicked!");});
    backpackButton.setOnAction(e->{System.out.println("Backpack button clicked!");});
    menuButton.setOnAction(e->{System.out.println("Menu button clicked!");});
  }

  public void setBackgroundImage(AnchorPane anchorPane, String resourceLocation){
    BackgroundImage myBI= new BackgroundImage(
            new Image(getClass().getResource(resourceLocation).toString()),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );

    anchorPane.setBackground(new Background(myBI));
  }

  @FXML
  private void handleOnKeyPressed(KeyEvent event) {
    player.handleKeys(event);
    playerImage.setX(player.getPositionX());
    playerImage.setY(player.getPositionY());
    System.out.println(player.getPositionX());
    System.out.println(player.getPositionY());
  }
}