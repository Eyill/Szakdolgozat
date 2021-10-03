package adventure.entity;

import adventure.entity.Enemy;
import adventure.entity.NPC;
import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
  public IntegerProperty mapId = new SimpleIntegerProperty(this,"id");
  public StringProperty mapName = new SimpleStringProperty(this,"mapName");
  public StringProperty mapPath = new SimpleStringProperty(this,"mapPath");

  public List<Enemy> enemy = new ArrayList<Enemy>();
  public List<NPC> npc = new ArrayList<NPC>();

  public void loadEnemyList(){
    // TODO: read enemy data from db
    Enemy mob = new Enemy("/adventure/entities/enemy/slime_0.png","Teszt szörny",1,10,0,1,0,10);
    Enemy mob2 = new Enemy("/adventure/entities/enemy/goblin_0.png","Teszt szörny2",1,10,0,1,0,10);
    enemy.add(mob);
    enemy.add(mob2);
  }

}
