package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.misc.GameMap;
import adventure.misc.UserDataHandler;

import java.sql.*;

public class GameMapDAO {

  private static String CONNECTION_URL;
  private static final String SELECT_gameplay = "SELECT * FROM MAP WHERE id = ?";

  public GameMapDAO(){
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public void findById(int id) {
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(SELECT_gameplay);
    ){
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs != null) {
        UserDataHandler.gameplayId = id;
        UserDataHandler.player = (new PlayerDAOImpl()).findById(rs.getInt("player_id"));
        //TODO : backpack_id,"quest_package_id, "current_map_id,
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}
