package views.menu;

import static global.Constants.FILE_MENU_TITLE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import views.menu.item.FileMenuItem;

public class FileMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static FileMenu fileMenu;

  private FileMenu() {
    super(FILE_MENU_TITLE);
    ActionHandler actionHandler = new ActionHandler();
    createMenuItems(actionHandler);
  }

  public static FileMenu getInstance() {
    if (fileMenu == null) {
      fileMenu = new FileMenu();
    }
    return fileMenu;
  }

  private void createMenuItems(ActionHandler actionHandler) {
    Arrays.stream(FileMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(item.toString());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(item.name());
      menuItem.setToolTipText(item.toString());
      menuItem.setAccelerator(item.getKeyStroke());
      this.add(menuItem);
      if (item.isSeperate()) {
        this.addSeparator();
      }
    });
  }

  static class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Optional<FileMenuItem> fileMenuItem = Arrays.stream(FileMenuItem.values())
          .filter(item -> e.getActionCommand().equals(item.name())).findFirst();
      fileMenuItem.ifPresent(FileMenuItem::operate);
    }
  }
}

