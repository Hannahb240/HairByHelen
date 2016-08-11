import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.text.*;
        
public class weeklyTotalGoJFrame extends javax.swing.JFrame {

 
         //Array for storing all dates of week:
         String[] weekFollowingStartDate = new String[6];
         
         private double totalWeekly = 0;
         private double purchasesWeekly = 0;
         
         // appointment result set initialisation
         ResultSet appointmentsRS;
         
         // purchases result set initialisation
         ResultSet purchasesRS;
    
    
    //Set the week- add elements to an array. 
    public void setWeek(String date)
    {     
         //Date of the start of the week. Also day 1
         String startOfWeekDate = null;
       
        //separate the date
        String[] splitDate = date.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = (Integer.parseInt(splitDate[1]) - 1);
        int day = Integer.parseInt(splitDate[2]);
            
        SimpleDateFormat startOfWeekSDF = new SimpleDateFormat("yyyy-MM-dd");
        
        //Set the calendar to the date given.
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        
        // The start of the week date.
        startOfWeekDate = startOfWeekSDF.format(cal.getTime());
        
        //Add to array as element 1 (0).
        weekFollowingStartDate[0] = startOfWeekDate;
        
        //add one day to the date
        for (int n = 1; n < 6; n++)
        {
            cal.add(Calendar.DATE, 1);
            weekFollowingStartDate[n] = startOfWeekSDF.format(cal.getTime());
        }//for     
        
    }//setWeek
    
    public void setConnection()
    {
      appointmentsPart();
      purchasesPart();
      
      //calculate overall total
      double fullTotal = totalWeekly - purchasesWeekly;
      weeklyTotalJLabel.setText("£" + fullTotal);
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
         double totalAppWeekly = 0;
         
         //Keep count of the number of appointments, for use when displaying 
         //either the appointments, or "no appoinmens found".
         int noOfAppointments = 0;
         
        // Go through all appointments and look for date required.
        while(appointmentsRS.next())    
        {
            // the current date
            String currentDate = appointmentsRS.getString(5);
            
            //Go through the array. If the curent date matches any of the elemenst
            // in the array, save them as usual.
            for (int i = 0; i < weekFollowingStartDate.length; i++) 
            {
                if (currentDate.equals(weekFollowingStartDate[i]))
                {
                    String clientName = appointmentsRS.getString(1);
                    double cash = Double.parseDouble(appointmentsRS.getString(3));
                    totalAppWeekly += cash;
                    noOfAppointments++;
                    weeklyTakingsTextArea.append("Date: " + currentDate 
                                                 + " Name: " + clientName + " Cash: £" + cash + "\n");
                }//if    
            }//for
        }//while
         weeklyTakingsTotalJLabel.setText("£" + totalAppWeekly);
        if (noOfAppointments == 0)
        {
            weeklyTakingsTextArea.append("No appointments found for this day.");
        }//if
        
        totalWeekly = totalAppWeekly;
        
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
        double totalPurchasesWeekly = 0;
        
        //Keep count of the number of purchases, for use when saying
        //no of purchases, or "no purchases found".
        int noOfPurchases = 0;
        
        // Go through all purchases and look for date required.
        while(purchasesRS.next())    
        {
            String currentDate = purchasesRS.getString(4);
            //Go through the array. If the curent date matches any of the elemenst
            // in the array, save them as usual.
            for (int i = 0; i < weekFollowingStartDate.length; i++) 
            {
                if (currentDate.equals(weekFollowingStartDate[i]))
                {
                    String itemName = purchasesRS.getString(1);
                    double cash = Double.parseDouble(purchasesRS.getString(2));
                    totalPurchasesWeekly += cash;
                    noOfPurchases++;                   
                    weeklyPurchaseSpendTextArea.append("Date: " + currentDate 
                                                       + " Item: " + itemName + " Cash: £" + cash + "\n");  
                }//if
            }//for
        }//while
        weeklyPurchaseSpendTotalJLabel.setText("£" + totalPurchasesWeekly);
        if (noOfPurchases == 0)
        {
            weeklyPurchaseSpendTextArea.append("No purchases found for this day.");
        }//if
        
        purchasesWeekly = totalPurchasesWeekly;
        
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
    
    
    
    
    
    
    
    
    
    
    
    public weeklyTotalGoJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        weeklyTakingsTotalJLabel = new javax.swing.JLabel();
        weeklyPurchaseSpendTotalJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        weeklyTakingsTextArea = new javax.swing.JTextArea();
        weeklyTotalJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        weeklyPurchaseSpendTextArea = new javax.swing.JTextArea();

        jLabel1.setText("Weekly takings:");

        jLabel3.setText("Total:");

        jLabel2.setText("Weekly purchase spend:");

        jLabel4.setText("Total:");

        jLabel5.setText("Overall profit:");

        weeklyTakingsTotalJLabel.setText("jLabel6");
        weeklyTakingsTotalJLabel.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                weeklyTakingsTotalJLabelHierarchyChanged(evt);
            }
        });

        weeklyPurchaseSpendTotalJLabel.setText("jLabel6");

        weeklyTakingsTextArea.setColumns(20);
        weeklyTakingsTextArea.setRows(5);
        weeklyTakingsTextArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                weeklyTakingsTextAreaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(weeklyTakingsTextArea);

        weeklyTotalJLabel.setText("jLabel6");

        weeklyPurchaseSpendTextArea.setColumns(20);
        weeklyPurchaseSpendTextArea.setRows(5);
        jScrollPane2.setViewportView(weeklyPurchaseSpendTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(153, 153, 153))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weeklyPurchaseSpendTotalJLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weeklyTakingsTotalJLabel)
                        .addGap(145, 145, 145))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)))
                .addComponent(weeklyTotalJLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(weeklyPurchaseSpendTotalJLabel))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weeklyTakingsTotalJLabel)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(weeklyTotalJLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void weeklyTakingsTextAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_weeklyTakingsTextAreaAncestorAdded

    }//GEN-LAST:event_weeklyTakingsTextAreaAncestorAdded

    private void weeklyTakingsTotalJLabelHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_weeklyTakingsTotalJLabelHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_weeklyTakingsTotalJLabelHierarchyChanged

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
            java.util.logging.Logger.getLogger(weeklyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(weeklyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(weeklyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(weeklyTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new weeklyTotalGoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea weeklyPurchaseSpendTextArea;
    private javax.swing.JLabel weeklyPurchaseSpendTotalJLabel;
    private javax.swing.JTextArea weeklyTakingsTextArea;
    private javax.swing.JLabel weeklyTakingsTotalJLabel;
    private javax.swing.JLabel weeklyTotalJLabel;
    // End of variables declaration//GEN-END:variables
}
