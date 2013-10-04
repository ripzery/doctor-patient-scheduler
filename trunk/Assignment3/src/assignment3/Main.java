/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private String[] time,bloodlist;
    private JPanel TitleBar,allergyBox,informationBox,updateBox;
    private JCheckBox[] day;
    private JTextField namefield;
    private JComboBox start,end,blood;
    private JButton insert,clear,finish,change;
    private JList patientList;
    private ButtonGroup bg;
    private DefaultListModel model;
    boolean checker = false;
    int i = 0;
        
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
        Font f = new Font("Arial",Font.BOLD,24);   
        allergyBox = new JPanel(new MigLayout());
        day = new JCheckBox[5];
        day[0] = new JCheckBox("  MONDAY");day[0].setFont(f);day[0].setOpaque(false);
        day[1] = new JCheckBox("  TUESDAY");day[1].setFont(f);day[1].setOpaque(false);
        day[2] = new JCheckBox("  WEDNESDAY");day[2].setFont(f);day[2].setOpaque(false);
        day[3] = new JCheckBox("  THURSDAY");day[3].setFont(f);day[3].setOpaque(false);
        day[4] = new JCheckBox("  FRIDAY");day[4].setFont(f);day[4].setOpaque(false);
        
        bg = new ButtonGroup();
        bg.add(day[0]);
        bg.add(day[1]);
        bg.add(day[2]);
        bg.add(day[3]);
        bg.add(day[4]);
        
        
        JLabel allergyTitle = new JLabel("Patient's day");
        allergyTitle.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
        
        allergyBox.add(allergyTitle,"wrap 30px,center");
        allergyBox.add(day[0],"gapleft 3%,wrap 30px");
        allergyBox.add(day[1],"gapleft 3%,wrap 30px");
        allergyBox.add(day[2],"gapleft 3%,wrap 30px");
        allergyBox.add(day[3],"gapleft 3%,wrap 30px");
        allergyBox.add(day[4],"gapleft 3%,wrap 30px");
        
        allergyBox.setBackground(new Color(0x91,0xaa,0x9d));
        
        this.add(allergyBox,"gapleft 1%,id day");
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
        bloodlist = new String[]{"A","B","AB","O"};
        blood = new JComboBox(bloodlist);
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
        
        this.add(informationBox,"pos day.x2+10 day.y 550 day.y2,id information");
    }
    
    private void addUpdateBox(){
        Font f = new Font("Arial",Font.BOLD,20);  
        updateBox = new JPanel(new MigLayout("fillx,debug"));
        JLabel heading = new JLabel("Patient Added");
        heading.setFont(f);
        updateBox.add(heading,"center,wrap 10px");
        model = new DefaultListModel();
        patientList = new JList(model);
        patientList.setFont(f);
        updateBox.add(patientList,"center,wrap");
        this.add(updateBox,"pos information.x2+10 information.y 770 information.y2");
    }
    
    private void addListener(){
        namefield.addKeyListener(new KeyAdapter(){
            String starttime,endtime;
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER&&!namefield.getText().equals("")){
                    starttime = (String)start.getSelectedItem();
                    endtime = (String)end.getSelectedItem();
                    name = starttime+" - "+endtime+" "+namefield.getText();
                    namefield.setText("");
                    model.addElement(name);
                }
                patientList.validate();
            }
        });
        
        insert.addActionListener(new ActionListener(){
            String starttime,endtime;
            public void actionPerformed(ActionEvent e){
                
                for(i=0 ;i<5;i++){
                    if(day[i].isSelected()){
                        checker = true;
                        break;
                    }else checker = false;                    
                }
                
                System.out.print(i);
                System.out.print(checker);
                if(!namefield.getText().equals("") && checker==true){
                    starttime = (String)start.getSelectedItem();
                    endtime = (String)end.getSelectedItem();
                    name = starttime+" - "+endtime+" "+namefield.getText();
                    namefield.setText("");
                    model.addElement(name);
                }else if(namefield.getText().equals("")){
                    JOptionPane.showMessageDialog(informationBox,"Please enter patient name's !","Error",JOptionPane.ERROR_MESSAGE); 
                }else if(checker == false)
                    JOptionPane.showMessageDialog(informationBox,"Please enter schedule day !","Error",JOptionPane.ERROR_MESSAGE);
                patientList.validate();
            }
        });
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                model.removeAllElements();
            }
        });
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
