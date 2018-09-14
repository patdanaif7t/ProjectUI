
package motorbike_rental_system;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static motorbike_rental_system.Login.c;
import org.bson.types.ObjectId;


public class F_Manage_Motor extends javax.swing.JFrame {

    DBCollection coll;
    DB db;
    public F_Manage_Motor() {
        initComponents();
        showData();
    }

   public void showData(){
       try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
           
            DBCursor cursor = coll.find();
            tb_moter.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสรถ", "ยี่ห้อ","รุ่น","สี","ทะเบียน","จังหวัด"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String brand = (String)obj.get("brand");
                String gen = (String)obj.get("gen");
                String color = (String)obj.get("color");
                String license = (String)obj.get("license");
                String province = (String)obj.get("province");
                String ID = (String)obj.get("ID");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, brand, gen,color,license,province });
            }
            tb_moter.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
   }
   public void search(){
       try {
            // เชื่อมต่อ DB
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
           //ล็อคเป้า
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("ID", tx_se.getText());
            DBCursor cursor = coll.find(whereQuery); 
            //เซ็ตตาราง
            tb_moter.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสรถ", "ยี่ห้อ","รุ่น","สี","ทะเบียน","จังหวัด"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            //เปลี่ยนแปลงตาราง
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String brand = (String)obj.get("brand");
                String gen = (String)obj.get("gen");
                String color = (String)obj.get("color");
                String license = (String)obj.get("license");
                String province = (String)obj.get("province");
                String ID = (String)obj.get("ID");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, brand, gen,color,license,province });
            }
             tb_moter.setModel(model);
             
             //ปรับขนาดตาราง
             TableColumnModel columnModel = tb_moter.getColumnModel();
             tb_moter.setRowHeight(30);
            
            


        } catch (Exception e) {
            System.out.println(e);
        }
       
   }
   public void Delete(){ //ใช้ลบ
        try {
            String selectid = tx_id.getText(); //ล็อคเป้า ID ที่จะลบ
            
            //เอาไอดีที่เก็บมา เอาไปลบ
            BasicDBObject DeleteQuery = new BasicDBObject();
            DeleteQuery.put("ID", selectid);
            coll.remove(DeleteQuery);
            
            JOptionPane.showMessageDialog(this, "successful!");
            tx_id.setText("");
            tx_brand.setText("");
            tx_gen.setText("");
            tx_color.setText("");
            tx_license.setText("");
            tx_prov.setText("");
            showData(); //เป็นการรีเฟรส
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Edit(){
        try {
            //ล็อคเป้า
            BasicDBObject query = new BasicDBObject();
            query.put("ID", tx_id.getText()); 
        
            //ข้อมูลใหม่ที่จะแก้ไข
            BasicDBObject newDocument = new BasicDBObject();            
            newDocument.put("brand", tx_brand.getText());
            newDocument.put("gen", tx_gen.getText());
            newDocument.put("color", tx_color.getText());
            newDocument.put("license", tx_license.getText());
            newDocument.put("province", tx_prov.getText());
        
            //เก็บคำสั่ง
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);
            
            //ใช้คำสั่ง
            coll.update(query, updateObject);
            
            JOptionPane.showMessageDialog(this, "Success!");
            tx_id.setText("");
            tx_brand.setText("");
            tx_gen.setText("");
            tx_color.setText("");
            tx_license.setText("");
            tx_prov.setText("");
            showData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Insert(){
         try{
            //เก็บข้อมูลที่เพิ่ม
            BasicDBObject document = new BasicDBObject();
            document.put("ID", tx_id.getText());
            document.put("brand", tx_brand.getText());
            document.put("gen", tx_gen.getText());
            document.put("color", tx_color.getText());
            document.put("license", tx_license.getText());
            document.put("province", tx_prov.getText());
            
            coll.insert(document); //คำสั่งเพิ่ม
            
            JLabel text = new JLabel("เพิ่มข้อมูลเรียบร้อย");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);  
            
            tx_id.setText("");
            tx_brand.setText("");
            tx_gen.setText("");
            tx_color.setText("");
            tx_license.setText("");
            tx_prov.setText("");
            showData();
                    
        }catch (Exception e) {
            
        }  
    }
    public  void clear(){
        tx_id.setText("");
        tx_brand.setText("");
        tx_gen.setText("");
        tx_color.setText("");
        tx_license.setText("");
        tx_prov.setText("");
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_moter = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tx_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tx_brand = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_gen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tx_color = new javax.swing.JTextField();
        tx_se = new javax.swing.JTextField();
        tx_license = new javax.swing.JTextField();
        bt_add = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_del = new javax.swing.JButton();
        tx_prov = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bt_se = new javax.swing.JButton();
        bt_all = new javax.swing.JButton();
        bt_get = new javax.swing.JButton();
        bt_get1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bt_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tb_moter.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tb_moter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tb_moter);

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel2.setText("รหัสรถ :");

        tx_id.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tx_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx_idKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel3.setText("ยี่ห้อ :");

        tx_brand.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel4.setText("รุ่น :");

        tx_gen.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel5.setText("สี :");

        jLabel6.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel6.setText("จังหวัด :");

        jLabel8.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel8.setText("ทะเบียน :");

        tx_color.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        tx_se.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        tx_license.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

        bt_add.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/save.png"))); // NOI18N
        bt_add.setText("เพิ่ม");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        bt_edit.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/wrench.png"))); // NOI18N
        bt_edit.setText("แก้ไข");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_del.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        bt_del.setText("ลบ");
        bt_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delActionPerformed(evt);
            }
        });

        tx_prov.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

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

        bt_get.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_get.setText("ดึงข้อมูล");
        bt_get.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_getActionPerformed(evt);
            }
        });

        bt_get1.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_get1.setText("ล้างข้อมูล");
        bt_get1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_get1ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

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
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10)
                        .addGap(17, 17, 17)
                        .addComponent(bt_get)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(tx_license, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(tx_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(bt_get1)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(tx_prov, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35)
                        .addComponent(tx_gen, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(bt_add)
                        .addGap(44, 44, 44)
                        .addComponent(bt_del))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43)
                        .addComponent(tx_color, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(bt_edit)))
                .addGap(10, 10, 10))
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
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10))
                    .addComponent(bt_get)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tx_license, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_get1)
                    .addComponent(jLabel6)
                    .addComponent(tx_prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tx_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_add)
                    .addComponent(bt_del)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tx_gen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_edit)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tx_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("หน้าจัดการรถจักรยานยนต์");

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
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_backActionPerformed
        // TODO add your handling code here:
        new F_Home_information().show();
        dispose();
    }//GEN-LAST:event_bt_backActionPerformed

    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
        // TODO add your handling code here:
       try {
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("ID", tx_id.getText());            
            DBCursor cursor = coll.find(whereQuery);          
            boolean ck = false; //ไว้เช็คว่าซ้ำไหม
            while(cursor.hasNext()) {               
               JLabel text = new JLabel("มีรหัสรถนี้อยู่ในระบบแล้ว");
               text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
               JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);                
               clear();
               ck = true;   //ถ้าซ้ำให้เป็นจริง
               break;
            }
            if(!ck){ //ถ้าไม่ซ้ำให้เพิ่มเข้าไป
                 Insert();
            }
            
        } catch (Exception e) {
            
        }      
       
    }//GEN-LAST:event_bt_addActionPerformed

    private void bt_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delActionPerformed
        // TODO add your handling code here:
        if(tx_id.getText().equals("")){
            JLabel text = new JLabel("กรุณากรอก ID รถจักรยานยนต์");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);
        }else{
            Delete();
        }
        
    }//GEN-LAST:event_bt_delActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
        Edit();
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_seActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_seActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_bt_seActionPerformed

    private void bt_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_allActionPerformed
        // TODO add your handling code here:
        showData();
        tx_se.setText("");
    }//GEN-LAST:event_bt_allActionPerformed

    private void tx_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_idKeyReleased

    private void bt_getActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_getActionPerformed
        // TODO add your handling code here:
        if(tx_id.getText().equals("")){
            JLabel text = new JLabel("กรุณากรอก ID รถจักรยานยนต์ที่ต้องการดึงข้อมูล");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);
        }else{
            try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("motorcycle");
            
            boolean ck = false; //เช็คว่าไอดีรถนั้นถูกหรือไม่
            
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("ID", tx_id.getText());
            DBCursor cursor = coll.find(whereQuery);  
            
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                ck=true; //ถ้าถุกให้เปลี่ยนเป็นจริง
                tx_brand.setText((String)obj.get("brand"));
                tx_gen.setText((String)obj.get("gen"));
                tx_color.setText((String)obj.get("color"));
                tx_prov.setText((String)obj.get("province"));
                tx_license.setText((String)obj.get("license"));               
                break;
                
            }if(!ck){ //ถ้าไอดีรถไม่ถูก
                JLabel text = new JLabel("ID ไม่ถูกต้องกรุณากรอก ID รถจักรยานยนต์ใหม่");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text);
                tx_id.setText("");
            
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        }
    }//GEN-LAST:event_bt_getActionPerformed

    private void bt_get1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_get1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_bt_get1ActionPerformed

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
            java.util.logging.Logger.getLogger(F_Manage_Motor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Motor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Motor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Motor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Manage_Motor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_all;
    private javax.swing.JButton bt_back;
    private javax.swing.JButton bt_del;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_get;
    private javax.swing.JButton bt_get1;
    private javax.swing.JButton bt_se;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_moter;
    private javax.swing.JTextField tx_brand;
    private javax.swing.JTextField tx_color;
    private javax.swing.JTextField tx_gen;
    private javax.swing.JTextField tx_id;
    private javax.swing.JTextField tx_license;
    private javax.swing.JTextField tx_prov;
    private javax.swing.JTextField tx_se;
    // End of variables declaration//GEN-END:variables
}
