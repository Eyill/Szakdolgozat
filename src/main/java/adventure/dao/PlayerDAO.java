package adventure.dao;

import adventure.entity.Player;

public interface PlayerDAO {
  public Player findById(int id);
  public boolean save(Player player);
  public boolean createPlayer(Player player);
  public boolean delete(int id);
}
