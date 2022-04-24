package utils;

import containers.DrawingPanel;
import enums.Exception;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class FileStore {

  private DrawingPanel drawingPanel;

  public void associate(DrawingPanel drawingPanel) {
    this.drawingPanel = drawingPanel;
  }

  public void save(String path, Object object) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(path);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(object);
      objectOutputStream.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.FILE_STORE_SAVE_ERROR,
          Exception.FILE_STORE_SAVE_ERROR.getTitle(), JOptionPane.ERROR_MESSAGE);
    }
  }

  public Object load(String path) {
    try {
      FileInputStream fileInputStream = new FileInputStream(path);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object object = objectInputStream.readObject();
      objectInputStream.close();
      return object;
    } catch (IOException | ClassNotFoundException e) {
      JOptionPane.showMessageDialog(drawingPanel, Exception.FILE_STORE_LOAD_ERROR,
          Exception.FILE_STORE_LOAD_ERROR.getTitle(), JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }
}
