package adventure.entity;

import adventure.common_files.Sprite;
import adventure.misc.UserDataHandler;

public class NPC extends Sprite {
  public final int id;
  public final String name;
  private Backpack backpack;

  public NPC(int id, String name, String imagePath, int level, int maxHealth, int def, int dam, int crit, int x, int y) {
    super(UserDataHandler.class.getResource(imagePath).toString(), level, maxHealth, maxHealth, def, dam, crit, x, y,15);
    this.id = id;
    this.name = name;
  }

  public void showItems() {
  }

  public Backpack getBackpack(){
    return backpack;
  }
}