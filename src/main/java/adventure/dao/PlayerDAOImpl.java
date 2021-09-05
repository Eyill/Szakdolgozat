package adventure.dao;

import adventure.entity.Player;
import adventure.common_files.configuration.Configuration;

public class PlayerDAOImpl implements PlayerDAO {
  private static String CONNECTION_URL;

  public PlayerDAOImpl(){
    CONNECTION_URL = Configuration.getProperty("db.url");
  }

  @Override
  public Player findById(int id) {
    return null;
  }

  @Override
  public void save(Player player) {

  }

  @Override
  public void delete(int id) {

  }
}
