package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Player;
import adventure.misc.UserDataHandler;

import java.sql.*;

public class GamePlayDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_gameplay = "SELECT * FROM GAMEPLAY WHERE id = ?";
  private static final String CREATE_gameplay = "INSERT INTO GAMEPLAY " +
          "(id,player_id,backpack_id,quest_package_id,current_map_id)" +
          "VALUES (?,?,?,?,?)";

  private static final String UPDATE_gameplay =
          "UPDATE GAMEPLAY SET " +
                  "player_id = ?," +
                  "backpack_id = ?," +
                  "quest_package_id = ?," +
                  "current_map_id = ?," +
                  "WHERE id = ?";

  private static final String DELETE_gameplay = "DELETE FROM GAMEPLAY WHERE id = ?";

  public GamePlayDAO(){
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

  public boolean save(int gameId,int playerId,int mapId) {
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(UPDATE_gameplay)
    ){
      stmt.setInt(1,gameId);
      stmt.setInt(2,playerId);
      stmt.setInt(5,mapId);

      int res = stmt.executeUpdate();
      return res == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public int createGamePlay(int playerId, int currentMapId){
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(CREATE_gameplay)
    ){
      stmt.setInt(2,playerId);
      stmt.setInt(5,currentMapId);

      return stmt.getGeneratedKeys().getInt(1);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public boolean delete(int id){
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(DELETE_gameplay);
    ) {
      stmt.setInt(1, id);
      int res = stmt.executeUpdate();
      return res == 1;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}