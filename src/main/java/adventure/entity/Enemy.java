package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.UserDataHandler;
import adventure.misc.Vector;
import java.util.List;

public class Enemy extends Sprite {
  private int id;
  private int experienceDrop;
  private final int radius  = 22;
  private final int followRadius = 100;
  private final float speed = 0.01f;
  private int pathPointer = 0;
  private int gold = 0;
  private final List<Vector> path = List.of(
          new Vector(100, 100),
          new Vector(600, 100),
          new Vector(600, 300),
          new Vector(100, 300));

  /**
   *
   * @param String imagePath
   * @param String entityName
   * @param int positionX
   * @param int positionY
   * @param int lvl
   * @param int (current)health
   * @param int defense
   * @param int damage
   * @param int criticalDamage
   * @param int experienceDrop
   */

  public Enemy(
          String imagePath,
          String enemyName,
          int lvl,
          int health,
          int defense,
          int damage,
          int criticalDamage,
          int experienceDrop
  ) {
    super(UserDataHandler.class.getResource(imagePath).toString(), lvl, health, health,defense, damage, criticalDamage);
    this.experienceDrop = experienceDrop;
  }

  public int getDropExperience() {
    return experienceDrop;
  }

  public int getRadius() {
    return radius;
  }

  public int getGold() {
    return gold;
  }

  public void attackPlayer(Player player){
    if(isColliding(player,this)){
      setIsAttacking(true);
      player.setCurrentHealth(player.getCurrentHealth() - getDamage());
    }

    if(player.getCurrentHealth() <= 0 ){
      setIsAttacking(false);
    }
  }

  public void movementHandler(Player player) {
    double distance = getDistance(player,this);
    if (followRadius > getDistance(player,this)) {
      double x = player.getLayoutX() - getLayoutX();
      double y = player.getLayoutY() - getLayoutY();
      x *= speed;
      y *= speed;

      setLayoutX(getLayoutX() + x);
      setLayoutY(getLayoutY() + y);
      if (distance < 20.0) {attackPlayer(player);}
    } else if (!getIsAttacking()){
      defaultMove();
    }
  }

  public void defaultMove(){
    Vector target = path.get(pathPointer);

    double a = (target.x - this.getLayoutX());
    double b = (target.y - this.getLayoutY());
    double distance = Math.sqrt(a * a + b * b);
    if (radius > distance) {
      pathPointer++;

      if (pathPointer >= path.size())  {
        pathPointer = 0;
      }
    }

    double x = target.x - getLayoutX();
    double y = target.y - getLayoutY();
    x *= speed;
    y *= speed;

    setLayoutX(getLayoutX() + x);
    setLayoutY(getLayoutY() + y);
  }

  public int death(){
    if(this.getCurrentHealth() <= 0){
      this.setIsAttacking(false);
      super.spriteDeath();
      return this.experienceDrop;
    }
    return 0;
  }

}