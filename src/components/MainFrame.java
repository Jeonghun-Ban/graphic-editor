package components;

import global.Constants;
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

        this.add(drawingPanel);
        this.setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.NORTH);
    }

    public void initialize(){
        this.setTitle(Constants.TITLE_MAINFRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH_MAINFRAME, Constants.HEIGHT_MAINFRAME);
        this.setVisible(true);
    }
}
