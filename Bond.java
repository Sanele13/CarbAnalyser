/*
Name: Sanele Mpangalala
Date:08/09/2016
Capstone Project

*/

import java.util.*;

public class Bond{

   
   //pair of bonding atoms
   Atom A;
   Atom B;

   Bond(Atom A, Atom B){
      
      this.A = A;
      this.B = B;
   }
   
   public boolean isBonded(Atom a, Atom b, double distance){
      boolean result = false;
      if((a.getSymbol().equals("C")&&b.getSymbol().equals("C"))){
         if (distance < 2.00){
        // System.out.println(distance);
            result = true;
         }
      }

      else if((a.getSymbol().equals("C")&&b.getSymbol().equals("H"))||(a.getSymbol().equals("H")&&b.getSymbol().equals("C"))){
         if (distance < 1.60){
        // System.out.println(distance);
            result = true;
         }
      }

     else if((a.getSymbol().equals("C")&&b.getSymbol().equals("O"))||(a.getSymbol().equals("O")&&b.getSymbol().equals("C"))){
         if (distance < 1.96){
        // System.out.println(distance);
            result = true;
         }
      }
      

      else if((a.getSymbol().equals("H")&&b.getSymbol().equals("O"))||(a.getSymbol().equals("O")&&b.getSymbol().equals("H"))){
         if (distance < 1.10){
        // System.out.println(distance);
            result = true;
         }
      }     

      
      
      else{
         result = false;
      }
      return result;
   }
   
   public Atom getA(){
   
      return A;
   }

   public Atom getB(){
   
      return B;
   }
   
}