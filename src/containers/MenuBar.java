package containers;

import javax.swing.JMenuBar;
import menus.ColorMenu;
import menus.EditMenu;
import menus.FileMenu;

public class MenuBar extends JMenuBar {

  private static final long serialVersionUID = 1L;

  private final FileMenu fileMenu;
  private final EditMenu editMenu;
  private final ColorMenu colorMenu;

  public MenuBar() {
    fileMenu = new FileMenu();
    editMenu = new EditMenu();
    colorMenu = new ColorMenu();

    this.add(fileMenu);
    this.add(editMenu);
    this.add(colorMenu);
  }

  public void associate(DrawingPanel drawingPanel) {
    this.fileMenu.associate(drawingPanel);
    this.colorMenu.associate(drawingPanel);
  }
}
