package controls;

public class Consts 
{
	public static final double initNearClip = 0.1;
	public static final double initFarClip = 100;
	public static final double initFOV = 35;
	
												//DR   DG   DB   DA   SR   SG   SB   SA
	public static final double[] initMatValues = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
	
													//near far  fov  distance;
	public static final double [] initCameraValues = {0.1, 100, 35,  10};
	
												  // Lr   Lg   Lb   light height  lightAngle
	public static final double [] initLightValues = {1.0, 1.0, 1.0, 0,            0};
	
										 //x y z scale 
	public static final double [] initModelValues = {0,0,0,1};
	
}
