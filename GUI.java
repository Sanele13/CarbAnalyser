import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GUI extends JFrame{
   private JLabel label;
   private JButton button;
   private JLabel filename;
   private JTextField textfield;
   private JMenuBar menuBar;
   
   private JMenu file;
   private JMenu properties;
   private JMenu about;
   private JMenu help;
   
   private JMenuItem exportAngles;
   private JMenuItem exportDihedralAngles;
   private JMenuItem exportScreenData;
   private JMenuItem viewAngles;
   private JMenuItem viewDihedralAngles;

   JFileChooser chooser = new JFileChooser();
   
   String PDBFile;
   
   
   Carbanalyser carb;
   public GUI(){
      setLayout(null);
      
      menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      //File
      file = new JMenu("File");
      menuBar.add(file);
      //File submenu
      exportAngles = new JMenuItem("Export Angles");
      file.add(exportAngles);
      exportDihedralAngles = new JMenuItem("Export Dihedral Angles");
      file.add(exportDihedralAngles);
      exportScreenData = new JMenuItem("Export Data On Screen");
      file.add(exportScreenData);      
      
      //Properties
      properties = new JMenu("Properties");
      menuBar.add(properties);
      
      //Properties submenu
      viewAngles = new JMenuItem("View Angles");
      properties.add(viewAngles);
      viewDihedralAngles = new JMenuItem("View Dihedral Angles");
      properties.add(viewDihedralAngles);
      
      about = new JMenu("About");
      JMenuItem ab =  new JMenuItem("<html>This software was designed to analyse the structure of carbohydrates.<br>This information could help in determining the minimum energy of <br>a molecule.</html>");
      about.add(ab);
      menuBar.add(about);
      
      help = new JMenu("Help");
      menuBar.add(help);
     
      Toolkit toolkit = getToolkit();
      Dimension size = toolkit.getScreenSize();
      setLocation(800,300);
      
      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon.jpg")));
      //labels to display on screen
      filename = new JLabel("Filename: ");
      filename.setBounds(0, 0, 300, 50);
      filename.setForeground(Color.WHITE);
      filename.setFont(new Font("Arial", Font.BOLD, 14));
      add(filename);
      
      numOfAtomsLabel = new JLabel("Number of atoms: ");
      numOfAtomsLabel.setBounds(0, 20, 300, 50);
      numOfAtomsLabel.setForeground(Color.WHITE);
      numOfAtomsLabel.setFont(new Font("Arial", Font.BOLD, 14));
      add(numOfAtomsLabel);

      
      JLabel numOfBondsLabel = new JLabel("Number of bonds: ");
      numOfBondsLabel.setBounds(0, 40, 300, 50);
      numOfBondsLabel.setForeground(Color.WHITE);
      numOfBondsLabel.setFont(new Font("Arial", Font.BOLD, 14));
      add(numOfBondsLabel);
            
      JLabel numOfAnglesLabel = new JLabel("Number of angles: ");
      numOfAnglesLabel.setBounds(0, 60, 300, 50);
      numOfAnglesLabel.setForeground(Color.WHITE);
      numOfAnglesLabel.setFont(new Font("Arial", Font.BOLD, 14));
      add(numOfAnglesLabel);
            
      JLabel numOfDihedralAnglesLabel = new JLabel("Number of dihedral angles: ");
      numOfDihedralAnglesLabel.setBounds(0, 80, 300, 50);
      numOfDihedralAnglesLabel.setForeground(Color.WHITE);
      numOfDihedralAnglesLabel.setFont(new Font("Arial", Font.BOLD, 14));
      add(numOfDihedralAnglesLabel);
      
      JLabel chemFormula = new JLabel();
      chemFormula.setBounds(130, 100, 300, 50);
      chemFormula.setForeground(Color.WHITE);
      chemFormula.setFont(new Font("Arial", Font.BOLD, 14));
      add(chemFormula);
      
      JLabel chemFormulaField = new JLabel("Chemical Formula: ");
      chemFormulaField.setBounds(0, 100, 300, 50);
      chemFormulaField.setForeground(Color.WHITE);
      chemFormulaField.setFont(new Font("Arial", Font.BOLD, 14));
      add(chemFormulaField);            
      
      //'Choose PDB file' button
      button = new JButton("Choose PDB file");
      button.setBounds(0, 140, 150, 30);
      add(button);
      //'Analyse' button
      JButton runButton = new JButton("Analyse!");
      runButton.setBounds(0, 180, 150, 30);
      add(runButton);

      //listener for the 'Choose PDB file' button
      button.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            //GUI gui = new GUI();
         
            if(file != null && state == JFileChooser.APPROVE_OPTION) {
            //JOptionPane.showMessageDialog(null, file.getPath());
               filename.setText("Filename: "+file.getName());
               PDBFile = file.getPath();
            }
            else if(state == JFileChooser.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "Canceled");
            }
         }
      });
      
      //listener for the 'Analyse' button
      runButton.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            carb = new Carbanalyser();
            
            carb.readPDB(PDBFile);
            carb.bondsCounter(); 
            carb.anglesCounter();
            carb.dihedralAngleCounter();
            //display data
            numOfAtomsLabel.setText("Number of atoms: "+ carb.mol.getNumOfAtoms());
            numOfBonsLabel.setText("Number of bonds: "+ carb.mol.getNumOfBonds());
            numOfAnglesLabel.setText("Number of angles: "+ carb.mol.getNumOfAngles());
            numOfDihedralAnglesLabel.setText("Number of dihedral angles: "+ carb.mol.getNumOfDihedralAngles());
            
                  
            String formula = "<html>C<sub>"+carb.mol.getNumberOfCarbonAtoms()+"</sub>H<sub>"+carb.mol.getNumberOfHydrogenAtoms()+"</sub>O<sub>"+carb.mol.getNumberOfOxygenAtoms()+"</sub></html>"; 
            //display chemical formula
            chemFormula.setText(formula);
            
         }
      });

      viewAngles.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //create a gui to display angles data
            JFrame anglesProperties = new JFrame();
            anglesProperties.setTitle("Angle Properties"); 
            Color color = Color.decode("0x121E31");
            anglesProperties.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            anglesProperties.setSize(600,600);
            anglesProperties.getContentPane().setBackground(color);
            
            String html = "<html>"+carb.getAngleDisplay()+"</html>"; //This is a good way of display text on a gui 
            JScrollPane scrollpane = new JScrollPane();//To give users the ability to scroll up and down
            JLabel data = new JLabel(html);
            data.setForeground(Color.WHITE);
            scrollpane.setViewportView(data);
            scrollpane.getViewport().setBackground(color);
            anglesProperties.add(scrollpane);            
            anglesProperties.setVisible(true);        
         }
      });
      
      viewDihedralAngles.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //create a gui to display torsional angles data
            JFrame DihedralAnglesProperties = new JFrame();
            DihedralAnglesProperties.setTitle("Angle Properties"); 
            Color color = Color.decode("0x121E31");
            
            DihedralAnglesProperties.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            DihedralAnglesProperties.setSize(600,600);
            DihedralAnglesProperties.getContentPane().setBackground(color);
            
            String html = "<html>"+carb.getDihedralAngleDisplay()+"</html>";
            JScrollPane scrollpane = new JScrollPane();
            JLabel data = new JLabel(html);
            data.setForeground(Color.WHITE);
            scrollpane.setViewportView(data);
            scrollpane.getViewport().setBackground(color);
            DihedralAnglesProperties.add(scrollpane);            
            DihedralAnglesProperties.setVisible(true);    
         }
      });
      
      exportAngles.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //Export to a file (i.e. save the list angles)
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
         
            if(state == JFileChooser.APPROVE_OPTION) {
               filename.setText("Filename: "+file.getName());
               try{
                  //write to file
                  BufferedWriter output = new BufferedWriter(new FileWriter(file));
                  output.write(carb.getAnglesToSave());
                  output.close();
               
               }catch(Exception ex){}
            }
            else if(state == JFileChooser.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "Canceled");
            }
          }
      });
      
      exportDihedralAngles.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //Export to a file (i.e. save the list dihedral angles)
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
         
            if(state == JFileChooser.APPROVE_OPTION) {
               filename.setText("Filename: "+file.getName());
               try{
             
                  BufferedWriter output = new BufferedWriter(new FileWriter(file));
                  output.write(carb.getTorsionalAnglesToSave());
                  output.close();
               
               }catch(Exception ex){}
            }
            else if(state == JFileChooser.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "Canceled");
            }
          }
      });

      exportScreenData.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //Export to a file (i.e. save the stuff on the screen)
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
         
            if(state == JFileChooser.APPROVE_OPTION) {
               filename.setText("Filename: "+file.getName());
               try{
             
                  BufferedWriter output = new BufferedWriter(new FileWriter(file));
                  output.write("Number Of Atoms:\n"+carb.mol.getNumOfAtoms()+"\n"+"Number Of Bonds:\n"+carb.mol.getNumOfBonds()+"\n"+"Number Of Angles:\n"+carb.mol.getNumOfAngles()+"\n"+"Number Of Dihedtal Angles:\n"+carb.mol.getNumOfDihedralAngles()+"\n");
                  output.close();
               
               }catch(Exception ex){}
            }
            else if(state == JFileChooser.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "Canceled");
            }
          }
      });       
      
   }
   
   public static void main(String [] args){
      //Set GUI properties and display GUI
      GUI gui = new GUI();
      Color color = Color.decode("0x121E31");
      gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gui.setSize(400,400);
      gui.getContentPane().setBackground(color);
      gui.setVisible(true);
      gui.setTitle("CarbAnalyser");
 

   }
}
