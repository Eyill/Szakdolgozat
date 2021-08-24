package adventure.entity;

import adventure.common_files.Sprite;

public class Enemy extends Sprite {
  private int experienceDrop;

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
          int positionX,
          int positionY,
          int lvl,
          int health,
          int defense,
          int damage,
          int criticalDamage,
          int experienceDrop
  ) {
    super(imagePath, enemyName, 16, 16, positionX, positionY, lvl, health, health, defense, damage, criticalDamage);
    this.experienceDrop = experienceDrop;
  }

  public int getDropExperience() {
    return experienceDrop;
  }

  public void attackPlayer(Player player){
    // if player is nearby then start attacking (while player is alive)
    // if player attacks absorbe some damage
    // with some percentage use critical damage
    // if player health is 0 then stop attacking -> Game Over for the Player
  }

  public int death(){
    if(this.getCurrentHealth() <= 0){
      super.spriteDeath();
      return this.experienceDrop;
    }
    return 0;
  }
}
