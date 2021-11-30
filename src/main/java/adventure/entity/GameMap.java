package adventure.entity;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
  private int mapId;
  private String mapName;
  private String mapPath;

  public List<Enemy> enemy = new ArrayList<Enemy>();
  public List<NPC> npc = new ArrayList<NPC>();

  public int getMapId() {
    return mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public String getMapName() {
    return mapName;
  }

  public void setMapName(String mapName) {
    this.mapName = mapName;
  }

  public String getMapPath() {
    return mapPath;
  }

  public void setMapPath(String mapPath) {
    this.mapPath = mapPath;
  }

  public List<Enemy> getEnemy() {
    return enemy;
  }

  public void setEnemy(List<Enemy> enemy) {
    this.enemy = enemy;
  }

  public List<NPC> getNpc() {
    return npc;
  }

  public void setNpc(List<NPC> npc) {
    this.npc = npc;
  }

  public void loadEnemyList() {
    // TODO: read enemy data from db
    Enemy mob = new Enemy("/adventure/entities/enemy/slime_0.png", "Teszt szörny", 1, 10, 0, 1, 0, 10, 0, 0, 20);
    enemy.add(mob);
  }

}
