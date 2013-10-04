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
    private JCheckBox[] blood;
    private JTextField namefield;
    private JComboBox start,end,day;
    private JButton insert,clear,finish,change;
    private ButtonGroup bg;
    
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
        
        addTitleBar();
        addBloodBox();
        addInformationBox();
        addUpdateBox();
        
    }
    
    private void addTitleBar(){
        Font f = new Font("Arial",Font.BOLD,36);        
        TitleBar = new JPanel();
        TitleBar.setLayout(new MigLayout());
        JLabel title = new JLabel("Dr. "+name+" 's scheduler");
        title.setFont(f);
        title.setForeground(new Color(0xd1,0xdb,0xbd));
        
        TitleBar.add(title,"gapleft 50px");
        TitleBar.setBackground(new Color(0xfc,0xff,0xf5));
        TitleBar.setOpaque(false);
        
        this.add(TitleBar,"gapleft 20%,width 60%,gapright 20%,wrap 20px,id title");
    }
    
    private void addBloodBox(){
        Font f = new Font("Arial",Font.BOLD,36);   
        allergyBox = new JPanel(new MigLayout());
        blood = new JCheckBox[5];
        blood[0] = new JCheckBox("  A");blood[0].setFont(f);blood[0].setOpaque(false);
        blood[1] = new JCheckBox("  B");blood[1].setFont(f);blood[1].setOpaque(false);
        blood[2] = new JCheckBox("  O");blood[2].setFont(f);blood[2].setOpaque(false);
        blood[3] = new JCheckBox("  AB");blood[3].setFont(f);blood[3].setOpaque(false);
        blood[4] = new JCheckBox("  X");blood[4].setFont(f);blood[4].setOpaque(false);
        
        bg = new ButtonGroup();
        bg.add(blood[0]);
        bg.add(blood[1]);
        bg.add(blood[2]);
        bg.add(blood[3]);
        bg.add(blood[4]);
        
        
        JLabel allergyTitle = new JLabel("Patient's blood");
        allergyTitle.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
        
        allergyBox.add(allergyTitle,"wrap 30px,center");
        allergyBox.add(blood[0],"gapleft 3%,wrap 30px");
        allergyBox.add(blood[1],"gapleft 3%,wrap 30px");
        allergyBox.add(blood[2],"gapleft 3%,wrap 30px");
        allergyBox.add(blood[3],"gapleft 3%,wrap 30px");
        allergyBox.add(blood[4],"gapleft 3%,wrap 30px");
        
        allergyBox.setBackground(new Color(0x91,0xaa,0x9d));
        
        this.add(allergyBox,"gapleft 1%,id blood");
    }
    
    private void addInformationBox(){
        informationBox = new JPanel(new MigLayout("fillx","[][grow][][grow]","nogrid"));
        Font f = new Font("Arial",Font.BOLD,20);  
        JLabel patientName = new JLabel("Patient name : ");
        patientName.setFont(f);
        namefield = new JTextField(30);
        namefield.setFont(f);
        JLabel choosetimefrom = new JLabel("Choose time : ");
        choosetimefrom.setFont(f);
        JLabel choosetimeto = new JLabel(" to");
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
        
        this.add(informationBox,"pos blood.x2+10 blood.y 550 blood.y2,id information");
    }
    
    private void addUpdateBox(){
        Font f = new Font("Arial",Font.BOLD,20);  
        updateBox = new JPanel(new MigLayout("fillx,debug","[center]","[]50[]"));
        JLabel heading = new JLabel("Patient Added");
        heading.setFont(f);
        updateBox.add(heading,"wrap");
        this.add(updateBox,"pos information.x2+10 information.y 770 information.y2");
        JLabel test = new JLabel("Testing");
        test.setFont(f);
        updateBox.add(test);
    }
    
    private void addListener(){
        
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
