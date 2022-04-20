package utils;

import java.awt.BasicStroke;
import java.io.Serializable;

public class CustomStroke extends BasicStroke implements Serializable {
    public CustomStroke() {
        super();
    }

    public CustomStroke(int lineSize) {
        super(lineSize);
    }

    public CustomStroke(int lineSize, int capRound, int joinRound, int i, float[] floats, int i1) {
        super(lineSize, capRound, joinRound, i, floats, i1);
    }
}
