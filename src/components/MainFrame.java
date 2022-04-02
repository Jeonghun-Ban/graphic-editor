package components;

import constants.Constants;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import menus.ColorMenu;
import menus.EditMenu;
import menus.FileMenu;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        DrawingPanel drawingPanel = new DrawingPanel();
        this.add(drawingPanel);

        MenuBar menuBar = new MenuBar();
        ColorMenu colorMenu = new ColorMenu();
        colorMenu.linkPanel(drawingPanel);

        this.setJMenuBar(menuBar
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
                .addMenu(colorMenu)
        );

        ToolBar toolBar = new ToolBar();
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
