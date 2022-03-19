package menus;

import constants.Constants;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu() {
        super("Edit");

        Arrays.stream(Constants.EditMenuList).forEach(value -> {
            this.add(new JMenuItem(value));
        });
    }
}