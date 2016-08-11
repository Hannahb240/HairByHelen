import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class TestActionListenerHi implements ActionListener
{
    private final JLabel test;
    private int number = 0;
    
    //constructor
    public TestActionListenerHi(JLabel requiredTest)
    {
        test = requiredTest;
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
      if (Math.random() <= 0.5)
      {
          test.setText("hi");
      }//if
      else
      {
          test.setText("hello");
      }//actionPerformed
    }
}// class TestActionListener
