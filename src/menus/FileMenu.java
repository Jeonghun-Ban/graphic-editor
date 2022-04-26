package menus;

import containers.DrawingPanel;
import dialogs.FileDialog;
import enums.FileMenuEnum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

  private final ActionHandler actionHandler;
  private DrawingPanel drawingPanel;
  private FileDialog fileDialog;

  public FileMenu() {
    super("File");
    actionHandler = new ActionHandler();
    fileDialog = new FileDialog();

    createMenuItems();
  }

  public void associate(DrawingPanel drawingPanel) {
    this.drawingPanel = drawingPanel;
    this.fileDialog.associate(drawingPanel);
  }

  private void createMenuItems() {
    Arrays.stream(FileMenuEnum.values()).forEach(value -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(value.getLabel());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(value.getLabel());
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

