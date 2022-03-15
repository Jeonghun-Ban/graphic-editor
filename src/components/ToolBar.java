package components;

import javax.swing.*;

public class ToolBar extends JToolBar {
    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;

    public ToolBar () {
        ButtonGroup buttonGroup = new ButtonGroup();

        this.rectangleTool = new JRadioButton("rectangle");
        this.add(rectangleTool);
        buttonGroup.add(rectangleTool);

        this.ovalTool = new JRadioButton("oval");
        this.add(ovalTool);
        buttonGroup.add(ovalTool);

        this.lineTool = new JRadioButton("line");
        this.add(lineTool);
        buttonGroup.add(lineTool);

        this.polygonTool = new JRadioButton("polygon");
        this.add(polygonTool);
        buttonGroup.add(polygonTool);
    }
}
