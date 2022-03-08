package elements;

import javax.swing.*;

public class FileMenu extends JMenu {
    public FileMenu() {
        super("File");

        add(new JMenuItem("New"));
        add(new JMenuItem("Open"));
        add(new JMenuItem("Save"));
        add(new JMenuItem("Save As..."));
        add(new JMenuItem("Exit"));
    }
}
