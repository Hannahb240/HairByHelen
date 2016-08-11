import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
        
public class AddTreatmentActionListener implements ActionListener
{
    //JLabel to update
    private final JLabel addTreatmentJLabel;
    
    public AddTreatmentActionListener(JLabel requiredJLabel)
    {
        addTreatmentJLabel = requiredJLabel;
    }// addTreatmentJLabel
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        try
     {
         addTreatmentFrame newFrame = new addTreatmentFrame();
         newFrame.setVisible(true);     
     }//try
   catch(Exception e)
   {
      System.out.println(e); 
   }//catch    
        
    }//actionPerformed
    
}// class AddTreatmentActionListener
