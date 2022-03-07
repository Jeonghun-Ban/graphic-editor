package components;

import javax.swing.JFrame;


public class Window extends JFrame {
    public Window(String title) {
        super(title);
    }

    public void initialize(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
