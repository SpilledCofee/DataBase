/*
        * This program will load the CSV File and the program will prompt commands to the user
        * allowing the user to head to different methods to process CSV File.
        * It will get data from the CSV File and process the data depending on the user
        * With 'createRecord' it will create a new entry with the data file
        * 'lookUpRecord' will proceed to search for the correct data file
        * 'updateRecord' will update the current entry with the data file
        * 'deleteRecord' will remove the current data entry from the data file
*/


import java.io.*;
import java.util.*;


public class DataBase {

    // temporarily disabled to allow local testing
    /*
    public DataBase{

    }
    */


    // Main method to call other methods
    public static void  main(String [] args) throws IOException {


        Scanner console = new Scanner(System.in);   // This will receive data input from the keyboard of the user
        List<EntryItem> records = new ArrayList<>();



        // Printing out prompts to the user
        System.out.print("a.    Create a new record\n" +
                "b.    Look up/read a record\n" +
                "c.    Update a record\n" +
                "d.    Delete an existing record\n" +
                "Please enter an letter prompt to proceed. ");


        //This will receive the user input and process the correct char to
        //the correct if statement to proceed to the methods
        String input = console.next();
        if(input.contains("a")){
            createRecord();
        }
        if(input.contains("b")){
            lookUpRecord();
        }
        if(input.contains("c")){
            updateRecord(console, records);
        }
        if(input.contains("d")){
            deleteRecord();
        }
    }


    // This method will read the CSV file and input in array
    public void readFile(Scanner console, ArrayList<List> record) throws IOException {

        try(BufferedReader br = new BufferedReader((new FileReader("CSV_FILE")))){
            String line;
            while (((line = br.readLine()) != null)) {
                String[] values = null; // null added to allow for testing
            }
        }
    }

    // Method will create a new entry
    public static void createRecord(){
        
        // user-defined variables
        String product_id = null, supplier_id = null;
        int quantity = 0;
        double wholesale_cost = 0, sale_price = 0;

        // intialize scanner for user input
        Scanner user_input = new Scanner(System.in);  // initialize scanner

        // seperator for user readibility
        String s = "----------------------------------------"; // separator
        
        // loop for user inputs and validation, exits when user confirms entries
        boolean user_confirmed = false;
        while (!user_confirmed) {
            
            // module header
            System.out.println(s);
            System.out.println("Create Inventory Record");
            System.out.println(s);
        
            // get user inputs for all variables

            // validate product id
            System.out.print("Enter Product ID: ");
            product_id = user_input.next();
            while ((product_id.length()) != 12) {
                System.out.println("Product ID must be 12 characters long!");
                System.out.print("Enter Product ID: ");
                user_input.next();
            }
            
            // validate quantity
            System.out.print("Enter Quantity: ");
            while (!user_input.hasNextInt()) {
                System.out.println("Quantity must be a whole number!");
                System.out.print("Enter Quantity: ");
                user_input.next();
            }
            quantity = user_input.nextInt();

            // validate wholesale cost
            System.out.print("Enter Wholesale Cost: ");
            while (!user_input.hasNextDouble()) {
                System.out.println("Wholesale cost must be whole number or decimal!");
                System.out.print("Enter Wholesale Cost: ");
                user_input.next();
            }
            wholesale_cost = user_input.nextDouble();

            // validate sale price
            System.out.print("Enter Sale Price: ");
            while (!user_input.hasNextDouble()) {
                System.out.println("Sale price must be whole number or decimal!");
                System.out.print("Enter Sale Price: ");
                user_input.next();
            }
            sale_price = user_input.nextDouble();

            // validate supplier id
            System.out.print("Enter Supplier ID: ");
            supplier_id = user_input.next();
            while ((supplier_id.length()) != 12) {
                System.out.println("Product ID must be 12 characters long!");
                System.out.print("Enter Supplier ID: ");
                user_input.next();
            }

            // create EntryItem object with user inputs
            EntryItem newItem = new EntryItem(product_id, quantity, wholesale_cost, sale_price, supplier_id);

            // confirm entries with user
            System.out.println(s);
            System.out.println("You entered the following values:");
            System.out.println(s);
            System.out.printf("%15s %10s %15s %12s %15s\n", "PRODUCT ID", "QUANTITY", "WHOLESALE COST", "SALE PRICE", "SUPPLIER ID");
            System.out.printf("%15s %10s %15s %12s %15s\n", product_id, quantity, wholesale_cost, sale_price, supplier_id);
            System.out.println(s);
            System.out.println("Is this correct?");
            System.out.print("Type 'yes' to add this record, type 'no' to start over: ");
            String inp = user_input.nextLine();
            boolean valid = false;
            while (!valid) {
                if (inp.toLowerCase().equals("yes")) {
                    valid = true;
                    user_confirmed = true;
                } else if (inp.toLowerCase().equals("no")) {
                    valid = true;
                } else {
                    System.out.print("Invalid response. Please type 'yes' or 'no': ");
                    inp = user_input.nextLine();
                }
            }
        }
        
        // alert user and get next step
        System.out.println(s);
        System.out.println("Entry added to inventory!");
        System.out.println(s);
        System.out.println("Do you want to add another entry?");
        System.out.print("Type 'yes' to add another entry, or 'no' to exit to main menu: ");
        String inp = user_input.nextLine();
        boolean valid = false;
        while (!valid) {
            if (inp.toLowerCase().equals("yes")) {
                valid = true;
                createRecord();
            } else if (inp.toLowerCase().equals("no")) {
                valid = true;                                       // possibly direct to main menu later
            } else {
                System.out.print("Invalid response. Please type 'yes' or 'no': ");
                inp = user_input.nextLine();
            }
        }
        
        // close scanner
        user_input.close();

    }

    // Method will search the data based and look the record
    public static void lookUpRecord(){

    }

    //Method will update entry
    public static void updateRecord(Scanner scanner, List<EntryItem> records){
        System.out.println("Enter product Id of the product to update: ");
        String productId = scanner.next();

        EntryItem itemToUpdate = null;
        for (int i=0; i<records.size(); i++ ){
            if (productId.equals(records.get(i).getProduct_id())) {
                itemToUpdate = records.get(i);
            }
        }

        if (itemToUpdate == null){
            System.out.println(" Invalid product id. ");
            return;
        }

        System.out.println("Current item values: product id: " + itemToUpdate.getProduct_id() + " quantity: " + itemToUpdate.getQuantity()
                + " wholesale cost: " + itemToUpdate.getWholesale_cost() + " sale price: " + itemToUpdate.getSale_price() + " supplier id: " +
                itemToUpdate.getSupplier_id());

        System.out.println("Enter the attribute to update: ");
        System.out.println(" a: product id \n b: quantity \n c: wholesale cost \n d: sale price \n e: supplier id ");
        String attribute = scanner.next();

        if (attribute.equals("a")){
            System.out.println("Enter the new product id: ");
            String newProductId = scanner.next();
            itemToUpdate.setProductId(newProductId);
        }
        else if(attribute.equals("b")) {
            System.out.println("Enter the new quantity: ");
            int newQuantity = Integer.parseInt(scanner.next());
            itemToUpdate.setQuantity(newQuantity);
        }
        else if(attribute.equals("c")) {
            System.out.println("Enter the new wholesale cost: ");
            double newWholesaleCost = Double.parseDouble(scanner.next());
            itemToUpdate.setWholesaleCost(newWholesaleCost);
        }
        else if(attribute.equals("d")) {
            System.out.println("Enter the new sale price: ");
            double newSalePrice = Double.parseDouble(scanner.next());
            itemToUpdate.setSalePrice(newSalePrice);
        }
        else if (attribute.equals("e")){
            System.out.println("Enter the new supplier id: ");
            String newSupplierId = scanner.next();
            itemToUpdate.setSupplierId(newSupplierId);
        }
        else{
            System.out.println("Invalid attribute");
            return;
        }

        System.out.println("Updated item values: product id: " + itemToUpdate.getProduct_id() + " quantity: " + itemToUpdate.getQuantity()
                + " wholesale cost: " + itemToUpdate.getWholesale_cost() + " sale price: " + itemToUpdate.getSale_price() + " supplier id: " +
                itemToUpdate.getSupplier_id());
    }

    //Method will delete entry record
    public static void deleteRecord(){

    }
}
