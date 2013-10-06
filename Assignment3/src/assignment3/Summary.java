/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author OATz
 */
public class Summary extends JFrame{
    
    private JLabel heading;
    private JComboBox day,doctor;
    private JPanel Schedule,Desc,spd;
    private JButton gostartup;
    private Startup start;
    private String filename;
    String[] weekdays= new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    
    public Summary(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,70,800,600);
        getContentPane().setBackground(new Color(0x3e,0x60,0x6f));
        setVisible(true);
        setLayout(new MigLayout("debug,fillx"));
        addComponent();
        addListener();
        
    }
    public void addComponent(){
        heading = new JLabel ("Summary of Schedule");
        Font f = new Font("Arial",Font.BOLD,36);
        heading.setFont(f);
        heading.setForeground(new Color(0xd1,0xdb,0xbd));
        
        this.add(heading,"center,wrap 20px,id title");
        
        Font d = new Font("Arial",Font.BOLD,30);
        day = new JComboBox(weekdays);
        day.setFont(d);
        add(day,"wrap 20px,center");
        
        spd = new JPanel(new MigLayout());
        
        Schedule = new JPanel(new MigLayout());
        Schedule.setBackground(Color.yellow);
        spd.add(Schedule,"width 300px,height 300px");
        
        Desc = new JPanel(new MigLayout());
        Desc.setBackground(Color.RED);
        spd.add(Desc,"gapx 30px,width 300px, height 300px");
        spd.setOpaque(false);
        add(spd,"center,wrap 20px");
        
        gostartup = new JButton("Back to Home Screen");
        Font g = new Font("Arial",Font.BOLD,20);
        gostartup.setFont(g);
        add(gostartup,"center");
    }
    
    public void addListener(){
        gostartup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(start==null){
                    start = new Startup();
                }else{
                    start.setVisible(true);
                }
                Summary.this.dispose();
            }
    });
    }
    
    public void setStartup(Startup a){
        start = a;
    }
    
    public void setDoctorFile(String name){
        filename = name;
    }
}
