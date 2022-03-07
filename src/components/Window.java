package components;

import javax.swing.*;


public class Window extends JFrame {
    public Window() {
        super("Graphic Editor");
    }

    public void initialize() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
