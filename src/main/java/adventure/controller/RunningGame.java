package adventure.controller;

import adventure.common_files.Modal;
import adventure.entity.Enemy;
import adventure.entity.NPC;
import adventure.entity.Player;
import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import adventure.modals.BackpackModal;
import adventure.modals.CharacterInfoModal;
import adventure.modals.GamePauseModal;
import adventure.modals.TradeModal;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class RunningGame {

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

  private Player player;

  private BackpackModal backpackModal;
  private TradeModal tradeModal;
  private CharacterInfoModal characterInfoModal;
  private GamePauseModal gamePauseModal;

  @FXML
  public void initialize() {
    gameTile = UserDataHandler.tileManager.loadGameMap(UserDataHandler.gameMap.getMapPath());
    gameWindow.getChildren().add(gameTile);
    gameTile.toBack();

    player = UserDataHandler.player;
    gameWindow.getChildren().add(player);

    playerNameLabel.setText(player.getPlayerName());

    backpackModal = new BackpackModal();
    characterInfoModal = new CharacterInfoModal();
    gamePauseModal = new GamePauseModal();
    tradeModal = new TradeModal();

    for (Enemy enemy : UserDataHandler.gameMap.enemy) {
      int y = 350;
      int x = 20;
      gameWindow.getChildren().add(enemy);
      enemy.setLayoutY(y);
      enemy.setLayoutX(x);
    }

    for (NPC npc : UserDataHandler.gameMap.npc) {
      gameWindow.getChildren().add(npc);
    }

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        for (Enemy enemy : UserDataHandler.gameMap.enemy) {
          enemy.movementHandler(player);
        }

        healthBar.setProgress((double) player.getCurrentHealth() / (double) player.getTotalHealth());
        expBar.setProgress(player.lvlUpHandler());

        player.setLayoutX(player.getLayoutX());
        player.setLayoutY(player.getLayoutY());
        player.run();

        hpLabel.setText(player.getTotalHealth() + " / " + player.getCurrentHealth());
        expLabel.setText(player.getExperienceToLvLUp() + " / " + player.getExperience());
        lvlLabel.setText(String.valueOf(player.getLvl()));

        UserDataHandler.checkEntityLife();

        if (gamePauseModal.getContent().isVisible()) {
          if (((Button) gamePauseModal.getContent().lookup("#mainMenuButton")).isPressed()) {
            this.stop();
          }
        }

        if (!player.isAlive()) {
          this.stop();
          GameOverMenu gameOverMenu = new GameOverMenu();
          try {
            TimeUnit.SECONDS.sleep(1);
            gameOverMenu.start((Stage) infoButton.getScene().getWindow());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }.start();

    gameWindow.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case SPACE:
          for (Enemy enemy : UserDataHandler.gameMap.enemy) {
            player.attack(enemy);
          }
          break;
        case E:
          for (NPC npc : UserDataHandler.gameMap.npc) {
            player.talkWithNPC(npc);
            checkIfModalIsAdded(tradeModal);
          }
          break;
        case B:
          checkIfModalIsAdded(backpackModal);
          break;
        case F5:
          System.out.println("Saving game!");
          UserDataHandler.saveGame(player, UserDataHandler.gameplayId, UserDataHandler.gameMap.getMapId());
          break;
        case ESCAPE:
          checkIfModalIsAdded(gamePauseModal);
          break;
        case I:
          checkIfModalIsAdded(characterInfoModal);
          break;
        default:
          break;
      }
    });

    infoButton.setOnAction(e -> {
      labelGroup.setVisible(!labelGroup.isVisible());
      infoButton.setLayoutX(!labelGroup.isVisible() ? 78 : 182);
      infoButton.setText(!labelGroup.isVisible() ? "+" : "-");
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
      default:
        break;
    }
    if (!TileManager.gameMap[y][x].collidable) {
      player.handleKeys(event);
    }
  }

  private void checkIfModalIsAdded(Modal modal) {
    boolean modalVisible = modal.getContent().isVisible();
    if (gameWindow.getChildren().contains(modal.getContent())) {
      modal.getContent().setVisible(!modalVisible);
    } else {
      gameWindow.getChildren().add(modal.getContent());
    }
  }
}