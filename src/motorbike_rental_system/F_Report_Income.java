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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;


public class F_Report_Income extends javax.swing.JFrame {

    DBCollection coll;
    DB db;
    public F_Report_Income() {
        initComponents();
        showDay();
        
    }

    public void showDay(){
       try {
            // TODO add your handling code here:
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
           
            tx_showDetail.setText("รายได้แต่ละรายการ");
            
            DBCursor cursor = coll.find();
            
            tb_income.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"วันที่","ค่าเช่า","ค่าปรับ"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            
            int sumSumR = 0;
            Double sumRe = 0.0;
            Double sumfine = 0.0;
            
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                sumSumR++;
                sumRe+=(Double)obj.get("SumR");
                sumfine+=(Double)obj.get("fine");
                
                Date Date = (Date)obj.get("DateR");
                Double SumR = (Double)obj.get("SumR");
                Double fine = (Double)obj.get("fine");
                ObjectId id = (ObjectId)obj.get("_id");
                model.addRow(new Object[] { Date, SumR, fine});
            }
             tb_income.setModel(model);
             
             TableColumnModel columnModel = tb_income.getColumnModel();
             columnModel.getColumn(0).setPreferredWidth(250);
             tb_income.setRowHeight(30);
             
             tx_sumR.setText(Integer.toString(sumSumR));
             tx_SumRe.setText(Double.toString(sumRe));
             tx_Sumfine.setText(Double.toString(sumfine));
             Double sumall = sumfine+sumRe;
             tx_SumAll.setText(Double.toString(sumall));
             

        } catch (Exception e) {
            System.out.println(e);
        }
   }
   public void showAllincome(){
       try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("admin");
            coll = db.getCollection("RentalMotorcycle");
           
            tx_showDetail.setText("รายได้ตลอดกาล");
            
            DBCursor cursor = coll.find();
            
            tb_income.getTableHeader().setFont(new Font("TH Sarabun New", Font.BOLD, 18));
            String[] columnNames = {"รายได้ทั้งหมด","ค่าปรับ"};
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            
            double sum = 0.0;
            double fine = 0.0;
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                sum+=(Double)obj.get("SumR");     
                fine+=(Double)obj.get("fine");                     
            }
            model.addRow(new Object[] { sum,fine });
            
            tb_income.setModel(model);
            
           
            
           
       } catch (Exception e) {
       
       }

    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_income = new javax.swing.JTable();
        bt_showDay = new javax.swing.JButton();
        bt_showAll = new javax.swing.JButton();
        tx_showDetail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_sumR = new javax.swing.JLabel();
        tx_SumRe = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tx_Sumfine = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_SumAll = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bt_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tb_income.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tb_income.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_income);

        bt_showDay.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        bt_showDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/checklist.png"))); // NOI18N
        bt_showDay.setText("ทุกรายการ");
        bt_showDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showDayActionPerformed(evt);
            }
        });

        bt_showAll.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        bt_showAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24-hours.png"))); // NOI18N
        bt_showAll.setText("ตลอดกาล");
        bt_showAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showAllActionPerformed(evt);
            }
        });

        tx_showDetail.setFont(new java.awt.Font("TH Sarabun New", 1, 48)); // NOI18N
        tx_showDetail.setText("jLabel2");

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel2.setText("จำนวน :");

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel3.setText("รวม :");

        tx_sumR.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_sumR.setText("Text");

        tx_SumRe.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_SumRe.setText("Text");

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel4.setText("รวม :");

        tx_Sumfine.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_Sumfine.setText("Text");

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel5.setText("รวมทั้งหมด :");

        tx_SumAll.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        tx_SumAll.setText("Text");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(bt_showDay)
                                .addGap(437, 437, 437))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_sumR)
                                .addGap(456, 456, 456)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_Sumfine)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(293, 293, 293)
                                .addComponent(tx_showDetail))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(355, 355, 355)
                                .addComponent(bt_showAll, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(366, 366, 366)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_SumRe))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(408, 408, 408)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_SumAll)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tx_showDetail)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tx_sumR)
                    .addComponent(tx_SumRe)
                    .addComponent(jLabel4)
                    .addComponent(tx_Sumfine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tx_SumAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_showDay)
                    .addComponent(bt_showAll))
                .addGap(23, 23, 23))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("รายงานรายได้");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(bt_back)
                .addGap(229, 229, 229)
                .addComponent(jLabel1)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bt_back))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        new F_Home_Report().show();
        dispose();
    }//GEN-LAST:event_bt_backActionPerformed

    private void bt_showDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showDayActionPerformed
        // TODO add your handling code here:
        showDay();
        
    }//GEN-LAST:event_bt_showDayActionPerformed

    private void bt_showAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showAllActionPerformed
        // TODO add your handling code here:
       showAllincome();
    }//GEN-LAST:event_bt_showAllActionPerformed

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
            java.util.logging.Logger.getLogger(F_Report_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Report_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Report_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Report_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Report_Income().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_back;
    private javax.swing.JButton bt_showAll;
    private javax.swing.JButton bt_showDay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_income;
    private javax.swing.JLabel tx_SumAll;
    private javax.swing.JLabel tx_SumRe;
    private javax.swing.JLabel tx_Sumfine;
    private javax.swing.JLabel tx_showDetail;
    private javax.swing.JLabel tx_sumR;
    // End of variables declaration//GEN-END:variables

   
}
