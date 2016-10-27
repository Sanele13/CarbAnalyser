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
     // System.out.println(dotProduct(A,B));
      float magA = (float)Math.sqrt(Math.pow((A[0]),2)+ Math.pow((A[1]),2) + Math.pow((A[2]),2));
      //System.out.println("MagA: "+magA);
      
      float magB = (float)Math.sqrt(Math.pow((B[0]),2)+ Math.pow((B[1]),2) + Math.pow((B[2]),2));
     // System.out.println("MagB: "+magB);
     // System.out.println((float)magB*(float)magA);
      //System.out.println("dot pro: "+dotProduct(A,B));
      double ratio = dotProduct(A,B)/(float)(magA*magB);
      //System.out.println(ratio);
      
     // System.out.println("ratio: "+ratio);
     // String result = String.format("%.3f", ratio);
     // float r = Float.parseFloat(result);
     // System.out.println(r);
      DecimalFormat df = new DecimalFormat("#0.00000");
      String s = df.format(ratio);
      return Math.toDegrees(Math.acos(Double.parseDouble(s)));
   }
   
    public double calcDihedral(double[] v1,double [] v2,double [] v3, double [] v4) {
    
		double cosphi;
		double sinphi;
		double phi;
      
      /*System.out.println(Arrays.toString(v1));
      System.out.println(Arrays.toString(v2));
      System.out.println(Arrays.toString(v3));
      System.out.println(Arrays.toString(v4));
      System.out.println("---------------------------------------");
      System.out.println("---------------------------------------");*/
		/*Vector3d rab = new Vector3d(v1);
		Vector3d rcb = new Vector3d(v2);
		Vector3d rbc = new Vector3d(v3);
		Vector3d rdc = new Vector3d(v4);*/

		/*rab.sub(i.getPosition(), j.getPosition());
		rcb.sub(k.getPosition(), j.getPosition());
		rbc.sub(j.getPosition(), k.getPosition());
		rdc.sub(l.getPosition(), k.getPosition());*/

      double [] v1_cross_v2 = crossProduct(v1,v2);
     // System.out.println("v1_cross_v2: "+Arrays.toString(v1_cross_v2));
     // v1_cross_v2[0]=v1_cross_v2[0]/Math.sin(angle(v1,v2));
     // v1_cross_v2[1]=v1_cross_v2[1]/Math.sin(angle(v1,v2));
      //v1_cross_v2[2]=v1_cross_v2[2]/Math.sin(angle(v1,v2));

      //System.out.println(Arrays.toString(v1_cross_v2));
		//Vector3d rabCrossrcb = new Vector3d();
		//rabCrossrcb.cross(rab, rbc);

      double [] v3_cross_v4 = crossProduct(v3,v4);
     // System.out.println("v3_cross_v4: "+Arrays.toString(v3_cross_v4));
      //v3_cross_v4[0]=v3_cross_v4[0]/Math.sin(angle(v3,v4));
     // v3_cross_v4[1]=v3_cross_v4[1]/Math.sin(angle(v3,v4));
     // v3_cross_v4[2]=v3_cross_v4[2]/Math.sin(angle(v3,v4));
      //System.out.println(Arrays.toString(v3_cross_v4));
		//Vector3d rbcCrossrdc = new Vector3d();
		//rbcCrossrdc.cross(rcb, rdc);

		//cosphi = rabCrossrcb.angle(rbcCrossrdc);
      cosphi = angle(v1_cross_v2,v3_cross_v4);
      //System.out.println(cosphi);
      
      
		//sinphi = rabCrossrcb.dot(rdc);
      sinphi = dotProduct(v1_cross_v2 , v4);
     // System.out.println("sinphi: "+sinphi);
		phi = sinphi < 0 ? -cosphi : cosphi;

		return phi;
      
    }
   
   
   public double dotProduct(double [] A, double [] B){
      double dp;
     // System.out.println("dot product of "+Arrays.toString(A)+ " and "+ Arrays.toString(B));
      dp = (A[0]*B[0]) + (A[1]*B[1]) + (A[2]*B[2]);
      return dp;
   }
   
   
   public double[] crossProduct(double [] A, double[] B){
      double[] cp = new double[3];
      cp[0]=A[1]*B[2] - A[2]*B[1];
      cp[1]=A[2]*B[0] - A[0]*B[2];
      cp[2]=A[0]*B[1] - A[1]*B[0];
      return cp;
   }
   /*public static void main(String [] args){
      double [] v1 = {-0.034,-1.071,-0.011};
      double [] v2 = {-1.314,0.516,0.221};
      double [] v3 = {1.314,-0.516,-0.221};
      double [] v4 = {0.04,1.334,0.491};
      
      
      
      AngleCalculator ac = new AngleCalculator();
      double [] cross1 = {-0.231015,0.021968,-1.424838};
      double [] cross2 = {0.041458,-0.654014,1.773516};
      System.out.println("angle: "+ac.calcDihedral(v1,v2,v3,v4));
     // System.out.println(ac.dotProduct(ac.crossProduct(v1,v2), ac.crossProduct(v3,v4)));
  
      
      
   }*/

   
}