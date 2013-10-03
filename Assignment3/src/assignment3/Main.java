/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Kunanont
 */
public class Main extends JFrame {
    
    private static Startup startup;
    private int width=800,height=600;
    private String name;
    private JPanel TitleBar,allergyBox,informationBox,updateBox;
    private JLabel title,allergyTitle;
    private JCheckBox[] allergy;
    
    public Main(String name){        
        this.setDoctorName(name);
        this.setLayout(new MigLayout());
        
        addComponent();
        addListener();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,70,width,height);
        
        getContentPane().setBackground(new Color(0x3e,0x60,0x6f));
        
        setVisible(true);
        
        
    }
    
    public static void main(String args[]){       
          startup = new Startup();
    }
    private void addComponent(){
        Font f = new Font("Arial",Font.BOLD,36);        
        TitleBar = new JPanel();
        TitleBar.setLayout(new MigLayout());
        title = new JLabel("Dr. "+name+" 's scheduler");
        title.setFont(f);
        title.setForeground(new Color(0xd1,0xdb,0xbd));
        
        TitleBar.add(title,"gapleft 50px");
        TitleBar.setBackground(new Color(0xfc,0xff,0xf5));
        TitleBar.setOpaque(false);
        
        this.add(TitleBar,"gapleft 20%,width 60%,gapright 20%,wrap 20px");
        
        /*
         * Ending of TitleBar
         */
        
        allergyBox = new JPanel(new MigLayout());
        allergy = new JCheckBox[5];
        allergy[0] = new JCheckBox("  A");allergy[0].setFont(f);allergy[0].setOpaque(false);
        allergy[1] = new JCheckBox("  B");allergy[1].setFont(f);allergy[1].setOpaque(false);
        allergy[2] = new JCheckBox("  C");allergy[2].setFont(f);allergy[2].setOpaque(false);
        allergy[3] = new JCheckBox("  D");allergy[3].setFont(f);allergy[3].setOpaque(false);
        allergy[4] = new JCheckBox("  E");allergy[4].setFont(f);allergy[4].setOpaque(false);
        
        allergyTitle = new JLabel("Patient's allergy");
        allergyTitle.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
        
        allergyBox.add(allergyTitle,"wrap 30px,center");
        allergyBox.add(allergy[0],"gapleft 3%,wrap 30px");
        allergyBox.add(allergy[1],"gapleft 3%,wrap 30px");
        allergyBox.add(allergy[2],"gapleft 3%,wrap 30px");
        allergyBox.add(allergy[3],"gapleft 3%,wrap 30px");
        allergyBox.add(allergy[4],"gapleft 3%,wrap 30px");
        
        allergyBox.setBackground(new Color(0x91,0xaa,0x9d));
        
        this.add(allergyBox,"gapleft 1%");
        
    }
    
    private void addListener(){
        
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
