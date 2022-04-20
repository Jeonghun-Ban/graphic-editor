package menus;

import components.DrawingPanel;
import global.Constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import shapes.MetaShape;
import utils.FileStore;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private DrawingPanel drawingPanel;
    private ActionHandler actionHandler;
    private FileStore fileStore;

    public FileMenu() {
        super("File");
        actionHandler = new ActionHandler();
        fileStore = new FileStore();
        createMenuItems();
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    private void createMenuItems() {
        Arrays.stream(Constants.FileMenuEnum.values()).forEach(value -> {
            JMenuItem menuItem = new JMenuItem(value.name());
            menuItem.addActionListener(actionHandler);
            menuItem.setActionCommand(menuItem.getName());
            this.add(menuItem);
        });
    }

    private void save() {
        try {
            ArrayList<MetaShape> shapeList = (ArrayList<MetaShape>) drawingPanel.getShapeList();
            fileStore.save("test", shapeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void open() {
        try {
            ArrayList<MetaShape> shapeList = (ArrayList<MetaShape>) fileStore.load("test");
            this.drawingPanel.setShapeList(shapeList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Save")) {
                save();
            } else if (e.getActionCommand().equals("Open")) {
                open();
            }
        }
    }
}

