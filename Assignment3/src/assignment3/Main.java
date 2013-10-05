/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
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
    private String[] time,bloodlist,time2;
    private JPanel TitleBar,allergyBox,informationBox,updateBox;
    private JCheckBox[] day;
    private JTextField namefield;
    private JComboBox start,end,blood;
    private JButton insert,clear,finish,change;
    private JList patientList;
    private ButtonGroup bg;
    private DefaultListModel[] model;
    private DefaultComboBoxModel t1,t2,previoust1,previoust2;
    private ArrayList<Integer> startMeeting = new ArrayList<>();//keep all the time when start meeting patient;
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
        addDayBox();
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
    
    private void addDayBox(){
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
                ,"12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30"};
        t1 = new DefaultComboBoxModel();
        for(String a : time){
            t1.addElement(a);
        }
        start = new JComboBox(t1);
        time2 = new String[]{time[1],time[2],time[3],time[4],time[5],time[6],time[7],time[8],time[9],time[10]
                ,time[11],time[12],time[13],time[14],time[15],"16:00"};
        t2 = new DefaultComboBoxModel();
        for(String a : time2){
            t2.addElement(a);
        }
        end = new JComboBox(t2);
        previoust1 = new DefaultComboBoxModel();
        backupTime(previoust1,t1);
        previoust2 = new DefaultComboBoxModel();
        backupTime(previoust2,t2);
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
    
    /*
     * 5 methods following these is relate to "starttime"(JComboBox) and "Endtime"(JComboBox).
     * 1.removeEndTime()
     *  - สิ่งที่มันทำก็คือ 1. ลบเวลาที่เป็นไปไม่ได้ที่จะนัดจองในช่อง "endtime" เมื่อช่อง "starttime" มีค่าๆหนึ่ง ยกตัวอย่างเช่น
     *          เมื่อ "starttime" = 10:00 -> เป็นไปไม่ได้ที่เวลาของ "endtime" จะน้อยกว่าหรือเท่ากับ 10:00 ดังนั้นเวลา
     *          08:00 จนถึง 10:00 จะถูกลบไป และ user จะเห็นเเพียงเวลาที่เป็นไปได้ในช่อง"endtime"เท่านั้น(10:30ขึ้นไป)
     *               2. ลบเวลาที่นัดจองซ้อนทับกัน ยกตัวอย่างเช่น
     *          ถ้าเราจองเวลา 10:00 ถึง 12:00 ไปแล้ว แล้วถ้าเกิด user เลือก "starttime" เป็น 8:00 เราจะไม่ให้ "endtime"
     *          มีค่าที่มากกว่า 12:00ให้เลือก เด็ดขาด เพราะถ้าหาก user เลือกไปจะทำให้เกิดการ time overlapped ดังนั้นถ้าuserเลือก
     *          เวลาน้อยกว่า 10:00 "endtime" จะมีค่าได้มากสุดคือ 10:00 และเวลาที่มากกว่า 10:00 จะถูกลบไปไม่ให้userมีโอกาสได้เลือก
     * 2.และ3. backupTime()&restoreTime()
     *  - สิ่งที่มันทำก็คือ การสำรองค่าเวลาก่อนถูกลบ และ การดึงค่าเวลาก่อนถูกลบมาใช้ ซึ่งจำเป็นในสถาณการณ์ดังนี้
     *          1. จาก method removeEndTime() จะเห็นได้ว่าถ้า user เลือกเวลา 10:00 ใน "starttime" เวลาที่มากกว่า "10:00"
     *          ใน "endtime" จะถูกลบไป แล้วถ้าเกิด user กลับมาเลือกเวลา 08:00 จะเกิดอะไรขึ้นกับ "endtime" ? 
     *          ด้วยสาเหตุนี้ จึงต้องมีการ backupTime() ก่อนที่จะลบเวลาใน "endtime" และต้องมีการ restoreTime()ทุกครั้งก่อนที่จะมีการลบค่า
     * 4.removeChosenTime()
     *  - สิ่งที่มันทำก็คือ ลบเวลาในช่วง period หนึ่งๆทิ้งไป เช่น เราจองเวลา 10:00 - 12:00 ให้คนไข้คนหนึ่ง เราจะต้องลบเวลาในช่วงนี้ทิ้งไปในทั้ง "starttime"
     *          และ "endtime" โดย *** "starttime" จะต้องลบเวลาตั้งแต่ 10:00 จนถึง 11:30 ทิ้งไป ส่วน *** "endtime" จะลบเวลาตั้งแต่
     *          10:30 - 12:00 ทิ้งไป
     * 5.convertTimetoInt()
     *  - สิ่งที่มันทำก็คือ แปลงเวลาให้เป็น Integer เพื่อที่จะได้ใช้เปรียบเทียบกันว่าเวลาใดมากกว่าหรือน้อยกว่าเวลาใด ซึ่งจำเป็นต้องใช้บ่อยใน method ด้านบนยกเว้น
     *          backupTime() กับ restoreTime()
     *       
     */
    
    private void removeEndTime(){
        restoreTime(previoust2,t2);
        int count = 0;
        int a = convertTimetoInt((String)start.getSelectedItem());
        /*
         * This will block the overlapping booking time case
         */
        for(int i=0;i<startMeeting.size();i++){
            if(a<startMeeting.get(i)){
                for(int j=0;j<t2.getSize();j++){
                    if(convertTimetoInt((String)t2.getElementAt(j))>startMeeting.get(i)){
                      count++;  
                    }
                }
                break;
            }
        }
        int oldmax = t2.getSize();
        //System.out.println("Selected : "+a);
        for(int i=0;i<count;i++){
            t2.removeElementAt(oldmax-count);
        }
        count = 0;
        /*
         * This will block the end time that lower than the start time that cannot be possible
         */
        for(int i=0;i<t2.getSize();i++){
            if(a>=(convertTimetoInt((String)t2.getElementAt(i)))){
                count++;
            }
            else{
                break;
            }
        }
        for(int i=0;i<count;i++){
            t2.removeElementAt(0);
        }
    }
    
    private void restoreTime(DefaultComboBoxModel previous,DefaultComboBoxModel current){
        current.removeAllElements();
        for(int i=0;i<previous.getSize();i++){
            current.addElement(previous.getElementAt(i));
        }
    }
    
    private void backupTime(DefaultComboBoxModel previous,DefaultComboBoxModel current){
        previous.removeAllElements();
        for(int i =0;i<current.getSize();i++){
            previous.addElement(current.getElementAt(i));
        }
    }
    
    private void removeChosenTime(){
        int count = 0;
        int a = convertTimetoInt((String)start.getSelectedItem());
        int b = convertTimetoInt((String)end.getSelectedItem());
        int index_a = start.getSelectedIndex();
        int index;
        
        for(int i=0;i<t2.getSize();i++){
            index = convertTimetoInt((String)t2.getElementAt(i));
            if(index>=a&&index<=b){
                count++;
            }
            else if(index>b){
                break;
            }
        }
        /*
         * Before remove the period of meeting time, we need to keep the "start meeting time" in ArrayList,
         * to check the period that possible to booking(record).
         * So, the solution is remove all impossible time in the combobox(time cannot be overlapped).
         */
        startMeeting.add(a);
        Collections.sort(startMeeting);
        restoreTime(previoust1,t1);
        restoreTime(previoust2,t2);
        for(int i=0;i<count;i++){
            //System.out.println("Remove"+">>"+t2.getElementAt(index_a));
            t2.removeElementAt(index_a);
            t1.removeElementAt(index_a);
        }
        backupTime(previoust1,t1);
        backupTime(previoust2,t2);
    }
    
    private int convertTimetoInt(String time){
        String[] b = time.split(":");
        String c = b[0]+b[1];
        int d = Integer.parseInt(c);
        return d;
    }
    
    private void addUpdateBox(){
        Font f = new Font("Arial",Font.BOLD,20);  
        updateBox = new JPanel(new MigLayout("fillx,debug"));
        JLabel heading = new JLabel("Patient Added");
        heading.setFont(f);
        updateBox.add(heading,"center,wrap 10px");
        model = new DefaultListModel[5];
        for(int i=0;i<model.length;i++){
            model[i] = new DefaultListModel();
        }
        patientList = new JList(model[0]);
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
                    for(int i=0;i<model.length;i++){
                        if(day[i].isSelected()){
                            model[i].addElement(name);
                        }
                    }
                    
                }
                patientList.validate();
            }
        });
        
        end.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                removeEndTime();
                end.validate();
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
                /*
                 * So, we need to store the day which selected in somewhere around here to prepare record to the file.
                 */
                if(!namefield.getText().equals("") && checker==true){
                    starttime = (String)start.getSelectedItem();
                    endtime = (String)end.getSelectedItem();
                    name = starttime+" - "+endtime+" "+namefield.getText();
                    namefield.setText("");
                    for(int i=0;i<model.length;i++){
                        if(day[i].isSelected()){
                            model[i].addElement(name);
                        }
                    }
                    /*
                     * Handling about time period in the combobox that has been chosen
                     */
                    removeChosenTime();
                }else if(namefield.getText().equals("")){
                    JOptionPane.showMessageDialog(informationBox,"Please enter patient name's !","Error",JOptionPane.ERROR_MESSAGE); 
                }else if(checker == false)
                    JOptionPane.showMessageDialog(informationBox,"Please enter schedule day !","Error",JOptionPane.ERROR_MESSAGE);
                patientList.validate();
            }
        });
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i=0;i<model.length;i++){
                        if(day[i].isSelected()){
                            model[i].removeAllElements();
                        }
                    }
            }
        });
        
        finish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             new Summary();   
            }
    });
        
        for(int i=0;i<model.length;i++){
            final int index = i;
            day[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    patientList.setModel(model[index]);
                }
        });
        }
  }
  
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }    
}
