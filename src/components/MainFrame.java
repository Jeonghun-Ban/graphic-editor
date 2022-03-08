package components;

import javax.swing.*;
import java.util.List;


public class MainFrame extends JFrame {

    public MainFrame(String title, List<JMenu> menuList) {
        super(title);
        setJMenuBar(new MenuBar(menuList));
    }

    public void initialize(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
