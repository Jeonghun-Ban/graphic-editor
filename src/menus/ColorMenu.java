package menus;

import constants.Constants;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ColorMenu extends JMenu {

    public ColorMenu() {
        super("Color");
        createMenuItems();
    }

    private void createMenuItems(){
        Arrays.stream(Constants.ColorMenuEnum.values()).forEach(value -> {
            this.add(new JMenuItem(value.name()));
        });
    }
}
