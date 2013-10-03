/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Kunanont
 */
public class Startup extends JFrame{
    private JPanel pstart;
    private JLabel lstart;
    private JButton bstart;
    private JComboBox combo;
    private JTextField text1,text2;
    private Main main;
    public Startup(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(320,70);
        setSize(800,600);  
        
        pstart = (JPanel)getContentPane();
        pstart.setBackground(Color.white);
        addComponents();
        addListener();
        
        setTitle("Doctor-patients Scheduler");
    
    }
    
    private void addComponents(){
    
        text1 = new JTextField("Create your profile");
        text2 = new JTextField("Or choose exists name: ");
        Font f = new Font("Arial",Font.BOLD,40);
        combo = new JComboBox();
    }
    
    private void addListener(){
        
        
    }
    
    public void setMain(Object menu){
        this.main = main;
    }
}
