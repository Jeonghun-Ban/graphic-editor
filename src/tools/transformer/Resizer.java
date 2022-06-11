package tools.transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import tools.anchor.Anchor;
import tools.draw.DrawShape;
import utils.ScalingFactor;
import utils.dto.ScalingFactorDto;
import utils.dto.ScalingRequestDto;

public class Resizer extends Transformer {

  private Point startPoint;
  private Anchor anchor;

  public Resizer(DrawShape drawShape) {
    super(drawShape);
    startPoint = new Point();
  }

  public void setAnchor(Anchor anchor) {
    this.anchor = anchor;
  }

  @Override
  public void init(Point startPoint) {
    this.startPoint = startPoint;
    drawShape.onAnchor(startPoint).ifPresent(this::setAnchor);
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
    Rectangle bounds = drawShape.getBounds();
    if (!isBoundsSizeZero(bounds)) {
      ScalingRequestDto request = new ScalingRequestDto(
          startPoint, currentPoint, bounds);
      ScalingFactorDto scalingFactorDto = ScalingFactor.valueOf(anchor.name()).compute(request);

      drawShape.scaleTo(scalingFactorDto);
      this.startPoint = currentPoint;
    }
  }

  private boolean isBoundsSizeZero(Rectangle bounds) {
    return bounds.getHeight() == 0 || bounds.getWidth() == 0;
  }
}
