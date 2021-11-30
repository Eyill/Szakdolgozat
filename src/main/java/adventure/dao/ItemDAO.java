package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Item;

import java.sql.*;

public class ItemDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_item = "SELECT * FROM ITEM WHERE id = ?";

  public ItemDAO() {
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public Item findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_item);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs != null) {
        Item item = new Item(
                id,
                rs.getString("name"),
                rs.getString("image_path"),
                rs.getInt("consumable") == 1,
                rs.getInt("default_price_per_unit")
        );
        rs.close();
        return item;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

}