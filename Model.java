import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;


public class Model extends MeshView 
{
	
 private Point3D xAxis = new Point3D(1,0,0);
 private Point3D yAxis = new Point3D(0,1,0);
 private Point3D zAxis = new Point3D(0,0,1);
 public PhongMaterial material= new PhongMaterial();
 
 private double initColorVal = 0.5;
 
 

 
 
 public double diffR=initColorVal;
 public double diffG=initColorVal;
 public double diffB=initColorVal;
 public double diffA=initColorVal;
 
 public double specR=initColorVal;
 public double specG=initColorVal;
 public  double specB=initColorVal;
 public double specA=initColorVal;
 
 
 private final Color defualtColor = new Color(initColorVal,initColorVal,initColorVal,1);
 
 
 public Model(Mesh mesh)
  {
	  super(mesh);
	  
	  initMaterialValues();
	  this.setMaterial(material);
	  this.setDrawMode(DrawMode.FILL);
	  
	  
  }
 
 
  
  
  private void initMaterialValues()
  {   
	  material.setDiffuseColor(defualtColor);
	  material.setSpecularColor(defualtColor);
  }
 
  public void rotate(char type,double amt)
  {
	  Rotate rot =null;
	  switch(type)
	  {
	  case 'x':rot= new Rotate(amt,xAxis);break;
	  case 'y':rot= new Rotate(amt,yAxis);break;
	  case 'z':rot =new Rotate(amt,zAxis);break;
	  }
	  this.getTransforms().add(rot);
  }
  
}
