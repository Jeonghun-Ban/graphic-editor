import components.MainFrame;
import components.DrawingPanel;
import components.MenuBar;
import components.ToolBar;

import elements.EditMenu;
import elements.FileMenu;

import java.awt.*;

public class GraphicEditor {
    public static void main(String[] args){
        MainFrame mainFrame =  new MainFrame("Graphic Editor");

        mainFrame.setJMenuBar(new MenuBar()
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );
        mainFrame.add(new ToolBar(), BorderLayout.NORTH);
        mainFrame.add(new DrawingPanel());

        mainFrame.initialize(600, 400);
    }
}
