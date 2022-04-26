package tools;

import java.awt.BasicStroke;
import java.io.Serializable;

public class CustomStroke extends BasicStroke implements Serializable {
    public CustomStroke() {
        super();
    }

    public CustomStroke(int lineSize) {
        super(lineSize);
    }

    public CustomStroke(int lineSize, int dashSize) {
        super(lineSize, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 10,
                new float[]{dashSize}, 0);
    }
}
