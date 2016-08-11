
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hannah
 */
public class dailyTotalGoJFrame extends javax.swing.JFrame {

    // The date that the user wishes to view
    private String dateToUse;
    
    // appointment result set initialisation
    ResultSet appointmentsRS;
    
    // purchases result set initialisation
    ResultSet purchasesRS;
    
    // save the totals to be subtracted at the end.
    private double totalDaily = 0;
    private double totalPurchase = 0;
    
    // Method to set up the date. called from viewTotalsActionListener.
    public void setDate(String date)
    {
        dateToUse = date;   
    }//setDate 
    
    // Method to set up the connection to the database.
    public void setConnection()
    {
        appointmentsPart();
        purchasesPart();
        
        double fullTotal = totalDaily - totalPurchase;
        overallTotalJLabel.setText("£" + fullTotal);
    }//setConnection
   
    public void appointmentsPart()
    {
        try
        {
         //Set up the connection
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hairbyhelen", "root", "2095ratlol");
         Statement stmt = (Statement) con.createStatement();
         Class.forName("com.mysql.jdbc.Driver");
         
         //appointments result set 
         ResultSet appointmentsRS = stmt.executeQuery("SELECT * FROM appointments");
         
         //Keep count of the total amount 
         double totalAppDaily = 0;
         //Keep count of the number of appointments, for use when displaying 
         //either the appointments, or "no appoinmens found".
         int noOfAppointments = 0;
         
         
         // Go through all appointments and look for date required.
        while(appointmentsRS.next())    
        {
            String currentDate = appointmentsRS.getString(5);
            //If the date is found, print, and then add the cash to the totalDaily.
            if (currentDate.equals(dateToUse))
                    {
                        String clientName = appointmentsRS.getString(1);
                        double cash = Double.parseDouble(appointmentsRS.getString(3));
                        totalAppDaily += cash;
                        noOfAppointments++;
                        dailyTakingsTextArea.append("Name: " + clientName + " Cash: £" + cash + "\n");       
                    }//if
            
        }//while 
        dailyTakingsTotalJLabel.setText("£" + totalAppDaily);
        if (noOfAppointments == 0)
        {
            dailyTakingsTextArea.append("No appointments found for this day.");
        }//if
        
        totalDaily = totalAppDaily;
        
        //Close all.
        appointmentsRS.close();
        con.close();
        stmt.close();
        System.out.println("DONE APPOINTMENTS");
        
         
        }//try
        catch(Exception e)
        {
            System.out.println(e);
        }//catch
        
     
    }//appointmentsPart 
    
    public void purchasesPart()
    {
        try
        {
         //Set up the connection
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hairbyhelen", "root", "2095ratlol");
         Statement stmt = (Statement) con.createStatement();
         Class.forName("com.mysql.jdbc.Driver");
         
        //Result set for purchases
        ResultSet purchasesRS = stmt.executeQuery("SELECT * FROM purchases");
        
        //Calculate the total purchases
        double totalPurchasesDaily = 0;
        
        //Keep count of the number of purchases, for use when saying
        //no of purchases, or "no purchases found".
        int noOfPurchases = 0;
        System.out.println("Got to here");
        
        // Go through all purchases and look for date required.
        while(purchasesRS.next())    
        {
            String currentDate = purchasesRS.getString(4);
            //If the date is found, print, and then add the cash to the totalDaily.
            if (currentDate.equals(dateToUse))
                    {
                        String itemName = purchasesRS.getString(1);
                        double cash = Double.parseDouble(purchasesRS.getString(2));
                        totalPurchasesDaily += cash;
                        noOfPurchases++;
                        purchaseSpendTextArea.append("Item: " + itemName + " Cash: £" + cash + "\n");       
                    }//if
      
        }//while
        dailyPurchaseSpendTotalJLabel.setText("£" + totalPurchasesDaily);
        if (noOfPurchases == 0)
        {
            purchaseSpendTextArea.append("No purchases found for this day.");
        }//if
        
        totalPurchase = totalPurchasesDaily;
        //Close all.
        purchasesRS.close();
        con.close();
        stmt.close();
        System.out.println("DONE PURCHASES");
        
        
    }//try
        catch(Exception e)
        {
            System.out.println(e);
        }//catch
    }//purchasesPart
    
    /**
     * Creates new form dailyTotalGoJFrame
     */
    public dailyTotalGoJFrame() {
        initComponents();
        createFrame();
    }
    
    
   private void createFrame()
   {
       
       setTitle("Daily Total");
       
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dailyTakingsTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchaseSpendTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dailyTakingsTotalJLabel = new javax.swing.JLabel();
        dailyPurchaseSpendTotalJLabel = new javax.swing.JLabel();
        overallTotalJLabel = new javax.swing.JLabel();

        dailyTakingsTextArea.setColumns(20);
        dailyTakingsTextArea.setRows(5);
        dailyTakingsTextArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dailyTakingsTextAreaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(dailyTakingsTextArea);

        purchaseSpendTextArea.setColumns(20);
        purchaseSpendTextArea.setRows(5);
        jScrollPane2.setViewportView(purchaseSpendTextArea);

        jLabel1.setText("Daily takings:");

        jLabel2.setText("Purchase spend:");

        jLabel3.setText("Total:");

        jLabel4.setText("Total:");

        jLabel5.setText("Overall daily profit:");

        dailyTakingsTotalJLabel.setText("jLabel6");

        dailyPurchaseSpendTotalJLabel.setText("jLabel6");

        overallTotalJLabel.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(154, 154, 154))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dailyTakingsTotalJLabel)
                                .addGap(74, 74, 74)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dailyPurchaseSpendTotalJLabel)
                                .addGap(115, 115, 115)))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(overallTotalJLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dailyPurchaseSpendTotalJLabel))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dailyTakingsTotalJLabel)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(overallTotalJLabel))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Display all daily takings from that day
    private void dailyTakingsTextAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dailyTakingsTextAreaAncestorAdded
        
        
     
      
       
    }//GEN-LAST:event_dailyTakingsTextAreaAncestorAdded

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
            java.util.logging.Logger.getLogger(dailyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dailyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dailyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dailyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dailyTotalGoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dailyPurchaseSpendTotalJLabel;
    private javax.swing.JTextArea dailyTakingsTextArea;
    private javax.swing.JLabel dailyTakingsTotalJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel overallTotalJLabel;
    private javax.swing.JTextArea purchaseSpendTextArea;
    // End of variables declaration//GEN-END:variables
}
