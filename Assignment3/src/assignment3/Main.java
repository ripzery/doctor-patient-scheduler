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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Kunanont
 */
public class Main extends JFrame {
    
    private static Startup startup;
    private int width=800,height=600,numberOfDay;
    private String name;
    private String[] time,bloodlist,time2;
    private JPanel TitleBar,allergyBox,informationBox,updateBox;
    private JCheckBox[] day,sex;
    private JTextField namefield,agefield;
    private JComboBox start,end,blood;
    private JButton insert,clear,finish,change,remove;
    private JList patientList;
    private ButtonGroup bg,sexbg;
    private DefaultListModel[] model;
    private JScrollPane scroll;
    private DefaultComboBoxModel[] t1,t2,previoust1,previoust2;
    private ArrayList<ArrayList<Integer>> startMeetingDay;
    boolean checker = false,checker2 = false,checker3 = false;
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
        namefield.setDocument(new JTextFieldLimit(20));
        JLabel choosetimefrom = new JLabel("Choose time : ");
        choosetimefrom.setFont(f);
        JLabel choosetimeto = new JLabel(" to");
        choosetimeto.setFont(f);
        initTime();
        start = new JComboBox(t1[0]);
        end = new JComboBox(t2[0]);
        bloodlist = new String[]{"A","B","AB","O"};
        blood = new JComboBox(bloodlist);
        insert = new JButton("Insert");
        clear = new JButton("Clear");
        finish = new JButton("Finish");
        change = new JButton("Change doctor");
        
        sex = new JCheckBox[2];
        sex[0] = new JCheckBox("  MALE");day[0].setFont(f);day[0].setOpaque(false);
        sex[1] = new JCheckBox("  FEMALE");day[1].setFont(f);day[1].setOpaque(false);
        
        sexbg = new ButtonGroup();
        sexbg.add(sex[0]);
        sexbg.add(sex[1]);
        
        JLabel lsex = new JLabel("Sex: ");
        JLabel lage = new JLabel("Age: ");
        agefield = new JTextField(2);
        agefield.setDocument(new JTextFieldLimit(2));
        lage.setFont(f);
        lsex.setFont(f);
        
        informationBox.add(patientName,"grow");
        informationBox.add(namefield,"gapx unrel unrel,wrap 30px");
        informationBox.add(choosetimefrom,"grow");
        informationBox.add(start,"grow");
        informationBox.add(choosetimeto,"grow");
        informationBox.add(end,",grow,wrap 30px");
        informationBox.add(lsex);
        informationBox.add(sex[0]);informationBox.add(sex[1]);
        informationBox.add(lage);informationBox.add(agefield,"wrap 30px");
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
        restoreTime(previoust2[numberOfDay],t2[numberOfDay]);
        int count = 0;
        int a = convertTimetoInt((String)start.getSelectedItem());
        /*
         * This will block the overlapping booking time case
         */
        for(int i=0;i<startMeetingDay.get(numberOfDay).size();i++){
            if(a<startMeetingDay.get(numberOfDay).get(i)){
                for(int j=0;j<t2[numberOfDay].getSize();j++){
                    if(convertTimetoInt((String)t2[numberOfDay].getElementAt(j))>startMeetingDay.get(numberOfDay).get(i)){
                      count++;  
                    }
                }
                break;
            }
        }
        int oldmax = t2[numberOfDay].getSize();
        for(int i=0;i<count;i++){
            //System.out.println("T2 removed "+t2[numberOfDay].getElementAt(oldmax-count));
            t2[numberOfDay].removeElementAt(oldmax-count);
        }
        count = 0;
        /*
         * This will block the end time that lower than the start time that cannot be possible
         */
        for(int i=0;i<t2[numberOfDay].getSize();i++){
            if(a>=(convertTimetoInt((String)t2[numberOfDay].getElementAt(i)))){
                count++;
            }
            else{
                break;
            }
        }
        for(int i=0;i<count;i++){
            t2[numberOfDay].removeElementAt(0);
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
        int index,keymodel=0;
        
        for(int i=0;i<t2[numberOfDay].getSize();i++){
            index = convertTimetoInt((String)t2[numberOfDay].getElementAt(i));
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
        startMeetingDay.get(numberOfDay).add(a);
        Collections.sort(startMeetingDay.get(numberOfDay));
        restoreTime(previoust1[numberOfDay],t1[numberOfDay]);
        restoreTime(previoust2[numberOfDay],t2[numberOfDay]);
        for(int i=0;i<count;i++){
            //System.out.println("T2 removed "+t2[numberOfDay].getElementAt(index_a));
            t2[numberOfDay].removeElementAt(index_a);
            t1[numberOfDay].removeElementAt(index_a);
        }
        backupTime(previoust1[numberOfDay],t1[numberOfDay]);
        backupTime(previoust2[numberOfDay],t2[numberOfDay]);
    }
    
    private int convertTimetoInt(String time){
        String[] b = time.split(":");
        String c = b[0]+b[1];
        int d = Integer.parseInt(c);
        return d;
    }
    
    private void freeTime(ArrayList<String> update){
        String[] a;
        String TimeStart,TimeEnd;
        int startcut=-2,endcut=-2,index=0;
        
        for(int i=0;i<update.size();i++){
            startcut=-2;
            endcut=-2;
            a = update.get(i).split(" ");
            TimeStart = a[0];
            TimeEnd = a[2];
            
            //For add the time back to t1
            for(int j=0;j<time.length;j++){
                if(TimeStart.equals(time[j])){
                    startcut = j;
                }
                else if(TimeEnd.equals(time[j])){
                    endcut = j;
                }
                else if(TimeEnd.equals("16:00")){
                    endcut = time.length;
                }
                if(startcut!=-2&&endcut!=-2)break;
            }
            
            for(int j=startcut;j<endcut;j++){
                t1[numberOfDay].addElement(time[j]);
            }
            startcut = -2;
            endcut = -2;
            //For add the time back to t2
            for(int j=0;j<time2.length;j++){
                if(TimeStart.equals(time2[j])){
                    startcut = j;
                }
                else if(TimeEnd.equals(time2[j])){
                    endcut = j;
                }
                else if(TimeStart.equals(time[0])){
                    startcut = -1;
                }
                if(startcut!=-2&&endcut!=-2)break;
            }
            
            for(int j=endcut;j>startcut;j--){
                //System.out.println("T2 added "+time2[j]);
                t2[numberOfDay].addElement(time2[j]);
            }
            sortTime();
            backupTime(previoust1[numberOfDay],t1[numberOfDay]);
            backupTime(previoust2[numberOfDay],t2[numberOfDay]);
        }
    }
    
    private void sortTime(){
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        
        for(int i=0;i<t1[numberOfDay].getSize();i++){
            if(!a.contains((String)t1[numberOfDay].getElementAt(i)))
            a.add((String)t1[numberOfDay].getElementAt(i));
        }
        for(int i=0;i<t2[numberOfDay].getSize();i++){
            if(!b.contains((String)t2[numberOfDay].getElementAt(i)))
            b.add((String)t2[numberOfDay].getElementAt(i));
        }
        
        Collections.sort(a);
        Collections.sort(b);
        t1[numberOfDay].removeAllElements();
        t2[numberOfDay].removeAllElements();
        for(int i=0;i<a.size();i++){
            t1[numberOfDay].addElement(a.get(i));
        }
        for(int i=0;i<b.size();i++){
            t2[numberOfDay].addElement(b.get(i));
        }
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
        patientList.setVisibleRowCount(8);
        patientList.setFont(f);
        scroll = new JScrollPane(patientList);
        updateBox.add(scroll,"center,height 280px,wrap 20px");
        
        remove = new JButton("Remove");
        remove.setFont(f);
        updateBox.add(remove,"center");
        
        this.add(updateBox,"pos information.x2+10 information.y 770 information.y2");
    }
    
    private void addListener(){
        namefield.addKeyListener(new KeyAdapter(){
            String starttime,endtime;
            int index;
            public void keyPressed(KeyEvent e){                
               char key = e.getKeyChar();
               int key2 = e.getKeyCode();
               if(key >='A' && key <= 'z'||(key == KeyEvent.VK_BACK_SPACE) 
                       ||( key == KeyEvent.VK_DELETE)
                       ||( key == KeyEvent.VK_ENTER)
                       ||( key2 == KeyEvent.VK_SHIFT))
                   e.setKeyChar(' ');
               else{
                   JOptionPane.showMessageDialog(informationBox,"Please enter character only !","Error",JOptionPane.ERROR_MESSAGE);
                   namefield.setText("");
               }                
                for(i=0 ;i<5;i++){
                    if(day[i].isSelected()){
                        checker = true;
                        index = i;
                        break;
                    }else checker = false;                    
                }
                
                if(e.getKeyCode()==KeyEvent.VK_ENTER&&!namefield.getText().equals("")&&checker==true&&start.getItemCount()>0&&!agefield.getText().equals("")&&(sex[0].isSelected()||sex[1].isSelected())){
                    starttime = (String)start.getSelectedItem();
                    endtime = (String)end.getSelectedItem();
                    name = starttime+" - "+endtime+" "+namefield.getText();
                    namefield.setText("");
                    model[index].addElement(name);
                    removeChosenTime();
                }else if(namefield.getText().equals("")&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(informationBox,"Please enter patient name's !","Error",JOptionPane.ERROR_MESSAGE); 
                }else if(checker == false&&e.getKeyCode()==KeyEvent.VK_ENTER)
                    JOptionPane.showMessageDialog(informationBox,"Please enter schedule day !","Error",JOptionPane.ERROR_MESSAGE);
                else if(start.getItemCount()==0&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(informationBox,"All time period has been reserved","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!(sex[0].isSelected()||sex[1].isSelected())&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(informationBox,"Please select sex.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(agefield.getText().equals("")&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(informationBox,"Please enter your age.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        agefield.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
               char key = e.getKeyChar();
               int key2 = e.getKeyCode();
               if(key >='0' && key <= '9'||(key == KeyEvent.VK_BACK_SPACE) 
                       ||( key == KeyEvent.VK_DELETE)
                       ||( key == KeyEvent.VK_ENTER)
                       ||( key2 == KeyEvent.VK_SHIFT))
                   e.setKeyChar(' ');
               else{
                   JOptionPane.showMessageDialog(informationBox,"Please enter number only !","Error",JOptionPane.ERROR_MESSAGE);
                   agefield.setText("");
               }
           } 
        });
        
        end.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(end.getItemCount()>0){
                    removeEndTime();
                    end.validate();
                }
            }
        });
        
        insert.addActionListener(new ActionListener(){
            String starttime,endtime;
            int index;
            public void actionPerformed(ActionEvent e){   
                for(i=0 ;i<5;i++){
                    if(day[i].isSelected()){
                        checker = true;
                        index = i;
                        break;
                    }else checker = false;                    
                }
                //LOOP for sex
                for(int j =0;j<2;j++){
                    if(sex[j].isSelected()){
                        checker2 = true;
                        break;
                    }else checker2 =false;
                }
                /*
                 * So, we need to store the day which selected in somewhere around here to prepare record to the file.
                 */
                if(!namefield.getText().equals("") && checker==true && checker2==true &&!agefield.getText().equals("")&&(sex[0].isSelected()||sex[1].isSelected())){
                    starttime = (String)start.getSelectedItem();
                    endtime = (String)end.getSelectedItem();
                    name = starttime+" - "+endtime+" "+namefield.getText();
                    namefield.setText("");
                    model[index].addElement(name);
                    /*
                     * Handling about time period in the combobox that has been chosen
                     */
                    removeChosenTime();
                }else if(namefield.getText().equals("")){
                    JOptionPane.showMessageDialog(informationBox,"Please enter patient name's !","Error",JOptionPane.ERROR_MESSAGE); 
                }else if(checker == false)
                    JOptionPane.showMessageDialog(informationBox,"Please enter schedule day !","Error",JOptionPane.ERROR_MESSAGE);
                else if(!(sex[0].isSelected()||sex[1].isSelected())){
                    JOptionPane.showMessageDialog(informationBox,"Please select sex.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(agefield.getText().equals("")){
                    JOptionPane.showMessageDialog(informationBox,"Please enter your age.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                bg.clearSelection();
                sexbg.clearSelection();
                agefield.setText("");
                namefield.setText("");
            }
        });
        
        finish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             new Summary();   
            }
    });
        
        for(int i=0;i<model.length;i++){
            day[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    numberOfDay = getDay();
                    patientList.setModel(model[numberOfDay]);
                    start.setModel(t1[numberOfDay]);
                    end.setModel(t2[numberOfDay]);
                }
        });
        }
        
        remove.addActionListener(new ActionListener(){
            int index=0;
            public void actionPerformed(ActionEvent e){
                if(!patientList.isSelectionEmpty()){
                    freeTime((ArrayList<String>)patientList.getSelectedValuesList());
                    for(int i=patientList.getSelectedIndices().length-1;i>=0;i--){
                        startMeetingDay.get(numberOfDay).remove(patientList.getSelectedIndices()[i]);
                        model[numberOfDay].removeElementAt(patientList.getSelectedIndices()[i]);
                    }
                }
            }
        });
  }
    
    private void initTime(){
        startMeetingDay = new ArrayList<>();
        for(int i=0;i<5;i++){
            startMeetingDay.add(new ArrayList<Integer>());
        }
        
        time = new String[]{"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30"
                ,"12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30"};
        t1 = new DefaultComboBoxModel[5];
        for(int i=0;i<5;i++){
            t1[i] = new DefaultComboBoxModel();
        }
        for(int i=0;i<5;i++){
            for(String a : time){
            t1[i].addElement(a);
            }
        }
        time2 = new String[]{time[1],time[2],time[3],time[4],time[5],time[6],time[7],time[8],time[9],time[10]
                ,time[11],time[12],time[13],time[14],time[15],"16:00"};
        
        t2 = new DefaultComboBoxModel[5];
        for(int i=0;i<5;i++){
            t2[i] = new DefaultComboBoxModel();
        }
        for(int i=0;i<5;i++){
            for(String a : time2){
            t2[i].addElement(a);
            }
        }
        previoust1 = new DefaultComboBoxModel[5];
        for(int i=0;i<5;i++){
            previoust1[i] = new DefaultComboBoxModel();
            backupTime(previoust1[i],t1[i]);
        }
        previoust2 = new DefaultComboBoxModel[5];
        for(int i=0;i<5;i++){
            previoust2[i] = new DefaultComboBoxModel();
            backupTime(previoust2[i],t2[i]);
        }
        
    }
  
    private int getDay(){
        for(int i=0;i<day.length;i++){
            if(day[i].isSelected()){
                return i;
            }
        }
        return 0;
    }
    
    public final void setDoctorName(String s){
      name = s;
      setTitle("Doctor "+name+" 's scheduler");
      startup.dispose();
    }
    
    // Use to limit number of character in JTextFields
   public class JTextFieldLimit extends PlainDocument {
        private int limit;

            JTextFieldLimit(int limit) {
             super();
             this.limit = limit;
             }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            
            if (str == null) return;
            if ((getLength() + str.length()) <= limit) {
              super.insertString(offset, str, attr);
            }
          }
      }
}
