package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BackpackDAO {
  private static String CONNECTION_URL;
  private static final String CREATE_BACKPACK = "INSERT INTO BACKPACK_ID (id) VALUES (null)";
  private static final String SELECT_backpack = "SELECT * FROM BACKPACK WHERE backpack_id = ?";
  private static final String DELETE_FROM_backpack = "DELETE FROM BACKPACK WHERE backpack_id = ?";

  private static final String INSERT_INTO_backpack =
          "INSERT INTO BACKPACK " + "(id,backpack_id,item_id,amount)" + "VALUES (?,?,?,?)";

  private static final String UPDATE_backpack =
          "UPDATE BACKPACK SET " +
                  "backpack_id = ?," +
                  "item_id = ?," +
                  "amount = ?," +
                  "WHERE backpack_id = ? AND item_id = ?";

  public BackpackDAO() {
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public int createNewBackpack() {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(CREATE_BACKPACK)
    ) {
      stmt.executeUpdate();

      return stmt.getGeneratedKeys().getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public void findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_backpack);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      List<Item> itemList = new ArrayList<>();

      if (rs != null) {
        while (rs.next()) {

        }
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }


  public boolean save(int backpack_id, int item_id, int amount) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(UPDATE_backpack)
    ) {
      stmt.setInt(1, backpack_id);
      stmt.setInt(2, item_id);
      stmt.setInt(5, amount);

      int res = stmt.executeUpdate();
      return res == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public void addItemToBackpack(int backpack_id, int item_id, int amount) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(INSERT_INTO_backpack)
    ) {
      stmt.setInt(2, backpack_id);
      stmt.setInt(3, item_id);
      stmt.setInt(4, amount);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public boolean delete(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(DELETE_FROM_backpack);
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
