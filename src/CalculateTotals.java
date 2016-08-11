
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
public class CalculateTotals {
    
    public double totalAppDaily;
    
    public double calculateAppointments(String date)
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
         totalAppDaily = 0;
         //Keep count of the number of appointments, for use when displaying 
         //either the appointments, or "no appoinmens found".
         int noOfAppointments = 0;
         
         String dateToUse = date;
         
         
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
                    }//if
            
        }//while 
     
        
        //Close all.
        appointmentsRS.close();
        con.close();
        stmt.close();
        
        }//try
        catch(Exception e)
        {
            System.out.println(e);
        }//catch
     
    return totalAppDaily;
    }//calculateAppointments
    
    public void calculatePurchases(String date)
    {
        
    }//calculatePurchases
    
    }// class calculateTotals 