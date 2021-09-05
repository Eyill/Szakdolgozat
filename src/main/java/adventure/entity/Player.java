package adventure.entity;

import adventure.common_files.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
  public IntegerProperty id;
  public StringProperty playerName;
  public ObjectProperty<ObservableList<Item>> items[];    // change it to items type
  public ObjectProperty<ObservableList<Quest>> quests[];   // Change it to quest type
  private IntegerProperty experienceToLvLUp = new SimpleIntegerProperty(this,"experienceToLvLUp");
  private IntegerProperty experience= new SimpleIntegerProperty(this,"experience");
  private final int radius = 15;

  /**
   *
   * @param String imagePath
   * @param int lvl
   * @param int (current)health
   * @param int defense
   * @param int damage
   * @param int criticalDamage
   * @param int experienceToLvLUp
   */

  public Player(
          String imagePath,
          int health,
          int defense,
          int damage,
          int criticalDamage,
          int requiredExperience,
          int currentExperience
  ){
    super(imagePath, 1, health, defense, damage, criticalDamage);
    experienceToLvLUp.set(requiredExperience);
    experience.set(currentExperience);
  }

  public void lvlUp(int newRequiredExperience) {
    if (experience.get() >= experienceToLvLUp.get()){
      this.setLvl(this.getLvl() + 1);
      experienceToLvLUp.set(experienceToLvLUp.get() + newRequiredExperience);
    }
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

  public void attack(Enemy enemy){
    if(isColliding(this,enemy) && enemy != null) {
      setIsAttacking(true);
      enemy.setCurrentHealth(enemy.getCurrentHealth() - getDamage());
    }
    if(enemy.getCurrentHealth() <= 0){
      this.setIsAttacking(false);
      experience.set(experience.get() + enemy.death());
    }
  }

  public void death(){
    if (this.getCurrentHealth() <= 0){
      super.spriteDeath();
    }
  }

  public void heal(){
    while(this.getCurrentHealth() < this.getTotalHealth() && !this.getIsAttacking()){
      this.setCurrentHealth(this.getCurrentHealth() + 2);
    }
  }

  public void moveUp(int coefficient) {
    if (getLayoutY() - coefficient >= 100) {
      setLayoutY(getLayoutY() - coefficient);
    }
  }

  public void moveDown(int coefficient) {
    if (getLayoutY() + coefficient < 300) {
      setLayoutY(getLayoutY() + coefficient);
    }
  }

  public void moveLeft(int coefficient) {
    if (getLayoutX() - coefficient >= 0) {
      for (int i = 0; i <= 5; i++){
        Image newImage = new Image(getClass().getResource("/adventure/entities/player/knight_run_anim_f" + i +".png").toString(),30,30,false, false);
        this.setImage(newImage);
        setLayoutX(getLayoutX() - coefficient);
      }
    }
  }

  public void moveRight(int coefficient) {
    if (getLayoutX() + coefficient < 650) {
      setLayoutX(getLayoutX() + coefficient);
    }
  }

  public void handleKeys(KeyEvent event) {
    switch (event.getCode()){
      case W:
        this.moveUp(5);
        break;
      case S:
        this.moveDown(5);
        break;
      case A:
        this.moveLeft(5);
        break;
      case D:
        this.moveRight(5);
        break;
    }
  }
}