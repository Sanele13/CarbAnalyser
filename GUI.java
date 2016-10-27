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
      
      filename = new JLabel("Filename: ");
      filename.setBounds(0, 0, 300, 50);
      filename.setForeground(Color.WHITE);
      filename.setFont(new Font("Arial", Font.BOLD, 14));
      add(filename);
      
      label = new JLabel("Number of atoms: ");
      label.setBounds(0, 20, 300, 50);
      label.setForeground(Color.WHITE);
      label.setFont(new Font("Arial", Font.BOLD, 14));
      add(label);

      
      JLabel label1 = new JLabel("Number of bonds: ");
      label1.setBounds(0, 40, 300, 50);
      label1.setForeground(Color.WHITE);
      label1.setFont(new Font("Arial", Font.BOLD, 14));
      add(label1);

    ///  textfield = new JTextField(6);
    //  add(textfield);
            
      JLabel label2 = new JLabel("Number of angles: ");
      label2.setBounds(0, 60, 300, 50);
      label2.setForeground(Color.WHITE);
      label2.setFont(new Font("Arial", Font.BOLD, 14));
      add(label2);
      
    ///  textfield = new JTextField(6);
     // add(textfield);
            
      JLabel label3 = new JLabel("Number of dihedral angles: ");
      label3.setBounds(0, 80, 300, 50);
      label3.setForeground(Color.WHITE);
      label3.setFont(new Font("Arial", Font.BOLD, 14));
      add(label3);
      
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
      

      button = new JButton("Choose PDB file");
      button.setBounds(0, 140, 150, 30);
      add(button);
      
      JButton runButton = new JButton("Analyse!");
      runButton.setBounds(0, 180, 150, 30);
      add(runButton);

      //listeners
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
      
      runButton.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            carb = new Carbanalyser();
            
            carb.readPDB(PDBFile);
      
            //System.out.println(carb.atomsList.get(0).getX());
            carb.bondsCounter();
            carb.anglesCounter();
            carb.dihedralAngleCounter();
            
            label.setText("Number of atoms: "+ carb.mol.getNumOfAtoms());
            
            label1.setText("Number of bonds: "+ carb.mol.getNumOfBonds());
            label2.setText("Number of angles: "+ carb.mol.getNumOfAngles());
            label3.setText("Number of dihedral angles: "+ carb.mol.getNumOfDihedralAngles());
            
            String formula = "<html>C<sub>"+carb.mol.getNumberOfCarbonAtoms()+"</sub>H<sub>"+carb.mol.getNumberOfHydrogenAtoms()+"</sub>O<sub>"+carb.mol.getNumberOfOxygenAtoms()+"</sub></html>"; 

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
            
            String html = "<html>"+carb.getAngleDisplay()+"</html>";
            //System.out.println(s);
           // p.add(new JLabel(html));
            JScrollPane scrollpane = new JScrollPane();
            JLabel data = new JLabel(html);
            data.setForeground(Color.WHITE);
            scrollpane.setViewportView(data);
            //scroll.getContentPane().add(scrollpane, BorderLayout.LINE_START); 
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
            //System.out.println(s);
           // p.add(new JLabel(html));
            JScrollPane scrollpane = new JScrollPane();
            JLabel data = new JLabel(html);
            data.setForeground(Color.WHITE);
            scrollpane.setViewportView(data);
            //scroll.getContentPane().add(scrollpane, BorderLayout.LINE_START); 
            scrollpane.getViewport().setBackground(color);
            DihedralAnglesProperties.add(scrollpane);            
            DihedralAnglesProperties.setVisible(true);    
         }
      });
      
      exportAngles.addActionListener(new  ActionListener () {
         public void actionPerformed(ActionEvent e){
            //Export to a file
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            //GUI gui = new GUI();
         
            if(state == JFileChooser.APPROVE_OPTION) {
            //JOptionPane.showMessageDialog(null, file.getPath());
               filename.setText("Filename: "+file.getName());
               try{
             
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
            //Export to a file
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            //GUI gui = new GUI();
         
            if(state == JFileChooser.APPROVE_OPTION) {
            //JOptionPane.showMessageDialog(null, file.getPath());
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
            //Export to a file
            JFileChooser chooser = new JFileChooser();
            int state = chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            //GUI gui = new GUI();
         
            if(state == JFileChooser.APPROVE_OPTION) {
            //JOptionPane.showMessageDialog(null, file.getPath());
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
   
   //public class event implements
   public static void main(String [] args){
      GUI gui = new GUI();
      Color color = Color.decode("0x121E31");
      gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gui.setSize(400,400);
      gui.getContentPane().setBackground(color);
      gui.setVisible(true);
      gui.setTitle("CarbAnalyser");
 

   }
}