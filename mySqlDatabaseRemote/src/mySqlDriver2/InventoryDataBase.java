/*
-This GUI will take in user input, but it will not store that input unless it a valid input
-It will send out messages to inform user that their input is invalid
-It also has a 'success' message for when all the input is valid
*/
//MySQL imports
package mySqlDriver2;

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
import javax.swing.plaf.ColorUIResource;

/**Product ID: 5
Product Title: African Roots
Product Description: 16 oz, whole bean, Medium roast, Bright and complex with notes of fruit.
Quantity: 1000
Wholesale: 10.31
Sale Price: 14.50
Supplier ID: ETHIORST */
 
@SuppressWarnings("serial")
public class InventoryDataBase extends JFrame {

    //This is credentials required to connect to MySQL
    private String url, username, password;

    //These are unchaing integers that will assist with choosing options
    private boolean PID_SELCTION = false;
    private boolean TITLE_SELECTION =  false;
    private boolean SID_SELECTION = false;

    String menuSelection;//Used in displayMenue()
    String fieldSelection; // Used in updateRecord()
 
    // Constructor to setup the GUI components and event handlers
    public InventoryDataBase() {
        getLogin();
        //displayMenue();
        //searchRecords();
        //createRecord();
        //getLogin();

   }

   public void displayMenue(){
    
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());

    // Create JComboBox for getting the prompt to run
    cp.add(new JLabel("Select a prompt:"));

    final String[] prompts = {"Create a new record", "Look up a record", "Update a record", "Delete an existing record"};
    menuSelection = prompts[0]; // Setting initial selection to first item

    final JComboBox<String> selections = new JComboBox<String>(prompts);
    selections.setPreferredSize(new Dimension(300, 80));
    cp.add(selections);

    // Update menu selection when something is selected in drop down
    selections.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                menuSelection = (String)selections.getSelectedItem();
            }
        }
    });

        //Jbutton  
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(menuSelection);
            if(menuSelection.equals("Create a new record")){
                cp.removeAll();
                cp.revalidate();
                cp.repaint();
                createRecord();
            }
            else if(menuSelection.equals("Look up a record")){
                cp.removeAll();
                cp.revalidate();
                cp.repaint();
                searchRecords();
            }
            else if(menuSelection.equals("Update a record")){
                cp.removeAll();
                cp.revalidate();
                cp.repaint();
                updateRecord();
            }
            else if(menuSelection.equals("Delete an existing record")){
                cp.removeAll();
                cp.revalidate();
                cp.repaint();
                deleteRecord();
            }
        }

     });
      cp.add(submitButton);

    // Allocate an anonymous instance of an anonymous inner class that
    // implements ActionListener as ActionEvent listener

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Menu:"); // "super" JFrame sets title
    setSize(350, 225);         // "super" JFrame sets initial size
    setVisible(true);          // "super" JFrame shows

   }
   
   private void deleteRecord() {
    }

   public void updateRecord(){
       Container cp = getContentPane();
       cp.setLayout(new FlowLayout());

       // JPanel to get product id of product to be updated
       JTextField productIdText = new JTextField(10);
       JButton submitProductId = new JButton("Submit");

       JPanel productIdPanel = new JPanel(new GridLayout(3, 1));
       productIdPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
       productIdPanel.add(new JLabel("Enter product ID to update: "));
       productIdPanel.add(productIdText);
       productIdPanel.add(submitProductId);
       cp.add(productIdPanel);

       // JPanel to get which field to update
       String[] prompts = {"Product ID", "Quantity", "Wholesale Price", "Sale Price", "Supplier ID", "Product Title", "Product Description"};
       fieldSelection = prompts[0]; // Setting initial selection to first item
       JComboBox<String> fieldSelections = new JComboBox<String>(prompts);
       fieldSelections.setPreferredSize(new Dimension(10, 10));
       JButton submitFieldSelection = new JButton("Submit");

       JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
       fieldPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
       fieldPanel.add(new JLabel("Select field to update: "));
       fieldPanel.add(fieldSelections);
       fieldPanel.add(submitFieldSelection);
       fieldPanel.setVisible(false);
       cp.add(fieldPanel);

       // Label to print error messages
       JLabel errorLabel = new JLabel();
       errorLabel.setFont(new Font("Serif", Font.BOLD, 13));
       errorLabel.setForeground(Color.red);
       errorLabel.setOpaque(true);
       cp.add(errorLabel);

       fieldSelections.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                   fieldSelection = (String)fieldSelections.getSelectedItem();
               }
           }
       });

       submitProductId.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               errorLabel.setText("");

               if(productIdText.getText().equals(null) || productIdText.getText().isEmpty()){
                   errorLabel.setText("Invalid product id.");
               }
               else {
                   Connection connection = null;

                   try{
                       connection = DriverManager.getConnection(url, username, password);

                       PreparedStatement myStmt2 = connection.prepareStatement("Select * FROM new_inventory WHERE product_id = '" + productIdText.getText() + "'");

                       ResultSet myRs = myStmt2.executeQuery();

                       if (myRs == null) {
                           System.out.println("Record not found for entered product id");
                           errorLabel.setText("Invalid product id. Record does not exist.");
                       }
                       else{
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

                           productIdPanel.setVisible(false);
                           fieldPanel.setVisible(true);
                       }
                   } catch (SQLException se) {
                       // TODO Auto-generated catch block
                       System.out.println("oops, error!");
                       se.printStackTrace();
                   } catch (InputMismatchException exception) {
                       System.out.print(exception.getMessage()); //try to find out specific reason.
                   }

                   try {
                       if(connection!=null)
                           connection.close();
                   }catch(SQLException se) {
                       se.printStackTrace();
                   }
               }
           }

       });

       submitFieldSelection.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println(fieldSelection);

               JTextField updatedFieldText = new JTextField(10);
               JButton submitUpdate = new JButton("Submit");

               JPanel updatePanel = new JPanel(new GridLayout(3, 1));
               updatePanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
               updatePanel.add(new JLabel("Enter updated " + fieldSelection + ":"));
               updatePanel.add(updatedFieldText);
               updatePanel.add(submitUpdate);
               cp.add(updatePanel);

               fieldPanel.setVisible(false);
               updatePanel.setVisible(true);

               submitUpdate.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       Connection connection = null;

                       PreparedStatement updateStmt;

                       try{
                           connection = DriverManager.getConnection(url, username, password);

                           if(fieldSelection.equals("Product ID")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET product_id = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else if(fieldSelection.equals("Quantity")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET quantity = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else if(fieldSelection.equals("Wholesale Price")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET wholesale_price = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else if(fieldSelection.equals("Sale Price")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET sale_price = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else if(fieldSelection.equals("Supplier ID")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET supplier_id = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else if(fieldSelection.equals("Product Title")){
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET product_title = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }
                           else {
                               updateStmt = connection.prepareStatement("UPDATE new_inventory SET product_description = '" + updatedFieldText.getText() +"' WHERE product_id = '"+ productIdText.getText() +"'");
                           }

                           updateStmt.execute();

                           System.out.println("Record has been updated.");

                           cp.removeAll();
                           cp.revalidate();
                           cp.repaint();
                           displayMenue();
                       } catch (SQLException se) {

                           // TODO Auto-generated catch block
                           System.out.println("oops, error!");
                           se.printStackTrace();
                       } catch (InputMismatchException exception) {
                           System.out.print(exception.getMessage()); //try to find out specific reason.
                       }

                       try {
                           if(connection!=null)
                               connection.close();
                       }catch(SQLException se) {
                           se.printStackTrace();
                       }
                   }

               });
           }
       });

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
       setTitle("Update Record:"); // "super" JFrame sets title
       setSize(350, 225);         // "super" JFrame sets initial size
       setVisible(true);          // "super" JFrame shows
   }

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
      // All operations done on the content-pane
      JRadioButton pid, title, sid;
      JTextField searchPid, searchTitle, searchSid;  // Use Swing's JTextField instead of AWT's TextField
      JButton submitButton;    // Using Swing's JButton instead of AWT's Button

      //DISCRIPTION_SELECTION = 3;
      //WHOLESALE_SELECTION = 4;
      //SALES_SELECTION = 5;




      
      Container cp = getContentPane();
      cp.setLayout(new FlowLayout());
      //This panel will make so it will make a new row for the textFields
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.setVisible(true);

      searchPid = new JTextField(10);
      searchPid.setEditable(false);
      searchTitle = new JTextField(10);
      searchTitle.setEditable(false);
      searchSid = new JTextField(10);
      searchSid.setEditable(false);
      panel.add(searchPid);
      panel.add(searchTitle);
      panel.add(searchSid);
    
      
      pid = new JRadioButton("Product ID");
      pid.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PID_SELCTION = true;
            TITLE_SELECTION =  false;
            SID_SELECTION = false;
            searchPid.setEditable(true);
            searchSid.setEditable(false);
            searchTitle.setEditable(false);
        }
     });
      cp.add(pid);

      title = new JRadioButton("Product Title");
      title.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PID_SELCTION = false;
            TITLE_SELECTION = true;
            SID_SELECTION = false;
            searchPid.setEditable(false);
            searchSid.setEditable(false);
            searchTitle.setEditable(true);
        }
     });
      cp.add(title);

      sid = new JRadioButton("Supplier's ID");
      sid.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PID_SELCTION = false;
            TITLE_SELECTION =  false;
            SID_SELECTION = true;
            searchPid.setEditable(false);
            searchSid.setEditable(true);
            searchTitle.setEditable(false);
        }
     });
      cp.add(sid);
      cp.add(panel);
     //This makes it so only one radio button can be slected at a time
      ButtonGroup btnGp = new ButtonGroup();
      btnGp.add(pid);
      btnGp.add(title);
      btnGp.add(sid);

        //Jbutton  
      submitButton = new JButton("Submit");
      submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String record = null;
           if(PID_SELCTION && searchPid.getText() != null){
               record = searchPid.getText();
               printResults(1, record);
               
           }
           else if(TITLE_SELECTION && searchTitle.getText() != null){
               record = searchTitle.getText();
               printResults(2, record);
           }
           else if(SID_SELECTION && searchSid.getText() != null){
               record = searchSid.getText();
               printResults(5, record);
            }

            cp.removeAll();
            displayMenue();

            //System.out.println(record + " " + PID_SELCTION + TITLE_SELECTION + SID_SELECTION);
        }
     });
      cp.add(submitButton);
 
      // Allocate an anonymous instance of an anonymous inner class that
      //  implements ActionListener as ActionEvent listener
      submitButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {

         }
      });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Search Records"); // "super" JFrame sets title
      setSize(400, 150);         // "super" JFrame sets initial size
      setVisible(true);          // "super" JFrame shows

    }//END SEARCH RECORDS

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
        // Private variables of the GUI components
    JTextField titleText, discriptionText, quantityText, wholesale_priceText, sales_priceText, sidText;
    JLabel label = new JLabel();

    //These also have getter methods that we can extrate the values from the GUI

        
        JPanel tfPanel = new JPanel(new GridLayout(6, 2, 10, 2));//6 rows and 2 columns
        tfPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JButton button = new JButton("Add Record");

        //(Row 1)
        tfPanel.add(new JLabel("Product Title"));
        titleText = new JTextField(20);
        tfPanel.add(titleText);

        //(Row 2)
        tfPanel.add(new JLabel("Product Discription"));
        discriptionText = new JTextField(30);
        tfPanel.add(discriptionText);

        //(Row 3)
        tfPanel.add(new JLabel("Quantity"));
        quantityText = new JTextField(8);
        tfPanel.add(quantityText);

        //(Row 4)
        tfPanel.add(new JLabel("Wholesale Price"));
        wholesale_priceText = new JTextField(8);
        tfPanel.add(wholesale_priceText);

        //(Row 5)
        tfPanel.add(new JLabel("Sales Price"));
        sales_priceText = new JTextField(8);
        tfPanel.add(sales_priceText);

        //(Row 6)
        tfPanel.add(new JLabel("Supplier's ID"));
        sidText = new JTextField(8);
        tfPanel.add(sidText);

        //This is another pannel just for the button that gets out of the grid layout
        JPanel btnPanel = new JPanel();
        btnPanel.setLocation(100, 200);
        btnPanel.add(button);
        label.setFont(new Font("Serif", Font.BOLD, 13));
        label.setForeground(Color.red);
        label.setOpaque(true);
        btnPanel.add(label);

        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout(5, 5));


        button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //This will reset the label every time that the button is pressed
            label.setForeground(Color.red);
            label.setText("");


            //This will check if the quantity ID is a number
            if(titleText.getText().isEmpty()){   
                label.setText("Enter a Product Title!!!");
            }
            else if(discriptionText.getText().isEmpty()){   
                label.setText("Enter a Product Discription!!!");
            }
            else if(!checkQuantity(quantityText.getText())){
                label.setText("Invalid Quantity!");
            }
            else if(quantityText.getText().length() > 8){
                label.setText("Quantity is too Big");
            }
            else if(quantityText.getText() == null){
                label.setText("Enter a Quantity");
            }
            else if(wholesale_priceText.getText() == null){
                label.setText("Enter Wholesales Price");
            }
            else if(checkDouble(wholesale_priceText.getText()) == false){
                label.setText("Invalid Wholesale Price");
            }
            else if(sales_priceText.getText() == null || sales_priceText.getText().length() < 2){
                label.setText("Enter a Sales Price");
            }
            else if(!checkDouble(sales_priceText.getText())){
                label.setText("Invalid Sales Price"); 
            }
            //These will make sure that the string Text Fields are not blank
            else if(sidText.getText() == null){   
                label.setText("Invalid Supplier ID!!!");
            }
            else{
            String title = titleText.getText();
            String discription = discriptionText.getText();
            int quantity = Integer.parseInt(quantityText.getText());
            double wholesale_price = Double.parseDouble(wholesale_priceText.getText());
            double sales_price = Double.parseDouble(sales_priceText.getText());
            String sid = sidText.getText();
            label.setForeground(Color.green);
            label.setText("Success!");

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(url, username, password);
                String query = " INSERT INTO new_inventory (product_title, product_description, quantity, sale_price, wholesale_price, supplier_id)" +
                        " VALUES (?, ?, ?, ?, ?, ?)";
    
                PreparedStatement prepStmt = connection.prepareStatement(query);
                prepStmt.setString (1, title);
                prepStmt.setString (2, discription);
                prepStmt.setInt (3, quantity);
                prepStmt.setDouble (4, sales_price);
                prepStmt.setDouble (5, wholesale_price);
                prepStmt.setString (6, sid);
    
                //Test

                System.out.println(title);
                System.out.println(discription);
                System.out.println(quantity);
                System.out.println(sales_price);
                System.out.println(wholesale_price);
                System.out.println(sid);
                prepStmt.execute();
                //*/
    
            } catch (SQLException se) {
    
                // TODO Auto-generated catch block
                System.out.println("oops, error!");
                se.printStackTrace();
            } catch (InputMismatchException ex) {
                System.out.print(ex.getMessage()); //try to find out specific reason.
            }
    
    
            try {
                if(connection!=null)
                    connection.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
            cp.removeAll();
            displayMenue();
        }

          /* For TESTING: this is the data that the GUI is getting
          
          System.out.println(title);
          System.out.println(discription);
          System.out.println(quantity);
          System.out.println(wholesale_price);
          System.out.println(sales_price);
          System.out.println(sid);
          */
        }
   });
   

        // Setup the content-pane of JFrame in BorderLayout
        cp.add(tfPanel, BorderLayout.NORTH);
        cp.add(btnPanel, BorderLayout.PAGE_END);
        //cp.add(tAreaScrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Record");
        setSize(350, 275);
        setVisible(true);
        //setForeground(new ColorUIResource(1, 2, 3));

    }//END CREATE RECORD



   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new InventoryDataBase(); // Let the constructor do the job
         }
      });
   }





   /**************************************
    * These methods checks to make sure that we are only getting integers and doubles
    * The parsing wont work otherwise
    ************************************/

   public boolean checkQuantity(String string){
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
