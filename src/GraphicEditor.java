import components.*;

public class GraphicEditor {
    public static void main(String[] args){
        MainFrame mainFrame =  new MainFrame("Graphic Editor");

        mainFrame.setJMenuBar(new MenuBar()
                .addMenu(new FileMenu())
                .addMenu(new EditMenu())
        );

        mainFrame.initialize(600, 400);
    }
}
