import components.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicEditor {
    public static void main(String args[]){

        List<JMenu> menuList = new ArrayList<JMenu>();
        menuList.add(new FileMenu());
        menuList.add(new EditMenu());

        MainFrame mainFrame =  new MainFrame("Graphic Editor", menuList);
        mainFrame.initialize(600, 400);
    }
}
