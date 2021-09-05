package adventure.misc;
import adventure.entity.Enemy;
import adventure.entity.Player;

public class UserDataHandler {
  public static Player player = null;
  public static Enemy enemy = new Enemy(UserDataHandler.class.getResource("/adventure/entities/enemy/slime_0.png").toString(),"Kaka nyeknyek",1,10,0,1,0,10);
}