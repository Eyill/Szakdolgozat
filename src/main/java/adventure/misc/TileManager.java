package adventure.misc;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TileManager {
  public static Block[][] gameMap = new Block[25][44];

  public Canvas loadGameMap(String fileName) {
    final Canvas canvas = new Canvas();

    GraphicsContext gc1 = canvas.getGraphicsContext2D();
    canvas.setHeight(400);
    canvas.setWidth(700);

    try {
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      Document doc = builder.parse(getClass().getResource("/adventure/game_maps/" + fileName).toString());
      doc.getDocumentElement().normalize();

      NodeList list = doc.getElementsByTagName("layer");
      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          drawLayer(element, gc1);
        }
      }
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
    return canvas;
  }

  private void drawLayer(Element element, GraphicsContext gc1) {
    int x = 0;
    int y = 0;
    String tempList = element.getElementsByTagName("data").item(0).getTextContent();
    List<Integer> integerList =
            Arrays.stream(tempList.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
    for (int i : integerList) {
      Block block = Block.fromId(i);

      if (block != null) {
        Image image =
                new Image(getClass().getResource(block.path).toString(),
                        16,
                        16,
                        false,
                        false
                );
        gc1.drawImage(image, x * 16, y * 16, 16, 16);
      }
      if (i != 0) {
        gameMap[y][x] = Block.fromId(i);
      }

      x++;
      if (x % 44 == 0) {
        y++;
        x = 0;
      }
    }
  }
}