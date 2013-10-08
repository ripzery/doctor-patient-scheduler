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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author OATz
 */
public class Summary extends JFrame{
    
    private JLabel heading;
    private JComboBox day,doctor;
    private JPanel button;
    private JButton gostartup,sorting,sorting2;
    private Startup start;
    private String filename;
    private File file;
    private Scanner read;
    private DefaultTableModel[] table;
    private JTable tb;
    private int dayindex=0;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private String line;
    private ArrayList<ArrayList<String>> row = new ArrayList<>();
    String[] columnNames={"DAY","TIME-START","TIME-END","PATIENT","BLOOD","SEX","AGE"},word,weekdays= new String[]{"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
    
    public Summary(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,70,800,600);
        getContentPane().setBackground(new Color(0x3e,0x60,0x6f));
        setVisible(true);
        setLayout(new MigLayout("fillx"));
        
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
        
       
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        table = new DefaultTableModel[5];
        for(int i=0;i<table.length;i++){
            table[i] = new DefaultTableModel(columnNames,0);
        }
        tb = new JTable(table[0]);
        add(new JScrollPane(tb),"width 70%,center,wrap 20px");
        tb.setRowHeight(40);
        tb.setGridColor(Color.yellow);
        tb.setDefaultRenderer(Object.class, centerRenderer);
        tb.setFont(new Font("Arial", Font.BOLD, 12));
        tb.setBackground(Color.orange);
        tb.setForeground(Color.blue);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        button = new JPanel();
        button.setOpaque(false);
        
        gostartup = new JButton("Back to Home Screen");
        Font g = new Font("Arial",Font.BOLD,20);
        gostartup.setFont(g);
        button.add(gostartup);
        
        sorting = new JButton("Sort by time!");
        sorting.setFont(new Font("Arial",Font.BOLD,20));
        button.add(sorting);
        
        sorting2 = new JButton("Sort by age!");
        sorting2.setFont(new Font("Arial",Font.BOLD,20));
        button.add(sorting2);
        add(button,"center");
    }
    
    public void addListener(){
        gostartup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(start==null){
                    start = new Startup();
                }else{
                    start.setVisible(true);
                }
                removeRowAllTable();
                Summary.this.dispose();
            }
    });
        
        day.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                dayindex = day.getSelectedIndex();
                tb.setModel(table[dayindex]);
            }
        });
        
        sorting.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sortTime();
            }
        });
        
        sorting2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sortAge();
            }
        });
    }
    
    public void setStartup(Startup a){
        start = a;
    }
    
    public void setDoctor(String name){
        filename = name+".txt";
        heading.setText("Summary of Dr."+filename.substring(0,filename.length()-4)+" Schedule");
        readFile();
        heading.validate();
    }
    
    public void readFile(){
        row.clear();
        for(int i=0;i<weekdays.length;i++){
            row.add(new ArrayList<String>());
        }
        if(file==null||!file.getName().equals(filename)){
            file = new File("Schedule/"+filename);
        }
        
        try {
            if(read==null||!read.equals(new Scanner(file)))
            {
                read = new Scanner(file);
            }
            
            while(read.hasNext()){
               line = read.nextLine();
               word = line.split(" ");
               if(word[0].equals(weekdays[0])){
                   row.get(0).add(line);
                   table[0].addRow(word);
               }
               else if(word[0].equals(weekdays[1])){
                   row.get(1).add(line);
                   table[1].addRow(word);
               }else if(word[0].equals(weekdays[2])){
                   row.get(2).add(line);
                   table[2].addRow(word);
               }else if(word[0].equals(weekdays[3])){
                   row.get(3).add(line);
                   table[3].addRow(word);
               }else{
                   row.get(4).add(line);
                   table[4].addRow(word);
               }
            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Summary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortTime(){        
        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> sort = new ArrayList<>();
            for(int i=0;i<table[dayindex].getRowCount();i++){
                time.add((String)table[dayindex].getValueAt(i, 1));
            }
            Collections.sort(time);
            //while(sort.size()<row.get(dayindex).size()){
                for(int k=0;k<time.size();k++){
                    for(int i=0;i<table[dayindex].getRowCount();i++){
                        if(time.get(k).equals(row.get(dayindex).get(i).split(" ")[1])){
                            sort.add(row.get(dayindex).get(i));
                        }
                    }
                }
            //}
            for(int i=table[dayindex].getRowCount()-1;i>=0;i--){
                table[dayindex].removeRow(i);
            }
            for(int i=0;i<sort.size();i++){
                table[dayindex].addRow(sort.get(i).split(" "));
            }
            time.clear();
            sort.clear();
    }
    
    public void sortAge(){
        class temp implements Comparable
        {
            int age;
            String name;
            public temp(String age, String name)
            {
                this.age = Integer.parseInt(age);
                this.name = name;
            }
            public int getAge()
            { return age; }
            public String getName()
            { return name; }
            public int compareTo(Object O)
            {
                temp cast = (temp) O;
                return age - cast.age;
                
            }
        }
        ArrayList<temp> data = new ArrayList<>();
        ArrayList<String> allLine = new ArrayList<>();
        for(int i=0;i<table[dayindex].getRowCount();i++){
            data.add( new temp((String)(table[dayindex].getValueAt(i, 6)), (String)(table[dayindex].getValueAt(i, 3))));
        }
            
        Collections.sort(data);

            for(int k=0;k<data.size();k++){
                for(int i=0;i<table[dayindex].getRowCount();i++){
                    if((String.valueOf(data.get(k).getAge())).equals(row.get(dayindex).get(i).split(" ")[6]) && data.get(k).getName().equals(row.get(dayindex).get(i).split(" ")[3])){                                                     
                        allLine.add(row.get(dayindex).get(i));                            
                    }
                }
            }
            
            for(int i=table[dayindex].getRowCount()-1;i>=0;i--){
                table[dayindex].removeRow(i);           
            }
            for(int i=0;i<allLine.size();i++){
                table[dayindex].addRow(allLine.get(i).split(" ")); 
            }
    }
    
    public void removeRowAllTable(){
        for(int j=0;j<table.length;j++){
            for(int i=table[j].getRowCount()-1;i>=0;i--){
                table[j].removeRow(i);
            }
        }
    }   
}
