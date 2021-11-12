package adventure.common_files;

import adventure.entity.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

  private IntegerProperty lvl = new SimpleIntegerProperty(this, "lvl");
  private IntegerProperty totalHealth = new SimpleIntegerProperty(this, "totalHealth");
  private IntegerProperty currentHealth = new SimpleIntegerProperty(this, "currentHealth");
  private IntegerProperty defense = new SimpleIntegerProperty(this, "defense");
  private IntegerProperty damage = new SimpleIntegerProperty(this, "damage");
  private IntegerProperty criticalDamage = new SimpleIntegerProperty(this, "criticalDamage");
  private StringProperty imagePath = new SimpleStringProperty(this, "imagePath");

  private int position_x;
  private int position_y;

  private boolean isAttacking = false;
  private boolean alive = true;
  private final int radius = 22;

  public Sprite(
          String image,
          int level,
          int maxHealth,
          int current_health,
          int def,
          int dam,
          int crit,
          int x,
          int y
  ) {
    super(new Image(image, 25, 25, false, false));
    lvl.set(level);
    totalHealth.set(maxHealth);
    currentHealth.set(current_health);
    defense.set(def);
    damage.set(dam);
    criticalDamage.set(crit);
    position_x = x;
    position_y = y;
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

  public String getImagePath() {
    return imagePath.get();
  }

  public StringProperty imagePathProperty() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath.set(imagePath);
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

  public int getRadius() {
    return radius;
  }

  public int getPosition_x() {
    return position_x;
  }

  public int getPosition_y() {
    return position_y;
  }

  public void spriteDeath() {
    this.alive = false;
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