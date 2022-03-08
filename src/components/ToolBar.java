package components;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        this.add(new JButton("Undo"));
        this.add(new JButton("Redo"));
    }
}
