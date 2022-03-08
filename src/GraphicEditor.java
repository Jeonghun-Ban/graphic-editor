import components.*;
import components.MenuBar;

import java.awt.*;


public class GraphicEditor {
    public static void main(String[] args){
        MainFrame mainFrame =  new MainFrame("Graphic Editor");

        mainFrame.setJMenuBar(new MenuBar()
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );
        mainFrame.add(new ToolBar(), BorderLayout.NORTH);

        mainFrame.initialize(600, 400);
    }
}
