package adventure.entity;

import adventure.dao.BackpackDAO;

import java.util.HashMap;
import java.util.Map;

public class Backpack {
  private final int id;
  private final int maxSize = 24;
  private Map<Item, Integer> pack;

  public Backpack(int backpackId) {
    id = backpackId;
    loadBackpack();
  }

  public int getId() {
    return id;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public Map<Item, Integer> getPack() {
    return pack;
  }

  public void loadBackpack() {
    pack = (new BackpackDAO()).findById(id);
    if (pack.isEmpty()) {
      pack = new HashMap<>();
    }
  }

  public Item getItemById(int id) {
    for (Map.Entry<Item, Integer> entry : pack.entrySet()) {
      if (entry.getKey().getItemId() == id) {
        return entry.getKey();
      }
    }
    return null;
  }

  public void addItem(Item item) {
    if (pack.containsKey(item)) {
      for (Map.Entry<Item, Integer> entry : pack.entrySet()) {
        if (entry.getKey() == item) {
          entry.setValue(entry.getValue() + 1);
        }
      }
    }
    if (pack.size() < maxSize) {
      pack.put(item, 1);
    }
  }

  public void useItem(Item item) {
    if (item.isConsumable()) {
      for (Map.Entry<Item, Integer> entry : pack.entrySet()) {
        if (entry.getKey().getItemId() == item.getItemId()) {
          entry.setValue(entry.getValue() - 1);
        }
      }
    } else {
      System.out.println("You can't use it!");
    }
  }

  public void removeItem(Item item) {
    pack.remove(item);
  }

}