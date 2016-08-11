import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
        
public class RemoveClientActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public RemoveClientActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
    }// addTreatmentJLabel
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        try
       {
        RemoveClientJFrame RemoveClientFrame = new RemoveClientJFrame();
        RemoveClientFrame.setVisible(true);
       }//try
       catch(Exception e)
       {
           System.out.println(e);
                   
       }//catch
        
    }//actionPerformed
    
}// class AddTreatmentActionListener
