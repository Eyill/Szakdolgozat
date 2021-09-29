package adventure.dao;

import adventure.entity.Player;
import adventure.common_files.configuration.Configuration;
import java.sql.*;

public class PlayerDAOImpl implements PlayerDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_player = "SELECT * FROM PLAYER WHERE id = ?";
  private static final String CREATE_player = "INSERT INTO PLAYER " +
          "(name, lvl,total_health,current_health,defense,damage,critical_damage,experience,current_experience,image_path,gold) " +
          "VALUES (?,?,?,?,?,?,?,?,?,?, " +
          "?)";

  private static final String UPDATE_player =
          "UPDATE PLAYER SET " +
                  "lvl = ?," +
                  "total_health = ?," +
                  "current_health = ?," +
                  "defense = ?," +
                  "damage = ? ," +
                  "critical_damage = ?," +
                  "experience = ?," +
                  "current_experience = ?," +
                  "image_path = ?," +
                  "gold = ?" +
                  "WHERE id=?";

  private static final String DELETE_ingredient = "DELETE FROM PLAYER WHERE id = ?";

  public PlayerDAOImpl(){
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  @Override
  public Player findById(int id) {
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(SELECT_player);
    ){
      stmt.setInt(1, id);

      ResultSet rs = stmt.executeQuery();

      if (rs != null) {
       Player player = new Player(
               rs.getString("image_path"),
               rs.getInt("lvl"),
               rs.getInt("total_health"),
               rs.getInt("current_health"),
               rs.getInt("defense"),
               rs.getInt("damage"),
               rs.getInt("critical_damage"),
               rs.getInt("experience"),
               rs.getInt("current_experience"),
               rs.getString("name")
               );
       player.setPlayerId(rs.getInt("id"));
       return player;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean save(Player player) {
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(UPDATE_player)
    ){
      stmt.setInt(1,player.getPlayerId());
      stmt.setString(2,player.getPlayerName());
      stmt.setInt(3, player.getLvl());
      stmt.setInt(4, player.getTotalHealth());
      stmt.setInt(5, player.getCurrentHealth());
      stmt.setInt(6, player.getDefense());
      stmt.setInt(7, player.getDamage());
      stmt.setInt(8, player.getCriticalDamage());
      stmt.setInt(9, player.getExperienceToLvLUp());
      stmt.setInt(10, player.getExperience());
      stmt.setString(11,player.getImagePath());

      int res = stmt.executeUpdate();
      return res == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean createPlayer(Player player){
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(CREATE_player)
    ){
      stmt.setString(1,player.getPlayerName());
      stmt.setInt(2, player.getLvl());
      stmt.setInt(3, player.getTotalHealth());
      stmt.setInt(4, player.getCurrentHealth());
      stmt.setInt(5, player.getDefense());
      stmt.setInt(6, player.getDamage());
      stmt.setInt(7, player.getCriticalDamage());
      stmt.setInt(8, player.getExperienceToLvLUp());
      stmt.setInt(9, player.getExperience());
      stmt.setString(10,player.getImagePath());

      int res = stmt.executeUpdate();
      player.setPlayerId(stmt.getGeneratedKeys().getInt(1));

      return res == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean delete(int id){
    try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        PreparedStatement stmt = c.prepareStatement(DELETE_ingredient);
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