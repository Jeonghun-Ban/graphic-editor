package components;

import elements.EditMenu;
import elements.FileMenu;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private ToolBar toolBar;

    public MainFrame(String title) {
        super(title);
        this.initialize();

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

    private void initialize(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
