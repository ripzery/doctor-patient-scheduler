/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import net.miginfocom.swing.MigLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Kunanont
 */
public class Startup extends JFrame{
    private JPanel pstart;
    private JLabel l1,l2;
    private JButton b1;
    private JComboBox combo;
    private JTextField text1;
    private Main main;
    public Startup(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setLocation(320,70);
        setSize(800,600);  

        pstart = (JPanel)getContentPane();
        pstart.setLayout(new MigLayout("insets 50 0 0 0"));
        pstart.setBackground(new Color(0xff,0xf0,0xa5));
        
        addComponents();
        addListener();
        
        setVisible(true);
        setTitle("Doctor-patients Scheduler");
    
    }
    
    private void addComponents(){
    
        text1 = new JTextField("Create your profile");
        l2 = new JLabel("Or choose exists name:");
        l1 = new JLabel("Doctor-patients Scheduler");
        b1 = new JButton("GO!");
        combo = new JComboBox();
        combo.addItem("TEXXXXT");
        Font f = new Font("Arial",Font.BOLD,23);
        
        
        text1.setFont(new Font("Arial",Font.BOLD,40));
        l1.setFont(f);
        l2.setFont(f);
        b1.setFont(f);
        combo.setFont(new Font("Arial",Font.BOLD,20));
        
        pstart.add(l1,"gapright 30%, gapleft 30%, width 50%,wrap 150px");
        pstart.add(text1,"gapright 20%, gapleft 20%, width 70%,wrap 120px");
        pstart.add(l2,"gapleft 10%");
        pstart.add(combo,"pos 350px 405px");              
        
        pstart.add(b1,"pos 650px 490px");
    }
    
    private void addListener(){
        
        
    }
    
    public void setMain(Object menu){
        this.main = main;
    }

}
