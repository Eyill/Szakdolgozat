package adventure.entity;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
  private int id;
  private List<Item> itemList = new ArrayList<>();

//  public Backpack(){
//    JSONObject jo = new JSONObject();
//    jo.put("name", "jon doe");
//    jo.put("age", "22");
//    jo.put("city", "chicago");

//  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public void getItemById(int id){}

  public void removeItem(){}

  public void addItem(){}

}
