/*
    This class will be able to update the inventory csv by order input. It will e able to do this in two ways
        1. reading input from an order file
        2. getting individual orders from user input

        It will will call on the DataBase class and use it's methods to help update records
 */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.ArrayList;

public class InventoryUpdator {
    private DataBase dataBase;
    private String FILE;
    private Scanner in;
    private ArrayList<OrderItem> array;
    private boolean product_idOK, stockOK;
    EntryItem item;

    public InventoryUpdator(){
        in = new Scanner(System.in);
        validOrders = new ArrayList<>(4000000);
        failedOrders = new ArrayList<>(4000000);
        dataBase = new DataBase();
        array = new ArrayList<>(60000);
        try {
            dataBase.loadFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }

    //This will make a menu giving the user options to use either a file or place a single order
    public void displayMenu(){

    }

    //This will load a file from the user
    public void loadUserFile(){

    }

    //will call most of the other methods to validate that an order is valid
    //This will be able to access the data base and save the changes to the file....../XDm
    public void makeOrder(){

    }

    private void processOrder(OrderItem order) throws FileNotFoundException {
        // Validating product id
        if (!validateProductID(order.getProductId())){
            System.out.println("Product does not exist in inventory.");

            // Product id is invalid, adding order to failed orders
            failedOrders.add(order);

            return;
        }

        // Validating stock
        if (!validateStock(order.getQuantity())){
            if (item.getQuantity() == 0){
                System.out.println("The product: "+ order.getProductId() + "is out of stock.");
                return;
            }
            else{
                System.out.println("Insufficient stock for product: " + order.getProductId() + " Only " + item.getQuantity() + " remaining");
            }

            // Not enough stock for product, adding order to failed orders
            failedOrders.add(order);

            return;
        }

        // Order is valid, updating quantity
        int currentQuantity = item.getQuantity();
        item.setQuantity(currentQuantity - order.getQuantity());

        validOrders.add(order);

        System.out.println();
        System.out.println("Order completed successfully. New inventory value for product: ");
        System.out.println("Product ID:        " + item.getProduct_id()
                + "\nQuantity:          " + item.getQuantity()
                + "\nWhole Sale Cost:   " + item.getWholesale_cost()
                + "\nSale Price:        " + item.getSale_price()
                + "\nSupplier ID:       " + item.getSupplier_id());

        dataBase.saveFile();
    }

    private OrderItem GetOrder(){
        // Variables
        String date = null, cust_email = null, cust_location = null, product_id = null;
        int quantity = 0;

        // Date-Time Format "YYYY-MM-DD"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        boolean date_Valid = false;

        // Separator for user readability
        String s = "----------------------------------------"; // separator

        boolean user_confirmed = false;
        while (!user_confirmed) {
            // Getting the user date for entry
            System.out.print("Enter Date(YYYY-MM-DD): ");
            date = in.next();
            //This while loop will check if the date is valid
            while (!date_Valid) {
                try {
                    LocalDate.parse(date, dateFormatter);
                    System.out.println("Validated Date");
                    date_Valid = true;
                } catch (DateTimeParseException e) {
                    date_Valid =  false;
                    System.out.println("Invalid Date");
                    System.out.print("Enter valid date(YYYY-MM-DD):");
                    date = in.next();
                }
            }

            // Getting user email
            System.out.print("Enter customer email: ");
            cust_email = in.next();
            boolean flag = false;
            int countr = 0, countd = 0;
            while(!flag) {
                //This loop will check if the email is valid
                for (int i = 0; i < cust_email.length(); i++) {
                    if (cust_email.charAt(i) == '@') {
                        countr++;
                        if (countr > 1) {
                            flag = false;
                            break;
                        }
                        if (i >= 1) flag = true;
                        else {
                            flag = false;
                            break;
                        }

                    }
                    if (cust_email.charAt(i) == '.') {
                        countd++;
                        if (countd > 1) {
                            flag = false;
                            break;
                        }
                        if (i >= 3) flag = true;
                        else {
                            flag = false;
                            break;
                        }
                    }
                    if (cust_email.indexOf(".") - cust_email.indexOf("@") >= 2) {
                        flag = true;
                    }
                    if (!flag) break;
                }
                if (flag && cust_email.length() >= 5) {
                    System.out.println("Validated Email");
                    break;
                } else {
                    System.out.println("Invalid Email");
                }
            }

            //Validate the customer ZIP code
            System.out.print("Enter ZIP Code: ");
            cust_location = in.next();
            while((cust_location.length()) != 5){
                System.out.println("ZIP Code must be 5 characters long");
                System.out.print("Enter ZIP Code: ");
                cust_location = in.next();
            }

            // Validate product id
            System.out.print("Enter Product ID: ");
            product_id = in.next();
            while ((product_id.length()) != 12) {
                System.out.println("Product ID must be 12 characters long!");
                System.out.print("Enter Product ID: ");
                product_id = in.next();
            }

            // Validate quantity
            System.out.print("Enter Quantity: ");
            while (!in.hasNextInt()) {
                System.out.println("Quantity must be a whole number!");
                System.out.print("Enter Quantity: ");
                in.next();
            }
            quantity = in.nextInt();

            //Confirming Entries
            System.out.println(s);
            System.out.println("You entered the following values:");
            System.out.println(s);
            System.out.printf("%-11s %-20s %-20s  %-18s %-11s\n", "|DATE:|", "|CUSTOMER EMAIL:|", "|CUSTOMER LOCATION:|", "|PRODUCT ID:|", "|QUANTITY:|");
            System.out.printf("%-11s %-20s %-20s  %-18s %11s\n", date, cust_email, cust_location, product_id, quantity);
            System.out.println(s);
            System.out.println("Is this correct?");
            System.out.print("Type 'yes' to add this record, type 'no' to start over: ");
            String inp = in.next();
            boolean validated = false;
            while (validated == false) {
                if (inp.toLowerCase().equals("yes")) {
                    validated = true;
                    user_confirmed = true;
                }
                else if (inp.toLowerCase().equals("no")) {
                    validated = true;

                }
                else {
                    System.out.print("Invalid response. Please type 'yes' or 'no': ");
                    inp = in.next();
                }
            }
        }

        return new OrderItem(date, cust_email, cust_location, product_id, quantity);
    }

    //This will check to see if there is a product with the same ID
    public boolean validateProductID( String product_id){
        item = dataBase.viewRecord(product_id);
        if(item == null){ product_idOK = false;}
        else{product_idOK = true;}
        //TEST: System.out.println(item.toString());
        //TEST: System.out.println(product_idOK);
        return product_idOK;
    }

    //This will validate that there is enough items in stock to place the order
    public boolean validateStock(int quantity){
        //automatically making it false reduces the needs for checks
        stockOK = false;
        //If the product doesn't exist then there is not need to continue
        if(product_idOK){
            //this checks how much the Entryitem has;
            int stock = item.getQuantity();
            if(quantity <= stock) {
                stockOK = true;
            }
        }

        return stockOK;
    }

    //This will create an interface with the user to upload their file
    public void massOrdering(){}

    //This will create an interface with the user to make a single order
    public void individualOrdering(){
    public void individualOrdering() throws FileNotFoundException {
        // Getting order information from user
        OrderItem order = GetOrder();

        // Processing order
        processOrder(order);
    }
}//FIN
