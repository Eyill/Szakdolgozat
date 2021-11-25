package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.GameMap;
import adventure.misc.UserDataHandler;

import java.sql.*;

public class GameMapDAO {

  private static String CONNECTION_URL;
  private static final String SELECT_gameplay = "SELECT * FROM MAP WHERE id = ?";

  public GameMapDAO() {
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public GameMap findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_gameplay);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs != null) {
        GameMap gameMap = new GameMap();
        gameMap.setMapId(id);
        gameMap.setMapPath(rs.getString("map_path"));
        gameMap.setNpc((new NPCDAO()).getAllNPCByMapId(rs.getInt("npc_package_id")));
        return gameMap;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }
}