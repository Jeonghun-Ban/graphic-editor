package utils;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class CustomAffineTransform extends AffineTransform {

  public CustomAffineTransform() {
    super();
  }

  @Override
  public Shape createTransformedShape(Shape pSrc) {
    if (pSrc == null) {
      return null;
    }
    return (pSrc instanceof Path2D.Float) ? new Path2D.Float(pSrc, this)
        : new Path2D.Double(pSrc, this);
  }

}
