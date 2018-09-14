/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorbike_rental_system;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;

/**
 *
 * @author firstx
 */
public class F_Manage_Rental extends javax.swing.JFrame {

    DBCollection coll;
    DB db;
    public F_Manage_Rental() {
        initComponents();
        showData();
    }

    public void showData(){
       try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
           
            DBCursor cursor = coll.find();
           tb_rental_moter.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสการเช่า", "รหัสลูกค้า","วันที่เช่า","ค่าเช่า","รหัสรถ"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String ID = (String)obj.get("RID");
                String CusID = (String)obj.get("CusID");
                Date Date = (Date)obj.get("DateR");
                Double SumR = (Double)obj.get("SumR");
                String MotorID = (String)obj.get("MotorID");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, CusID, Date,SumR,MotorID});
            }
             tb_rental_moter.setModel(model);
             TableColumnModel columnModel = tb_rental_moter.getColumnModel();
             columnModel.getColumn(0).setPreferredWidth(20);
             columnModel.getColumn(1).setPreferredWidth(50);
             columnModel.getColumn(2).setPreferredWidth(250);
             columnModel.getColumn(3).setPreferredWidth(20);
             columnModel.getColumn(4).setPreferredWidth(100);
              tb_rental_moter.setRowHeight(30);
            
            


        } catch (Exception e) {
            System.out.println(e);
        }
   }
   public void search(){
       try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
           
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("RID", tx_se.getText());
            DBCursor cursor = coll.find(whereQuery); 
            tb_rental_moter.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสการเช่า", "รหัสลูกค้า","วันที่เช่า","ค่าเช่า","รหัสรถ"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String ID = (String)obj.get("RID");
                String CusID = (String)obj.get("CusID");
                Date Date = (Date)obj.get("DateR");
                Double SumR = (Double)obj.get("SumR");
                String MotorID = (String)obj.get("MotorID");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, CusID, Date,SumR,MotorID});
            }
             tb_rental_moter.setModel(model);
             TableColumnModel columnModel = tb_rental_moter.getColumnModel();
             columnModel.getColumn(0).setPreferredWidth(20);
             columnModel.getColumn(1).setPreferredWidth(50);
             columnModel.getColumn(2).setPreferredWidth(250);
             columnModel.getColumn(3).setPreferredWidth(20);
             columnModel.getColumn(4).setPreferredWidth(100);
             tb_rental_moter.setRowHeight(30);
            


        } catch (Exception e) {
            System.out.println(e);
        }
       
   }
   public void DeleteCus(){
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
            
            String selectid = tx_cusid.getText();
            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("ID", selectid);
            coll.remove(searchQuery);
            JOptionPane.showMessageDialog(this, "successful!");
            clear();
            showData();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   public void Delete(){
        try {
            String selectid = tx_rentalid.getText();
            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("RID", selectid);
            coll.remove(searchQuery);           
            clear();
            showData();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    public void EditStatus(){
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
            
            BasicDBObject query = new BasicDBObject();
            query.put("ID", tx_MID.getText()); 
        
            BasicDBObject newDocument = new BasicDBObject();            
            newDocument.put("Status", "ไม่ว่าง");
            
        
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);
            coll.update(query, updateObject);           
            clear();
            showData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void EditStatusDel(){
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
            
            BasicDBObject query = new BasicDBObject();
            query.put("ID", tx_MID.getText()); 
        
            BasicDBObject newDocument = new BasicDBObject();            
            newDocument.put("Status", "ว่าง");
            
        
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);
            coll.update(query, updateObject);           
            clear();
            showData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void InsertM(){
         try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
            Date now = new Date();

                   
            BasicDBObject document = new BasicDBObject();
            document.put("RID", tx_rentalid.getText());
            document.put("CusID", tx_cusid.getText());
            document.put("DateR", now);
            document.put("Return", "ยังไม่ได้นำมาคืน");
            document.put("SumR", Double.parseDouble(tx_sumR.getText()));
            document.put("fine", 0.0);
            document.put("MotorID", tx_MID.getText());
            coll.insert(document);
            showData();

                    
        }catch (Exception e) {
            
        }  
    }
    public void InsertCus(){
         try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
            
            BasicDBObject document = new BasicDBObject();
            document.put("ID", tx_cusid.getText());
            document.put("name", tx_name.getText());
            document.put("lname", tx_lname.getText());
            document.put("call", tx_call.getText());
            document.put("IDcard", tx_IDcard.getText());
            coll.insert(document);
            showData();
            
                    
        }catch (Exception e) {
            
        }  
    }
    public  void clear(){
        tx_cusid.setText("");
        tx_rentalid.setText("");
        tx_call.setText("");
        tx_name.setText("");
        tx_lname.setText("");
        tx_IDcard.setText("");
        tx_MID.setText("");
       
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_rental_moter = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tx_cusid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tx_name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_lname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tx_call = new javax.swing.JTextField();
        tx_se = new javax.swing.JTextField();
        tx_rentalid = new javax.swing.JTextField();
        bt_add = new javax.swing.JButton();
        bt_del = new javax.swing.JButton();
        tx_dateren = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bt_se = new javax.swing.JButton();
        bt_all = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tx_sumR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tx_MID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tx_IDcard = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bt_get = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bt_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tb_rental_moter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_rental_moter);

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel2.setText("รหัสลูกค้า :");

        tx_cusid.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tx_cusid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx_cusidKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel3.setText("ชื่อ :");

        tx_name.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel4.setText("นามสกุล :");

        tx_lname.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel5.setText("เบอร์โทร :");

        jLabel6.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel6.setText("วันที่เช่า :");

        jLabel8.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel8.setText("รหัสการเช่า-คืน :");

        tx_call.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        tx_se.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        tx_rentalid.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        bt_add.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        bt_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/save.png"))); // NOI18N
        bt_add.setText("เพิ่ม");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        bt_del.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        bt_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        bt_del.setText("ลบ");
        bt_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delActionPerformed(evt);
            }
        });

        tx_dateren.setEditable(false);
        tx_dateren.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tx_dateren.setText("          วันที่ปัจจุบัน");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N

        bt_se.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_se.setText("ค้นหา");
        bt_se.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_seActionPerformed(evt);
            }
        });

        bt_all.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_all.setText("ดูทั้งหมด");
        bt_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_allActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel9.setText("ค่าเช่า");

        tx_sumR.setEditable(false);
        tx_sumR.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tx_sumR.setText("200");

        jLabel10.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel10.setText("รหัสรถ :");

        tx_MID.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel11.setText("เลขบัตรประชาชน :");

        tx_IDcard.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

        bt_get.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_get.setText("ดึงข้อมูล");
        bt_get.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_getActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(tx_se, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bt_se)
                        .addGap(12, 12, 12)
                        .addComponent(bt_all))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(tx_IDcard, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(bt_add)
                        .addGap(51, 51, 51)
                        .addComponent(bt_del, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(tx_cusid, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_get))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel4))
                                    .addComponent(jLabel5))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tx_name, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_call, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(12, 12, 12)
                                .addComponent(tx_rentalid, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel10)))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tx_dateren, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_sumR, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_MID, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel14)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tx_se, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_se)
                    .addComponent(bt_all))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tx_cusid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(tx_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(tx_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(tx_call, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(bt_get))
                            .addComponent(tx_rentalid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel9)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tx_dateren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(tx_sumR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(tx_MID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel14)))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(tx_IDcard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_add)
                            .addComponent(bt_del)))))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("การเช่ารถจักรยานยนต์");

        bt_back.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back-button-icon-png-13.jpg"))); // NOI18N
        bt_back.setText("ย้อนกลับ");
        bt_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(bt_back)
                .addGap(180, 180, 180)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_back)
                    .addComponent(jLabel1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tx_cusidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_cusidKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_cusidKeyReleased
    boolean mcheck = false; 
    public  void ckmotor(){
            try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
            
            BasicDBObject andQuery = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject("ID", tx_MID.getText()));
            obj.add(new BasicDBObject("Status", "ไม่ว่าง"));
            andQuery.put("$and", obj);
            
            DBCursor cursor = coll.find(andQuery);
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
                JLabel text = new JLabel("รถคันนี้ไม่ว่าง");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                mcheck=true;
                tx_MID.setText("");
                break;
	}
            
                    
        }catch (Exception e) {
            
        }  
        
    } 
    boolean ckmotor = false;
    public  void ckmoter(){ //เช็คว่ารถคันนี้ไม่มี
        try {
            
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
            
            BasicDBObject Query = new BasicDBObject();
	    Query.put("ID", tx_MID.getText());            
            DBCursor cursor = coll.find(Query);
            
            while (cursor.hasNext()) {               
                ckmotor=true;
                break;
            }
            if(!ckmotor){
                JLabel text = new JLabel("ไม่มีรถคันนี้ในระบบ");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                tx_MID.setText("");
            }
            
            
            
        } catch (Exception e) {
            
        }
        
    }
    boolean recheck = false; 
    public  void ckrental(){
        try{
            recheck = false; 
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("RID", tx_rentalid.getText());
            DBCursor cursor = coll.find(whereQuery);
            while(cursor.hasNext()) {
                JLabel text = new JLabel("รหัสการเช่าคืนซ้ำโปรดกรอกใหม่");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                tx_rentalid.setText("");
                recheck=true;
                break;
            }
            
                    
        }catch (Exception e) {
            
        }  
        
    }
    public void get(){
         try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
           
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("ID", tx_cusid.getText());
            DBCursor cursor = coll.find(whereQuery);  
            
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();                
                tx_name.setText((String)obj.get("name"));
                tx_lname.setText((String)obj.get("lname"));
                tx_call.setText((String)obj.get("call"));
                tx_IDcard.setText((String)obj.get("IDcard"));                
                ObjectId id = (ObjectId)obj.get("_id");
                break;  
            }           

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    boolean ckget = false;
    public void ckget(){
        try {
            ckget = false;
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
            
            BasicDBObject Query = new BasicDBObject();
	    Query.put("ID", tx_cusid.getText());            
            DBCursor cursor = coll.find(Query);
            
            while (cursor.hasNext()) {               
                ckget=true;
                break;
            }
            if(!ckget){
                JLabel text = new JLabel("ไม่พบรหัสลูกค้านี้!");
                tx_MID.setText("");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                
            }
 
        } catch (Exception e) {
            
        }
        
    }
    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
        // TODO add your handling code here:
        try {
            /*MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("ID", tx_cusid.getText());
            DBCursor cursor = coll.find(whereQuery);
            boolean ck = false;
            while(cursor.hasNext()) {
                JLabel text = new JLabel("มีรหัสลูกค้านี้อยู่ในระบบแล้ว");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                tx_cusid.setText("");
                clear();
                ck = true;
                break;
            }*/
            ckmotor();
            ckrental();
            ckmoter();
            if(!mcheck&&!recheck&&ckmotor){
                InsertM();
                InsertCus();  
                EditStatus();
                JLabel text = new JLabel("เพิ่มข้อมูลการเช่าเรียบร้อย");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text);           
                clear();
                
            }

        } catch (Exception e) {

        }

    }//GEN-LAST:event_bt_addActionPerformed

    private void bt_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delActionPerformed
        // TODO add your handling code here:
        if(tx_rentalid.getText().equals("")){
            JLabel text = new JLabel("กรุณากรอกรหัสการเช่าที่ต้องการลบ");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);
        }else{
            Delete();
            DeleteCus();
            EditStatusDel();
        }

    }//GEN-LAST:event_bt_delActionPerformed

    private void bt_seActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_seActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_bt_seActionPerformed

    private void bt_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_allActionPerformed
        // TODO add your handling code here:
        showData();
        tx_se.setText("");
    }//GEN-LAST:event_bt_allActionPerformed

    private void bt_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_backActionPerformed
        // TODO add your handling code here:
        new F_Rental_Return().show();
        dispose();
    }//GEN-LAST:event_bt_backActionPerformed

    private void bt_getActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_getActionPerformed
        // TODO add your handling code here:
        if(!tx_cusid.getText().equals("")){
            ckget();
        }
        if(ckget&&!tx_cusid.getText().equals("")){
            get();
        }else {
            JLabel text = new JLabel("กรุณากรอกรหัสลูกค้าที่ต้องการดึงข้อมูล");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);
            clear();
        }
        
    }//GEN-LAST:event_bt_getActionPerformed

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
            java.util.logging.Logger.getLogger(F_Manage_Rental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Rental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Rental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Rental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Manage_Rental().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_all;
    private javax.swing.JButton bt_back;
    private javax.swing.JButton bt_del;
    private javax.swing.JButton bt_get;
    private javax.swing.JButton bt_se;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_rental_moter;
    private javax.swing.JTextField tx_IDcard;
    private javax.swing.JTextField tx_MID;
    private javax.swing.JTextField tx_call;
    private javax.swing.JTextField tx_cusid;
    private javax.swing.JTextField tx_dateren;
    private javax.swing.JTextField tx_lname;
    private javax.swing.JTextField tx_name;
    private javax.swing.JTextField tx_rentalid;
    private javax.swing.JTextField tx_se;
    private javax.swing.JTextField tx_sumR;
    // End of variables declaration//GEN-END:variables
}
