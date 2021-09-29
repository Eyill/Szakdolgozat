package adventure.misc;
import adventure.dao.GamePlayDAO;
import adventure.dao.PlayerDAOImpl;
import adventure.entity.Enemy;
import adventure.entity.Player;

public class UserDataHandler {
  public static int gameplayId = 0;
  public static Player player = null;
  public static GameMap gameMap;

  public static PlayerDAOImpl playerDAOImp = new PlayerDAOImpl();
  public static GamePlayDAO gamePlayDAO = new GamePlayDAO();

  public static void loadPlayer(int id){
    player = playerDAOImp.findById(id);
    //player = new Player("/adventure/entities/player/player.gif",100, 10,10 , 6, 0, 0, 0,0);
  }

  public static void savePlayer(Player player){
    playerDAOImp.save(player);
  }

  public static void loadGameMap(){
  }

  public static void loadGame(){
  }

  public static void createNewGame(String playerName){
    // TODO: create game entity in db
    // TODO: initialize GameMap entity
    // TODO: load enemy and game map
    player = new Player("/adventure/entities/player/player.gif",100, 10,10 , 6, 10, 0, 0,0,playerName);
    playerDAOImp.createPlayer(player);
    gamePlayDAO.createGamePlay(player.getPlayerId(),1);
    gameMap = new GameMap();
    gameMap.loadEnemyList();
  }

  public static void checkEnemyLife(){
    if (!gameMap.enemy.isEmpty()){
      gameMap.enemy.removeIf(e -> e.getCurrentHealth() <= 0);
    }
  }

}