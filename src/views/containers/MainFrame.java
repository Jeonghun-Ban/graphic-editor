package views.containers;

import static global.Constants.HEIGHT_MAINFRAME;
import static global.Constants.TITLE_MAINFRAME;
import static global.Constants.WIDTH_MAINFRAME;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import views.menubar.MenuBar;
import views.toolbar.ToolBar;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private static MainFrame mainFrame = null;

  private MainFrame() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();
    MenuBar menuBar = MenuBar.getInstance();
    ToolBar toolBar = ToolBar.getInstance();

    toolBar.setDefaultButton();

    this.add(drawingPanel);
    this.setJMenuBar(menuBar);
    this.add(toolBar, BorderLayout.NORTH);
  }

  public static MainFrame getInstance() {
    if (mainFrame == null) {
      mainFrame = new MainFrame();
    }
    return mainFrame;
  }

  public void initialize() {
    this.setTitle(TITLE_MAINFRAME);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH_MAINFRAME, HEIGHT_MAINFRAME);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}
