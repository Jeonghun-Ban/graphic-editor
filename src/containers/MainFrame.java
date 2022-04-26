package containers;

import static global.Constants.HEIGHT_MAINFRAME;
import static global.Constants.TITLE_MAINFRAME;
import static global.Constants.WIDTH_MAINFRAME;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  public MainFrame() {
    DrawingPanel drawingPanel = new DrawingPanel();
    MenuBar menuBar = new MenuBar();
    ToolBar toolBar = new ToolBar();

    menuBar.associate(drawingPanel);
    toolBar.associate(drawingPanel);
    toolBar.setDefaultButton();

    this.add(drawingPanel);
    this.setJMenuBar(menuBar);
    this.add(toolBar, BorderLayout.NORTH);
  }

  public void initialize() {
    this.setTitle(TITLE_MAINFRAME);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH_MAINFRAME, HEIGHT_MAINFRAME);
    this.setVisible(true);
  }
}
