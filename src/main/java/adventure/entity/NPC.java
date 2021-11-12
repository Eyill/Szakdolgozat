package adventure.entity;

import adventure.common_files.Sprite;

import java.util.List;

public class NPC extends Sprite {
  public int id;
  public List<Quest> questList;
  public List<Item> itemList;

  public NPC(String imagePath, int level, int maxHealth, int def, int dam, int crit, int x, int y) {
    super(imagePath, level, maxHealth, maxHealth, def, dam, crit, x, y);
  }

  public void showItems() {

  }
}
