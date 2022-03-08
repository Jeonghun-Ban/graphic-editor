package components;

import javax.swing.*;
import java.util.List;

public class MenuBar extends JMenuBar {
    public MenuBar(List<JMenu> menuList) {
        menuList.stream().forEach(menu -> add(menu));
    }
}
