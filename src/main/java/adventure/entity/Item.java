package adventure.entity;

import adventure.misc.UserDataHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends ImageView {
  private final int itemId;
  private final String name;
  private final boolean consumable;

  public Item(int id, String name, String imagePath, boolean consumable) {
    super(new Image(UserDataHandler.class.getResource(imagePath).toString(),
            16,
            16,
            false,
            false)
    );

    this.itemId = id;
    this.name = name;
    this.consumable = consumable;
  }

  public int getItemId() {
    return itemId;
  }

  public String getName() {
    return name;
  }

  public boolean isConsumable() {
    return consumable;
  }

}