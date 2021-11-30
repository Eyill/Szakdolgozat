package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.Enemy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_enemy = "SELECT * FROM ENEMY WHERE id = ?";
  private static final String SELECT_enemy_package = "SELECT * FROM NPC_PACKAGE WHERE npc_package_id = ?";

  public EnemyDAO(){
    CONNECTION_URL = Configuration.getProperty("db.url");
  }
  public Enemy findById(int id) {
    return null;
  }

  public List<Enemy> getAllEnemyByMapId(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_enemy_package);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      List<Enemy> enemies = new ArrayList<>();

      if (rs != null) {
        while (rs.next()) {
          enemies.add(findById(rs.getInt("enemy_id")));
        }
      }

      return enemies;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }
}
