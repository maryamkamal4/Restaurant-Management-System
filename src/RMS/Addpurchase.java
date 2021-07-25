/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMS;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author saman
 */
public class Addpurchase extends javax.swing.JFrame {
  int x,y;
  private static int  num;
   private static int id;
   private static int a;
   
    /**
     * Creates new form Addpurchase
     */
    public Addpurchase() {
        initComponents();
        savedata();
         this.setLocationRelativeTo(null);
        showdate();
       this.getContentPane().setBackground(new Color(255,255,255));
        DefaultTableCellRenderer MyHeaderRender = new DefaultTableCellRenderer();
        MyHeaderRender.setBackground(new Color(0,0,0));
        MyHeaderRender.setForeground(new Color(255,255,255));
       purchasetbl.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(MyHeaderRender);
        purchasetbl.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(MyHeaderRender);
        purchasetbl.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(MyHeaderRender);
         purchasetbl.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(MyHeaderRender);
         purchasetbl.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(MyHeaderRender);
           purchasetbl.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(MyHeaderRender);
             purchasetbl.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(MyHeaderRender);
               purchasetbl.getTableHeader().getColumnModel().getColumn(7).setHeaderRenderer(MyHeaderRender);
            clientnametxt.requestFocus();
    }
    private void savedata()
    {
         try {
         BufferedReader  buff = new BufferedReader(new FileReader("Files/purchasereport.txt"));
        String str=buff.readLine();
         while (str != null) 
       {
        texttbl.append(str+ "\n");
       str=buff.readLine();
        }
        
      } catch (IOException ex) {
          Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
      }
     
    }
      private void savefile()
    { 
       try{
        
        FileWriter writter=new FileWriter("Files/purchasereport.txt");
        BufferedWriter  b1=new BufferedWriter(writter);
        b1.write("");
        b1.write(texttbl.getText());  
       {for(int i=0;i<purchasetbl.getRowCount(); i++)
        { for(int j=0;j<purchasetbl.getColumnCount();j++)
            {
                b1.write((String)purchasetbl.getModel().getValueAt(i,j)+",");
               
            }
        b1.write("\n");
        }
        b1.close();
        writter.close();
        
        }
      
      
       }
       
        catch(IOException ex)
        {
        JOptionPane.showMessageDialog(null,"Error");
        }
           
         
        }
    
    
    private void editbutton() throws IOException
    {
           DefaultTableModel t1=(DefaultTableModel)purchasetbl.getModel();
                 if(purchasetbl.getSelectedRowCount()==1)
                 {
                       netamount();
                      
                      String  id =idtxt.getText();
                      String clientname=clientnametxt.getText();
                       String itemname=itemnametxt.getText();
                      String price= pricetxt.getText();
                       String quantity=quantitytxt.getText();
                      String detail= detailtxt.getText();
                      String netAmount=amounttxt.getText();
                    t1.setValueAt(id,purchasetbl.getSelectedRow(),0);
                    t1.setValueAt(clientname,purchasetbl.getSelectedRow(),1);
                    t1.setValueAt(itemname,purchasetbl.getSelectedRow(),2);
                    t1.setValueAt(price,purchasetbl.getSelectedRow(),3);
                    t1.setValueAt(quantity,purchasetbl.getSelectedRow(),4);
                    t1.setValueAt(netAmount,purchasetbl.getSelectedRow(),5);
                    t1.setValueAt(detail,purchasetbl.getSelectedRow(),6);
                       add();
                    savefile();
                     blank();
                      JOptionPane.showMessageDialog(this, "updated!");
                        }
                 else
                 {
                     if(purchasetbl.getRowCount()==0)
                     {
                         JOptionPane.showMessageDialog(this,"no values in table");
                     }
                     else
                     {
                           JOptionPane.showMessageDialog(this,"select a single row to update");

                     }
    
                         
                 }
    }
    private void addbutton() throws IOException
    {
           try {
       
    
          autoId();
      } catch (IOException ex) {
          Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
      }
          
         if (clientnametxt.getText().equals("")||itemnametxt.getText().equals("")||pricetxt.getText().equals("")||quantitytxt.getText().equals("")||detailtxt.getText().equals(""))
        { 
            JOptionPane.showMessageDialog(this,"Please Enter All Data");
        }
        else
        {
  
        String number=String.valueOf(id);
                String data[] ={number,clientnametxt.getText(),itemnametxt.getText(),pricetxt.getText(),quantitytxt.getText(),amounttxt.getText(),detailtxt.getText(),datetxt.getText()};
                       DefaultTableModel t2=(DefaultTableModel)purchasetbl.getModel();
                          t2.addRow(data);
                       JOptionPane.showMessageDialog(this,"Data Added Successfully");
                
                       add();
                         blank();
      
        savefile();
        }
       
        
    }
    private void deletebutton() throws IOException
    {
      DefaultTableModel model=(DefaultTableModel)purchasetbl.getModel();
      
           try{
               if(purchasetbl.getSelectedRow()!=-1)
               {
               
                model.removeRow(purchasetbl.getSelectedRow());
           }
                blank();
           }
           catch (Exception ex)
           {
               JOptionPane.showMessageDialog(null, ex);
           }
          savefile();  
    }
  
    private void newid(int num)
  {
       try{
        File file=new File("Resources/id2.txt");
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
      private void autoId() throws IOException
   {  
      File tempFile = new File("Resources/id2.txt");
         boolean exists = tempFile.exists();
         if(exists==false)
         {
             
             newid(0);
         }
         else{
            System.out.println("id is already there");
         }
       String content = new String(Files.readAllBytes(Paths.get("Resources/id2.txt")));
       num=Integer.parseInt(content);  
       id=num+1;
        File myObj = new File("Resources/id2.txt"); 
            myObj.delete();
          newid(id);
   }
   
    private void netamount()
    {
        int one=Integer.parseInt(pricetxt.getText());
       int two=Integer.parseInt(quantitytxt.getText());
       String total=String.valueOf(one*two);
      amounttxt.setText(total);
    }
    private void add()
    {
         int total=0;
        for(int i=0; i<purchasetbl.getRowCount();i++)
        {
            total=total+Integer.parseInt(purchasetbl.getValueAt(i, 5).toString().trim());
        }
        
        netamounttxt.setText(Integer.toString(total).trim());
    }
    private void showdate()
    {
         Date d=new Date();
        SimpleDateFormat d1=new SimpleDateFormat("dd/MM/yyyy");
        datetxt.setText(d1.format(d));
    }

     
  public void blank()
  {
       idtxt.setText("");
       clientnametxt.setText("");
       itemnametxt.setText("");
        pricetxt.setText("");
       quantitytxt.setText("");
        detailtxt.setText("");
        amounttxt.setText("");
  }
 
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idlbl = new javax.swing.JLabel();
        clientnamelbl = new javax.swing.JLabel();
        itmnamelbl = new javax.swing.JLabel();
        pricelbl = new javax.swing.JLabel();
        quantitylbl = new javax.swing.JLabel();
        detaillbl = new javax.swing.JLabel();
        idtxt = new javax.swing.JTextField();
        clientnametxt = new javax.swing.JTextField();
        itemnametxt = new javax.swing.JTextField();
        pricetxt = new javax.swing.JTextField();
        quantitytxt = new javax.swing.JTextField();
        detailtxt = new javax.swing.JTextField();
        addbtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        closebtn = new javax.swing.JButton();
        netamountlbl = new javax.swing.JLabel();
        netamounttxt = new javax.swing.JTextField();
        amountlbl = new javax.swing.JLabel();
        datetxt = new javax.swing.JTextField();
        amounttxt = new javax.swing.JTextField();
        clearbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        addpurchase = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchasetbl = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        texttbl = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idlbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idlbl.setText("ID");
        getContentPane().add(idlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        clientnamelbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        clientnamelbl.setText("Client Name");
        getContentPane().add(clientnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        itmnamelbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        itmnamelbl.setText("Item Name");
        getContentPane().add(itmnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        pricelbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pricelbl.setText("Price");
        getContentPane().add(pricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        quantitylbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quantitylbl.setText("Quantity");
        getContentPane().add(quantitylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        detaillbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        detaillbl.setText("Detail");
        getContentPane().add(detaillbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        idtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 117, -1));

        clientnametxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        clientnametxt.setNextFocusableComponent(itemnametxt);
        getContentPane().add(clientnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 118, -1));

        itemnametxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemnametxt.setNextFocusableComponent(pricetxt);
        itemnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemnametxtActionPerformed(evt);
            }
        });
        getContentPane().add(itemnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 118, -1));

        pricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pricetxt.setNextFocusableComponent(quantitytxt);
        getContentPane().add(pricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 119, -1));

        quantitytxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        quantitytxt.setNextFocusableComponent(detailtxt);
        quantitytxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantitytxtFocusLost(evt);
            }
        });
        getContentPane().add(quantitytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 119, -1));

        detailtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detailtxt.setNextFocusableComponent(addbtn);
        getContentPane().add(detailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 117, -1));

        addbtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\plus (2).png")); // NOI18N
        addbtn.setText("Add");
        addbtn.setNextFocusableComponent(editbtn);
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
        getContentPane().add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 77, 30));

        editbtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\updated.png")); // NOI18N
        editbtn.setText("Edit");
        editbtn.setNextFocusableComponent(deletebtn);
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
        getContentPane().add(editbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 76, 30));

        deletebtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\minus.png")); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setNextFocusableComponent(clearbtn);
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
        getContentPane().add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 90, 30));

        closebtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        closebtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\close.png")); // NOI18N
        closebtn.setText("Close");
        closebtn.setNextFocusableComponent(idtxt);
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
        getContentPane().add(closebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 80, 30));

        netamountlbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        netamountlbl.setText("Total Amount");
        getContentPane().add(netamountlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, -1, 25));

        netamounttxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(netamounttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 122, 20));

        amountlbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        amountlbl.setText("Net Amount");
        getContentPane().add(amountlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        datetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(datetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 117, -1));

        amounttxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(amounttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 119, -1));

        clearbtn.setIcon(new javax.swing.ImageIcon("D:\\Restaurant Managment System Oop Project\\RMS\\Resources\\remove.png")); // NOI18N
        clearbtn.setText("Clear");
        clearbtn.setNextFocusableComponent(closebtn);
        clearbtn.setPreferredSize(new java.awt.Dimension(75, 25));
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });
        getContentPane().add(clearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 89, 30));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        addpurchase.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        addpurchase.setForeground(new java.awt.Color(255, 255, 255));
        addpurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addpurchase.setText("Add Purchase");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addpurchase, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addpurchase, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        datelbl.setText("Date");
        getContentPane().add(datelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        purchasetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Client name", "Item name", "price", "quantity", "net Amount", "Detail", "Date"
            }
        ));
        purchasetbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchasetblMouseClicked(evt);
            }
        });
        purchasetbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purchasetblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(purchasetbl);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 710, 280));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(153, 153, 153));

        texttbl.setBackground(new java.awt.Color(102, 102, 102));
        texttbl.setColumns(20);
        texttbl.setFont(new java.awt.Font("Monospaced", 0, 3)); // NOI18N
        texttbl.setForeground(new java.awt.Color(255, 255, 255));
        texttbl.setRows(5);
        jScrollPane2.setViewportView(texttbl);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 10, 10));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 1000, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemnametxtActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
      try {
          // TODO add your handling code here:
          editbutton();
          clientnametxt.requestFocus();
      } catch (IOException ex) {
          Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_editbtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
   
      try {          
          addbutton();
          clientnametxt.requestFocus();
      } catch (IOException ex) {
          Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
      }
                     
    }//GEN-LAST:event_addbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
      try {
          // TODO add your handling code here:
          deletebutton();
          clientnametxt.requestFocus();
          clientnametxt.requestFocus();
      } catch (IOException ex) {
          Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addbtnKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                addbutton();
                clientnametxt.requestFocus();
            } catch (IOException ex) {
                Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addbtnKeyPressed

    private void editbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editbtnKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                editbutton();
                clientnametxt.requestFocus();
            } catch (IOException ex) {
                Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editbtnKeyPressed

    private void deletebtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deletebtnKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                deletebutton();
                clientnametxt.requestFocus();
            } catch (IOException ex) {
                Logger.getLogger(Addpurchase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deletebtnKeyPressed

    private void closebtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closebtnKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }//GEN-LAST:event_closebtnKeyPressed

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closebtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed

        blank();
    }//GEN-LAST:event_clearbtnActionPerformed

    private void purchasetblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchasetblMouseClicked
       
         int row[]=this.purchasetbl.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
        int modelRow = purchasetbl.convertRowIndexToModel(row[i]);
        DefaultTableModel m1=(DefaultTableModel)purchasetbl.getModel();
        String id=m1.getValueAt(modelRow,0).toString();
        String clientname=m1.getValueAt(modelRow,1).toString();
        String itemname=m1.getValueAt(modelRow,2).toString();
        String price=m1.getValueAt(modelRow,3).toString();
        String quantity=m1.getValueAt(modelRow,4).toString();
        String amount=m1.getValueAt(modelRow,5).toString();
        String detail=m1.getValueAt(modelRow,6).toString();
        idtxt.setText(id);
        clientnametxt.setText(clientname);
        itemnametxt.setText(itemname);
        pricetxt.setText(price);
        quantitytxt.setText(quantity);
        amounttxt.setText(amount);
        detailtxt.setText(detail);
        }
    }//GEN-LAST:event_purchasetblMouseClicked

    private void purchasetblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purchasetblKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        int row[]=this.purchasetbl.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
        int modelRow = purchasetbl.convertRowIndexToModel(row[i]);
        DefaultTableModel m1=(DefaultTableModel)purchasetbl.getModel();
        String id=m1.getValueAt(modelRow,0).toString();
        String clientname=m1.getValueAt(modelRow,1).toString();
        String itemname=m1.getValueAt(modelRow,2).toString();
        String price=m1.getValueAt(modelRow,3).toString();
        String quantity=m1.getValueAt(modelRow,4).toString();
        String amount=m1.getValueAt(modelRow,5).toString();
        String detail=m1.getValueAt(modelRow,6).toString();
        idtxt.setText(id);
        clientnametxt.setText(clientname);
        itemnametxt.setText(itemname);
        pricetxt.setText(price);
        quantitytxt.setText(quantity);
        amounttxt.setText(amount);
        detailtxt.setText(detail);
        }
           }
    }//GEN-LAST:event_purchasetblKeyPressed

    private void quantitytxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantitytxtFocusLost
              netamount();
    }//GEN-LAST:event_quantitytxtFocusLost

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
            java.util.logging.Logger.getLogger(Addpurchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addpurchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addpurchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addpurchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addpurchase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JLabel addpurchase;
    private javax.swing.JLabel amountlbl;
    private javax.swing.JTextField amounttxt;
    private javax.swing.JButton clearbtn;
    private javax.swing.JLabel clientnamelbl;
    private javax.swing.JTextField clientnametxt;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel datelbl;
    private javax.swing.JTextField datetxt;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel detaillbl;
    private javax.swing.JTextField detailtxt;
    private javax.swing.JButton editbtn;
    private javax.swing.JLabel idlbl;
    private javax.swing.JTextField idtxt;
    private javax.swing.JTextField itemnametxt;
    private javax.swing.JLabel itmnamelbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel netamountlbl;
    private javax.swing.JTextField netamounttxt;
    private javax.swing.JLabel pricelbl;
    private javax.swing.JTextField pricetxt;
    private javax.swing.JTable purchasetbl;
    private javax.swing.JLabel quantitylbl;
    private javax.swing.JTextField quantitytxt;
    private javax.swing.JTextArea texttbl;
    // End of variables declaration//GEN-END:variables
}
