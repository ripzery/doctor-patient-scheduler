/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
    private JPanel Schedule,Desc,spd;
    private JButton gostartup;
    private Startup start;
    private String filename;
    private File file;
    private Scanner read;
    private DefaultTableModel table;
    private JTable tb;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private String line;
    String[] columnNames={"DAY","TIME-START","TIME-END","PATIENT","SEX","AGE"},word,weekdays= new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    
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
        
       
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        
        table = new DefaultTableModel(columnNames,0);
        tb = new JTable(table);
        add(new JScrollPane(tb),"width 70%,center,wrap 20px");
        tb.setRowHeight(40);
        tb.setGridColor(Color.yellow);
        tb.setDefaultRenderer(Object.class, centerRenderer);
        tb.setFont(new Font("Arial", Font.BOLD, 12));
        tb.setBackground(Color.orange);
        tb.setForeground(Color.blue);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
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
                for(int i=table.getRowCount()-1;i>=0;i--){
                    table.removeRow(i);
                }
                Summary.this.dispose();
            }
    });
    }
    
    public void setStartup(Startup a){
        start = a;
    }
    
    public void setDoctor(String name){
        filename = name+".txt";
        heading.setText("Summary of Dr."+filename.substring(0,filename.length()-4)+" Schedule");
        
        if(file==null||!file.getName().equals(filename)){
            file = new File(filename);
        }
        
        try {
            if(read==null||!read.equals(new Scanner(file)))
            {
                System.out.println("Read");
                read = new Scanner(file);
            }
            
            while(read.hasNext()){
               line = read.nextLine();
               word = line.split(" ");
               table.addRow(word);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        heading.validate();
    }
    
    
}
