package views.dialogs;

import global.Exception;
import global.Message;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tools.draw.DrawShape;
import utils.FileStore;
import views.containers.DrawingPanel;

public class FileDialog {

  private static FileDialog fileDialog;

  private final JFileChooser fileChooser;
  private final FileStore fileStore;

  private File file;
  private int dialogOption;
  private final DrawingPanel drawingPanel;

  private FileDialog() {
    fileChooser = new JFileChooser();
    fileStore = new FileStore();
    file = null;
    drawingPanel = DrawingPanel.getInstance();
  }

  public static FileDialog getInstance() {
    if (fileDialog == null) {
      fileDialog = new FileDialog();
    }
    return fileDialog;
  }

  public void newFile() {
    if (this.drawingPanel.isUpdated()) {
      dialogOption = JOptionPane.showConfirmDialog(drawingPanel, Message.SAVE_FILE_DIALOG,
          Message.SAVE_FILE_DIALOG.getTitle(), JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);
      if (dialogOption == JOptionPane.YES_OPTION) {
        saveFile();
      }
    }

    dialogOption = JOptionPane.showConfirmDialog(drawingPanel, Message.NEW_FILE_DIALOG,
        Message.NEW_FILE_DIALOG.getTitle(), JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    if (dialogOption == JOptionPane.YES_OPTION) {
      drawingPanel.clear();
      this.file = null;
    }
  }

  public void openFile() {
    if (this.drawingPanel.isUpdated()) {
      dialogOption = JOptionPane.showConfirmDialog(drawingPanel, Message.SAVE_FILE_DIALOG,
          Message.SAVE_FILE_DIALOG.getTitle(), JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);
      if (dialogOption == JOptionPane.YES_OPTION) {
        saveFile();
      }
    }

    try {
      dialogOption = fileChooser.showOpenDialog(drawingPanel);
      if (dialogOption == JFileChooser.APPROVE_OPTION) {
        this.file = fileChooser.getSelectedFile();
        @SuppressWarnings("unchecked")
        List<DrawShape> drawShapeList = (List<DrawShape>) fileStore.load(file);
        this.drawingPanel.setDrawShapes(drawShapeList);
      }
    } catch (IOException | ClassNotFoundException e) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.FILE_STORE_LOAD_ERROR,
          Exception.FILE_STORE_LOAD_ERROR.getTitle(), JOptionPane.ERROR_MESSAGE);
    }
  }

  public void saveFile() {
    try {
      if (this.drawingPanel.isUpdated()) {
        if (this.file == null) {
          saveFileAs();
        } else {
          List<DrawShape> drawShapeList = drawingPanel.getDrawShapes();
          fileStore.save(this.file, drawShapeList);
          this.drawingPanel.setUpdated(false);
        }
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.FILE_STORE_SAVE_ERROR,
          Exception.FILE_STORE_SAVE_ERROR.getTitle(), JOptionPane.ERROR_MESSAGE);
    }
  }

  public void saveFileAs() {
    try {
      dialogOption = fileChooser.showSaveDialog(drawingPanel);
      if (dialogOption == JFileChooser.APPROVE_OPTION) {
        this.file = fileChooser.getSelectedFile();
        List<DrawShape> drawShapeList = drawingPanel.getDrawShapes();
        fileStore.save(this.file, drawShapeList);
        this.drawingPanel.setUpdated(false);
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.FILE_STORE_SAVE_ERROR,
          Exception.FILE_STORE_SAVE_ERROR.getTitle(), JOptionPane.ERROR_MESSAGE);
    }
  }

  public void print() {
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

  public void quit() {
    dialogOption = JOptionPane.showConfirmDialog(drawingPanel, Message.QUIT_DIALOG,
        Message.QUIT_DIALOG.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (dialogOption == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }
}
