import java.util.*;
import java.io.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class AngleCalculator{
   private double [] vector = new double[3];
   private double angle;
   private double dihedralAngle;
   
   AngleCalculator(){
      this.angle = 0;
      this.dihedralAngle = 0;
   }
   
   public double getLength(double [] vector){
      return Math.sqrt(Math.pow((vector[0]),2)+ Math.pow((vector[1]),2) + Math.pow((vector[2]),2));
   }
   public double angle(double [] A ,double [] B){
      float magA = (float)Math.sqrt(Math.pow((A[0]),2)+ Math.pow((A[1]),2) + Math.pow((A[2]),2)); //|A|
      float magB = (float)Math.sqrt(Math.pow((B[0]),2)+ Math.pow((B[1]),2) + Math.pow((B[2]),2));//|B|
      double ratio = dotProduct(A,B)/(float)(magA*magB);//(A dot B)/|A||B|
      DecimalFormat df = new DecimalFormat("#0.00000");
      String s = df.format(ratio);
      return Math.toDegrees(Math.acos(Double.parseDouble(s))); //return angle in degrees
   }
   
    public double calcDihedral(double[] v1,double [] v2,double [] v3, double [] v4) {
      //the dihedral angle is the angle between to planes (i.e. angle between the normal vectors of the plane)
      //If A-B-C-D represents the bonds, B-C is the vector that lies on both planes
      //v3 is basically a negated v2
      double phi;
      double n1_dot_v4;

      double [] n1 = crossProduct(v1,v2); // normal vector
      double [] n2 = crossProduct(v3,v4);
      phi = angle(n1,n2); //angle between the normal vectors
      n1_dot_v4 = dotProduct(n1 , v4); //to determine the sign of the angle
	    
      return n1_dot_v4 < 0 ? -phi : phi;;
      
    }
   
   
   public double dotProduct(double [] A, double [] B){
      double dp; //the dot product of A and B
      dp = (A[0]*B[0]) + (A[1]*B[1]) + (A[2]*B[2]);
      return dp;
   }
   
   
   public double[] crossProduct(double [] A, double[] B){
      double[] cp = new double[3]; //AXB
      cp[0]=A[1]*B[2] - A[2]*B[1];
      cp[1]=A[2]*B[0] - A[0]*B[2];
      cp[2]=A[0]*B[1] - A[1]*B[0];
      return cp;
   }
 
}
