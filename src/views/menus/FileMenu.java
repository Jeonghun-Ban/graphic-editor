package views.menus;

import static global.Constants.FILE_MENU_TITLE;

import views.dialogs.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static FileMenu fileMenu;
  private final FileDialog fileDialog;

  private FileMenu() {
    super(FILE_MENU_TITLE);
    fileDialog = new FileDialog();

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
      menuItem.setActionCommand(item.toString());
      menuItem.setToolTipText(item.toString());
      this.add(menuItem);
    });
  }

  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(FileMenuItem.New.toString())) {
        fileDialog.newFile();
      } else if (e.getActionCommand().equals(FileMenuItem.Open.toString())) {
        fileDialog.openFile();
      } else if (e.getActionCommand().equals(FileMenuItem.Save.toString())) {
        fileDialog.saveFile();
      } else if (e.getActionCommand().equals(FileMenuItem.SaveAs.toString())) {
        fileDialog.saveFileAs();
      } else if (e.getActionCommand().equals(FileMenuItem.Print.toString())) {
        fileDialog.print();
      } else if (e.getActionCommand().equals(FileMenuItem.Quit.toString())) {
        fileDialog.quit();
      }
    }
  }
}

