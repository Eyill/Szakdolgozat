package adventure.controller;

import adventure.entity.Enemy;
import adventure.entity.NPC;
import adventure.entity.Player;
import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import adventure.modals.BackpackModal;
import adventure.modals.CharacterInfoModal;
import adventure.modals.GamePauseModal;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class RunningGame {

  private Player player;
  private List<Enemy> enemies;
  private List<NPC> npcs;

  @FXML
  private AnchorPane gameWindow;

  @FXML
  private ProgressBar healthBar;

  @FXML
  private ProgressBar expBar;

  @FXML
  private Label playerNameLabel;

  @FXML
  private Pane labelGroup;

  @FXML
  private Label hpLabel;

  @FXML
  private Label expLabel;

  @FXML
  private Label lvlLabel;

  @FXML
  private Canvas gameTile;

  @FXML
  private Button infoButton;

  @FXML
  private Pane pauseMenu;

  @FXML
  private Button resumeButton;

  @FXML
  private Button saveButton;

  @FXML
  private Button loadButton;

  @FXML
  private Button exitButton;

  private BackpackModal backpackModal;
  private CharacterInfoModal characterInfoModal;
  private GamePauseModal gamePauseModal;

  @FXML
  public void initialize() {
    gameTile = UserDataHandler.tileManager.loadGameMap(UserDataHandler.gameMap.getMapPath());
    gameWindow.getChildren().add(gameTile);
    gameTile.toBack();

    player = UserDataHandler.player;
    player.setLayoutX(player.getPosition_x());
    player.setLayoutY(player.getPosition_y());
    gameWindow.getChildren().add(player);

    playerNameLabel.setText(player.getPlayerName());

    pauseMenu.setVisible(false);

    backpackModal = new BackpackModal();
    characterInfoModal = new CharacterInfoModal();
    gamePauseModal = new GamePauseModal();

    enemies = UserDataHandler.gameMap.enemy;
    for (Enemy enemy : enemies) {
      int y = 350;
      int x = 20;
      gameWindow.getChildren().add(enemy);
      enemy.setLayoutY(y);
      enemy.setLayoutX(x);
    }

    infoButton.setOnAction(e -> {
      labelGroup.setVisible(!labelGroup.isVisible());
      infoButton.setLayoutX(!labelGroup.isVisible() ? 78 : 182);
      infoButton.setText(!labelGroup.isVisible() ? "+" : "-");
    });

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        healthBar.setProgress((double) player.getCurrentHealth() / (double) player.getTotalHealth());
        expBar.setProgress(player.lvlUpHandler());

        player.setLayoutX(player.getLayoutX());
        player.setLayoutY(player.getLayoutY());

        hpLabel.setText(player.getTotalHealth() + " / " + player.getCurrentHealth());
        expLabel.setText(player.getExperienceToLvLUp() + " / " + player.getExperience());
        lvlLabel.setText(String.valueOf(player.getLvl()));

        UserDataHandler.checkEnemyLife();

        for (Enemy enemy : enemies) {
          enemy.movementHandler(player);
        }
      }
    }.start();

    gameWindow.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case SPACE:
          for (Enemy enemy : enemies) {
            player.attack(enemy);
          }
          break;

        case E:
          for (NPC npc : npcs) {
            player.talkWithNPC(npc);
          }
          break;

        case B:
          boolean modalVisible = backpackModal.getBackpack().isVisible();
          if (gameWindow.getChildren().contains(backpackModal.getBackpack())) {
            backpackModal.getBackpack().setVisible(!modalVisible);
          } else {
            gameWindow.getChildren().add(backpackModal.getBackpack());
          }
          break;

        case F5:
          System.out.println("Saving game!");
          UserDataHandler.saveGame(player, UserDataHandler.gameplayId, UserDataHandler.gameMap.getMapId());
          break;

        case ESCAPE:
          boolean pause = gamePauseModal.getPauseMenu().isVisible();
          if (gameWindow.getChildren().contains(gamePauseModal.getPauseMenu())) {
            gamePauseModal.getPauseMenu().setVisible(!pause);
          } else {
            gameWindow.getChildren().add(gamePauseModal.getPauseMenu());
          }
          break;

        case I:
          boolean characterInfo = characterInfoModal.getCharacterMenu().isVisible();
          if (gameWindow.getChildren().contains(characterInfoModal.getCharacterMenu())) {
            characterInfoModal.getCharacterMenu().setVisible(!characterInfo);
          } else {
            gameWindow.getChildren().add(characterInfoModal.getCharacterMenu());
          }
          break;
      }
    });

  }

  @FXML
  private void handleOnKeyPressed(KeyEvent event) {
    int x = (int) Math.ceil(player.getLayoutX() / 16);
    int y = (int) Math.ceil(player.getLayoutY() / 16);

    switch (event.getCode()) {
      case W:
        y--;
        break;
      case S:
        y++;
        break;
      case A:
        x--;
        break;
      case D:
        x++;
        break;
    }
    if (!TileManager.gameMap[y][x].collidable) {
      player.handleKeys(event);
    }
  }

}