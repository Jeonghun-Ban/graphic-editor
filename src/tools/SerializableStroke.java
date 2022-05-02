package tools;

import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_LINE_SIZE;

import java.awt.BasicStroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableStroke implements Serializable {

  private static final long serialVersionUID = 1L;

  private int lineSize;
  private int dashSize;

  private BasicStroke basicStroke;

  public SerializableStroke() {
    this.lineSize = DEFAULT_LINE_SIZE;
    this.dashSize = DEFAULT_DASH_SIZE;
    this.basicStroke = new BasicStroke(lineSize);
  }

  public BasicStroke getStroke() {
    changeStroke();
    return this.basicStroke;
  }

  public void setDashSize(int dashSize) {
    this.dashSize = dashSize;
  }

  public void setLineSize(int lineSize) {
    this.lineSize = lineSize;
  }

  private void changeStroke() {
    if (dashSize == 0) {
      createStroke(lineSize);
    } else {
      createDashStroke(lineSize, dashSize);
    }
  }

  private void createStroke(int lineSize) {
    basicStroke = new BasicStroke(lineSize);
  }

  private void createDashStroke(int lineSize, int dashSize) {
    basicStroke = new BasicStroke(lineSize, BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND, 10,
        new float[]{dashSize}, 0);
  }

  private void writeObject(ObjectOutputStream s) throws IOException {
    s.writeInt(lineSize);
    s.writeInt(dashSize);
  }

  private void readObject(ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    this.setLineSize(s.readInt());
    this.setDashSize(s.readInt());
  }
}
