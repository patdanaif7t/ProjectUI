
package motorbike_rental_system;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.Font;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;


public class F_Manage_Return extends javax.swing.JFrame {

   DBCollection coll;
   DB db;
    
   DBCollection coll2;
   DB db2;
   
    public F_Manage_Return() {
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
            String[] columnNames = {"รหัสการเช่า","วันที่เช่า","ค่าเช่า","รหัสรถ","สถานะการเช่า","ค่าปรับ"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String ID = (String)obj.get("RID");            
                Date Date = (Date)obj.get("DateR");
                Double SumR = (Double)obj.get("SumR");
                String MotorID = (String)obj.get("MotorID");
                String Return = (String)obj.get("Return");
                Double fine = (Double)obj.get("fine");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID,Date,SumR,MotorID,Return,fine});
            }
            
            
            tb_rental_moter.setModel(model);
             TableColumnModel columnModel = tb_rental_moter.getColumnModel();
             columnModel.getColumn(0).setPreferredWidth(100);
             columnModel.getColumn(1).setPreferredWidth(250);
             columnModel.getColumn(2).setPreferredWidth(50);
             columnModel.getColumn(3).setPreferredWidth(50);
             columnModel.getColumn(4).setPreferredWidth(100);
             tb_rental_moter.setRowHeight(30);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Return(){
        try {
            Date now = new Date();
            
            BasicDBObject query = new BasicDBObject();           
            query.put("RID", tx_RID.getText());   
            
            BasicDBObject newDocument = new BasicDBObject();            
            newDocument.put("Return", "คืนแล้ว");
            
            if(tx_fine.getText().isEmpty()){
                newDocument.put("fine", 0.0);
            }else{
                newDocument.put("fine", Double.parseDouble(tx_fine.getText()));
            }   
            
            newDocument.put("DateReturn", now);
            
           
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);
            
            coll.update(query, updateObject);
            
            MongoClient mongo = new MongoClient("localhost", 27017);
            db2 = mongo.getDB("admin");
            coll2 = db2.getCollection("motorcycle");
            
            BasicDBObject query2 = new BasicDBObject();
            query2.put("ID", tx_motorid.getText()); 
        
            BasicDBObject newDocument2 = new BasicDBObject();            
            newDocument2.put("Status", "ว่าง");
            
        
            BasicDBObject updateObject2 = new BasicDBObject();
            updateObject2.put("$set", newDocument2);
            
            coll2.update(query2, updateObject2);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    boolean ckidRe = false;
    public void ckidRe(){ //เช็ครหัสการเช่าว่ากรอกถูกต้องหรือไม่
        try {
             MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
            
            BasicDBObject Query = new BasicDBObject();
	    Query.put("RID", tx_RID.getText());            
            DBCursor cursor = coll.find(Query);
            
            while (cursor.hasNext()) {               
                ckidRe=true;
                break;
            }
            if(!ckidRe){
                JLabel text = new JLabel("ไม่มีรหัสการเช่า-คืนนี้!");
                text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
                JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                tx_RID.setText("");
            }
        } catch (Exception e) {
        }
        
    }
    public void get(){ //ดึง ID รถมาลงช่อง 
        try {
             MongoClient mongo = new MongoClient("localhost", 27017); //เชื่อมต่อฐานข้อมูล
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
            
            BasicDBObject Query = new BasicDBObject(); //เอาไว้ล็อคเป้า จะดูข้อมูลที่อยู่ใน RID
	    Query.put("RID", tx_RID.getText());      
            DBCursor cursor = coll.find(Query);
            
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String MID = (String)obj.get("MotorID");    //ดึงไอดีรถเก็บไว้ใน String MID
                tx_motorid.setText(MID); //เอามาใส่ใน tx_motorid
                break;
            }
        } catch (Exception e) {
        }
        
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bt_back = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_rental_moter = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tx_RID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tx_motorid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_fine = new javax.swing.JTextField();
        bt_return = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bt_get = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("การคืนรถจักรยานยนต์");

        bt_back.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back-button-icon-png-13.jpg"))); // NOI18N
        bt_back.setText("ย้อนกลับ");
        bt_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(bt_back)
                .addGap(167, 167, 167)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bt_back))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        tb_rental_moter.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tb_rental_moter.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_rental_moter);

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel2.setText("รหัสการเช่า-คืน :");

        tx_RID.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_RID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_RIDActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel3.setText("รหัสรถ :");

        tx_motorid.setEditable(false);
        tx_motorid.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_motorid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_motoridActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel4.setText("ค่าปรับ :");

        tx_fine.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N

        bt_return.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        bt_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/shopping-list.png"))); // NOI18N
        bt_return.setText("คืนแล้ว");
        bt_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_returnActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/asterisk.png"))); // NOI18N

        bt_get.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        bt_get.setText("ดึงข้อมูล");
        bt_get.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_getActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tx_motorid, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tx_fine)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_RID, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_get)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_return)
                        .addGap(47, 47, 47))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tx_RID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_get)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_return)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tx_motorid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_fine, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_backActionPerformed
        // TODO add your handling code here:
        new F_Rental_Return().show();
        dispose();
    }//GEN-LAST:event_bt_backActionPerformed

    private void tx_RIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_RIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_RIDActionPerformed

    private void bt_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_returnActionPerformed
        // TODO add your handling code here:        
        if(!tx_RID.getText().equals("")){ //เช็คว่ารหัสมีอยู่จริงไหม เราก็ต้องเช็คก่อนว่ามันว่างไหม ถ้าไม่ว่างถึงจะทำ แต่ถ้าว่างก็จะไปทำ if สุดท้าย
            ckidRe();
        }
        if(!tx_RID.getText().equals("")&&!tx_motorid.getText().equals("")&&ckidRe){
            Return(); 
            
            JLabel text = new JLabel("ทำการคืนรถเรียบร้อยแล้ว");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text);
            
            tx_motorid.setText("");
            tx_RID.setText("");
            tx_fine.setText("");
            showData();
            
        }else if(tx_RID.getText().equals("")){
            JLabel text = new JLabel("กรุณากรอกรหัสการเช่า-คืน!");
            text.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, text, "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
        }
              
    }//GEN-LAST:event_bt_returnActionPerformed

    private void tx_motoridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_motoridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_motoridActionPerformed

    private void bt_getActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_getActionPerformed
        // TODO add your handling code here:
        ckidRe();
        if(ckidRe){
            get();
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
            java.util.logging.Logger.getLogger(F_Manage_Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Manage_Return().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_back;
    private javax.swing.JButton bt_get;
    private javax.swing.JButton bt_return;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_rental_moter;
    private javax.swing.JTextField tx_RID;
    private javax.swing.JTextField tx_fine;
    private javax.swing.JTextField tx_motorid;
    // End of variables declaration//GEN-END:variables
}
