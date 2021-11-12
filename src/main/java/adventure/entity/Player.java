package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.UserDataHandler;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
  public IntegerProperty playerId = new SimpleIntegerProperty(this, "id");
  public StringProperty playerName = new SimpleStringProperty(this, "playerName");
  public ObjectProperty<ObservableList<Quest>> quests[];
  private IntegerProperty experienceToLvLUp = new SimpleIntegerProperty(this, "experienceToLvLUp");
  private IntegerProperty experience = new SimpleIntegerProperty(this, "experience");
  private final int radius = 15;
  public Backpack backpack;

  /**
   * @param String imagePath
   * @param int lvl
   * @param int health
   * @param int currenthealth
   * @param int defense
   * @param int damage
   * @param int criticalDamage
   * @param int experienceToLvLUp
   */

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
    super(UserDataHandler.class.getResource(path).toString(), lvl, health, current_health, defense, damage, criticalDamage,x,y);
    experienceToLvLUp.set(requiredExperience);
    experience.set(currentExperience);
    playerName.set(name);
    setImagePath(path);
    backpack = new Backpack();
  }

  public int getExperienceToLvLUp() {
    return experienceToLvLUp.get();
  }

  public IntegerProperty experienceToLvLUpProperty() {
    return experienceToLvLUp;
  }

  public void setExperienceToLvLUp(int experienceToLvLUp) {
    this.experienceToLvLUp.set(experienceToLvLUp);
  }

  public int getExperience() {
    return experience.get();
  }

  public IntegerProperty experienceProperty() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience.set(experience);
  }

  public int getRadius() {
    return radius;
  }

  public String getPlayerName() {
    return playerName.get();
  }

  public StringProperty playerNameProperty() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName.set(playerName);
  }

  public int getPlayerId() {
    return playerId.get();
  }

  public IntegerProperty playerIdProperty() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId.set(playerId);
  }

  public void attack(Enemy enemy) {
    if (enemy == null) {
      return;
    }

    if (isColliding(this, enemy)) {
      setIsAttacking(true);
      enemy.setCurrentHealth(enemy.getCurrentHealth() - getDamage());
    }

    if (enemy.getCurrentHealth() <= 0) {
      this.setIsAttacking(false);
      experience.set(experience.get() + enemy.death());
      heal();
    }
  }

  public void talkWithNPC(NPC npc){
    if (isColliding(this,npc)){
      npc.showItems();
    }
  }

  public void death() {
    if (this.getCurrentHealth() <= 0) {
      super.spriteDeath();
    }
  }

  public void heal() {
    while (this.getCurrentHealth() < this.getTotalHealth() && !this.getIsAttacking()) {
      this.setCurrentHealth(this.getCurrentHealth() + 1);
    }
  }

  public double lvlUpHandler() {
    if (experience.get() >= experienceToLvLUp.get()) {
      this.setLvl(this.getLvl() + 1);
      experienceToLvLUp.set(experienceToLvLUp.get()*2);
      experience.set(0);
      return (double) experience.get();
    }
    return (double) experience.get() / (double) experienceToLvLUp.get();
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