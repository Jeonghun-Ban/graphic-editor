package tools;

import enums.AnchorEnum;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Anchor {

  private ArrayList<Ellipse2D> anchorList;

  public Anchor() {
    this.anchorList = new ArrayList<>();
    Arrays.stream(AnchorEnum.values()).forEach(anchor -> {
      this.anchorList.add(new Ellipse2D.Double());
    });
  }

  public void draw(Graphics2D g2D, Rectangle rectangle) {
    Arrays.stream(AnchorEnum.values()).forEach(anchorEnum -> {
      Ellipse2D anchor = this.anchorList.get(anchorEnum.ordinal());
      g2D.draw(anchorEnum.getBoundAnchor(anchor, rectangle));
    });
  }
}
