package adventure.controller;

import adventure.entity.Enemy;
import adventure.entity.Player;
import adventure.misc.GameMap;
import adventure.misc.UserDataHandler;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.List;

public class RunningGame {

  @FXML
  private AnchorPane gameWindow;

  @FXML
  private Button questButton;

  @FXML
  private Button playerButton;

  @FXML
  private Button backpackButton;

  @FXML
  private Button menuButton;

  private Player player;

  private GameMap gameMap;

  private List<Enemy> enemy;

  @FXML
  public void initialize(){
    player = UserDataHandler.player;
    enemy = UserDataHandler.gameMap.enemy;

    setBackgroundImage(gameWindow, "/adventure/fxml_files/game_maps/map1.png");
    gameWindow.requestFocus();
    gameWindow.getChildren().add(player);

    for (Enemy e: enemy) {
      int x = 50;
      gameWindow.getChildren().add(e);
      e.setLayoutY(x);
      e.setLayoutY(x);
    }

    player.setLayoutY(50);    // later: get data from db

    gameWindow.addEventFilter(KeyEvent.KEY_PRESSED, event->{
      if (event.getCode() == KeyCode.SPACE) {
        for (Enemy e: enemy) {
          player.attack(e);
          System.out.println(e.getCurrentHealth());
        }
      }
    });

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        player.setLayoutX(player.getLayoutX());
        player.setLayoutY(player.getLayoutY());
        UserDataHandler.checkEnemyLife();

        for (Enemy e: enemy) {
          e.movementHandler(player);
        }
      }
    }.start();

    questButton.setFocusTraversable(false);
    playerButton.setFocusTraversable(false);
    backpackButton.setFocusTraversable(false);
    menuButton.setFocusTraversable(false);

    questButton.setOnAction(e->{System.out.println("Quest button clicked!");});
    playerButton.setOnAction(e->{System.out.println("Player button clicked!");});
    backpackButton.setOnAction(e->{System.out.println("Backpack button clicked!");});
    menuButton.setOnAction(e->{System.out.println("Menu button clicked!");});
  }

  public void setBackgroundImage(AnchorPane anchorPane, String resourceLocation) {
    BackgroundImage myBI = new BackgroundImage(
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
  }
}