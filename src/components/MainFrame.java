package components;

import constants.Constants;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import menus.EditMenu;
import menus.FileMenu;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private MenuBar menuBar;
    private ToolBar toolBar;

    public MainFrame() {
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);

        menuBar = new MenuBar();
        this.setJMenuBar(menuBar
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );

        toolBar = new ToolBar();
        toolBar.linkPanel(drawingPanel);
        this.add(toolBar, BorderLayout.NORTH);
    }

    public void initialize(){
        this.setTitle(Constants.TITLE_MAINFRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH_MAINFRAME, Constants.HEIGHT_MAINFRAME);
        this.setVisible(true);
    }
}
