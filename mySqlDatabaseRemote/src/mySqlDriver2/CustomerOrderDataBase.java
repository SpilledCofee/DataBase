/*
-This GUI will take in user input, but it will not store that input unless it a valid input
-It will send out messages to inform user that their input is invalid
-It also has a 'success' message for when all the input is valid
*/


package mySqlDriver2;
///load file imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//MySQL imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
//GUI imports
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



/**Product ID: 5
Product Title: African Roots
Product Description: 16 oz, whole bean, Medium roast, Bright and complex with notes of fruit.
Integer: 1000
Wholesale: 10.31
Sale Price: 14.50
Supplier ID: ETHIORST */
 
@SuppressWarnings("serial")
public class CustomerOrderDataBase extends JFrame {

    //This is credentials required to connect to MySQL
   //private String url, username, password;
   


   private static String FILE_NAME;


    //These are unchaing integers that will assist with choosing options
    private boolean PID_SELCTION = false;
    private boolean TITLE_SELECTION =  false;
    private boolean SID_SELECTION = false;

    String menuSelection;//Used in displayMenue()
 
    // Constructor to setup the GUI components and event handlers
    public CustomerOrderDataBase() {
        //getLogin();
        //displayMenue();
        //searchRecords();
        //createRecord();
        //getLogin();
        //deleteRecord();

        /*()
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


            

   }

   public void displayMenue(){


   }//END DisplayMenu
   
   private void deleteRecord() {
  
    }//END Delete Record

   public void updateRecord(){

   }//END Update Record

   public void getLogin(){
       JTextField urlText, usernameText, passwordText;
       JLabel label = new JLabel();

    // Reference for container: https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
    // Reference for dropdown: https://www3.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI_2.html
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        cp.add(new JLabel("Enter MySql Credentials:"));

        JPanel tfPanel = new JPanel(new GridLayout(3, 2));
        tfPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        cp.add(tfPanel);

        tfPanel.add(new JLabel("Url"));
        urlText = new JTextField(10);
        tfPanel.add(urlText);
        tfPanel.add(new JLabel("Username"));
        usernameText = new JTextField(10);
        tfPanel.add(usernameText);
        tfPanel.add(new JLabel("Password"));
        passwordText = new JPasswordField(10);
        tfPanel.add(passwordText);

        final JButton submitSelection= new JButton("Submit");
        cp.add(submitSelection);

        label.setFont(new Font("Serif", Font.BOLD, 13));
        label.setForeground(Color.red);
        label.setOpaque(true);
        cp.add(label);

        // Allocate an anonymous instance of an anonymous inner class that
        // implements ActionListener as ActionEvent listener
        submitSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            //This will reset the label every time that the button is pressed
            label.setText("");

            // Getting url, username and password from text fields


            if (urlText.getText() == null || urlText.getText().length() < 1){
                label.setText("Please enter a valid url!");
            }
            else if (usernameText.getText()== null || usernameText.getText().length() < 1){
                label.setText("Please enter a valid username!");
            }
            else if (passwordText.getText() == null || passwordText.getText().length() < 1){
                label.setText("Please enter a valid password!");
            }
            else{
                url = urlText.getText();
                username = usernameText.getText();
                password = passwordText.getText();
                //System.out.println(url + "\n" + username + "\n" + password);
                }
                // When submit button is clicked, close the menu
                //setVisible(false);
                //dispose(); // Close the menu after selection
                cp.removeAll();
                displayMenue();
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Credentials:"); // "super" JFrame sets title
        setSize(300, 225);         // "super" JFrame sets initial size
        setVisible(true);          // "super" JFrame shows

        // Wait until a selection is made before returning
        // reference https://stackoverflow.com/questions/20069374/java-swing-main-class-wait-until-jframe-is-closed

    }//END GET LOGIN

    public void searchRecords(){
      // Retrieve the content-pane of the top-level container JFrame


    }//END SEARCH RECORDS


    //This method is used to print the results from the MySQL result set. IT allows to search method to be flexible
    public void printResults(int fieldtype, String itemLookup){

		String query = "product_id";//default

		switch(fieldtype){
			case 1: query = "product_id";
					break;
			case 2: query = "product_title";
					break;
			case 3: query = "product_description";
					break;
			case 4: query = "quantity";
					break;
			case 5: query = "supplier_id";
					break;
			case 6: query = "wholesale_price";
					break;
			case 7: query = "sale_price";
					break;
			default: query = "product_id";
					break;

		}
		try {
			System.out.println("sreaching for a " + query +" that has " + itemLookup);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_inventory WHERE " + query + "= '" + itemLookup + "'", ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
	
		
			ResultSet myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				
				myRs.beforeFirst();
			
			
				while (myRs.next()) {
					
					System.out.println();
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Product Title: " + myRs.getString("product_title"));
					System.out.println("Product Description: " + myRs.getString("product_description"));
					System.out.println("Quantity: " + myRs.getInt("quantity"));
					System.out.println("Wholesale: " + myRs.getString("wholesale_price"));
					System.out.println("Sale Price: " + myRs.getString("sale_price"));
					System.out.println("Supplier ID: " + myRs.getString("supplier_id"));
					System.out.println();
				}
            }
			else {
				System.out.println("No record found");
				System.out.println();
			}
			
        } catch (SQLException e) {
			System.out.println("oops, error!");
			e.printStackTrace();
		}
        
	}//END PRINT RESULTS

    public void createRecord() {

    }//END CREATE RECORD



   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new CustomerOrderDataBase(); // Let the constructor do the job
         }
      });
   }


   public void loadFile() throws FileNotFoundException {

    }//END loadFile



   /**************************************
    * These methods checks to make sure that we are only getting integers and doubles
    * The parsing wont work otherwise
    ************************************/

   public boolean checkInteger(String string){
    boolean valid = true;
    for(int i = 0; i<string.length(); i++){
        char a = string.charAt(i);
        if(!Character.isDigit(a)){
            valid = false;
            break;
        }
        
    }
return valid;
}

public boolean checkDouble(String string){
   /*This will check to see if there is only numbers and/or 1 period */
    boolean valid = true;
    char chr;
    //This is needed to because characters don't like being compared nicely
    String dot = ".";
    int dotcounter = 0;
    for(int i = 0; i< string.length(); i++){  
        chr = string.charAt(i);
        String str = string.substring(i,i+1);
        if(!Character.isDigit(chr)){
            if(!str.equals(dot)){
                valid = false;
                break;
            }
            else{
                dotcounter++;
                if(dotcounter > 1){
                    valid = false;
                    break;
                }
                continue;
            }   
        }
    }
    return valid;
}


}//FIN!!!
