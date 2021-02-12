/*
        * This program will load the CSV File and the program will prompt commands to the user
        * allowing the user to head to different methods to process CSV File.
        * It will get data from the CSV File and process the data depending on the user
        * With 'createRecord' it will create a new entry with the data file
        * 'lookUpRecord' will proceed to search for the correct data file
        * 'updateRecord' will update the current entry with the data file
'deleteRecord' will remove the current data entry from the data file
*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DataBase {
    private Scanner console;   // This will receive data input from the keyboard of the user
    private ArrayList<EntryItem> records;
    private static String FILE_NAME = "inventory_team1.csv";


    public DataBase(){
        records = new ArrayList<>(400000);
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
            String titiles = in.nextLine();
            //System.out.println(titiles);
            while(in.hasNextLine()) {
                String line = in.nextLine();
                int end = line.indexOf(",", 0);
                String product_id = line.substring(0, end);
                int start = end + 1;
                end = line.indexOf(",", start);
                String tempQuantity = line.substring(start, end);
                int quantity = Integer.parseInt(tempQuantity);
                start = end + 1;
                end = line.indexOf(",", start);
                String tempWholesale_cost = line.substring(start, end);
                double wholesale_cost = Double.parseDouble(tempWholesale_cost);
                start = end + 1;
                end = line.indexOf(",", start);
                String tempSale_price = line.substring(start, end);
                double sale_price = Double.parseDouble(tempSale_price);
                String supplier_id = line.substring(end + 1);

                EntryItem entryItem = new EntryItem(product_id, quantity, wholesale_cost, sale_price, supplier_id);
                records.add(entryItem);
            }
            in.close();

        }catch (FileNotFoundException e) { }
        //TESTING PURPOSES
        //System.out.println(records.size());
        //System.out.println(records.get(75).toString());
    }
    public void saveFile() throws FileNotFoundException {
        try {
            PrintWriter out = new PrintWriter(FILE_NAME);
            out.println("product_id,quantity,wholesale_cost,sale_price,supplier_id");
            int i = 0;

            while( < records.size()){
                String saved = records.get(i).toString();
                out.println(saved);
                i++;
            }
            out.close();
        } catch(FileNotFoundException e) {}

    }

    // Main method to call other methods
    public static void  main(String [] args) throws IOException{
        boolean quit = false;
        DataBase dataBase = new DataBase();
        while (!quit) {
            // Printing out prompts to the user
            System.out.print("a.    Create a new record\n" +
                    "b.    Look up/read a record\n" +
                    "c.    Update a record\n" +
                    "d.    Delete an existing record\n" +
                    "f.    Quit\n" +
                    "Please enter an letter prompt to proceed. ");


            //This will receive the user input and process the correct char to
            //the correct if statement to proceed to the methods
            String input = dataBase.console.next();
            if (input.contains("a")) {
                dataBase.createRecord();
            }
            if (input.contains("b")) {
                dataBase.lookUpRecord();
            }
            if (input.contains("c")) {
                dataBase.updateRecord(dataBase.console, dataBase.records);
            }
            if (input.contains("d")) {
                dataBase.deleteRecord(dataBase.console, dataBase.records);
            }
            if(input.contains("f")){
                dataBase.saveFile();
                quit = true;
            }
        }


    }


    // Method will create a new entry
    public void createRecord(){

    }


    // Method will search the database and look for a specific record
    public void lookUpRecord(){

        // Variables
        String entered_product_id;

        // Mirrors back to user their chosen function (in this case to look-up)
        System.out.println("\n------------------------------------------");
        System.out.println("==> You have chosen to look up a record!");
        System.out.println("------------------------------------------\n");

        // Prompts user for product id
        Scanner lookUpScanner = new Scanner(System.in); // Look-up scanner for user's product id
        System.out.println("-> Please Enter the Product_id from the Record you would like to view:"); // prompt user
        entered_product_id = lookUpScanner.nextLine(); // Read user's product id

        // If product id is less than or greater than 12 characters it's invalid so notify user
        if (entered_product_id.length() < 12 || entered_product_id.length() > 12){
            System.out.println("*** Error - Invalid product_id entry: Not 12 characters long! ***"); // Error Message
        }

        // Else if it's correct, mirror back entered product id
        else {
            System.out.println("\n-------------------------------------");
            System.out.println("You Entered: " + entered_product_id);
            System.out.println("-------------------------------------");
        }

        // Read CSV file, look to see if user's product id exists
        // If so, print appropriate information, else notify user that it does not exist/not found
        try {

            // Variables
            String line = "";

            // Read CSV file and input it into array --- basically same as method readFile()
            BufferedReader br = new BufferedReader(new FileReader("inventory_team1.csv"));
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // If current line contains the user's product id, then return that whole row of information
                if(line.contains(entered_product_id)){
                    System.out.println("Requested Info: ");
                    System.out.println("Product ID: " + values[0] +  ", Quantity: " + values[1] + ", Wholesale Cost: " // Prints values
                            + values[2] + ", Sale Price: " + values[3] + ", Supplier ID: " + values[4]);
                    break;
                }
            }

            // If current line is null, then state user's product id was not found
            if((line = br.readLine()) == null){
                System.out.println("\n-> PRODUCT ID NOT FOUND IN DATABASE! <-"); // Prints message
            }

        }
        // Catch statements for the above try statements
        catch (FileNotFoundException e){
            System.out.println("\n*** ERROR: FILE WAS NOT FOUND! ***"); // Error message if File was not found
        }
        catch (IOException e) {
            System.out.println("\n*** ERROR: Checking/Reading Lines! ***"); // Error message if checking/reading lines goes wrong
        }
    }


    //Method will update entry
    public void updateRecord(Scanner scanner, List<EntryItem> records){
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
    public static void deleteRecord(Scanner scanner, List<EntryItem> records){

       //asks user to enter product id
       System.out.println("");
       System.out.println("Enter product Id of the product to delete: ");
       System.out.println("");
       String productId = scanner.next();

       //searches entryitem for product to delete
       EntryItem itemToDeleItem = null;
       for (int i=0; i<records.size(); i++ ){
        if (productId.equals(records.get(i).getProduct_id())) {
            itemToDeleItem = records.get(i);
        }
       }
       //if item doesn't exists gives error
       if (itemToDeleItem == null){
        System.out.println("");
        System.out.println(" Invalid product id. Record does not exist.\n");
        return;
       }

       //shows all fields of entry to be deleted
       System.out.println("");
       System.out.println("Current item values: \nproduct id: " + itemToDeleItem.getProduct_id() + " \nquantity: " + itemToDeleItem.getQuantity()
            + " \nwholesale cost: " + itemToDeleItem.getWholesale_cost() + " \nsale price: " + itemToDeleItem.getSale_price() + " \nsupplier id: " +
            itemToDeleItem.getSupplier_id());

       //asks user if they want to delete the item
       System.out.println("\nAre you sure you want to delete this record?: y for yes, n for no ");
       String attribute = scanner.next();

       //if user selects yes then the item is deleted using the index
       if (attribute.equals("y")){

        records.remove(itemToDeleItem);
        System.out.println("\nRecord has been deleted.");
        System.out.println("");
       }
       //if user selects anything else they are told the item was not deleted and are brought back to the menu
       else{
        System.out.println("\nRecord was NOT deleted.\n");
    }
    }
}
