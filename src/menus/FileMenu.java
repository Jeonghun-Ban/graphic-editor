package menus;

import constants.Constants;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public FileMenu() {
        super("File");
        createMenuItems();
    }

    private void createMenuItems(){
        Arrays.stream(Constants.FileMenuEnum.values()).forEach(value -> {
            this.add(new JMenuItem(value.name()));
        });
    }

}
