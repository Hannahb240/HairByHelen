import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
        
public class ViewClientListActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public ViewClientListActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
    }// addTreatmentJLabel
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        try
       {
        DisplayClientListForm clientList = new DisplayClientListForm();
        clientList.setVisible(true);
       }//try
       catch(Exception e)
       {
           System.out.println(e);
       }//catch 
    }//actionPerformed
    
}// class AddTreatmentActionListener
