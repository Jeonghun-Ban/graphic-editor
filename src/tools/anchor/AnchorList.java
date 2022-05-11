package tools.anchor;

import static global.Constants.ANCHOR_STROKE;
import static global.Constants.DEFAULT_BACKGROUND_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class AnchorList implements Serializable {

  private static final long serialVersionUID = 1L;

  private final ArrayList<Ellipse2D> anchorList;

  public AnchorList() {
    this.anchorList = new ArrayList<>();
    Arrays.stream(Anchor.values()).forEach(anchor -> this.anchorList.add(new Ellipse2D.Double()));
  }

  public void draw(Graphics2D g2D, Rectangle rectangle) {
    g2D.setStroke(ANCHOR_STROKE);
    Arrays.stream(Anchor.values()).forEach(anchor -> {
      Ellipse2D ellipse2D = anchor.getBoundAnchor(this.anchorList.get(anchor.ordinal()), rectangle);
      g2D.setColor(DEFAULT_BACKGROUND_COLOR);
      g2D.fill(ellipse2D);
      g2D.setColor(DEFAULT_LINE_COLOR);
      g2D.draw(ellipse2D);
    });
  }

  public Optional<Anchor> contains(Point point) {
    for(Anchor anchor: Anchor.values()) {
      Ellipse2D ellipse2D = anchorList.get(anchor.ordinal());
      if(ellipse2D.contains(point)) {
        return Optional.of(anchor);
      }
    }
    return Optional.empty();
  }
}
