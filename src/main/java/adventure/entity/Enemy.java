package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import adventure.misc.Vector;

import java.util.List;
import java.util.Random;

public class Enemy extends Sprite {
  private int id;
  private String name;
  private int experienceDrop;
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
   * @param String imagePath
   * @param String entityName
   * @param int    positionX
   * @param int    positionY
   * @param int    lvl
   * @param int    (current)health
   * @param int    defense
   * @param int    damage
   * @param int    criticalDamage
   * @param int    experienceDrop
   */

  public Enemy(
          String imagePath,
          String enemyName,
          int lvl,
          int health,
          int defense,
          int damage,
          int criticalDamage,
          int experienceDrop,
          int x,
          int y
  ) {
    super(UserDataHandler.class.getResource(imagePath).toString(), lvl, health, health, defense, damage, criticalDamage, x, y);
    this.name = enemyName;
    this.experienceDrop = experienceDrop;
  }

  public int getDropExperience() {
    return experienceDrop;
  }

  public int getGold() {
    return gold;
  }

  public void attackPlayer(Player player) {
    if (isColliding(player, this) && player.getCurrentHealth() >= 0) {
      player.setCurrentHealth(player.getCurrentHealth() - getDamage());
    }
  }

  public void movementHandler(Player player) {
    double distance = getDistance(player, this);
    if (followRadius > getDistance(player, this)) {
      double x = player.getLayoutX() - getLayoutX();
      double y = player.getLayoutY() - getLayoutY();
      x *= speed;
      y *= speed;

      setLayoutX(getLayoutX() + x);
      setLayoutY(getLayoutY() + y);
      if (distance < 20.0) {
        attackPlayer(player);
      }
    } else if (!getIsAttacking()) {
      defaultMove();
    }
  }

  public void defaultMove() {
    int x_position = (int) getLayoutX();
    int y_position = (int) getLayoutY();
    List<String> givenList = List.of("left", "right", "up", "down");

    Random rand = new Random();
    int randomIndex = rand.nextInt(givenList.size());
    String direction = givenList.get(randomIndex);
    int randomSteps = rand.nextInt(100);

    switch (direction) {
      case "left":
        x_position -= randomSteps;
        break;
      case "right":
        x_position += randomSteps;
        break;
      case "up":
        y_position -= randomSteps;
        break;
      case "down":
        y_position += randomSteps;
        break;
    }

    int new_x = (int) Math.ceil(((double) x_position) / 16);
    int new_y = (int) Math.ceil(((double) y_position) / 16);

    if (new_y < 25 && new_x < 43 && new_y >= 0 && new_x >= 0) {
      if (!TileManager.gameMap[new_y][new_x].collidable) {
        Vector target = new Vector(x_position, y_position);

        double x = target.x - getLayoutX();
        double y = target.y - getLayoutY();
        x *= speed;
        y *= speed;

        setLayoutX(getLayoutX() + x);
        setLayoutY(getLayoutY() + y);
      }
    }
  }

  public int death() {
    if (this.getCurrentHealth() <= 0) {
      this.setIsAttacking(false);
      super.spriteDeath();
      return this.experienceDrop;
    }
    return 0;
  }

}