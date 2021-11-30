package adventure.misc;

import adventure.dao.BackpackDAO;
import adventure.dao.GameMapDAO;
import adventure.dao.GamePlayDAO;
import adventure.dao.PlayerDAO;
import adventure.entity.Backpack;
import adventure.entity.GameMap;
import adventure.entity.NPC;
import adventure.entity.Player;

public final class UserDataHandler {
  public static int gameplayId = 0;
  public static Player player;
  public static NPC targetNPC;
  public static GameMap gameMap;

  public static PlayerDAO playerDAOImp = new PlayerDAO();
  public static GamePlayDAO gamePlayDAO = new GamePlayDAO();
  public static GameMapDAO gameMapDAO = new GameMapDAO();
  public static TileManager tileManager = new TileManager();

  private UserDataHandler(){}

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
            120
    );

    playerDAOImp.createPlayer(player);
    player.setBackpack(new Backpack(new BackpackDAO().createNewBackpack()));
    gameplayId = gamePlayDAO.createGamePlay(player.getPlayerId(), 1, player.getBackpack().getId());
    gameMap = gameMapDAO.findById(1);
    gameMap.loadEnemyList();
  }

  public static void checkEntityLife() {
    if (!gameMap.enemy.isEmpty()) {
      gameMap.enemy.removeIf(e -> !e.isAlive());
    }
    if (player.getCurrentHealth() <= 0) {
      player.death();
    }
  }

  public static void saveGame(Player player, int gameplayId, int gameMapId) {
    playerDAOImp.save(player);
    gamePlayDAO.saveGame(gameplayId, gameMapId);
  }

}