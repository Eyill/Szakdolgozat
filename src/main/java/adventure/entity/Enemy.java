package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import adventure.misc.Vector;
import javafx.scene.control.ProgressBar;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Enemy extends Sprite {
  private int id;
  private final String name;
  private final int experienceDrop;
  private final int followRadius = 100;
  private final float speed = 0.01f;
  private final int gold;
  private List<Vector> lastLocation = new ArrayList<>();
  private ProgressBar healthBar;

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
          int y,
          int gold
  ) {
    super(UserDataHandler.class.getResource(imagePath).toString(), lvl, health, health, defense, damage, criticalDamage, x, y, 22);
    this.name = enemyName;
    this.experienceDrop = experienceDrop;
    this.gold = gold;
  }

  public int getDropExperience() {
    return experienceDrop;
  }

  public int getGold() {
    return gold;
  }

  public String getName() {
    return name;
  }

  public ProgressBar getHealthBar() {
    return healthBar;
  }

  public void setHealthBar(ProgressBar healthBar) {
    this.healthBar = healthBar;
  }

  public void attackPlayer(Player player) {
    if (isColliding(player, this) && player.getCurrentHealth() >= 0) {
      Random rand = new Random();
      int damage = rand.nextInt(getDamage());
      int trueDamage = damage - rand.nextInt(player.getDefense());
      if (trueDamage < 0) trueDamage = 1;
      player.setCurrentHealth(player.getCurrentHealth() - trueDamage);
    }
    setAttacking(false);
  }

  public void movementHandler(Player player) {
    double distance = getDistance(player, this);

    if (followRadius > getDistance(player, this)) {
      setAttacking(true);
      getNextPosition(player);
      if (distance < getRadius()) {
        attackPlayer(player);
      }
    } else if (!isAttacking()) {
      defaultMove();
    }
  }

  public void defaultMove() {
    int xPosition = (int) getLayoutX();
    int yPosition = (int) getLayoutY();
    List<String> givenList = List.of("left", "right", "up", "down");

    Random rand = new Random();
    int randomIndex = rand.nextInt(givenList.size());
    String direction = givenList.get(randomIndex);
    int randomSteps = rand.nextInt(50) + 1;

    switch (direction) {
      case "left":
        xPosition -= randomSteps;
        break;
      case "right":
        xPosition += randomSteps;
        break;
      case "up":
        yPosition -= randomSteps;
        break;
      case "down":
        yPosition += randomSteps;
        break;
      default:
        break;
    }

    int newX = (int) Math.ceil(((double) xPosition) / 16);
    int newY = (int) Math.ceil(((double) yPosition) / 16);

    if (newY < 25 && newX < 43 && newY >= 0 && newX >= 0) {
      if (!TileManager.gameMap[newY][newX].collidable) {
        Vector target = new Vector(xPosition, yPosition);

        double x = target.x - getLayoutX();
        double y = target.y - getLayoutY();
        x *= speed;
        y *= speed;

        int nextPositionX = (int) Math.ceil((getLayoutX() + x) / 16);
        int nextPositionY = (int) Math.ceil((getLayoutY() + y) / 16);

        if (!TileManager.gameMap[nextPositionY][nextPositionX].collidable) {
          setLayoutX(getLayoutX() + x);
          setLayoutY(getLayoutY() + y);
        }
      }
    }
  }

  public int death() {
    if (this.getCurrentHealth() <= 0) {
      super.spriteDeath();
      return experienceDrop;
    }
    return 0;
  }

  public void getNextPosition(Player player) {
    Vector currentPosition = new Vector((int) this.getLayoutX(), (int) this.getLayoutY());

    List<Vector> availablePositions = new ArrayList<>();
    availablePositions.add(new Vector(currentPosition.x + 1, currentPosition.y));
    availablePositions.add(new Vector(currentPosition.x + 1, currentPosition.y + 1));
    availablePositions.add(new Vector(currentPosition.x, currentPosition.y + 1));
    availablePositions.add(new Vector(currentPosition.x - 1, currentPosition.y));
    availablePositions.add(new Vector(currentPosition.x - 1, currentPosition.y - 1));
    availablePositions.add(new Vector(currentPosition.x, currentPosition.y - 1));
    availablePositions.add(new Vector(currentPosition.x - 1, currentPosition.y + 1));
    availablePositions.add(new Vector(currentPosition.x + 1, currentPosition.y - 1));

    availablePositions.removeIf(vector -> {
      return TileManager.gameMap[vector.y / 16][vector.x / 16].collidable;
    });

    availablePositions.sort((a, b) -> {
      if (a.equals(b)) {
        return 0;
      }
      if (a.getVectorDistance(new Vector((int) player.getLayoutX(), (int) player.getLayoutY()))
              < b.getVectorDistance(new Vector((int) player.getLayoutX(), (int) player.getLayoutY()))) {
        return -1;
      }
      return 1;
    });

    lastLocation.add(currentPosition);

    availablePositions.removeIf((e) -> {
      return lastLocation.equals(e);
    });

    Vector nextPosition = availablePositions.remove(0);

    int nextPositionX = nextPosition.x;
    int nextPositionY = nextPosition.y;

    setLayoutX(nextPositionX);
    setLayoutY(nextPositionY);
  }

}