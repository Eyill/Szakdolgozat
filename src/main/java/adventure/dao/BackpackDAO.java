package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Backpack;
import adventure.entity.Item;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BackpackDAO {
  private static String CONNECTION_URL;
  private static final String CREATE_BACKPACK = "INSERT INTO BACKPACK_ID (id) VALUES (null)";
  private static final String SELECT_backpack = "SELECT * FROM BACKPACK WHERE backpack_id = ?";
  private static final String DELETE_FROM_backpack = "DELETE FROM BACKPACK WHERE backpack_id = ? AND item_id = ?";

  private static final String INSERT_INTO_backpack =
          "INSERT INTO BACKPACK " + "(id,backpack_id,item_id,amount)" + "VALUES (?,?,?,?)";

  private static final String UPDATE_backpack =
          "UPDATE BACKPACK SET " +
                  "backpack_id = ?," +
                  "item_id = ?," +
                  "amount = ?," +
                  "WHERE backpack_id = ? AND item_id = ?";

  private static final String SELECT_NPC_inventory = "SELECT * FROM NPC_INVENTORY WHERE npc_inventory_id = ?";
  private static final String INSERT_INTO_NPC_inventory = "INSERT INTO NPC_INVENTORY " +
          "(npc_inventory_id,item_id,amount)" + "VALUES (?,?,?)";

  private static final String UPDATE_NPC_inventory =
          "UPDATE BACKPACK SET " +
                  "npc_inventory_id = ?," +
                  "item_id = ?," +
                  "amount = ?," +
                  "WHERE npc_inventory_id = ? AND item_id = ?";

  private static final String DELETE_FROM_NPC_inventory = "DELETE FROM NPC_INVENTORY WHERE npc_inventory_id = ? AND item_id = ?";

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

  // Player's backpack
  public Map<Item, Integer> findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_backpack);
    ) {
      Map<Item, Integer> backpack = new HashMap<>();
      ItemDAO itemDAO = new ItemDAO();

      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs != null) {
        while (rs.next()) {
          Item item = itemDAO.findById(rs.getInt("item_id"));
          item.setSellPrice((int) (item.getSellPrice() * 0.8));
          backpack.put(itemDAO.findById(rs.getInt("item_id")), rs.getInt("amount"));
        }
      }
      return backpack;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return new HashMap<>();
  }


  public boolean save(Backpack backpack) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(UPDATE_backpack)
    ) {
      if (!backpack.getPack().isEmpty()) {
        for (Map.Entry<Item, Integer> entry : backpack.getPack().entrySet()) {
          stmt.setInt(1, backpack.getId());
          stmt.setInt(2, entry.getKey().getItemId());
          stmt.setInt(5, entry.getValue());
          stmt.addBatch();
        }
        stmt.executeBatch();
      }
      return true;
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

  public boolean delete(int backpack_id, int item_id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(DELETE_FROM_backpack);
    ) {
      stmt.setInt(1, backpack_id);
      stmt.setInt(2, item_id);
      int res = stmt.executeUpdate();
      return res == 1;

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }
}
