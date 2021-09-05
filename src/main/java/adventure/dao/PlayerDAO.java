package adventure.dao;

import adventure.entity.Player;

public interface PlayerDAO {
  public Player findById(int id);

  public void save(Player player);

  public void delete(int id);
}
