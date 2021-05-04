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
import java.sql.Timestamp;
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
   private String url, username, password;

    private static String FILE_NAME;


    //These are unchaing integers that will assist with choosing options
    private boolean PID_SELCTION = false;
    private boolean OID_SELECTION =  false;
    private boolean EMAIL_SELECTION = false;
    private boolean ADDRESS_SELECTION = false;
    private boolean DATE_SELECTION = false;

    String menuSelection;//Used in displayMenue()
 
    // Constructor to setup the GUI components and event handlers
    public CustomerOrderDataBase() {
        getLogin();
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
       Container cp = getContentPane();
       cp.setLayout(new FlowLayout());

       // Create JComboBox for getting the prompt to run
       cp.add(new JLabel("Select a prompt:"));

       final String[] prompts = {"Create a new record", "Search records", "Update a record", "Delete an existing record"};
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
               else if(menuSelection.equals("Search records")){
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

    } //END GET LOGIN

    public void searchRecords(){
        // Retrieve the content-pane of the top-level container JFrame
        // All operations done on the content-pane
        JRadioButton pid, oid, email, address, date;
        JTextField searchPid, searchOid, searchEmail, searchAddress, searchDate;  // Use Swing's JTextField instead of AWT's TextField
        JButton submitButton;    // Using Swing's JButton instead of AWT's Button

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        //This panel will make so it will make a new row for the textFields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        panel.setVisible(true);

        searchPid = new JTextField(10);
        searchPid.setEditable(false);
        searchOid = new JTextField(10);
        searchOid.setEditable(false);
        searchEmail = new JTextField(10);
        searchEmail.setEditable(false);
        searchAddress = new JTextField(10);
        searchAddress.setEditable(false);
        searchDate = new JTextField(10);
        searchDate.setEditable(false);

        pid = new JRadioButton("Product ID");
        pid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PID_SELCTION = true;
                OID_SELECTION =  false;
                EMAIL_SELECTION = false;
                ADDRESS_SELECTION =  false;
                DATE_SELECTION = false;
                searchPid.setEditable(true);
                searchEmail.setEditable(false);
                searchOid.setEditable(false);
                searchAddress.setEditable(false);
                searchDate.setEditable(false);
            }
        });
        panel.add(pid);
        panel.add(searchPid);

        oid = new JRadioButton("Order ID");
        oid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PID_SELCTION = false;
                OID_SELECTION =  true;
                EMAIL_SELECTION = false;
                ADDRESS_SELECTION =  false;
                DATE_SELECTION = false;
                searchPid.setEditable(false);
                searchEmail.setEditable(false);
                searchOid.setEditable(true);
                searchAddress.setEditable(false);
                searchDate.setEditable(false);
            }
        });
        panel.add(oid);
        panel.add(searchOid);

        email = new JRadioButton("Email Address");
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PID_SELCTION = false;
                OID_SELECTION =  false;
                EMAIL_SELECTION = true;
                ADDRESS_SELECTION =  false;
                DATE_SELECTION = false;
                searchPid.setEditable(false);
                searchEmail.setEditable(true);
                searchOid.setEditable(false);
                searchAddress.setEditable(false);
                searchDate.setEditable(false);
            }
        });
        panel.add(email);
        panel.add(searchEmail);

        address = new JRadioButton("Customer Zipcode");
        address.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PID_SELCTION = false;
                OID_SELECTION =  false;
                EMAIL_SELECTION = false;
                ADDRESS_SELECTION =  true;
                DATE_SELECTION = false;
                searchPid.setEditable(false);
                searchEmail.setEditable(false);
                searchOid.setEditable(false);
                searchAddress.setEditable(true);
                searchDate.setEditable(false);
            }
        });
        panel.add(address);
        panel.add(searchAddress);

        date = new JRadioButton("Date(ex: 2021-01-01)");
        date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PID_SELCTION = false;
                OID_SELECTION =  false;
                EMAIL_SELECTION = false;
                ADDRESS_SELECTION =  false;
                DATE_SELECTION = true;
                searchPid.setEditable(false);
                searchEmail.setEditable(false);
                searchOid.setEditable(false);
                searchAddress.setEditable(false);
                searchDate.setEditable(true);
            }
        });
        panel.add(date);
        panel.add(searchDate);

        cp.add(panel);
        //This makes it so only one radio button can be slected at a time
        ButtonGroup btnGp = new ButtonGroup();
        btnGp.add(pid);
        btnGp.add(oid);
        btnGp.add(email);
        btnGp.add(address);
        btnGp.add(date);

        //Jbutton
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String record = null;
                if(PID_SELCTION && searchPid.getText() != null){
                    record = searchPid.getText();
                    printOrderItemResults(record);

                }
                else if(OID_SELECTION && searchOid.getText() != null){
                    record = searchOid.getText();
                    printCustomerOrderResults(1, record);
                }
                else if(EMAIL_SELECTION && searchEmail.getText() != null){
                    record = searchEmail.getText();
                    printCustomerOrderResults(2, record);
                }
                else if(ADDRESS_SELECTION && searchAddress.getText() != null){
                    record = searchAddress.getText();
                    printCustomerOrderResults(3, record);
                }
                else if(DATE_SELECTION && searchDate.getText() != null){
                    record = searchEmail.getText();
                    printCustomerOrderResults(4, record);
                }

                cp.removeAll();
                displayMenue();
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
        setSize(400, 200);         // "super" JFrame sets initial size
        setVisible(true);          // "super" JFrame shows
    }//END SEARCH RECORDS

    public void printOrderItemResults(String itemLookup){
        try {
            System.out.println("searching for a product_id that has " + itemLookup);
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_order_items WHERE " + "product_id" + "= '" + itemLookup + "'", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet myRs = myStmt.executeQuery();

            if (myRs.next()) {

                myRs.beforeFirst();

                while (myRs.next()) {
                    System.out.println();
                    System.out.println("Order ID: " + myRs.getInt("order_id"));
                    System.out.println("Email: " + myRs.getString("user_id"));
                    System.out.println("Product ID: " + myRs.getString("product_id"));
                    System.out.println("Quantity: " + myRs.getInt("quantity"));
                    System.out.println("Sale Price: " + myRs.getString("sale_price"));
                    System.out.println("Order Total: " + myRs.getDouble("item_total"));
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

    public void printCustomerOrderResults(int fieldtype, String itemLookup){

        String query;

        switch(fieldtype){
            case 1: query = "order_id";
                break;
            case 2: query = "user_id";
                break;
            case 3: query = "shipping_zipcode";
                break;
            case 4: query = "ordered_at";
                break;
            default: query = "order_id";
                break;

        }
        try {
            System.out.println("searching for a " + query +" that has " + itemLookup);
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement myStmt;
            if (fieldtype == 4){
                myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE ordered_at Like('%" + itemLookup + "%')", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            }
            else{
                myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE " + query + "= '" + itemLookup + "'", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            }

            ResultSet myRs = myStmt.executeQuery();
            if (myRs.next()) {
                //sets pointer to beginning of query
                myRs.beforeFirst();
                //prints all results of query with same zip
                while (myRs.next()) {
                    System.out.println();
                    System.out.println("Order ID: " + myRs.getInt("order_id"));
                    System.out.println("Date: " + myRs.getString("ordered_at"));
                    System.out.println("Status: " + myRs.getString("order_status"));
                    System.out.println("Email: " + myRs.getString("user_id"));
                    System.out.println("Quantity: " + myRs.getInt("order_quantity"));
                    System.out.println("Order total: " + myRs.getDouble("order_total"));
                    System.out.println("Street: " + myRs.getString("shipping_street"));
                    System.out.println("City: " + myRs.getString("shipping_city"));
                    System.out.println("Street: " + myRs.getString("shipping_state"));
                    System.out.println("Zip Code: " + myRs.getString("shipping_zipcode"));
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
        JTextField userIdText, productIdText, orderQuantityText, shippingStreetText, shippingCityText, shippingStateText, shippingZipcodeText;
        JLabel label = new JLabel();

        //These also have getter methods that we can extract the values from the GUI
        JPanel tfPanel = new JPanel(new GridLayout(7, 2, 10, 2));//6 rows and 2 columns
        tfPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JButton button = new JButton("Add Record");

        //(Row 1)
        tfPanel.add(new JLabel("Customer email or user id"));
        userIdText = new JTextField(10);
        tfPanel.add(userIdText);

        //(Row 2)
        tfPanel.add(new JLabel("Product id"));
        productIdText = new JTextField(10);
        tfPanel.add(productIdText);

        //(Row 3)
        tfPanel.add(new JLabel("Order quantity"));
        orderQuantityText = new JTextField(10);
        tfPanel.add(orderQuantityText);

        //(Row 4)
        tfPanel.add(new JLabel("Shipping street"));
        shippingStreetText = new JTextField(10);
        tfPanel.add(shippingStreetText);

        //(Row 5)
        tfPanel.add(new JLabel("Shipping city"));
        shippingCityText = new JTextField(10);
        tfPanel.add(shippingCityText);

        //(Row 6)
        tfPanel.add(new JLabel("Shipping state (ex. CO)"));
        shippingStateText = new JTextField(10);
        tfPanel.add(shippingStateText);

        //(Row 7)
        tfPanel.add(new JLabel("Shipping zip code"));
        shippingZipcodeText = new JTextField(10);
        tfPanel.add(shippingZipcodeText);

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

                if(userIdText.getText().isEmpty()){
                    label.setText("Enter a user id!");
                }
                else if(productIdText.getText().isEmpty()){
                    label.setText("Enter a product id!");
                }
                else if(!checkQuantity(orderQuantityText.getText())){
                    label.setText("Invalid Quantity!");
                }
                else if(orderQuantityText.getText().length() > 8){
                    label.setText("Quantity is too Big");
                }
                else if(orderQuantityText.getText().isEmpty()){
                    label.setText("Enter a Quantity!");
                }
                else if(shippingStreetText.getText() == null){
                    label.setText("Enter shipping street");
                }
                else if(shippingCityText.getText().isEmpty()){
                    label.setText("Enter shipping city");
                }
                else if(shippingStateText.getText().isEmpty()){
                    label.setText("Enter shipping state (ex. CO)");
                }
                else if(shippingStateText.getText().length() > 2){
                    label.setText("Invalid state, enter abbreviation (ex. CO)");
                }
                else if(shippingZipcodeText.getText().isEmpty()){
                    label.setText("Enter shipping zip code!");
                }
                else if(shippingZipcodeText.getText().length() > 5){
                    label.setText("Invalid zip code!");
                }
                else{
                    String userId = userIdText.getText();
                    int productId = Integer.parseInt(productIdText.getText());
                    int quantity = Integer.parseInt(orderQuantityText.getText());
                    String street = shippingStreetText.getText();
                    String city = shippingCityText.getText();
                    String state = shippingStateText.getText();
                    String custZip = shippingZipcodeText.getText();

                    Connection connection = null;

                    try {
                        connection = DriverManager.getConnection(url, username, password);

                        String query = " INSERT INTO new_customer_orders (ordered_at, user_id, shipping_street, shipping_city, shipping_state, shipping_zipcode)" +
                                " VALUES (?, ?, ?, ?, ?, ?)";

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        PreparedStatement prepStmt = connection.prepareStatement(query);
                        prepStmt.setTimestamp(1, timestamp);
                        prepStmt.setString (2, userId);
                        prepStmt.setString(3, street);
                        prepStmt.setString(4, city);
                        prepStmt.setString(5, state);
                        prepStmt.setString(6, custZip);

                        prepStmt.execute();

                        PreparedStatement myStmt3 = connection.prepareStatement("Select sale_price FROM new_inventory WHERE product_id = '" + productId + "'");
                        ResultSet myRs = myStmt3.executeQuery();

                        //Should this be something that is auto calculated with the quantity from the frontend or inventory?
                        if(myRs.next()){
                            PreparedStatement prepStmt2 = connection.prepareStatement("SELECT order_id FROM new_customer_orders ORDER BY order_id DESC LIMIT 1");

                            ResultSet myRs1 = prepStmt2.executeQuery();

                            if (myRs1.next()){
                                int oid = myRs1.getInt("order_id");

                                double price = myRs.getDouble("sale_price");
                                double total = quantity * price;

                                String iquery = " INSERT INTO new_order_items (order_id, user_id, product_id, quantity, sale_price, item_total) VALUES (?, ?, ?, ?, ?, ?)";

                                PreparedStatement prepStmt1 = connection.prepareStatement(iquery);
                                prepStmt1.setInt(1, oid);
                                prepStmt1.setString (2, userId);
                                prepStmt1.setInt(3, productId);
                                prepStmt1.setInt (4, quantity);
                                prepStmt1.setDouble(5, price);
                                prepStmt1.setDouble(6, total);

                                prepStmt1.execute();

                                PreparedStatement myStmt6 = connection.prepareStatement("SELECT SUM(quantity) total_quantity FROM new_order_items WHERE order_id = '" + oid + "'");

                                ResultSet myRs2 = myStmt6.executeQuery();
                                if(myRs2.next()){

                                    int item_total = myRs2.getInt("total_quantity");
                                    PreparedStatement myStmt7 = connection.prepareStatement("SELECT SUM(item_total) total_cost FROM new_order_items WHERE order_id = '" + oid + "'");
                                    ResultSet myRs3 = myStmt7.executeQuery();
                                    if(myRs3.next()){


                                        double order_total = myRs3.getDouble("total_cost");
                                        PreparedStatement prepStmt3 = connection.prepareStatement("UPDATE new_customer_orders SET order_total = '"+ order_total +"' WHERE order_id = '"+ oid +"'");
                                        prepStmt3.execute();
                                        PreparedStatement prepStmt4 = connection.prepareStatement("UPDATE new_customer_orders SET order_quantity = '"+ item_total +"' WHERE order_id = '"+ oid +"'");
                                        prepStmt4.execute();

                                    }
                                }
                            }

                            cp.removeAll();
                            displayMenue();
                        }
                        else{
                            label.setText("Product id not found in inventory!");

                            System.out.println();
                            System.out.println("Product id not found in inventory!");
                            System.out.println();
                        }

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
                }
            }
        });

        // Setup the content-pane of JFrame in BorderLayout
        cp.add(tfPanel, BorderLayout.NORTH);
        cp.add(btnPanel, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Record");
        setSize(400, 350);
        setVisible(true);
        //setForeground(new ColorUIResource(1, 2, 3));
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


}//FIN!!!
