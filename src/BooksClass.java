import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class BooksClass extends JFrame 
{
  //JButton 1
  private final JButton addTreatment = new JButton("Add treatment");
  
  //JButton 2
  private final JButton addPurchase = new JButton("Add purchase");
  
  //JButton 3
  private final JButton viewTotals = new JButton("View totals");
  
  //JButton 4
  private final JButton addClient = new JButton("Add client");
  
  //JButon 5
  private final JButton removeClient = new JButton("Remove client");
  
  //JButton 6
  private final JButton viewClientList = new JButton("View client list");
  
  //JLabels for testing 
  JLabel addTreatmentJLabel = new JLabel("");
  JLabel addClientJLabel = new JLabel("");
  JLabel addPurchaseJLabel = new JLabel("");
  JLabel removeClientJLabel = new JLabel("");
  JLabel viewTotalsJLabel = new JLabel("");
  JLabel viewClientListJLabel = new JLabel("");
  
  
  
  //Construct a GUI for the Books Class
  public BooksClass()
  {
      setTitle("Books");
      Container contents = getContentPane();
      contents.setLayout(new GridLayout(0, 2, 10, 20));
     
      contents.add(addTreatment);
      contents.add(addClient);
      contents.add(addPurchase);
      contents.add(removeClient);
      contents.add(viewTotals); 
      contents.add(viewClientList);
      
      contents.add(addTreatmentJLabel);
      //Add action listeners
      
      //Create the addTretment action listener object
      AddTreatmentActionListener addTreatmentListener 
        = new AddTreatmentActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      addTreatment.addActionListener(addTreatmentListener);
      
      
      
     //Create the addClient action listener object
      AddClientActionListener addClientListener 
       = new AddClientActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      addClient.addActionListener(addClientListener);
      
      
      //Create the AddPurchase action lisener object
      AddPurchaseActionListener addPurchaseListener 
       = new AddPurchaseActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      addPurchase.addActionListener(addPurchaseListener);
      
      
      
      //Create the RemoveClient action lisener object
      RemoveClientActionListener removeClientListener 
       = new RemoveClientActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      removeClient.addActionListener(removeClientListener);
      
      
      
        //Create the ViewTotals action lisener object
      ViewTotalsActionListener viewTotalsListener 
       = new ViewTotalsActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      viewTotals.addActionListener(viewTotalsListener);
      
      
      
        //Create the ViewTotals action lisener object
      ViewClientListActionListener viewClientListListener 
       = new ViewClientListActionListener(addTreatmentJLabel);
      
      //Link the actionlistener to the JButton
      viewClientList.addActionListener(viewClientListListener);
      
   
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
  }// constructor BooksClass
  
  //create a booksclass and make it appear on screen
  public static void main(String args[])
  {
      BooksClass theBooksClass = new BooksClass();
      theBooksClass.setVisible(true);
  }//main
    
}//class BooksClass
