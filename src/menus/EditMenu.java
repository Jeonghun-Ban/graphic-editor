package menus;

import enums.EditMenuEnum;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu() {
        super("Edit");
        createMenuItems();
    }

    private void createMenuItems(){
        Arrays.stream(EditMenuEnum.values()).forEach(value -> {
            this.add(new JMenuItem(value.name()));
        });
    }
}