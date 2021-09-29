package adventure.misc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class TileManager {

  public enum Block{
    GROUND(13, "Hello World",false),
    WALL(144, "Wall.jpg", true);

    public int id;
    public String path;
    public boolean collidable;

    Block(int id, String path, boolean collidable) {
      this.id = id;
      this.path = path;
      this.collidable = collidable;
    }

    public static Block fromId(int id) {
      for (Block block : Block.values()) {
        if (block.id == id) {
          return block;
        }
      }
      return GROUND;
    }
  }

  public void loadTest() {
    int[] tiles = {13, 144, 13, 144, 144};
    ArrayList<Block> blocks = new ArrayList<>();

    for (int tile : tiles) {
      blocks.add(Block.fromId(tile));
    }
  }

  public void loadGameMap(String path){

    try {
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
      doc.getDocumentElement().normalize();
  } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}
