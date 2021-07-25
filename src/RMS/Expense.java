/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMS;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author saman
 */
public class Expense extends javax.swing.JFrame {

    /**
     * Creates new form Expense
     */
    public Expense() {
        initComponents();

       this.getContentPane().setBackground(new Color(255,255,255));
        DefaultTableCellRenderer MyHeaderRender = new DefaultTableCellRenderer();
        MyHeaderRender.setBackground(new Color(0,0,0));
        MyHeaderRender.setForeground(new Color(255,255,255));
        expensetbl.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(MyHeaderRender);
         expensetbl.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(MyHeaderRender);
         expensetbl.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(MyHeaderRender);
         expensetbl.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(MyHeaderRender);
        expensetbl.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(MyHeaderRender);
        this.setLocationRelativeTo(null);
      showdate();
      
    }
  private static int  num;
   private static int id;
    int a;
  private void newfile(int num)
  {
       try{
        File file=new File("Resources/id.txt");
        FileWriter writter = new FileWriter(file,true);
        BufferedWriter  b1=new BufferedWriter(writter);
        String newvalue=String.valueOf(num);
        b1.write(newvalue);
        b1.close();
        writter.close();
        }
        catch(IOException ex)
        {
        JOptionPane.showMessageDialog(null,"Error");
        }
  }
  private void deletebutton()
  {
       DefaultTableModel model=(DefaultTableModel) expensetbl.getModel();
           try{
                int modelRow = expensetbl.convertRowIndexToModel(expensetbl.getSelectedRow());
                model.removeRow(modelRow);
                searchtxt.setText("");
                searchtxt.requestFocus();
                 DefaultTableModel m1=(DefaultTableModel) expensetbl.getModel();
        TableRowSorter<DefaultTableModel> t4=new TableRowSorter<DefaultTableModel>(m1);
        expensetbl.setRowSorter(t4);
        t4.setRowFilter(null);
                blank();
           }
           catch (Exception ex)
           {
               JOptionPane.showMessageDialog(null, ex);
           }
            File myObj = new File("Files/expensereport.txt"); 
            myObj.delete();
           savefile();
            File Obj = new File("Resources/expensereporttotal.txt"); 
            Obj.delete();
            add();
            totalfile();
  }
  private void editbutton()
  {
      DefaultTableModel t1=(DefaultTableModel)expensetbl.getModel();
                 if(expensetbl.getSelectedRowCount()==1)
                 {
                      String  id =idtxt.getText();
                      String name=nametxt.getText();
                      String price= pricetxt.getText();
                      String detail= detailstxt.getText();
                      t1.setValueAt(id,expensetbl.getSelectedRow(),0);
                     t1.setValueAt(name,expensetbl.getSelectedRow(),1);
                     t1.setValueAt(price,expensetbl.getSelectedRow(),2);
                      t1.setValueAt(detail,expensetbl.getSelectedRow(),3);
                      add();
                     blank();
                      JOptionPane.showMessageDialog(this, "updated!");
                        }
                 else
                 {
                     if(expensetbl.getRowCount()==0)
                     {
                         JOptionPane.showMessageDialog(this,"no values in table");
                     }
                     else
                     {
                           JOptionPane.showMessageDialog(this,"select a single row to update");

                     }
                     
                         
                 }
           File myObj = new File("Files/expensereport.txt"); 
            myObj.delete();
             savefile();
              File Obj = new File("Resources/expensereporttotal.txt"); 
            Obj.delete();
            totalfile();
  }
    private void addbutton()
    {
          if (nametxt.getText().equals("")||pricetxt.getText().equals("")||detailstxt.getText().equals(""))
        { 
            JOptionPane.showMessageDialog(this,"Please Enter All Data");
        }
        else
        {
         try {
             autoId();
         } catch (IOException ex) {
             System.out.println("An error occurred."); 
         }
   
       String number=String.valueOf(id);
           String data[] ={number,nametxt.getText(),pricetxt.getText(),detailstxt.getText(),datetxt.getText()};
                       DefaultTableModel t2=(DefaultTableModel)expensetbl.getModel();
                          t2.addRow(data);
                       JOptionPane.showMessageDialog(this,"Data Added Successfully");
                        add();
                       blank();
                           
        }
           File myObj = new File("Files/expensereport.txt"); 
            myObj.delete();
             savefile();
             File Obj = new File("Resources/expensereporttotal.txt"); 
            Obj.delete();
            totalfile();
             
    }
  private void printbutton() 
  {
       try {
             // TODO add your handling code here:
           expensetbl.print(); 
         } 
       catch (PrinterException ex) {
              System.out.println("An error occurred.");
         }
           
  }
   private void autoId() throws IOException
   {  
      File tempFile = new File("Resources/id.txt");
         boolean exists = tempFile.exists();
         if(exists==false)
         {
             
             newfile(0);
         }
         else{
            System.out.println("id id already there");
         }
       String content = new String(Files.readAllBytes(Paths.get("Resources/id.txt")));
       num=Integer.parseInt(content);  
       id=num+1;
        File myObj = new File("Resources/id.txt"); 
            myObj.delete();
          newfile(id);
            
       
      
       
   }
  
   
     private void showdate()
    {
         Date d=new Date();
        SimpleDateFormat d1=new SimpleDateFormat("dd/MM/yyyy");
        datetxt.setText(d1.format(d));
        nametxt.requestFocus();
    }

 
    
 private void readfile()
 {
      DefaultTableModel model=(DefaultTableModel)expensetbl.getModel();
    
     
    try {
        if(model.getRowCount()<=0){
            File Obj = new File("Files/expensereport.txt");
            if (Obj.createNewFile()) {
                System.out.println("File created: " + Obj.getName());
            } else {
                System.out.println("File already exists.");
            }
  
            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                model.insertRow(model.getRowCount(), new Object[]{data.split(",")[0], data.split(",")[1], data.split(",")[2],data.split(",")[3],data.split(",")[4]});
            }
            Reader.close();
           
    }
        } catch (IOException e) {
            System.out.println("An error occurred.");
           
        }
     
 }
  private void savefile()
  {
           try{
             File file=new File("Files/expensereport.txt");
        FileWriter writter = new FileWriter(file,true);
        BufferedWriter  b1=new BufferedWriter(writter);
        for(int i=0;i<expensetbl.getRowCount(); i++)
        { for(int j=0;j<expensetbl.getColumnCount();j++)
            {
                b1.write((String)expensetbl.getModel().getValueAt(i,j)+",");
               
            }
        b1.write("\n");
        }
        b1.close();
        writter.close();
        
        }
        catch(IOException ex)
        {
        JOptionPane.showMessageDialog(null,"Error");
        }
           
           
  }
  private void totalfile()
  {
      
           try{
             File file=new File("Resources/expensereporttotal.txt");
        FileWriter writter = new FileWriter(file,true);
        BufferedWriter  b1=new BufferedWriter(writter);
        String total=totaltxt.getText();
          b1.write(total);
                b1.close();
              writter.close();   
           }
           catch(IOException ex)
        {
        JOptionPane.showMessageDialog(null,"Error");
        }
      
  }
  private void blank()
  {
       idtxt.setText("");
        nametxt.setText("");
        pricetxt.setText("");
        detailstxt.setText("");
  }
  private void add()
  {
       int total=0;
        for(int i=0; i<expensetbl.getRowCount();i++)
        {
            total=total+Integer.parseInt(expensetbl.getValueAt(i, 2).toString().trim());
        }
        totaltxt.setText(Integer.toString(total).trim());
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        clearbtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        addexpenselbl = new javax.swing.JLabel();
        idlbl = new javax.swing.JLabel();
        closebtn = new javax.swing.JButton();
        namelbl = new javax.swing.JLabel();
        searchtxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pricelbl = new javax.swing.JLabel();
        searchlbl = new javax.swing.JLabel();
        Detailslbl = new javax.swing.JLabel();
        totallbl = new javax.swing.JLabel();
        idtxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        nametxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        expensetbl = new javax.swing.JTable();
        pricetxt = new javax.swing.JTextField();
        detailstxt = new javax.swing.JTextField();
        datetxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        addbtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\plus (2).png")); // NOI18N
        addbtn.setText("Add");
        addbtn.setPreferredSize(new java.awt.Dimension(75, 25));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        addbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addbtnKeyPressed(evt);
            }
        });

        printbtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        printbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\printer.png")); // NOI18N
        printbtn.setText("Print");
        printbtn.setPreferredSize(new java.awt.Dimension(75, 25));
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });
        printbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                printbtnKeyPressed(evt);
            }
        });

        editbtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\updated.png")); // NOI18N
        editbtn.setText("Edit");
        editbtn.setPreferredSize(new java.awt.Dimension(75, 25));
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });
        editbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editbtnKeyPressed(evt);
            }
        });

        clearbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\remove.png")); // NOI18N
        clearbtn.setText("Clear");
        clearbtn.setPreferredSize(new java.awt.Dimension(75, 25));
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        deletebtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\minus.png")); // NOI18N
        deletebtn.setText("Delete ");
        deletebtn.setPreferredSize(new java.awt.Dimension(75, 25));
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        deletebtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deletebtnKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        addexpenselbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        addexpenselbl.setForeground(new java.awt.Color(255, 255, 255));
        addexpenselbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addexpenselbl.setText("Add Expense");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addexpenselbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addexpenselbl, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        idlbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idlbl.setText("ID");

        closebtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        closebtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\close.png")); // NOI18N
        closebtn.setText("Close");
        closebtn.setPreferredSize(new java.awt.Dimension(75, 25));
        closebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtnActionPerformed(evt);
            }
        });
        closebtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closebtnKeyPressed(evt);
            }
        });

        namelbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        namelbl.setText("Name");

        searchtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchtxtMouseClicked(evt);
            }
        });
        searchtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxtActionPerformed(evt);
            }
        });
        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
        });

        jLabel2.setText("Date");

        pricelbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pricelbl.setText("Price");

        searchlbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchlbl.setForeground(new java.awt.Color(0, 51, 51));
        searchlbl.setText("SEARCH");

        Detailslbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Detailslbl.setText("Details");

        totallbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totallbl.setText("TOTAL");

        idtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxtActionPerformed(evt);
            }
        });

        totaltxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nametxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nametxt.setNextFocusableComponent(pricetxt);
        nametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nametxtActionPerformed(evt);
            }
        });

        expensetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Details", "Date"
            }
        ));
        expensetbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expensetblMouseClicked(evt);
            }
        });
        expensetbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                expensetblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(expensetbl);

        pricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pricetxt.setNextFocusableComponent(detailstxt);
        pricetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricetxtActionPerformed(evt);
            }
        });

        detailstxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detailstxt.setNextFocusableComponent(addbtn);
        detailstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailstxtActionPerformed(evt);
            }
        });

        datetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Detailslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(detailstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(searchlbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(printbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(closebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(totallbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idlbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namelbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pricetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricelbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(detailstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Detailslbl)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totallbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(printbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(closebtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {datetxt, detailstxt, idtxt, nametxt, pricetxt});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addbtn, clearbtn, closebtn, deletebtn, editbtn, printbtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        // TODO add your handling code here

        addbutton();
        nametxt.requestFocus();

    }//GEN-LAST:event_addbtnActionPerformed

    private void addbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addbtnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            addbutton();
            nametxt.requestFocus();
        }
    }//GEN-LAST:event_addbtnKeyPressed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        printbutton();
    }//GEN-LAST:event_printbtnActionPerformed

    private void printbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printbtnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            printbutton();
        }
    }//GEN-LAST:event_printbtnKeyPressed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        // TODO add your handling code here:
        editbutton();
        nametxt.requestFocus();
    }//GEN-LAST:event_editbtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed

        // TODO add your handling code here:
        blank();
    }//GEN-LAST:event_clearbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        // TODO add your handling code here:
        deletebutton();
        nametxt.requestFocus();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void deletebtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deletebtnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            deletebutton();
            nametxt.requestFocus();
        }
    }//GEN-LAST:event_deletebtnKeyPressed

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:

        dispose();
        //        Dashboard d1=new Dashboard();
        //          d1.setVisible(true);
    }//GEN-LAST:event_closebtnActionPerformed

    private void closebtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closebtnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            dispose();
        }
    }//GEN-LAST:event_closebtnKeyPressed

    private void searchtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtxtMouseClicked
        // TODO add your handling code here:
        searchtxt.setText("");

    }//GEN-LAST:event_searchtxtMouseClicked

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel m1=(DefaultTableModel) expensetbl.getModel();
        TableRowSorter<DefaultTableModel> t4=new TableRowSorter<DefaultTableModel>(m1);
        expensetbl.setRowSorter(t4);
        t4.setRowFilter(RowFilter.regexFilter(searchtxt.getText().trim()));

    }//GEN-LAST:event_searchtxtKeyReleased

    private void idtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtxtActionPerformed

    private void nametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nametxtActionPerformed

    private void expensetblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensetblMouseClicked
        // TODO add your handling code here:
        int row[]=this.expensetbl.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
            int modelRow = expensetbl.convertRowIndexToModel(row[i]);
            DefaultTableModel model=(DefaultTableModel)expensetbl.getModel();
            String id=model.getValueAt(modelRow,0).toString();
            String name=model.getValueAt(modelRow,1).toString();
            String price=model.getValueAt(modelRow,2).toString();
            String details=model.getValueAt(modelRow,3).toString();
            idtxt.setText(id);
            nametxt.setText(name);
            pricetxt.setText(price);
            detailstxt.setText(details);
        }
    }//GEN-LAST:event_expensetblMouseClicked

    private void expensetblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expensetblKeyPressed
        int row[]=this.expensetbl.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
            int modelRow = expensetbl.convertRowIndexToModel(row[i]);
            DefaultTableModel model=(DefaultTableModel)expensetbl.getModel();
            String id=model.getValueAt(modelRow,0).toString();
            String name=model.getValueAt(modelRow,1).toString();
            String price=model.getValueAt(modelRow,2).toString();
            String details=model.getValueAt(modelRow,3).toString();
            idtxt.setText(id);
            nametxt.setText(name);
            pricetxt.setText(price);
            detailstxt.setText(details);
        }

    }//GEN-LAST:event_expensetblKeyPressed

    private void pricetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricetxtActionPerformed

    private void detailstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailstxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detailstxtActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        readfile();
         add();
         add();
      File Obj = new File("Resources/expensereporttotal.txt"); 
      Obj.delete();
     totalfile();
    }//GEN-LAST:event_formWindowActivated

    private void editbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editbtnKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
        editbutton();
        nametxt.requestFocus();
         }
    }//GEN-LAST:event_editbtnKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expense().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Detailslbl;
    private javax.swing.JButton addbtn;
    private javax.swing.JLabel addexpenselbl;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton closebtn;
    private javax.swing.JTextField datetxt;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField detailstxt;
    private javax.swing.JButton editbtn;
    private javax.swing.JTable expensetbl;
    private javax.swing.JLabel idlbl;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel namelbl;
    private javax.swing.JTextField nametxt;
    private javax.swing.JLabel pricelbl;
    private javax.swing.JTextField pricetxt;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel searchlbl;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JLabel totallbl;
    private javax.swing.JTextField totaltxt;
    // End of variables declaration//GEN-END:variables
}
