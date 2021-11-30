package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.UserDataHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import java.util.Random;

public class Player extends Sprite {
  private int playerId;
  private String playerName;
  private int experienceToLvLUp;
  private int experience;
  private int gold;
  private Backpack backpack;
  private boolean isRunning = false;
  private int animationFrame = 0;
  private String defaultImage = "/adventure/entities/player/player.gif";
  private String right, left;
  private String direction = defaultImage;

  public Player(
          String path,
          int lvl,
          int health,
          int currentHealth,
          int defense,
          int damage,
          int criticalDamage,
          int requiredExperience,
          int currentExperience,
          String name,
          int x,
          int y
  ) {
    super(UserDataHandler.class.getResource(path).toString(), lvl, health, currentHealth, defense, damage, criticalDamage, x, y, 5);
    setExperienceToLvLUp(requiredExperience);
    setExperience(currentExperience);
    setPlayerName(name);
    setImagePath(path);
    setRunningImages();
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getExperienceToLvLUp() {
    return experienceToLvLUp;
  }

  public void setExperienceToLvLUp(int experienceToLvLUp) {
    this.experienceToLvLUp = experienceToLvLUp;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public Backpack getBackpack() {
    return backpack;
  }

  public void setBackpack(Backpack backpack) {
    this.backpack = backpack;
  }

  public void attack(Enemy enemy) {
    if (enemy == null) {
      return;
    }

    if (isColliding(this, enemy)) {
      setAttacking(true);

      Random rand = new Random();
      int damage = rand.nextInt(getDamage());
      int trueDamage = damage - rand.nextInt(enemy.getDefense());
      if (trueDamage < 0) trueDamage = 0;

      enemy.setCurrentHealth(enemy.getCurrentHealth() - trueDamage);
    }

    if (enemy.getCurrentHealth() <= 0) {
      this.setAttacking(false);
      setExperience(getExperience() + enemy.death());
      Random random = new Random();
      random.nextDouble();
      this.gold += enemy.getGold();
    }
  }

  public void talkWithNPC(NPC npc) {
    if (isColliding(this, npc)) {
      System.out.println("Talking with: " + npc.name);
      UserDataHandler.targetNPC = npc;
    }
  }

  public void death() {
    if (this.getCurrentHealth() <= 0) {
      super.spriteDeath();
    }
  }

  public void heal() {
    while (this.getCurrentHealth() < this.getTotalHealth() && !this.isAttacking()) {
      this.setCurrentHealth(this.getCurrentHealth() + 1);
    }
  }

  public double lvlUpHandler() {
    if (getExperience() >= getExperienceToLvLUp()) {
      this.setLvl(this.getLvl() + 1);
      setExperienceToLvLUp(getExperienceToLvLUp() * 2);
      setExperience(0);
      return (double) getExperience();
    }
    return (double) getExperience() / (double) getExperienceToLvLUp();
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

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void handleKeys(KeyEvent event) {
    String previousDirection = direction;
    switch (event.getCode()) {
      case W:
        this.moveUp(2);
        break;
      case S:
        this.moveDown(2);
        break;
      case A:
        direction = "left";
        this.moveLeft(2);
        break;
      case D:
        direction = "right";
        this.moveRight(2);
        break;
      default:
        direction = "idle";
        break;
    }
    if (animationFrame == 5 || previousDirection != direction) {
      animationFrame = 0;
    }
    animationFrame += 1;
  }

  public void setRunningImages() {
    right = "/adventure/entities/player/right_";
    left = "/adventure/entities/player/left_";
  }

  public void setPlayerImage(String directionFrame, int currentFrame) {
    this.setImage(new Image(UserDataHandler.class.getResource(directionFrame + currentFrame + ".png").toString(),
            25,
            25,
            false,
            false));
  }

  public void setDefaultImage() {
    this.setImage(new Image(UserDataHandler.class.getResource(defaultImage).toString(),
            25,
            25,
            false,
            false));
  }

  public void run() {
    switch (direction) {
      case "right":
        checkCurrentFrame(right);
        break;
      case "left":
        checkCurrentFrame(left);
        break;
      case "idle":
        setDefaultImage();
        break;
      default:
        break;
    }
  }

  public void checkCurrentFrame(String direction) {
    switch (animationFrame) {
      case 0:
        setPlayerImage(direction, 0);
        break;
      case 1:
        setPlayerImage(direction, 1);
        break;
      case 2:
        setPlayerImage(direction, 2);
        break;
      case 3:
        setPlayerImage(direction, 3);
        break;
      case 4:
        setPlayerImage(direction, 4);
        break;
      case 5:
        setPlayerImage(direction, 5);
        break;
      default:
        break;
    }
  }
}