/*
Name: Sanele Mpangalala
Date:08/09/2016
Capstone Project

*/

import java.util.*;
import java.io.*;

public class Atom{
   
   //symbol of the atom
   private String symbol;
   
   //number of atom in the chain
   private int num;
   
   private int id;
   
   //radius
   
   private double radius;
   
   //coordinates
   private double x;
   private double y;
   private double z;
   
   Atom(String symbol, int num, double radius, double x, double y, double z, int id){
      this.symbol = symbol;
      this.num = num;
      this.x = x;
      this.y = y;
      this.z = z; 
      this.radius = radius; 
      this.id = id;   

   }
   
   
   public double getRadius(){
      return radius;
   }
   
   public double getX(){
      return x;
   }
   
   public double getY(){
      return y;
   }
   
   public double getZ(){
      return z;
   }
   public String getSymbol(){
      return symbol;
   }
   
   public int getNum(){
      return num;
   }

   public int getID(){
      return id;
   }
   
   public String toString() {
      return symbol + " " + num + " " + x + " " + y + "  "+ z + " " + radius;
   }

}
