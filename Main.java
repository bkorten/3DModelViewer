import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;


public class Main extends Application {

  private ControlPanel controlPanel;
  private ModelViewer modelViewer;
  private Group root;
  private int windowSize = 500;
  
@Override
public void start(Stage mainStage) throws Exception 
{
    modelViewer = new ModelViewer(mainStage);
	controlPanel = new ControlPanel(modelViewer);
	
	

	
	/*controls stage
	 * UI components run in the application thread
	 * */
	root = new Group();
	Scene scene = new Scene(root, windowSize, windowSize, true);
	mainStage.setScene(scene);
	root.getChildren().add(controlPanel);
	mainStage.setX(0);
	mainStage.setY(0);
	mainStage.show();
	
	 
}
  class MainLoop extends AnimationTimer
  {

	@Override
	public void handle(long arg0) {
		// TODO Auto-generated method stub
		
	}
	  
  }
  
	
	
public static void main(String[] args)
{
 launch(args);	
}
}