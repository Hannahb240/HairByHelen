import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JLabel;

public class AddPurchaseActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public AddPurchaseActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
        
    }// addTreatmentActionListener

    @Override
    public void actionPerformed(ActionEvent event)
    {
       try
       {
        AddPurchaseJFrame purchasesFrame = new AddPurchaseJFrame();
        purchasesFrame.setVisible(true);
       }//try
       catch(Exception e)
       {
           System.out.println(e);
                   
       }//catch
        
        
    }//actionPerformed
    
}// class AddTreatmentActionListener

