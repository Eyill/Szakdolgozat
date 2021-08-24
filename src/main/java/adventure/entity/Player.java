package adventure.entity;

import adventure.common_files.Sprite;

public class Player extends Sprite {
  public String playerName;
  public String items[];    // change it to items type
  public String quests[];   // Change it to quest type

  private int experienceToLvLUp;
  private int experience;

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
   * @param int experienceToLvLUp
   */

  public Player(
          String imagePath,
          String playerName,
          int positionX,
          int positionY,
          int health,
          int defense,
          int damage,
          int criticalDamage,
          int experienceToLvLUp
  ){
    super(imagePath, playerName, 16, 16, positionX, positionY, 1, health, health, defense, damage, criticalDamage);
    this.experienceToLvLUp = experienceToLvLUp;
    this.experience = 0;
  }

  public void lvlUp(int newRequiredExperience) {
    if (this.experience >= experienceToLvLUp){
      this.setLvl(this.getLvl() + 1);
      this.experienceToLvLUp += newRequiredExperience;
    }
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  // if enemy is nearby and pressing SPACE button then start attacking
  // deal critical damage
  // if enemy attacks -> absorbe some damage
  // if enemy dies, then get experience

  public void attack(Enemy enemy){
    if(enemy.getCurrentHealth() <= 0){
      this.setIsAttackcing(false);
      this.experience += enemy.death();
    }
  }

  public void death(){
    if (this.getCurrentHealth() <= 0){
      super.spriteDeath();
    }
  }

  public void heal(){
    while(this.getCurrentHealth() < this.getTotalHealth() && !this.getIsAttacking()){
      this.setCurrentHealth(this.getCurrentHealth() +2);
    }
  }
}