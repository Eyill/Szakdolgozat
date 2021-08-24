package adventure.common_files;

import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import java.io.FileInputStream;

public class Sprite {

  private Image spriteImage;
  private String entityName;
  private int height;
  private int width;
  private int positionX;
  private int positionY;

  private int lvl;
  private int totalHealth;
  private int currentHealth;
  private int defense;
  private int damage;
  private int criticalDamage;
  private boolean isAttacking = false;
  private boolean alive = true;

  public Sprite(
          String imagePath,
          String entityName,
          int height,
          int width,
          int positionX,
          int positionY,
          int lvl,
          int totalHealth,
          int currentHealth,
          int defense,
          int damage,
          int criticalDamage
  ) {
    this.height = height;
    this.width = width;
    this.positionX = positionX;
    this.positionY = positionY;
    this.lvl = lvl;
    this.totalHealth = totalHealth;
    this.currentHealth = totalHealth;
    this.defense = defense;
    this.damage = damage;
    this.criticalDamage = criticalDamage;

    try {
      this.spriteImage = new Image(getClass().getResource(imagePath).toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getLvl() {
    return lvl;
  }

  public void setLvl(int lvl) {
    this.lvl = lvl;
  }

  public int getTotalHealth() {
    return this.totalHealth;
  }

  public void setTotalHealth(int health) {
    this.totalHealth = health;
  }

  public int getCurrentHealth() {
    return this.currentHealth;
  }

  public void setCurrentHealth(int currentHealth) {
    this.currentHealth = currentHealth;
  }

  public int getDefense() {
    return defense;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  public int getDamage() {
    return damage;
  }

  public int getCriticalDamage() {
    return criticalDamage;
  }

  public boolean getIsAttacking() {
    return this.isAttacking;
  }

  public void setIsAttackcing(boolean attack) {
    this.isAttacking = attack;
  }

  public Image getSpriteImage() {
    return this.spriteImage;
  }

  public void setSpriteImage(String imagePath) {
    this.spriteImage = new Image(getClass().getResource(imagePath).toString());
  }

  public void moveUp() {
    if (this.getPositionY() - 5 >= -65) {
      this.positionY -= 5;
    }
  }

  public void moveDown() {
    if (this.getPositionY() + 5 < 230) {
      this.positionY += 5;
    }
  }

  public void moveLeft() {
    if (this.getPositionX() - 5 >= -50) {
      this.positionX -= 5;
    }
  }

  public void moveRight() {
    if (this.getPositionX() + 5 < 575) {
      this.positionX += 5;
    }
  }

  public void spriteDeath() {
    this.alive = false;
    this.spriteImage = null;
  }

  public void startAttacking(){
    //sprite attack animation
    this.setIsAttackcing(true);
  }

  public void stopAttacking(String imagePath){
    this.setSpriteImage(imagePath);
    this.setIsAttackcing(false);
  }

  public void handleKeys(KeyEvent event) {
    switch (event.getCode()){
      case W:
        this.moveUp();
        break;
      case S:
        this.moveDown();
        break;
      case A:
        this.moveLeft();
        break;
      case D:
        this.moveRight();
        break;
    }
  }
}