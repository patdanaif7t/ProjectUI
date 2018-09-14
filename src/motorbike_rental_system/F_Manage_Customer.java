
package motorbike_rental_system;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;


public class F_Manage_Customer extends javax.swing.JFrame {

    
    DBCollection coll;
    DB db;
    public F_Manage_Customer() {
        initComponents();
        showData();
    }

   public void showData(){
       try {
            // เชื่อมต่อ DB
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
           
            DBCursor cursor = coll.find(); //โยนเข้าไปใน cursor โดยเอาทั้งหมด
            tb_cus.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสลูกค้า", "ชื่อ","นามสกุล","เบอร์โทร","เลขบัตรประชาชน"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String ID = (String)obj.get("ID");
                String name = (String)obj.get("name");
                String lname = (String)obj.get("lname");
                String call = (String)obj.get("call");
                String IDcard = (String)obj.get("IDcard");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, name, lname,call,IDcard});
            }
             tb_cus.setModel(model);
            
            


        } catch (Exception e) {
            System.out.println(e);
        }
   }
   public void search(){
       try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("Customer");
           
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("name", tx_se.getText());
            DBCursor cursor = coll.find(whereQuery);                   
            tb_cus.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รหัสลูกค้า", "ชื่อ","นามสกุล","เบอร์โทร","เลขบัตรประชาชน"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                String ID = (String)obj.get("ID");
                String name = (String)obj.get("name");
                String lname = (String)obj.get("lname");
                String call = (String)obj.get("call");
                String IDcard = (String)obj.get("IDcard");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { ID, name, lname,call,IDcard});
            }
             tb_cus.setModel(model);
            
            


        } catch (Exception e) {
            System.out.println(e);
        }
       
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cus = new javax.swing.JTable();
        tx_se = new javax.swing.JTextField();
        bt_se = new javax.swing.JButton();
        bt_all = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tb_cus.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N
        tb_cus.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_cus);

        tx_se.setFont(new java.awt.Font("TH Sarabun New", 1, 18)); // NOI18N

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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_se, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_se)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_all)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_se, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_se)
                        .addComponent(bt_all)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 91, 701, 490));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("หน้าข้อมูลลูกค้า");

        jButton4.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back-button-icon-png-13.jpg"))); // NOI18N
        jButton4.setText("ย้อนกลับ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton4)
                .addGap(167, 167, 167)
                .addComponent(jLabel1)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 90));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new F_Home_information().show();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bt_seActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_seActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_bt_seActionPerformed

    private void bt_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_allActionPerformed
        // TODO add your handling code here:
        showData();
        tx_se.setText("");
    }//GEN-LAST:event_bt_allActionPerformed

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
            java.util.logging.Logger.getLogger(F_Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Manage_Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_all;
    private javax.swing.JButton bt_se;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_cus;
    private javax.swing.JTextField tx_se;
    // End of variables declaration//GEN-END:variables
}
