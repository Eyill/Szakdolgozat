package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Backpack;
import adventure.misc.UserDataHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static adventure.misc.UserDataHandler.gameMapDAO;

public class GamePlayDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_gameplay = "SELECT * FROM GAMEPLAY WHERE id = ?";
  private static final String CREATE_gameplay = "INSERT INTO GAMEPLAY " +
          "(player_id,backpack_id,current_map_id,created)" +
          "VALUES (?,?,?,?)";

  private static final String UPDATE_gameplay =
          "UPDATE GAMEPLAY SET " +
                  "current_map_id = ?," +
                  "updated = ?" +
                  "WHERE id = ?";

  private static final String DELETE_gameplay = "DELETE FROM GAMEPLAY WHERE id = ?";

  public GamePlayDAO() {
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public int createGamePlay(int playerId, int currentMapId, int backpackId) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(CREATE_gameplay)
    ) {
      stmt.setInt(1, playerId);
      stmt.setInt(2, backpackId);
      stmt.setInt(3, currentMapId);

      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      stmt.setString(4, dtf.format(now));

      stmt.executeUpdate();

      return stmt.getGeneratedKeys().getInt(1);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public void findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_gameplay);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs != null) {
        UserDataHandler.gameplayId = id;
        UserDataHandler.player = new PlayerDAO().findById(rs.getInt("player_id"));
        UserDataHandler.player.setBackpack(new Backpack(rs.getInt("backpack_id")));
        UserDataHandler.gameMap = gameMapDAO.findById(rs.getInt("current_map_id"));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public boolean saveGame(int gameId, int mapId) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(UPDATE_gameplay)
    ) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();

      stmt.setInt(1, mapId);
      stmt.setString(2, dtf.format(now));
      stmt.setInt(3, gameId);

      int res = stmt.executeUpdate();

      return res == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean delete(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
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

  public ArrayList findAllGameSaves() {
    ArrayList<String> gameSaves = new ArrayList<String>();

    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement("SELECT * FROM GAMEPLAY");
    ) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        String dateInfo = Objects.equals(rs.getString("updated"), null)
                ? rs.getString("created")
                : rs.getString("updated");

        gameSaves.add(rs.getInt("id") + " " +
                PlayerDAO.getPlayerNameById(rs.getInt("player_id")) + " " + dateInfo)
        ;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return gameSaves;
  }
}