package elements;

import javax.swing.*;

public class EditMenu extends JMenu {
    public EditMenu() {
        super("Edit");

        add(new JMenuItem("Cut"));
        add(new JMenuItem("Copy"));
        add(new JMenuItem("Paste"));
        add(new JMenuItem("Delete"));
        add(new JMenuItem("Select All"));
    }
}
