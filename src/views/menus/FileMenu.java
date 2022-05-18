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
    Arrays.stream(FileMenuEnum.values()).forEach(menuEnum -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(menuEnum.toString());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(menuEnum.toString());
      menuItem.setToolTipText(menuEnum.toString());
      this.add(menuItem);
    });
  }

  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(FileMenuEnum.New.toString())) {
        fileDialog.newFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Open.toString())) {
        fileDialog.openFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Save.toString())) {
        fileDialog.saveFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.SaveAs.toString())) {
        fileDialog.saveFileAs();
      } else if (e.getActionCommand().equals(FileMenuEnum.Print.toString())) {
        fileDialog.print();
      } else if (e.getActionCommand().equals(FileMenuEnum.Quit.toString())) {
        fileDialog.quit();
      }
    }
  }
}

