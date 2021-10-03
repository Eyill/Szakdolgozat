package adventure.misc;
import adventure.dao.GamePlayDAO;
import adventure.dao.PlayerDAOImpl;
import adventure.entity.GameMap;
import adventure.entity.Player;

public class UserDataHandler {
  public static int gameplayId = 0;
  public static Player player = null;
  public static GameMap gameMap;

  public static PlayerDAOImpl playerDAOImp = new PlayerDAOImpl();
  public static GamePlayDAO gamePlayDAO = new GamePlayDAO();
  public static TileManager tileManager = new TileManager();

  public static void loadPlayer(int id){
    player = playerDAOImp.findById(id);
  }

  public static void savePlayer(Player player){
    playerDAOImp.save(player);
  }

  public static void loadGameMap(int id){
  }

  public static void loadGame(int id){
    gamePlayDAO.findById(id);
  }

  public static void createNewGame(String playerName){
    player = new Player("/adventure/entities/player/player.gif",1, 10,10 , 6, 10, 0, 40,0,playerName);
    playerDAOImp.createPlayer(player);
    gameplayId = gamePlayDAO.createGamePlay(player.getPlayerId(),1);
    gameMap = new GameMap();
    gameMap.loadEnemyList();
  }

  public static void checkEnemyLife(){
    if (!gameMap.enemy.isEmpty()){
      gameMap.enemy.removeIf(e -> e.getCurrentHealth() <= 0);
    }
  }

}