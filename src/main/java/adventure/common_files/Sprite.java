package adventure.common_files;

import adventure.entity.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

  private int lvl;
  private int totalHealth;
  private int currentHealth;
  private int defense;
  private int damage;
  private int criticalDamage;
  private String imagePath;

  private boolean isAttacking;
  private boolean isAlive = true;
  private final int radius;

  public Sprite(
          String image,
          int level,
          int maxHealth,
          int current_health,
          int def,
          int dam,
          int crit,
          int x,
          int y,
          int radius
  ) {
    super(new Image(image, 25, 25, false, false));
    setLvl(level);
    setTotalHealth(maxHealth);
    setCurrentHealth(current_health);
    setDefense(def);
    setDamage(dam);
    setCriticalDamage(crit);
    setLayoutX(x);
    setLayoutY(y);
    this.radius = radius;
  }

  public int getLvl() {
    return lvl;
  }

  public void setLvl(int lvl) {
    this.lvl = lvl;
  }

  public int getTotalHealth() {
    return totalHealth;
  }

  public void setTotalHealth(int totalHealth) {
    this.totalHealth = totalHealth;
  }

  public int getCurrentHealth() {
    return currentHealth;
  }

  public void setCurrentHealth(int currentHealth) {
    this.currentHealth = currentHealth;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public int getCriticalDamage() {
    return criticalDamage;
  }

  public void setCriticalDamage(int criticalDamage) {
    this.criticalDamage = criticalDamage;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public boolean isAttacking() {
    return isAttacking;
  }

  public void setAttacking(boolean attacking) {
    isAttacking = attacking;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public int getRadius() {
    return radius;
  }

  public void spriteDeath() {
    this.isAlive = false;
    this.setImage(null);
  }

  public boolean isColliding(Player player, Sprite sprite) {
    int sumOfRadius = player.getRadius() + sprite.getRadius();
    return sumOfRadius > getDistance(player, sprite);
  }

  public double getDistance(Player player, Sprite sprite) {
    double a = (player.getLayoutX() - sprite.getLayoutX());
    double b = (player.getLayoutY() - sprite.getLayoutY());
    return Math.sqrt(a * a + b * b);
  }

}