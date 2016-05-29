import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ControlPanel extends Group
{
	
  
  //Sliders with sliders
  //rotation group;

 
  private ArrayList<Slider> materialsControls = new ArrayList<>(12); 
  
  private Slider modelRotationX; 	
  private Label  modelRotationXLabel;
 
  
  private Slider modelRotationY;
  private Label  modelRotationYLabel;

  private Slider modelRotationZ;
  private Label  modelRotationZLabel;
 
  private Slider modelScale;
  private Label modelScaleLabel;
 
  
  private Slider lightInclinationAngle;
  private Slider lightElevationAngle;
  private Slider lightRotationZ;
  private Slider lightDistance;
  
  private Slider cameraInclinationAngle;
  private Slider cameraElevationAngle;
  private Slider cameraRotationZ;
  private Slider cameraDistance;
  
  
  
  //light color
  private Slider lightColorRed;
  private Slider lightColorGreen;
  private Slider lightColorBlue;
  private Slider lightColorAlpha;
  
  // material properties
  private Slider matDiffRed;
  private Slider matDiffGreen;
  private Slider matDiffBlue;
  private Slider matDiffAlpha;
  
  private Slider matSpecRed;
  private Slider matSpecGreen;
  private Slider matSpecBlue;
  private Slider matSpecAlpha;
  
  private Slider matAmbRed;
  private Slider matAmbGreen;
  private Slider matAmbBlue;
  private Slider matAmbAlpha;
  
  // material properties
  private Slider camFarClip;
  private Slider camNearClip;
  private Slider camFeildOfView;
  
  //buttons
  private ModelViewer modelViewer;
  private Button loadNewMesh , reset,drawMode,cullFace;
  private ComboBox preDefMaterials;
  
  //Containers
  
  HBox masterLayout = new HBox();
  VBox leftColumn = new VBox();
  VBox rightColumn = new VBox();
  
  private GridPane orentationControls;
  private GridPane propertyControls;
  private VBox buttonContainer = new VBox();
  
 
  
  public ControlPanel(ModelViewer modelViewer) 
  {
	
	this.modelViewer = modelViewer;
	initControls();
  }
  
  
 
  private void initControls()
  {
	modelRotationXLabel= new Label("Rotate x");  
	modelRotationYLabel= new Label("Rotate y");
	modelRotationZLabel= new Label("Rotate z");
	modelScaleLabel = new Label("Scale");
	
	modelRotationXLabel.setVisible(true);
	modelRotationZLabel.setVisible(true);
	modelRotationZLabel.setVisible(true);
	
	modelRotationX = new Slider(0,360,0);
    modelRotationY = new Slider(0,360,0);
    modelRotationZ = new Slider(0,360,0);
    modelScale = new Slider(1, 1000, 100);
    
    lightInclinationAngle = new Slider(0,360,0);
    lightElevationAngle = new Slider(0,90,0);
    
    lightDistance = new Slider(0,20,10);
    
    cameraInclinationAngle = new Slider(0,180,0);
    cameraElevationAngle = new Slider(0,360,0);
    cameraDistance = new Slider(0,100,10);
   
    
    materialsControls.add((matDiffRed = new Slider(0,1,0.5)));
    materialsControls.add((matDiffGreen = new Slider(0,1,0.5)));
    materialsControls.add((matDiffBlue = new Slider(0,1,0.5)));
    materialsControls.add((matDiffAlpha = new Slider(0,1,0.5)));
    
    materialsControls.add((matSpecRed = new Slider(0,1,0.5)));
    materialsControls.add((matSpecGreen = new Slider(0,1,0.5)));
    materialsControls.add((matSpecBlue = new Slider(0,1,0.5)));
    materialsControls.add((matSpecAlpha = new Slider(0,1,0.5)));
    
    
     
    lightColorRed = new Slider(0,1,1);
    lightColorGreen = new Slider(0,1,1);
    lightColorBlue = new Slider(0,1,1);
    lightColorAlpha = new Slider(0,1,1);
    
    
    
    
    loadNewMesh = new Button("Load");
    reset = new Button("Reset");
    drawMode = new Button("Draw mode");
    cullFace = new Button("Cull Face");
    
    addButtons();
    
    initPropertyControls();
    initOrentationControls();
    addListeners();
    
    Separator sep = new Separator();
    sep.setOrientation(Orientation.VERTICAL);
    Separator sep2 = new Separator();
    sep2.setOrientation(Orientation.VERTICAL);
    
    
    
    
    masterLayout.getChildren().add(orentationControls);
    masterLayout.getChildren().add(sep);
    masterLayout.getChildren().add(propertyControls);
    masterLayout.getChildren().add(sep2);
    masterLayout.getChildren().add(buttonContainer);
    this.getChildren().add(masterLayout);
  }
  private void initPropertyControls()
  {
	
	  propertyControls= new GridPane();
	  propertyControls.setPadding(new Insets(10,10,10,10));
	  propertyControls.setVgap(10);
	  propertyControls.setHgap(50);
	  
	  propertyControls.add(new Label("Diffuse material color"), 0, 0);
	  propertyControls.add(matDiffRed, 0, 1);
	  propertyControls.add(matDiffGreen, 0, 2);
	  propertyControls.add(matDiffBlue, 0, 3);
	  propertyControls.add(matDiffAlpha, 0, 4);
	  
	  propertyControls.add(new Label("Specular material color"), 0, 5);
	  propertyControls.add(matSpecRed, 0, 6);
	  propertyControls.add(matSpecGreen, 0, 7);
	  propertyControls.add(matSpecBlue, 0, 8);
	  propertyControls.add(matSpecAlpha, 0, 9);
	  
	  propertyControls.add(new Label("Light color"), 0, 10);
	  propertyControls.add(lightColorRed, 0, 11);
	  propertyControls.add(lightColorGreen, 0, 12);
	  propertyControls.add(lightColorBlue, 0, 13);
	  propertyControls.add(lightColorAlpha, 0, 14);
	  
  }
  private void initOrentationControls()
  {
	
	 
	  orentationControls =new GridPane();
	  orentationControls.setPadding(new Insets(10,10,10,10));
	  orentationControls.setVgap(10);
	  orentationControls.setHgap(50);
	  orentationControls.add(new Label("Model Control"), 0, 0);
	  orentationControls.add(modelRotationX, 0, 1);
	  orentationControls.add(modelRotationXLabel,1, 1);
	  
	  orentationControls.add(modelRotationY, 0, 2);
	  orentationControls.add(modelRotationYLabel,1, 2);
	  
	  orentationControls.add(modelRotationZ, 0, 3);
	  orentationControls.add(modelRotationZLabel,1, 3);
	
	  orentationControls.add(modelScale, 0,4 );
	  orentationControls.add(modelScaleLabel, 1, 4);
	  
	  
	  orentationControls.add(new Label("Light Control"), 0, 5);
	  orentationControls.add(lightInclinationAngle, 0,6);
	  orentationControls.add(lightElevationAngle, 0,7);
	  orentationControls.add(lightDistance, 0,8);
	  
	  
	  orentationControls.add(new Label("Camera Control"), 0, 9);
	  orentationControls.add(cameraInclinationAngle, 0, 10);
	  orentationControls.add(cameraElevationAngle, 0, 11);
	  orentationControls.add(cameraDistance, 0, 12);
	 
	
  }
  private void addButtons()
  {
	  buttonContainer.getChildren().add(loadNewMesh);
	  buttonContainer.getChildren().add(reset);
	  buttonContainer.getChildren().add(drawMode);
	  buttonContainer.getChildren().add(cullFace);
	  
	
  }
  
  private void addListeners()
  {
	  
	  
	  //Buttons
	  
	  loadNewMesh.setOnAction(new EventHandler<ActionEvent>() {
		  @Override
          public void handle(ActionEvent event) {
           try {
			modelViewer.loadNewModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          }
      });
	  
	  reset.setOnAction(new EventHandler<ActionEvent>() {
		  @Override
          public void handle(ActionEvent event) {
			    modelViewer.currentModel.setRotationAxis(modelViewer.xAxis);
			    double xAngle = modelViewer.currentModel.getRotate();
			    
			    modelViewer.currentModel.setRotationAxis(modelViewer.yAxis);
			    double yAngle = modelViewer.currentModel.getRotate();
			    
			    modelViewer.currentModel.setRotationAxis(modelViewer.zAxis);
			    double zAngle = modelViewer.currentModel.getRotate();
			    
			   modelViewer.rotateModel('y', -yAngle);
			   modelViewer.rotateModel('x', -xAngle);
			   
			   modelViewer.rotateModel('z', -zAngle);
			   
			   modelRotationX.setValue(0);
			   modelRotationY.setValue(0);
			   modelRotationZ.setValue(0);
			    
          }
      });
	  
	  drawMode.setOnAction(new EventHandler<ActionEvent>() {
		  @Override
          public void handle(ActionEvent event) {
			    drawMode.setText(modelViewer.changeDrawMode());
          }
      });
	  
	  cullFace.setOnAction(new EventHandler<ActionEvent>() {
		  @Override
          public void handle(ActionEvent event) {
			    cullFace.setText(modelViewer.changeCullFace());
          }
      });
	  
	  
	  
	  //Sliders
	  modelRotationX.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	          double rotateAmount = new_val.doubleValue() -old_val.doubleValue();
                      modelViewer.currentModel.rotate('x',rotateAmount);
                      modelRotationXLabel.setText(String.format("%.2f", new_val));
              }
          });
	  
	  modelRotationY.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	          double rotateAmount = new_val.doubleValue() -old_val.doubleValue();
        	          modelViewer.currentModel.rotate('y',rotateAmount);
                      modelRotationYLabel.setText(String.format("%.2f", new_val));
              }
          });
	  
	  modelRotationZ.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	         double rotateAmount = new_val.doubleValue() -old_val.doubleValue();
                      modelViewer.rotateModel('z',rotateAmount);
                      modelRotationZLabel.setText(String.format("%.2f", new_val));
              }
          });
	  modelScale.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	          
        	         double scaleAmount = new_val.doubleValue()/100;
        	         modelScaleLabel.setText(String.format("%.2f", scaleAmount));
        	         modelViewer.currentModel.setScaleX(scaleAmount);
        	         modelViewer.currentModel.setScaleY(scaleAmount);
        	         modelViewer.currentModel.setScaleZ(scaleAmount);
              }
          });
	  
	  cameraInclinationAngle.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	         
                      modelViewer.rotateCamera(new_val.doubleValue(),modelViewer.cameraElevation,modelViewer.cameraDistance);
                      modelRotationZLabel.setText(String.format("%.2f", new_val));
              }
          });
	  
	  cameraElevationAngle.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	          
        	         modelViewer.rotateCamera(modelViewer.cameraInclination,new_val.doubleValue(),modelViewer.cameraDistance);
                      modelRotationZLabel.setText(String.format("%.2f", new_val));
              }
          });
	  
	  cameraDistance.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	        modelViewer.moveCamera(new_val.doubleValue());
              }
          });
	  
	  
	  lightInclinationAngle.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	        
        	         modelViewer.rotateLight(new_val.doubleValue(),modelViewer.lightElevation,modelViewer.lightDistance);
              }
          });
	  
	  lightElevationAngle.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.rotateLight(modelViewer.lightInclination,new_val.doubleValue(),modelViewer.lightDistance);
                    
              }
          });
	  lightDistance.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	        modelViewer.moveLight(new_val.doubleValue());
              }
          });
	 
	  
	  matDiffRed.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeDiffuse('r',new_val.doubleValue());
                    
              }
          });
	  matDiffGreen.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeDiffuse('g',new_val.doubleValue());
                    
              }
          });
	  matDiffBlue.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeDiffuse('b',new_val.doubleValue());
                    
              }
          });
	  matDiffAlpha.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeDiffuse('a',new_val.doubleValue());
                    
              }
          });
	  
	  
	  
	  matSpecRed.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeSpecular('r',new_val.doubleValue());
                    
              }
          });
	  matSpecGreen.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeSpecular('g',new_val.doubleValue());
                    
              }
          });
	  matSpecBlue.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeSpecular('b',new_val.doubleValue());
                    
              }
          });
	  matSpecAlpha.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeSpecular('a',new_val.doubleValue());
                    
              }
          });
	  
	  lightColorRed.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeLightColor('r',new_val.doubleValue());
                    
              }
          });
	  lightColorGreen.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeLightColor('g',new_val.doubleValue());
                    
              }
          });
	  lightColorBlue.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeLightColor('b',new_val.doubleValue());
                    
              }
          });
	  lightColorAlpha.valueProperty().addListener(new ChangeListener<Number>() {
          public void changed(ObservableValue<? extends Number> ov,
                  Number old_val, Number new_val) {
        	  modelViewer.changeLightColor('a',new_val.doubleValue());
                    
              }
          });
	  
	  
	  
	  
	  
	  
	  
  }
  
  
}
