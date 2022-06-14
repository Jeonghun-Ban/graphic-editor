package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileStore {

  public static final FileNameExtensionFilter filter =
      new FileNameExtensionFilter("graphic editor(.grt)", "grt");

  public void save(File file, Object object) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(object);
    objectOutputStream.close();
  }

  public Object load(File file) throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(file);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    Object object = objectInputStream.readObject();
    objectInputStream.close();
    return object;
  }
}
