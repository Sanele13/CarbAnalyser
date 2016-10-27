/*
Name: Sanele Mpangalala
Date:08/09/2016
Capstone Project

*/


import java.util.*;
import java.io.*;
import java.lang.Math;

public class Carbanalyser{

   LinkedList<Atom> atomsList = new LinkedList<Atom>();
   LinkedList<Bond> bondsList = new LinkedList<Bond>();   
   Molecule mol = new Molecule();
   Bond bond;
  
   private String displayAngles=""; 
   private String displayDihedralAngles="";
   private String anglesToSave=""; 
   private String torsionalAnglesToSave=""; 
   public void readPDB(String filename){
      //read in the pdb file, add atoms, and also count the number of atoms
      //variables to count number C,H,and O atoms
      int c=0;
      int h=0;
      int o = 0;
      //read PDB
      try{
      
         double radius = 0;
         Scanner file = new Scanner(new FileReader(filename));
         String line = file.nextLine();
         while(file.hasNext()&&(!(file.next()).equals("END"))){
            
            StringTokenizer st = new StringTokenizer(file.nextLine());
            LinkedList<String> temp = new LinkedList<String>(); 

            while(st.hasMoreElements()){
              
                temp.add(st.nextToken());
              
            }
             
            //setting unique IDs
            // token format: 1 C1 AMAN 1 1.803 -0.843 0.163 1.00 0.00 CARB C
            if(temp.get(10).equals("C")){
               c++;
               atomsList.add(new Atom((String)temp.get(10),Integer.parseInt((String)temp.get(0)),radius,Double.parseDouble((String)temp.get(4)),Double.parseDouble((String)temp.get(5)),Double.parseDouble((String)temp.get(6)), c));
            }

            if(temp.get(10).equals("H")){
                h++;
                atomsList.add(new Atom((String)temp.get(10),Integer.parseInt((String)temp.get(0)),radius,Double.parseDouble((String)temp.get(4)),Double.parseDouble((String)temp.get(5)),Double.parseDouble((String)temp.get(6)),h));
            }
            
            if(temp.get(10).equals("O")){
                o++;
                atomsList.add(new Atom((String)temp.get(10),Integer.parseInt((String)temp.get(0)),radius,Double.parseDouble((String)temp.get(4)),Double.parseDouble((String)temp.get(5)),Double.parseDouble((String)temp.get(6)),o));
            }                        
            
         }
      
      }catch(Exception e){}
      mol.setNumberOfCarbonAtoms(c);
      mol.setNumberOfHydrogenAtoms(h);
      mol.setNumberOfOxygenAtoms(o);
      mol.setNumOfAtoms(atomsList.size());
   }
   
   public void bondsCounter(){
      int numberOfBonds=0;
      int numberOfAngles=0;
      //Bond bond;
      for (int i=0; i< atomsList.size();i++){
         for (int j = i+1; j<atomsList.size();j++){
            //check if there is a bond between atoms
            Atom A = atomsList.get(i);
            Atom B = atomsList.get(j);
            bond = new Bond(A,B);
          
            if(bond.isBonded(A,B,distance(A,B))){
               numberOfBonds++;
               
               //add to list of bonds
               bondsList.add(bond);
            }
         }
      }
         mol.setNumOfBonds(numberOfBonds);
   }
   
   public void anglesCounter(){
      //number of angles
      int numberOfAngles = 0;
      for (int i=0; i< atomsList.size();i++){
         for (int j =0; j<atomsList.size();j++){
            for(int k = i+1; k<atomsList.size();k++){
               //check if there is a bond between atoms
               Atom A = atomsList.get(i);
               Atom B = atomsList.get(j);
               Atom C = atomsList.get(k);
               bond = new Bond(A,B);
               AngleCalculator ac = new AngleCalculator();
               
               double [] vecA = new double [3];
               double [] vecB = new double [3];
                           
               if((A.getNum()!= B.getNum())&&(A.getNum()!= C.getNum())&&(B.getNum()!= C.getNum())&&(bond.isBonded(A,B,distance(A,B)))&&(bond.isBonded(B,C,distance(B,C)))){
                  numberOfAngles++;
                  
                  
                  //create vectors that represent bonds 
                  vecA[0] = B.getX() - A.getX();
                  vecA[1] = B.getY() - A.getY();
                  vecA[2] = B.getZ() - A.getZ();
                  
                  vecB[0] = C.getX() - B.getX();
                  vecB[1] = C.getY() - B.getY();
                  vecB[2] = C.getZ() - B.getZ();                   
                  double ang = 180 - ac.angle(vecA, vecB);
                  
                  displayAngles = displayAngles + A.getSymbol()+A.getID()+ "--" + B.getSymbol()+B.getID() + "--" +  C.getSymbol()+C.getID() +"<br>" +  ang +"<br><br>";
                  anglesToSave = anglesToSave + A.getSymbol()+A.getID()+ "--" + B.getSymbol()+B.getID() + "--" +  C.getSymbol()+C.getID() +"\n" +  ang+"\n"; 
               }               
            }
           

         
         }
      }   
      mol.setNumOfAngles(numberOfAngles);
   }
   
   public void dihedralAngleCounter(){

      //number of dihedral angle
      int numOfDihedralAngles=0;
      
      for (int i=0; i< atomsList.size();i++){
         for (int j = 0; j<atomsList.size();j++){
            for(int k = 0; k<atomsList.size();k++){
               for(int l = i+1; l<atomsList.size();l++){
                  //check if there is a bond between atoms
                  Atom A = atomsList.get(i);
                  Atom B = atomsList.get(j);
                  Atom C = atomsList.get(k);
                  Atom D = atomsList.get(l);
                  bond = new Bond(A,B);

                  AngleCalculator ac = new AngleCalculator();
                  
                  double [] vec1 = new double [3];
                  double [] vec2 = new double [3]; 
                  double [] vec3 = new double [3];
                  double [] vec4 = new double [3];                 
                              
                  if((A.getNum()!= B.getNum())&&(A.getNum()!= C.getNum())&&(B.getNum()!= C.getNum())&&(A.getNum()!= D.getNum())&&(B.getNum()!= D.getNum())&&(C.getNum()!= D.getNum())&&(bond.isBonded(A,B,distance(A,B)))&&(bond.isBonded(B,C,distance(B,C)))&&(bond.isBonded(C,D,distance(C,D)))){
                     numOfDihedralAngles++;

                     //creating vectors to calculate the torsional angle
                     vec1[0] = D.getX() - C.getX();
                     vec1[1] = D.getY() - C.getY();
                     vec1[2] = D.getZ() - C.getZ();
                     
                     vec2[0] = B.getX() - C.getX();
                     vec2[1] = B.getY() - C.getY();
                     vec2[2] = B.getZ() - C.getZ();

                     vec3[0] = C.getX() - B.getX();
                     vec3[1] = C.getY() - B.getY();
                     vec3[2] = C.getZ() - B.getZ();
                     
                     vec4[0] = A.getX() - B.getX();
                     vec4[1] = A.getY() - B.getY();
                     vec4[2] = A.getZ() - B.getZ();
                     
                     double dh = ac.calcDihedral(vec1,vec2,vec3,vec4);
                     
                     displayDihedralAngles = displayDihedralAngles + A.getSymbol()+A.getID()+ "--" + B.getSymbol()+B.getID() + "--" +  C.getSymbol()+C.getID() + "--" +  D.getSymbol()+D.getID() +"<br>" + dh+"<br><br>";
                     torsionalAnglesToSave = torsionalAnglesToSave  + A.getSymbol()+A.getID()+ "--" + B.getSymbol()+B.getID() + "--" +  C.getSymbol()+C.getID() + "--" +  D.getSymbol()+D.getID() +"\n" + dh+"\n";                                         
                  }                   
               }
              
            }
         
         }
      }    
      mol.setNumOfDihedralAngles(numOfDihedralAngles);
   }
   
   //distance between two atoms
   public double distance(Atom a, Atom b){
      double x1 = a.getX();
      double y1 = a.getY();
      double z1 = a.getZ();
            
      double x2 = b.getX();
      double y2 = b.getY();
      double z2 = b.getZ();
            
      return Math.sqrt(Math.pow((x2-x1),2)+ Math.pow((y2-y1),2) + Math.pow((z2-z1),2));
   }
  
    
   public String getAngleDisplay(){
      return displayAngles;
   }
   public String getDihedralAngleDisplay(){
      return displayDihedralAngles;
   }
   
   public String getTorsionalAnglesToSave(){
      return torsionalAnglesToSave;
   }
   public String getAnglesToSave(){
      return anglesToSave;
   }
}
