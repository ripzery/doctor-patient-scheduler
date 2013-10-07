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
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Kunanont
 */
    class Startup extends JFrame{
    private JPanel pstart;
    private JLabel heading,choosename,entername;
    private JButton go,summary;
    private Summary sum;
    private JComboBox combo;
    private JTextField name;
    private Main main;
    private JLabel background;
    private File folder;
    private File[] listoffiles;
    private String files;
    private ArrayList<String> alldoctors;
    public Startup(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setLocation(320,70);
        setSize(800,600);  

        setLayout(new BorderLayout());
       // pstart.setBackground(new Color(0x3e,0x60,0x6f));
        URL img = this.getClass().getResource("pic1.jpg");
        background = new JLabel(new ImageIcon(img),JLabel.CENTER);
        background.setLayout(new MigLayout("insets 50 0 0 0,fillx"));
        background.setOpaque(false);
        add(background);
        addComponents();
        addListener();
        
        setVisible(true);
        setSize(799,599); 
        setSize(800,600); 
        setTitle("Doctor-Patients Scheduler");
        validate();
        repaint();
    
    }
    
    private void addComponents(){
        name = new JTextField();
        name.setDocument(new JTextFieldLimit(10));
        entername = new JLabel("Enter your name: ");
        choosename = new JLabel("Choose exists name:");
        heading = new JLabel("Doctor-Patients Scheduler");
        go = new JButton("GO!");
        
        alldoctors = new ArrayList<>();
        if(new File(System.getProperty("user.dir")+"/Schedule").mkdir()){
            JOptionPane.showMessageDialog(null, "the Schedule folder has been created successfully!");
        }
            folder = new File(System.getProperty("user.dir")+"/Schedule");
        
        listoffiles = folder.listFiles();
        for(int i=0;i<listoffiles.length;i++){
           if(listoffiles[i].isFile()){
               files = listoffiles[i].getName();
               if(files.endsWith(".txt")){
                   alldoctors.add(files);
               }
           }
        }
        combo = new JComboBox();
        for(int i=0;i<alldoctors.size();i++){
            combo.addItem(alldoctors.get(i).substring(0, alldoctors.get(i).length()-4));
        }
                      
        name.setFocusable(true);
        name.setFont(new Font("Arial",Font.BOLD,40));
        combo.setFont(new Font("Arial",Font.BOLD,20));  
        
        Font f = new Font("Arial",Font.BOLD,23);  
        heading.setFont(f);
        choosename.setFont(f);
        entername.setFont(f);
        go.setFont(f);
        
        summary = new JButton("Go to Summary");
        summary.setFont(f);
        
        background.add(heading,"gapright 80px,right,wrap 70px");
        background.add(entername,"gapright 180px,right,wrap 10px");
        background.add(name,"gapright 60px,right, width 40%,wrap 40px");
        background.add(choosename,"gapleft 54%,id entername,wrap 30px");
        background.add(combo,"gapleft 60%");              
        background.add(go,"pos 420px 440px");
        background.add(summary,"pos 520px 440px");
    }
    
    private void addListener(){
        go.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!name.getText().equals("")){
                    if(new File("Schedule/"+name.getText()+".txt").exists()){
                        JOptionPane.showMessageDialog(pstart,"This name already exist! Enter new name or choose name from the box.","Error",JOptionPane.ERROR_MESSAGE);
                        name.setText("");
                    }
                }else
                throwName();
            }
        });
        name.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){                
               char key = e.getKeyChar();
               int key2 = e.getKeyCode();
               if(key >='A' && key <= 'z'||(key == KeyEvent.VK_BACK_SPACE) 
                       ||( key == KeyEvent.VK_DELETE)
                       ||( key == KeyEvent.VK_ENTER)
                       ||( key2 == KeyEvent.VK_SHIFT))
                   e.setKeyChar(' ');
               else{
                   JOptionPane.showMessageDialog(pstart,"Please enter character only !","Error",JOptionPane.ERROR_MESSAGE);
                   name.setText("");
               } 
               
               if(name.getText().equals("")&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(pstart,"Please enter/choose your name !","Error",JOptionPane.ERROR_MESSAGE);   
               }
                
                else if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!name.getText().equals("")){
                    if(new File("Schedule/"+name.getText()+".txt").exists()){
                        JOptionPane.showMessageDialog(pstart,"This name already exist! Enter new name or choose name from the box.","Error",JOptionPane.ERROR_MESSAGE);
                        name.setText("");
                    }
                    }else
                    throwName();
                    }
            }
        });        
        summary.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(sum==null){
                    sum = new Summary();
                    
                }else{
                    sum.setVisible(true);
                }
                    sum.setDoctor((String)combo.getSelectedItem());
                    sum.setStartup(Startup.this);
                    Startup.this.dispose();
            }
        });
        
    }
    
    public void throwName(){
        if(name.getText().equals("")&&main==null){
            main = new Main((String)combo.getSelectedItem());
            //main.loadFile();
        }
        else if(main==null){
            main = new Main(name.getText());
            
        }
        else{
            main.setVisible(true);
            if(name.getText().equals("")){
                main.setDoctorName((String)combo.getSelectedItem());
                //main.loadFile();
            }
            else
                main.setDoctorName(name.getText());
        }
            main.setSummary(sum);
            main.setStartUp(this);
            this.dispose();
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
