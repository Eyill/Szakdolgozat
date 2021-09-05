package adventure.common_files;

import java.io.*;

import adventure.entity.Enemy;
import adventure.entity.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Sprite extends ImageView {

  private IntegerProperty lvl = new SimpleIntegerProperty(this,"lvl");
  private IntegerProperty totalHealth = new SimpleIntegerProperty(this,"totalHealth");;
  private IntegerProperty currentHealth = new SimpleIntegerProperty(this,"currentHealth");
  private IntegerProperty defense = new SimpleIntegerProperty(this,"defense");
  private IntegerProperty damage = new SimpleIntegerProperty(this,"damage");
  private IntegerProperty criticalDamage = new SimpleIntegerProperty(this,"criticalDamage");

  private boolean isAttacking = false;
  private boolean alive = true;

  public Sprite(
          String imagePath,
          int level,
          int maxHealth,
          int def,
          int dam,
          int crit
  ) {
    super(new Image(imagePath,30,30,false, false));
    lvl.set(level);
    totalHealth.set(maxHealth);
    currentHealth.set(totalHealth.get());
    defense.set(def);
    damage.set(dam);
    criticalDamage.set(crit);
  }

  public int getLvl() {
    return lvl.get();
  }

  public IntegerProperty lvlProperty() {
    return lvl;
  }

  public void setLvl(int lvl) {
    this.lvl.set(lvl);
  }

  public int getTotalHealth() {
    return totalHealth.get();
  }

  public IntegerProperty totalHealthProperty() {
    return totalHealth;
  }

  public void setTotalHealth(int totalHealth) {
    this.totalHealth.set(totalHealth);
  }

  public int getCurrentHealth() {
    return currentHealth.get();
  }

  public IntegerProperty currentHealthProperty() {
    return currentHealth;
  }

  public void setCurrentHealth(int currentHealth) {
    this.currentHealth.set(currentHealth);
  }

  public int getDefense() {
    return defense.get();
  }

  public IntegerProperty defenseProperty() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense.set(defense);
  }

  public int getDamage() {
    return damage.get();
  }

  public IntegerProperty damageProperty() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage.set(damage);
  }

  public int getCriticalDamage() {
    return criticalDamage.get();
  }

  public IntegerProperty criticalDamageProperty() {
    return criticalDamage;
  }

  public void setCriticalDamage(int criticalDamage) {
    this.criticalDamage.set(criticalDamage);
  }

  public boolean isAttacking() {
    return isAttacking;
  }

  public void setAttacking(boolean attacking) {
    isAttacking = attacking;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public boolean getIsAttacking() {
    return this.isAttacking;
  }

  public void setIsAttacking(boolean attack) {
    this.isAttacking = attack;
  }


  public void spriteDeath() {
    this.alive = false;
    this.setImage(null);
  }

  public void startAttacking(){
    //sprite attack animation
    this.setIsAttacking(true);
  }

  public void stopAttacking(String imagePath){
    this.setImage(new Image(imagePath));
    this.setIsAttacking(false);
  }

  public boolean isColliding(Player player, Enemy enemy){
    int sumOfRadius = player.getRadius() + enemy.getRadius();
    return sumOfRadius > getDistance(player,enemy);
  }

  public double getDistance(Player player, Enemy enemy){
    double a = (player.getLayoutX() - enemy.getLayoutX());
    double b = (player.getLayoutY() - enemy.getLayoutY());
    return Math.sqrt(a * a + b * b);
  }

}