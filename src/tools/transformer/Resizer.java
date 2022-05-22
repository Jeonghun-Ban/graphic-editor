package tools.transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import tools.anchor.Anchor;
import tools.draw.DrawShape;
import utils.ScalingFactor;
import utils.ScalingFactorDto;
import utils.ScalingRequestDto;

public class Resizer extends Transformer {

  private Point startPoint;
  private Anchor anchor;

  public Resizer(DrawShape drawShape) {
    super(drawShape);
    startPoint = new Point();
  }

  @Override
  public void init(Point startPoint) {
    this.startPoint = startPoint;
    this.anchor = drawShape.onAnchor(startPoint);
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    if (anchor != null) {
      ScalingRequestDto request = new ScalingRequestDto(
          startPoint, currentPoint, drawShape.getBounds());
      ScalingFactorDto scalingFactorDto = ScalingFactor.valueOf(anchor.name()).compute(request);

      g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
      drawShape.scaleTo(scalingFactorDto);
      this.startPoint = currentPoint;
    }
  }
}
