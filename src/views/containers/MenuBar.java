package views.containers;

import javax.swing.JMenuBar;
import views.menus.ColorMenu;
import views.menus.EditMenu;
import views.menus.FileMenu;

public class MenuBar extends JMenuBar {

  private static final long serialVersionUID = 1L;

  private static MenuBar menuBar;

  private MenuBar() {
    FileMenu fileMenu = FileMenu.getInstance();
    EditMenu editMenu = EditMenu.getInstance();
    ColorMenu colorMenu = ColorMenu.getInstance();

    this.add(fileMenu);
    this.add(editMenu);
    this.add(colorMenu);
  }

  public static MenuBar getInstance() {
    if (menuBar == null) {
      menuBar = new MenuBar();
    }
    return menuBar;
  }
}
