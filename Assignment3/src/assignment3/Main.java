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
    private JPanel main;
    private JLabel lhead;
    
    public Main(String name){        
        this.setDoctorName(name);
        
        addComponent();
        addListener();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,70,width,height);
        getContentPane().setBackground(new Color(0xff,0xf0,0xa5));
        setVisible(true);
    }
    
    public static void main(String args[]){       
          startup = new Startup();
    }
    private void addComponent(){
        Font f = new Font("Arial",Font.BOLD,36);        
        main = new JPanel();
        main.setLayout(new MigLayout());
        lhead = new JLabel("Welcome to Dr. "+name+" 's scheduler");
        lhead.setFont(f);
        lhead.setForeground(new Color(0x8e,0x28,0x00));
        
        main.add(lhead,"gapleft 15%");
        main.setOpaque(false);
        
        add(main);
    }
    
    private void addListener(){
        
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
