package components;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MenuBar () {}

    public MenuBar addMenu(JMenu menu){
        add(menu);
        return this;
    }
}
