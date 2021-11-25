package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.UserDataHandler;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
  private int playerId;
  private String playerName;
  private int experienceToLvLUp;
  private int experience;
  private Backpack backpack;
  public ObjectProperty<ObservableList<Quest>> quests[];

  public Player(
          String path,
          int lvl,
          int health,
          int current_health,
          int defense,
          int damage,
          int criticalDamage,
          int requiredExperience,
          int currentExperience,
          String name,
          int x,
          int y
  ) {
    super(UserDataHandler.class.getResource(path).toString(), lvl, health, current_health, defense, damage, criticalDamage, x, y,15);
    setExperienceToLvLUp(requiredExperience);
    setExperience(currentExperience);
    setPlayerName(name);
    setImagePath(path);
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getExperienceToLvLUp() {
    return experienceToLvLUp;
  }

  public void setExperienceToLvLUp(int experienceToLvLUp) {
    this.experienceToLvLUp = experienceToLvLUp;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public Backpack getBackpack() {
    return backpack;
  }

  public void setBackpack(Backpack backpack) {
    this.backpack = backpack;
  }

  public void attack(Enemy enemy) {
    if (enemy == null) {
      return;
    }

    if (isColliding(this, enemy)) {
      setAttacking(true);
      enemy.setCurrentHealth(enemy.getCurrentHealth() - getDamage());
    }

    if (enemy.getCurrentHealth() <= 0) {
      this.setAttacking(false);
      setExperience(getExperience() + enemy.death());
      heal();
    }
  }

  public void talkWithNPC(NPC npc) {
    if (isColliding(this, npc)) {
      System.out.println("Talking with: " + npc.name);
      npc.showItems();
    }
  }

  public void death() {
    if (this.getCurrentHealth() <= 0) {
      super.spriteDeath();
    }
  }

  public void heal() {
    while (this.getCurrentHealth() < this.getTotalHealth() && !this.isAttacking()) {
      this.setCurrentHealth(this.getCurrentHealth() + 1);
    }
  }

  public double lvlUpHandler() {
    if (getExperience() >= getExperienceToLvLUp()) {
      this.setLvl(this.getLvl() + 1);
      setExperienceToLvLUp(getExperienceToLvLUp() * 2);
      setExperience(0);
      return (double) getExperience();
    }
    return (double) getExperience() / (double) getExperienceToLvLUp();
  }

  public void moveUp(int coefficient) {
    setLayoutY(getLayoutY() - coefficient);
  }

  public void moveDown(int coefficient) {
    setLayoutY(getLayoutY() + coefficient);
  }

  public void moveLeft(int coefficient) {
    setLayoutX(getLayoutX() - coefficient);
  }

  public void moveRight(int coefficient) {
    setLayoutX(getLayoutX() + coefficient);
  }

  public void handleKeys(KeyEvent event) {
    switch (event.getCode()) {
      case W:
        this.moveUp(2);
        break;
      case S:
        this.moveDown(2);
        break;
      case A:
        this.moveLeft(2);
        break;
      case D:
        this.moveRight(2);
        break;
    }
  }
}