/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kunanont
 */
public class Main extends JFrame {
    
    private Startup frame_name;
    private int width=800,height=600;
    
    public Main(){        
        frame_name = new Startup();
        frame_name.setMain(this);
        //this.setContentPane(new JLabel(new ImageIcon("propractice/BackGround Menu.jpg")));
        
        //addComponent();
        //addListener();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0xff,0xf0,0xa5));
        setTitle("Cardculator");
    }
    public static void main(String args[]){
        
          try{
              for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                  if("Nimbus".equals(info.getName())){
                      UIManager.setLookAndFeel(info.getClassName());
                      break;
                  }
              }
          }
          catch(Exception e){}
          new Main();
    }
}
