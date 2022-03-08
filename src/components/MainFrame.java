package components;

import javax.swing.*;


public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);
    }

    public void initialize(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
