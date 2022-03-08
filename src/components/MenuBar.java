package components;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar () {}

    public MenuBar addMenu(JMenu menu){
        add(menu);
        return this;
    }
}
