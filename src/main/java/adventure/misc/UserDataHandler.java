package adventure.misc;

import adventure.dao.BackpackDAO;
import adventure.dao.GameMapDAO;
import adventure.dao.GamePlayDAO;
import adventure.dao.PlayerDAO;
import adventure.entity.GameMap;
import adventure.entity.Player;
import javafx.scene.canvas.Canvas;

public class UserDataHandler {
  public static int gameplayId = 0;
  public static Player player = null;
  public static GameMap gameMap;

  public static PlayerDAO playerDAOImp = new PlayerDAO();
  public static GamePlayDAO gamePlayDAO = new GamePlayDAO();
  public static GameMapDAO gameMapDAO = new GameMapDAO();
  public static TileManager tileManager = new TileManager();

  public static Canvas loadGameMap(String path) {
    return tileManager.loadGameMap(path);
  }

  public static void loadGame(int id) {
    gamePlayDAO.findById(id);
    gameMap.loadEnemyList();
  }

  public static void createNewGame(String playerName) {
    player = new Player(
            "/adventure/entities/player/player.gif",
            1,
            100,
            100,
            6,
            10,
            0,
            40,
            0,
            playerName,
            100,
            100
    );

    playerDAOImp.createPlayer(player);
    player.backpack.setId(new BackpackDAO().createNewBackpack());
    gameplayId = gamePlayDAO.createGamePlay(player.getPlayerId(), 1, player.backpack.getId());
    gameMap = gameMapDAO.findById(1);
    gameMap.loadEnemyList();
    gameMap.loadNPCList();
  }

  public static void checkEnemyLife() {
    if (!gameMap.enemy.isEmpty()) {
      gameMap.enemy.removeIf(e -> e.getCurrentHealth() <= 0);
    }
  }

  public static void saveGame(Player player, int gameplayId, int gameMapId) {
    playerDAOImp.save(player);
    gamePlayDAO.saveGame(gameplayId, gameMapId);
  }

}