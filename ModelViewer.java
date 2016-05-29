import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/*TODO
 * make model viewer run in separate thread.
 * 
 * 
 * 
 * */

public class ModelViewer 
{

  public  Model currentModel;
  public  ArrayList<Model> models = new ArrayList<Model>();
  public PerspectiveCamera camera;
  public PointLight light;
  private Color lightColor;
  private Material currentMaterial;
  private ArrayList<Material> preDefinedmaterials = new ArrayList<Material>();
  private ObjLoader objLoader;
  
  private Stage stage3D;
  private Scene scene3D;
  private Group root;
  public Point3D xAxis = new Point3D(1,0,0);
  public Point3D yAxis = new Point3D(0,1,0);
  public Point3D zAxis = new Point3D(0,0,1);
  
  public double lightInclination=0;
  public double lightElevation=0;
  public double lightDistance=10;
  
  public double cameraInclination=0;
  public double cameraElevation=0;
  public double cameraDistance=10;
  
  
  private double initLightColorVal = 1;
  public double  lightR = initLightColorVal;
  public double  lightG = initLightColorVal;
  public double  lightB = initLightColorVal;
  public double  lightA = initLightColorVal;
  
  
  public ModelViewer(Stage mainStage) throws IOException
  {
	  this.objLoader = new ObjLoader(mainStage);
	  currentModel= new Model(objLoader.getNewMeshFromFile());
	  currentModel.rotate('x', 180);
	  camera = new PerspectiveCamera(true);
	  lightColor = new Color(1f,1f,1f,1f);
	  light = new PointLight(lightColor);
	  
	  //light.setTranslateY(-lightDistance);
	  //camera.setTranslateZ(-cameraDistance);
	  root = new Group();
	  
	  
	  setCameraAndLight();
	  scene3D = new Scene(root,500,500,true);
	  scene3D.setFill(Color.BLACK);
	  root.getChildren().addAll(currentModel,light);
	  stage3D = new Stage();
	  stage3D.setScene(scene3D);
	  scene3D.setCamera(camera);
	  stage3D.show();
  }
  
  
  public void addModelToList(Model model)
  {
	  models.add(model);
  }
  
  public void loadNewModel() throws IOException
  {
	  models.add(currentModel);
	  root.getChildren().remove(currentModel);
	  
	  currentModel = new Model(objLoader.getNewMeshFromFile());
	  root.getChildren().add(currentModel);
	  
  }
  private void setCameraAndLight()
  {
	  light.setTranslateY(-lightDistance);
	  rotateLight(lightInclination,lightElevation,-lightDistance);
	  
	  camera.setTranslateZ(-cameraDistance);
	  rotateLight(cameraInclination,lightElevation,cameraDistance);
  }
  
  private void rotateNode(Node node,char type,double amt)
  {
	  Rotate rot =null;
	  switch(type)
	  {
	  case 'x':rot= new Rotate(amt,xAxis);break;
	  case 'y':rot= new Rotate(amt,yAxis);break;
	  case 'z':rot =new Rotate(amt,zAxis);break;
	  }
	  rot.setPivotX(currentModel.getTranslateX());
	  rot.setPivotY(currentModel.getTranslateY());
	  rot.setPivotZ(currentModel.getTranslateZ());
	  node.getTransforms().add(rot);
  }
  public void rotateModel(char type,double amt)
  {
	  rotateNode(this.currentModel,type, amt);
	  
  }
  
  private void rotateSpherically(Node node, double inclination , double elevation, double distance)
  {
	  double x = node.getTranslateX() + distance*Math.sin(elevation)*Math.cos(inclination);
	  double y = node.getTranslateY() + distance*Math.sin(elevation)*Math.sin(inclination);
	  double z = node.getTranslateZ() + distance*Math.cos(elevation);
	  
	  node.setTranslateX(x);
	  node.setTranslateY(y);
	  node.setTranslateZ(z);
	  
  }
  public String changeCullFace()
  {
	  String val;
	  CullFace currentFace = currentModel.getCullFace();
	  if(currentFace == CullFace.NONE)
	  {
		  val = "Back";
		  currentModel.setCullFace(CullFace.BACK);
	  }
	  else if (currentFace == CullFace.BACK)
	  {
		  val = "Front";
		  currentModel.setCullFace(CullFace.FRONT);
	  }
	  else 
	  {
		  val = "None";
		  currentModel.setCullFace(CullFace.NONE);
	  }
	  
	  return val;
	  
  }
  public String changeDrawMode()
  {
	  String val;
	  if(this.currentModel.getDrawMode()==DrawMode.FILL)
	  {
		  val = "Line";
		  this.currentModel.setDrawMode(DrawMode.LINE);
	  }
	  else
	  {
		  this.currentModel.setDrawMode(DrawMode.FILL);
		  val = "Fill";
	  }
	  return val;
  }
  public void moveCamera(double distance)
  {
	  camera.setTranslateZ(-distance);
  }
  public void moveLight(double distance)
  {
	  light.setTranslateY(-distance);
  }
  
  public void rotateCamera(double inclination, double elevation, double distance)
  {
	  
	  rotateSpherically(camera, inclination,elevation, distance);
	  cameraInclination= inclination;
	  cameraElevation= elevation;
	  cameraDistance=distance;
  }
  
  public void rotateLight(double inclination, double elevation, double distance)
  {
	  rotateSpherically(light, inclination,elevation, distance);
	  lightInclination= inclination;
	  lightElevation= elevation;
	  lightDistance=distance;
  }
  
  
  public void setUniformModelScale(double scaleAmount)
  {
	  currentModel.setScaleX(scaleAmount);
      currentModel.setScaleY(scaleAmount);
      currentModel.setScaleZ(scaleAmount);
  }
  public void changeLightColor(char type, double newValue)
  {
	 switch(type)
	 {
	 case 'r': lightR= newValue; break;
	 case 'g': lightG= newValue; break;
	 case 'b': lightB= newValue; break;
	 case 'a': lightA = newValue; break;
	 }
	 light.setColor(Color.color(lightR, lightG, lightB,lightA));
  }
  public void changeDiffuse(char type, double newValue)
  {
	 switch(type)
	 {
	 case 'r': currentModel.diffR= newValue; break;
	 case 'g': currentModel.diffG= newValue; break;
	 case 'b': currentModel.diffB= newValue; break;
	 case 'a': currentModel.diffA = newValue; break;
	 }
	 this.currentModel.material.setDiffuseColor(Color.color(currentModel.diffR, currentModel.diffG, currentModel.diffB,currentModel.diffA));
  }
 
  public void changeSpecular(char type, double newValue)
  {
	 switch(type)
	 {
	 case 'r': currentModel.specR= newValue; break;
	 case 'g': currentModel.specG= newValue; break;
	 case 'b': currentModel.specB= newValue; break;
	 case 'a': currentModel.specA = newValue; break;
	 }
	 this.currentModel.material.setSpecularColor(Color.color(currentModel.specR, currentModel.specG, currentModel.specB,currentModel.specA));
  }
  
}
