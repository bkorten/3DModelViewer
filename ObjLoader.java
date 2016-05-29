import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.shape.Mesh;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ObjLoader 
{
	File currentObjFile;
	
	
	
	
	float vertexCoords[];
	float vertexNormals[];
	float surfaceNormals[];
	int faceIndex[];
	Stage mainStage;
	FileChooser fileChooser;
	
	public ObjLoader(Stage mainStage)
	{
	
		this.mainStage = mainStage;
		fileChooser =new FileChooser();
		
	}
	
	public TriangleMesh getNewMeshFromFile() throws IOException
	{
		TriangleMesh newMesh = new TriangleMesh();
		newMesh.getTexCoords().setAll(0,0);
		fileChooser();
		parseFile();
		
		newMesh.getPoints().setAll(vertexCoords);
		newMesh.getFaces().setAll(faceIndex);
		return newMesh;
		
	}
	private void fileChooser()
	{
		currentObjFile = fileChooser.showOpenDialog(mainStage);	
	}
	private void parseFile() throws IOException
	{
		BufferedReader buffReader1 = new BufferedReader(new FileReader(currentObjFile));
		BufferedReader buffReader2 = new BufferedReader(new FileReader(currentObjFile));
		String currentLine;
		String[] tokens;
		String type;
		int numVertices=0;
	    int numFaces=0;
	    int numVertexNormals=0;
	
		while((currentLine= buffReader1.readLine())!=null)
		{
		  
		  tokens = currentLine.split("\\s+");
		  type = tokens[0];
		  if(type.equals("v"))
		  {
			  numVertices+=3;
		  }
		  else if (type.equals("vn"))
		  {
			  numVertexNormals+=3;
		  }
		  else if(type.equals("f"))
		  {
			  numFaces+=(tokens.length-1)*2;
		  }
		  
		}
		buffReader1.close();
		
		
		
		
		vertexCoords= new float [numVertices];
		faceIndex = new int [numFaces];
		vertexNormals= new float[numVertexNormals];
		
		int vertexCounter = 0;
	    int faceCounter = 0;
		int normalCounter =0;
		float x , y, z, length;
		
		while((currentLine= buffReader2.readLine())!=null)
		{
		
	      tokens = currentLine.split("\\s+");
		  type = tokens[0];
		   
		  if(type.equals("v"))
		  {
			 x = Float.parseFloat(tokens[1]);
			 y = Float.parseFloat(tokens[2]);
			 z = Float.parseFloat(tokens[3]);
			 
			 
			 vertexCoords[vertexCounter] = x;
			 vertexCounter++;
			 vertexCoords[vertexCounter] = y;
			 vertexCounter++;
			 vertexCoords[vertexCounter] = z;
			 vertexCounter++;
			 
		  }
		  else if (type.equals("vn"))
		  {
			  vertexNormals[normalCounter] = Float.parseFloat(tokens[1]);
			  normalCounter++;
			  vertexNormals[normalCounter] = Float.parseFloat(tokens[2]);
		 	  normalCounter++;
			  vertexNormals[normalCounter] = Float.parseFloat(tokens[3]);
		      normalCounter++;
		  }
		  else if (type.equals("vt"))
		  {
			  
		  }
		  else if(type.equals("f"))
		  {  
			 if(tokens.length-1==3)
			 {	 
			 faceIndex[faceCounter] = Integer.parseInt(tokens[1])-1;
		     faceCounter++;
		     
		     
		     faceIndex[faceCounter] = 0;
		     faceCounter++;
		    
		     
			 faceIndex[faceCounter] = Integer.parseInt(tokens[2])-1;
			 faceCounter++;
			 
			 faceIndex[faceCounter] = 0;
			 faceCounter++;
			 
			 
			 faceIndex[faceCounter] = Integer.parseInt(tokens[3])-1;
			 faceCounter++;
			
			 faceIndex[faceCounter] = 0;
			 faceCounter++;
			 }
			 
			 else
			 {
				 
				 //tri 1
				 faceIndex[faceCounter] = Integer.parseInt(tokens[1])-1;
			     faceCounter++;
			     
			     
			     faceIndex[faceCounter] = 0;
			     faceCounter++;
			    
			     
				 faceIndex[faceCounter] = Integer.parseInt(tokens[2])-1;
				 faceCounter++;
				 
				 faceIndex[faceCounter] = 0;
				 faceCounter++;
				 
				 
				 faceIndex[faceCounter] = Integer.parseInt(tokens[3])-1;
				 faceCounter++;
				
				 
				 
				 
				 //tri 2
				 faceIndex[faceCounter] = 0;
				 faceCounter++;
				 
				 faceIndex[faceCounter] = Integer.parseInt(tokens[1])-1;
			     faceCounter++;
			     
			     
			     faceIndex[faceCounter] = 0;
			     faceCounter++;
			    
			     
				 faceIndex[faceCounter] = Integer.parseInt(tokens[3])-1;
				 faceCounter++;
				 
				 faceIndex[faceCounter] = 0;
				 faceCounter++;
				 
				 
				 faceIndex[faceCounter] = Integer.parseInt(tokens[4])-1;
				 faceCounter++;
				
				 faceIndex[faceCounter] = 0;
				 faceCounter++;
				 
			 }
			 
			 
			
		  }
		 
		}
	    
		buffReader2.close();
	    
		
		
		
	}
	private float calculateLength(float x, float y, float z)
	{
		float inside = x*x + y*y + z*z;
		return (float)Math.sqrt(inside);
		
	}
	private void calculateNormals()
	{
		float vx ,vy, vz;
		 
		float v1x, v1y ,v1z , v2x, v2y, v2z;
		
		//v = v1 cross product v2
		
		//for (int i = 0; i < numFaces; i ++)
		{
		 
		}
		
		
	}
	
	
	
}
