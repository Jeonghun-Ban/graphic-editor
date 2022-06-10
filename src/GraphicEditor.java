import views.containers.DrawingPanel;
import views.containers.MainFrame;

public class GraphicEditor {

  public static void main(String[] args) {
    MainFrame.getInstance().initialize();
    DrawingPanel.getInstance().initialize();
  }
}
