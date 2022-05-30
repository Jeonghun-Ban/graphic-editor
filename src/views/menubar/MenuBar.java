package views.menubar;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

  private static final long serialVersionUID = 1L;

  private static MenuBar menuBar;

  private MenuBar() {
    FileMenu fileMenu = FileMenu.getInstance();
    EditMenu editMenu = EditMenu.getInstance();

    this.add(fileMenu);
    this.add(editMenu);
  }

  public static MenuBar getInstance() {
    if (menuBar == null) {
      menuBar = new MenuBar();
    }
    return menuBar;
  }
}
