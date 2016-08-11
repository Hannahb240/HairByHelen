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
public class anualTotalGoJFrame extends javax.swing.JFrame {

    
    // The date that the user wishes to view
    private int dateToUse;
    
    // appointment result set initialisation
    ResultSet appointmentsRS;
    
    // purchases result set initialisation
    ResultSet purchasesRS;
    
    // save the totals to be subtracted at the end.
    private double appAnual = 0;
    private double purchasesAnual = 0;
    
    // Method to set up the year. called from viewTotalsActionListener.
    public void setYear(int year)
    {
        dateToUse = year;   
    }//setDate 
    
    // Method to set up the connection to the database.
    public void setConnection()
    {
        appointmentsPart();
        purchasesPart();
        double fullTotal = appAnual - purchasesAnual;
        anuallTotalJLabel.setText("£" + fullTotal);
      
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
         double totalAppAnual = 0;
         //Keep count of the number of appointments, for use when displaying 
         //either the appointments, or "no appoinmens found".
         int noOfAppointments = 0;
         
         
         
          // Go through all appointments and look for date required.
         while(appointmentsRS.next())    
         {
           String currentDate = appointmentsRS.getString(5);
           int year = Integer.parseInt(currentDate.substring(0, 4));
           if(year == dateToUse)
           {
               String clientName = appointmentsRS.getString(1);
               double cash = Double.parseDouble(appointmentsRS.getString(3));
               totalAppAnual += cash;
               noOfAppointments++;
               anualTakingsTextArea.append("Date: " + currentDate + " Name: "
                                           + clientName + " Cash: £" + cash + "\n");
               
           }//if  
        }//while
        anualTakingsTotalJLabel.setText("£" + totalAppAnual);
        if (noOfAppointments == 0)
        {
            anualTakingsTextArea.append("No appointments found for this year.");
        }//if
        
        appAnual = totalAppAnual;
        //Close all.
        appointmentsRS.close();
        con.close();
        stmt.close();
        System.out.println("Done appointments");
   
       }//try
       catch(Exception e)
       {
        System.out.println(e);
       }//catch
    
    }//appointmentsPart
    
    public void purchasesPart()
    {
        System.out.println("hello");
       try
        {
         //Set up the connection
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hairbyhelen", "root", "2095ratlol");
         Statement stmt = (Statement) con.createStatement();
         Class.forName("com.mysql.jdbc.Driver");
         
        //Result set for purchases
        ResultSet purchasesRS = stmt.executeQuery("SELECT * FROM purchases");
        //Calculate the total purchases
        double totalPurchasesAnual = 0;
        
        //Keep count of the number of purchases, for use when saying
        //no of purchases, or "no purchases found".
        int noOfPurchases = 0;
        
        
        // Go through all purchases and look for date required.
        while(purchasesRS.next())    
        {
           String currentDate = purchasesRS.getString(4);
           int year = Integer.parseInt(currentDate.substring(0, 4));
           if(year == dateToUse)
                    {
                        String itemName = purchasesRS.getString(1);
                        double cash = Double.parseDouble(purchasesRS.getString(2));
                        totalPurchasesAnual += cash;
                        noOfPurchases++;
                        anualPurchaseSpendTextArea.append("Date: " + currentDate 
                                                          + " Item: " + itemName + " Cash: £" + cash + "\n");       
                    }//if
      
        }//while
        anualPurchaseSpendTotalJLabel.setText("£" + totalPurchasesAnual);
        if (noOfPurchases == 0)
        {
           anualPurchaseSpendTextArea.append("No purchases found for this day.");
        }//if
        
        purchasesAnual = totalPurchasesAnual;
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
     * Creates new form anualTotalGoJFrame
     */
    public anualTotalGoJFrame() {
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

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        anualTakingsTotalJLabel = new javax.swing.JLabel();
        anualPurchaseSpendTotalJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        anualTakingsTextArea = new javax.swing.JTextArea();
        anuallTotalJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        anualPurchaseSpendTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("Total:");

        jLabel4.setText("Total:");

        jLabel5.setText("Overall profit:");

        anualTakingsTotalJLabel.setText("jLabel6");

        anualPurchaseSpendTotalJLabel.setText("jLabel6");

        anualTakingsTextArea.setColumns(20);
        anualTakingsTextArea.setRows(5);
        anualTakingsTextArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                anualTakingsTextAreaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(anualTakingsTextArea);

        anuallTotalJLabel.setText("jLabel6");

        anualPurchaseSpendTextArea.setColumns(20);
        anualPurchaseSpendTextArea.setRows(5);
        jScrollPane2.setViewportView(anualPurchaseSpendTextArea);

        jLabel1.setText("Anual takings:");

        jLabel2.setText("Anual purchase spend:");

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
                        .addComponent(anualPurchaseSpendTotalJLabel)
                        .addContainerGap(180, Short.MAX_VALUE))
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
                        .addComponent(anualTakingsTotalJLabel)
                        .addGap(145, 145, 145))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)))
                .addComponent(anuallTotalJLabel)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(anualPurchaseSpendTotalJLabel))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(anualTakingsTotalJLabel)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(anuallTotalJLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void anualTakingsTextAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_anualTakingsTextAreaAncestorAdded

    }//GEN-LAST:event_anualTakingsTextAreaAncestorAdded

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
            java.util.logging.Logger.getLogger(anualTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(anualTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(anualTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(anualTotalGoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new anualTotalGoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea anualPurchaseSpendTextArea;
    private javax.swing.JLabel anualPurchaseSpendTotalJLabel;
    private javax.swing.JTextArea anualTakingsTextArea;
    private javax.swing.JLabel anualTakingsTotalJLabel;
    private javax.swing.JLabel anuallTotalJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
