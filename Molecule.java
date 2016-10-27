/*
Name: Sanele Mpangalala
Date:08/09/2016
Capstone Project

*/

import java.util.*;

public class Molecule{

   private int numOfAtoms;
   private int numOfBonds;
   private int numOfAngles;
   private int numOfDihedralAngles;
   
   private int numberOfCarbonAtoms;
   private int numberOfOxygenAtoms;
   private int numberOfHydrogenAtoms;

   
   //Setters
   public void setNumOfAtoms(int number){
   
     numOfAtoms=number;
   }
   
   public void setNumOfBonds(int number){
   
      numOfBonds = number;
   }
   
   public void setNumOfAngles(int number){
   
     numOfAngles = number;
   }
   
   public void setNumOfDihedralAngles(int number){
   
      numOfDihedralAngles = number;
   }   
   
   public void setNumberOfCarbonAtoms(int numC){
      numberOfCarbonAtoms=numC;
   }
   
   public void setNumberOfOxygenAtoms(int numC){
      numberOfOxygenAtoms=numC;
   }
   
   public void setNumberOfHydrogenAtoms(int numC){
      numberOfHydrogenAtoms=numC;
   }
   
   //Getters
   public int getNumOfAtoms(){
   
      return numOfAtoms;
   }
   
   public int getNumOfBonds(){
   
      return numOfBonds;
   }
   
   public int getNumOfAngles(){
   
      return numOfAngles;
   }
   
   public int getNumOfDihedralAngles(){
   
      return numOfDihedralAngles;
   }
   
   public int getNumberOfHydrogenAtoms(){
      return numberOfHydrogenAtoms;
   }
   
   public int getNumberOfCarbonAtoms(){
      return numberOfCarbonAtoms;
   }
   
   public int getNumberOfOxygenAtoms(){
      return numberOfOxygenAtoms;
   }

}