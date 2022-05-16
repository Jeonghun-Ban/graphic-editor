package menus;

import static global.Constants.FILE_MENU_TITLE;

import dialogs.FileDialog;
import enums.FileMenuEnum;
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
    Arrays.stream(FileMenuEnum.values()).forEach(value -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(value.getLabel());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(value.getLabel());
      menuItem.setToolTipText(value.getLabel());
      this.add(menuItem);
    });
  }

  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(FileMenuEnum.New.getLabel())) {
        fileDialog.newFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Open.getLabel())) {
        fileDialog.openFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Save.getLabel())) {
        fileDialog.saveFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.SaveAs.getLabel())) {
        fileDialog.saveFileAs();
      } else if (e.getActionCommand().equals(FileMenuEnum.Print.getLabel())) {
        fileDialog.print();
      } else if (e.getActionCommand().equals(FileMenuEnum.Quit.getLabel())) {
        fileDialog.quit();
      }
    }
  }
}

