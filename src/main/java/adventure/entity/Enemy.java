package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.TileManager;
import adventure.misc.UserDataHandler;
import adventure.misc.Vector;

import java.util.*;

public class Enemy extends Sprite {
  private int id;
  private final String name;
  private final int experienceDrop;
  private final int followRadius = 100;
  private final float speed = 0.01f;
  private final int gold;
  private List<Vector> lastLocation = new ArrayList<>();

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

  public void attackPlayer(Player player) {
    if (isColliding(player, this) && player.getCurrentHealth() >= 0) {
      player.setCurrentHealth(player.getCurrentHealth() - getDamage());
    }
  }

  public void movementHandler(Player player) {
    double distance = getDistance(player, this);

    if (followRadius > getDistance(player, this)) {
      getNextPosition(player);
      if (distance < getRadius()) {
        attackPlayer(player);
      }
    } else if (!isAttacking()) {
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
    int randomSteps = rand.nextInt(50) + 1;

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

        int next_position_x = (int) Math.ceil((getLayoutX() + x) / 16);
        int next_position_y = (int) Math.ceil((getLayoutY() + y) / 16);

        if (!TileManager.gameMap[next_position_y][next_position_x].collidable) {
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

    List<Vector> csucsSzomszedai = new ArrayList<>();
    csucsSzomszedai.add(new Vector(currentPosition.x + 1, currentPosition.y));
    csucsSzomszedai.add(new Vector(currentPosition.x + 1, currentPosition.y + 1));
    csucsSzomszedai.add(new Vector(currentPosition.x, currentPosition.y + 1));
    csucsSzomszedai.add(new Vector(currentPosition.x - 1, currentPosition.y));
    csucsSzomszedai.add(new Vector(currentPosition.x - 1, currentPosition.y - 1));
    csucsSzomszedai.add(new Vector(currentPosition.x, currentPosition.y - 1));
    csucsSzomszedai.add(new Vector(currentPosition.x - 1, currentPosition.y + 1));
    csucsSzomszedai.add(new Vector(currentPosition.x + 1, currentPosition.y - 1));

    csucsSzomszedai.removeIf(vector -> {
      return TileManager.gameMap[vector.y / 16][vector.x / 16].collidable;
    });

    csucsSzomszedai.sort((a, b) -> {
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

    csucsSzomszedai.removeIf((e) -> {
      return lastLocation.equals(e);
    });

    Vector nextPosition = csucsSzomszedai.remove(0);

    int next_position_x = nextPosition.x;
    int next_position_y = nextPosition.y;

    setLayoutX(next_position_x);
    setLayoutY(next_position_y);
  }
}

