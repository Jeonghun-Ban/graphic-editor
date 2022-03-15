package elements;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem closeItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem printItem;
    private JMenuItem quitItem;

    public FileMenu() {
        super("File");

        this.newItem = new JMenuItem("New");
        this.add(this.newItem);

        this.openItem = new JMenuItem("Open");
        this.add(this.openItem);

        this.closeItem = new JMenuItem("Close");
        this.add(this.closeItem);

        this.saveItem = new JMenuItem("Save");
        this.add(this.saveItem);

        this.saveAsItem = new JMenuItem("Save As..");
        this.add(this.saveAsItem);

        this.printItem = new JMenuItem("Print");
        this.add(this.printItem);

        this.quitItem = new JMenuItem("Quit");
        this.add(this.quitItem);
    }

}
