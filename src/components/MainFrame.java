package components;

import constants.Constants;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import menus.EditMenu;
import menus.FileMenu;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private ToolBar toolBar;

    public MainFrame() {
        this.setJMenuBar(new MenuBar()
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );

        drawingPanel = new DrawingPanel();
        toolBar = new ToolBar();

        this.add(drawingPanel);
        this.add(toolBar, BorderLayout.NORTH);
        toolBar.init(drawingPanel);
    }

    public void initialize(){
        this.setTitle(Constants.TITLE_MAINFRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
    }
}
