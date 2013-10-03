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
    private String[] time,daylist;
    private JPanel TitleBar,allergyBox,informationBox,updateBox;
    private JLabel title,allergyTitle,patientName,choosetimefrom,choosetimeto;
    private JCheckBox[] allergy;
    private JTextField namefield;
    private JComboBox start,end,day;
    private JButton insert,clear,finish,change;
    
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
        
        this.add(TitleBar,"gapleft 20%,width 60%,gapright 20%,wrap 20px,id title");
        
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
        
        this.add(allergyBox,"gapleft 1%,id allergy");
        
        /*
         * Ending of allergyBox
         */
        
        informationBox = new JPanel(new MigLayout("fillx","[][grow][][grow]","nogrid"));
        f = new Font("Arial",Font.BOLD,20);  
        patientName = new JLabel("Patient name : ");
        patientName.setFont(f);
        namefield = new JTextField(30);
        namefield.setFont(f);
        choosetimefrom = new JLabel("Choose time : ");
        choosetimefrom.setFont(f);
        choosetimeto = new JLabel(" to");
        choosetimeto.setFont(f);
        time = new String[]{"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30"
                ,"12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00"};
        start = new JComboBox(time);
        end = new JComboBox(time);
        daylist = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        day = new JComboBox(daylist);
        insert = new JButton("Insert");
        clear = new JButton("Clear");
        finish = new JButton("Finish");
        change = new JButton("Change doctor");
        
        informationBox.add(patientName,"grow");
        informationBox.add(namefield,"gapx unrel unrel,wrap 30px");
        informationBox.add(choosetimefrom,"grow");
        informationBox.add(start,"grow");
        informationBox.add(choosetimeto,"grow");
        informationBox.add(end,",grow,wrap 30px");
        informationBox.add(insert,"grow");
        informationBox.add(clear,"grow");
        informationBox.add(finish,"grow");
        informationBox.add(change,"grow");
        
        this.add(informationBox,"pos allergy.x2+10 allergy.y 550 allergy.y2,id information");
        
        /*
         * Ending of informationBox
         */
        
        updateBox = new JPanel();
        
        this.add(updateBox,"pos information.x2+10 information.y 770 information.y2");
        
    }
    
    private void addListener(){
        
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
