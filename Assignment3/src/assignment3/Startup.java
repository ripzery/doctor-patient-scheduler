/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Kunanont
 */
public class Startup extends JFrame{
    private JPanel pstart;
    private JLabel l1,l2,l3;
    private JButton b1;
    private JComboBox combo;
    private JTextField name;
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
        setTitle("Doctor-Patients Scheduler");
    
    }
    
    private void addComponents(){
    
        name = new JTextField();
        l3 = new JLabel("Enter your name: ");
        l2 = new JLabel("Or choose exists name:");
        l1 = new JLabel("Doctor-Patients Scheduler");
        b1 = new JButton("GO!");
        combo = new JComboBox();
        combo.addItem("TEST1");
        combo.addItem("TEST2");
                      
        name.setFocusable(true);
        name.setFont(new Font("Arial",Font.BOLD,40));
        combo.setFont(new Font("Arial",Font.BOLD,20));  
        
        Font f = new Font("Arial",Font.BOLD,23);  
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        b1.setFont(f);
            
        pstart.add(l1,"gapright 30%, gapleft 30%, width 50%,wrap 110px");
        pstart.add(l3,"gapright 20%, gapleft 20%, width 70%,wrap 10px");
        pstart.add(name,"gapright 20%, gapleft 20%, width 70%,wrap 80px");
        pstart.add(l2,"gapleft 20%,id l3");
        pstart.add(combo,"pos l3.x2+20 l3.y");              
        
        pstart.add(b1,"pos 650px 490px");
    }
    
    private void addListener(){
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(name.getText().equals("")){
                    JOptionPane.showMessageDialog(pstart,"Please enter/choose your name !","Error",JOptionPane.ERROR_MESSAGE);                    
                }
                
                else throwName();
            }
        });
        name.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(name.getText().equals("")&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(pstart,"Please enter/choose your name !","Error",JOptionPane.ERROR_MESSAGE);   
                }
                else if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    throwName();
                }
            }
        });        

        
    }
    
    public void throwName(){     
        main.setVisible(true);
        main.setName(name.getText());
   }
    public void setMain(Object main){
        this.main = (Main)main;        
    }

}
