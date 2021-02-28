/**
 * The finance, marketing, and sales departments want to understand customers better but need the historical data to do so.
 * They want to have a database/pseudo-DB that stores all of the customer order
 * information along with the time and date of their orders.
 * This database will grow each simulated day.
 *
 * This will program will function as a database that the employee is able to:
 *
 * **View the inventory of the product**
 *   -- This will allow the user to look product inventory
 *
 * **Adding the order of the product
 *   -- This will add in a new entry into the database.
 *
 * **Updating the order
 *   -- Will update the entry within the database.
 *
 * **Deleting the order of the product
 *   -- Will be able to delete an entry in the database.
 *
 *
 * **Viewing the orders made within the database
 *   -- Will search the database by finding the product ID and user_email
 *   -- View the current order
 *
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class CustomerOrderDataBase {


    private Scanner console;
    private ArrayList<OrderItem> orderInfo;
    private static String FILE_NAME = "customer_orders_team1.csv";


    public CustomerOrderDataBase() {
        orderInfo = new ArrayList<>(4000000);
        console = new Scanner(System.in);
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadFile() throws FileNotFoundException {
        try {
            Scanner in = new Scanner(new FileInputStream(FILE_NAME));
            //This will get past the first line that is just the titles of the collums and not data
            String titles = in.nextLine();
            //Test System.out.println(titles);

            //This loop will extrapolate the data from each line and create the entryItem with the line's data
            while (in.hasNextLine()) {
                String[] parsedLine = in.nextLine().split(",");
                String date = parsedLine[0];
                String cust_email = parsedLine[1];
                String cust_location = parsedLine[2];
                String product_id = parsedLine[3];
                int product_quantity = Integer.parseInt(parsedLine[4]);

                OrderItem orderItem = new OrderItem(date, cust_email, cust_location, product_id, product_quantity);
                orderInfo.add(orderItem);
                //TEST System.out.println(orderItem.toString());
            }
                in.close();


        } catch (FileNotFoundException e) {
        }
        //TEST System.out.print(orderInfo.size());

    }

    //This will take all the elemnts in the array and save them back onto the CSV file
    public void saveFile() throws FileNotFoundException {
        try {
            PrintWriter out = new PrintWriter(FILE_NAME);
            //This puts back the labels that the loadFile removed
            out.println();
            int i = 0;

            while (i < orderInfo.size()) {
                String saved = orderInfo.get(i).toString();
                out.println(saved);
                i++;
            }
            out.close();
        } catch (FileNotFoundException e) {
        }

    }

    public static void main(String[] args) throws IOException {

        //Initializing the constructor
        CustomerOrderDataBase custOrder = new CustomerOrderDataBase();

        /*If the load isn't working try running the saveFile method.
        The save file will create a new file has the proper name and location in your computer to run.
        Then just copy and past the data into the new file that it creates.

        custOrder.saveFile();


         */
    }



    public void displayMenue(){
        boolean quit = false;
        System.out.println("Welcome to the Customer Order Data Base!");
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.println("Please type in the corresponding letter to proceed.");
        System.out.println();
        while (!quit) {
            // Printing out prompts to the user
            System.out.print("a.    Create a new order\n" +
                    "b.    View an order\n" +
                    "c.    Update an order\n" +
                    "d.    Delete an order\n" +
                    "f.    Quit\n");


            //This will receive the user input and process the correct char to
            //the correct if statement to proceed to the methods
            String input = console.next();
            if (input.contains("a")) {
                addOrder();
            }
            else if (input.contains("b")) {
                viewOrder();
            }
            else if (input.contains("c")) {
                updateOrder();
            }
            else if (input.contains("d")) {
                deleteOrder();
            }
            else if(input.contains("f")){ ;
                quit = true;
            }
            else{
                System.out.println("Invalid selection");
                System.out.println("Please type the corresponding letter next to the option you want.");
            }
        }
    }//FIN


    //This method will have the employee add in customer data into the database
    private void addOrder() {

    }

    //This method will update the current entry within the database
    private void updateOrder() {

    }

    //This method will delete the current entry within the database
    private void deleteOrder() {

    }

    //This method will view the order specifically with the
    // date, customer_email, customer_location, productID
    private void viewOrder() {

        System.out.println();
        System.out.println("     View Orders");
        System.out.println();


        //asks user to select which variable they would like to search by
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to search by: ");
        System.out.println(" a.   Product ID \n b.   Customer Email \n c.   Customer Location \n d.   Order Date \n");
        String attribute = console.next();


        //if a is selected, enter the product id to search for
        if (attribute.equals("a")){
            System.out.println("Enter the product id you would like to search for: ");
            String searchProductId = console.next();
            OrderItem pIdSearchingFor = null;
            int count1 = 0;
            for (int i=0; i<orderInfo.size(); i++ ){
                if (searchProductId.equalsIgnoreCase(orderInfo.get(i).getProductId())) { // if product id is found print data
                	pIdSearchingFor = orderInfo.get(i); 
                    
                    
                    System.out.println();

                    System.out.println("Product ID: " + pIdSearchingFor.getProductId()+ "     "
                            + "Quantity: " + pIdSearchingFor.getQuantity()+ "     "
                            + "Customer Email: " + pIdSearchingFor.getCustomerEmail()+ "     "
                            + "Customer Zip Code: " + pIdSearchingFor.getCustomerLocation()+ "     "
                            + "Order Date: " + pIdSearchingFor.getOrderDate());
                    
                    System.out.println();
        
        }
    
                else{
                    count1++;   //if product id is not found, add to count and compare to array size. If greater than or equal display product id not found
                    if(count1 >= orderInfo.size()){
                        System.out.println();
                        System.out.println("Product ID Not Found.");
                        System.out.println();
        }
}
            }
            //prompt to continue viewing orders or exit program
            System.out.println("Would you like to continue viewing orders? Enter yes or no: ");
            String cont = console.next();
            if(cont.equalsIgnoreCase("yes")){
                viewOrder();
            }
            else{
                ///direct to main menu eventually
            }
        }
        //if b is selected enter the customer email
        else if(attribute.equals("b")) {
            System.out.println("Enter the Customer Email you would like to search for: ");
            String searchCustomerEmail = console.next();
            OrderItem custSearchingFor = null;
            int count2 = 0;
            for (int j=0; j<orderInfo.size(); j++ ){
                if (searchCustomerEmail.equalsIgnoreCase(orderInfo.get(j).getCustomerEmail())) { // if customer email is found print data
                    custSearchingFor = orderInfo.get(j); 

        
                    System.out.println();

                    System.out.println("Product ID: " + custSearchingFor.getProductId()+ "     "
                            + "Quantity: " + custSearchingFor.getQuantity()+ "     "
                            + "Customer Email: " + custSearchingFor.getCustomerEmail()+ "     "
                            + "Customer Zip Code: " + custSearchingFor.getCustomerLocation()+ "     "
                            + "Order Date: " + custSearchingFor.getOrderDate());
                    
                    System.out.println();
                 
            }       
                else{
                    count2++;   //if email is not found, add to count and compare to array size. If greater than or equal display email not found
                    if(count2 >= orderInfo.size()){
                        System.out.println();
                        System.out.println("Email Not Found.");
                        System.out.println();
                }
                        
            }
        }
            //prompt to continue viewing orders or exit program
            System.out.println("Would you like to continue viewing orders? Enter yes or no: ");
            String cont = console.next();
            if(cont.equalsIgnoreCase("yes")){
                viewOrder();
            }
            else{
                ///direct to main menu
            }
        }
         //if c is selected enter the customer zip code
        else if(attribute.equals("c")) {
            System.out.println("Enter the Customer ZIP code you would like to search for: ");
            String searchCustomerLoc = console.next();
            OrderItem locSearchingFor = null;
            int count3 = 0;
            for (int j=0; j<orderInfo.size(); j++ ){
                if (searchCustomerLoc.equalsIgnoreCase(orderInfo.get(j).getCustomerLocation())) { // if zip code is found display data
                	locSearchingFor = orderInfo.get(j); 

                    
                    System.out.println();

                    System.out.println("Product ID: " + locSearchingFor.getProductId() + "     "
                            + "Quantity: " + locSearchingFor.getQuantity() + "     "
                            + "Customer Email: " + locSearchingFor.getCustomerEmail() + "     "
                            + "Customer Zip Code: " + locSearchingFor.getCustomerLocation() + "     "
                            + "Order Date: " + locSearchingFor.getOrderDate());

                    System.out.println();
                }
                
            
                else{
                    count3++;   //if zip code is not found, add to count and compare to array size. If greater than or equal display zip code not found
                    if(count3 >= orderInfo.size()){
                        System.out.println();
                        System.out.println("Zip Code Not Found.");
                        System.out.println();
                }
        }
            }
            //prompt to continue viewing orders or exit program
            System.out.println("Would you like to continue viewing orders? Enter yes or no: ");
            String cont = console.next();
            if(cont.equalsIgnoreCase("yes")){
                viewOrder();
            }
            else{
                ///direct to main menu
            }
    }

        //if d is selected enter the customer order date
        else if (attribute.equals("d")){
            System.out.println("Enter the date of the order you would like to search for in the format(Year-month-day e.g. 2020-01-13): ");
            String searchOrderDate = console.next();
            OrderItem dateSearchingFor = null;
            int count4 = 0;
            for (int k=0; k<orderInfo.size(); k++ ){
                if (searchOrderDate.equalsIgnoreCase(orderInfo.get(k).getOrderDate())) { // if date is found display data
                	dateSearchingFor = orderInfo.get(k); 

                    
                    System.out.println();

                    System.out.println("Product ID:     " + dateSearchingFor.getProductId()+ "     "
                            + "Quantity:        " + dateSearchingFor.getQuantity() + "     "
                            + "Customer Email:   " + dateSearchingFor.getCustomerEmail() + "     "
                            + "Customer Zip Code:       " + dateSearchingFor.getCustomerLocation() + "     "
                            + "Order Date:       " + dateSearchingFor.getOrderDate());
                    
                    System.out.println();}
                
                
                else{
                    count4++;   //if date is not found, add to count and compare to array size. If greater than or equal display date not found
                    if(count4 >= orderInfo.size()){
                        System.out.println();
                        System.out.println("Order Date Not Found.");
                        System.out.println();
                    }
                
        }
            }
            //prompt to continue viewing orders or exit program
            System.out.println("Would you like to continue viewing orders? Enter yes or no: ");
            String cont = console.next();
            if(cont.equalsIgnoreCase("yes")){
                viewOrder();
            }
            else{
                ///direct to main menu
            }
}
}
}

