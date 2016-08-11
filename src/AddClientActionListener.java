import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
        
public class AddClientActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public AddClientActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
    }// addTreatmentJLabel
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
       try
       {
        AddClientJFrame clientFrame = new AddClientJFrame();
        clientFrame.setVisible(true);
       }//try
       catch(Exception e)
       {
           System.out.println(e);
                   
       }//catch
        
    }//actionPerformed
    
}// class AddTreatmentActionListener
