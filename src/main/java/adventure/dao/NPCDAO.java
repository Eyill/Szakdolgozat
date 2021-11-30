package adventure.dao;

import adventure.common_files.configuration.Configuration;
import adventure.entity.NPC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NPCDAO {
  private static String CONNECTION_URL;
  private static final String SELECT_npc = "SELECT * FROM NPC WHERE id = ?";
  private static final String SELECT_npc_package = "SELECT * FROM NPC_PACKAGE WHERE npc_package_id = ?";

  public NPCDAO() {
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  public List<NPC> getAllNPCByMapId(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_npc_package);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      List<NPC> npcs = new ArrayList<>();

      if (rs != null) {
        while (rs.next()) {
          npcs.add(findById(rs.getInt("npc_id")));
        }
      }

      return npcs;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public NPC findById(int id) {
    try (Connection c = DriverManager.getConnection(CONNECTION_URL);
         PreparedStatement stmt = c.prepareStatement(SELECT_npc);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs != null) {
        return new NPC(
                id,
                rs.getString("name"),
                rs.getString("image_path"),
                999,
                999,
                999,
                999,
                999,
                rs.getInt("position_x"),
                rs.getInt("position_y")
        );

      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }
}