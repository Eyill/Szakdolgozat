package adventure.common_files;

import adventure.entity.Enemy;
import adventure.entity.Player;

public class CommonGameMap {

  Player player;
  Enemy enemy[];

  //Map<CommonGameMap, Pair<Integer,Integer>> nearbyMaps;

  public CommonGameMap(Player player, int numberOfEnemies){
    this.player = player;
    this.enemy = new Enemy[numberOfEnemies];
  }
}