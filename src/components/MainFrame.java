package components;

import elements.EditMenu;
import elements.FileMenu;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);
        this.initialize();

        this.setJMenuBar(new MenuBar()
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );
        this.add(new ToolBar(), BorderLayout.NORTH);
        this.add(new DrawingPanel());
    }

    private void initialize(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
