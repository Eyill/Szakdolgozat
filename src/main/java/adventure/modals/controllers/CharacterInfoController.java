package adventure.modals.controllers;

import adventure.misc.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CharacterInfoController {
  @FXML
  private Label playerName;

  @FXML
  private Label playerLvl;

  @FXML
  private Label playerHp;

  @FXML
  private Label playerDamage;

  @FXML
  private Label playerDefense;

  @FXML
  private Label playerCrit;

  @FXML
  void initialize() {
    playerName.setText(UserDataHandler.player.getPlayerName());
    playerLvl.setText(String.valueOf(UserDataHandler.player.getLvl()));
    playerHp.setText(String.valueOf(UserDataHandler.player.getTotalHealth()));
    playerDamage.setText(String.valueOf(UserDataHandler.player.getDamage()));
    playerDefense.setText(String.valueOf(UserDataHandler.player.getDefense()));
    playerCrit.setText(String.valueOf(UserDataHandler.player.getCriticalDamage()));
  }

}