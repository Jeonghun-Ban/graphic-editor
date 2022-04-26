package menus;

import static global.Constants.NEW_FILE_DIALOG_MESSAGE;
import static global.Constants.NEW_FILE_DIALOG_TITLE;
import static global.Constants.QUIT_DIALOG_MESSAGE;
import static global.Constants.QUIT_DIALOG_TITLE;

import containers.DrawingPanel;
import enums.Exception;
import enums.FileMenuEnum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import tools.draw.DrawTool;
import utils.FileStore;

public class FileMenu extends JMenu {

  private final ActionHandler actionHandler;
  private final FileStore fileStore;
  private final JFileChooser fileChooser;

  private DrawingPanel drawingPanel;
  private File file;

  public FileMenu() {
    super("File");
    actionHandler = new ActionHandler();
    file = null;
    fileChooser = new JFileChooser();

    fileStore = new FileStore();
    createMenuItems();
  }

  public void associate(DrawingPanel drawingPanel) {
    this.drawingPanel = drawingPanel;
    fileStore.associate(drawingPanel);
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

  private void newFile() {
    int result = JOptionPane.showConfirmDialog(drawingPanel, NEW_FILE_DIALOG_MESSAGE,
        NEW_FILE_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (result == JOptionPane.YES_OPTION) {
      drawingPanel.clean();
      this.file = null;
    }
  }

  private void openFile() {
    int result = fileChooser.showOpenDialog(drawingPanel);
    if (result == JFileChooser.APPROVE_OPTION) {
      this.file = fileChooser.getSelectedFile();
      ArrayList<DrawTool> shapeList = (ArrayList<DrawTool>) fileStore.load(file);
      this.drawingPanel.setDrawTools(shapeList);
    }
  }

  private void saveFile() {
    if (this.file == null) {
      saveFileAs();
    }
    ArrayList<DrawTool> shapeList = (ArrayList<DrawTool>) drawingPanel.getDrawTools();
    fileStore.save(this.file, shapeList);

  }

  private void saveFileAs() {
    int result = fileChooser.showSaveDialog(drawingPanel);
    if (result == JFileChooser.APPROVE_OPTION) {
      this.file = fileChooser.getSelectedFile();
      ArrayList<DrawTool> shapeList = (ArrayList<DrawTool>) drawingPanel.getDrawTools();
      fileStore.save(this.file, shapeList);
    }
  }

  private void print() {
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    printerJob.setPrintable(drawingPanel);
    boolean isPrintable = printerJob.printDialog();

    try {
      if (isPrintable) {
        printerJob.print();
      }
    } catch (PrinterException exception) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.PRINT_NOT_AVAILABLE,
          Exception.PRINT_NOT_AVAILABLE.getTitle(), JOptionPane.ERROR_MESSAGE);
    }
  }

  private void quit() {
    int dialogResult = JOptionPane.showConfirmDialog(drawingPanel, QUIT_DIALOG_MESSAGE,
        QUIT_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (dialogResult == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(FileMenuEnum.New.getLabel())) {
        newFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Open.getLabel())) {
        openFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.Save.getLabel())) {
        saveFile();
      } else if (e.getActionCommand().equals(FileMenuEnum.SaveAs.getLabel())) {
        saveFileAs();
      } else if (e.getActionCommand().equals(FileMenuEnum.Print.getLabel())) {
        print();
      } else if (e.getActionCommand().equals(FileMenuEnum.Quit.getLabel())) {
        quit();
      }
    }
  }
}

