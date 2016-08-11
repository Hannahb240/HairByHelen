import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
        
public class ViewTotalsActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public ViewTotalsActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
    }// addTreatmentJLabel
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
    try
       {
        viewTotalsJFrame totalsFrame = new viewTotalsJFrame();
        totalsFrame.setVisible(true);
       }//try
       catch(Exception e)
       {
           System.out.println(e);
                   
       }//catch
        
    }//actionPerformed
    
}// class AddTreatmentActionListener
