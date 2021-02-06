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
    public static void  main(String [] args) throws IOException{


        Scanner console = new Scanner(System.in);   // This will receive data input from the keyboard of the user
        List<List<String>> records = new ArrayList<>();



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
            updateRecord();
        }
        if(input.contains("d")){
            deleteRecord();
        }
    }


    // This method will read the CSV file and input in array
    public void readFile(Scanner console, ArrayList<List> record) {

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
        String product_id, quantity, wholesale_cost, sale_price, supplier_id;

        // intialize scanner for user input
        Scanner user_input = new Scanner(System.in);  // create scanner

        // seperator for user readibility
        String s = "----------------------------------------"; // separator
        
        // loop for user inputs and validation, exits when user confirms entries
        boolean user_confirmed = false;
        while (!user_confirmed) {
            // module header
            
            System.out.println(s);
            System.out.println("Inventory Record Creation");
            System.out.println(s);
        
            // get user inputs for all variables
            System.out.print("Enter Product ID: ");
            product_id = user_input.nextLine();
            System.out.print("Enter Quantity: ");
            quantity = user_input.nextLine();
            System.out.print("Enter Wholesale Cost: ");
            wholesale_cost = user_input.nextLine();
            System.out.print("Enter Sale Price: ");
            sale_price = user_input.nextLine();
            System.out.print("Enter Wholesale Cost: ");
            supplier_id = user_input.nextLine();

            // check validity with user
            System.out.println(s);
            System.out.println("You entered the following values:");
            System.out.println(s);
            System.out.printf("%15s %10s %15s %12s %12s\n", "PRODUCT ID", "QUANTITY", "WHOLESALE COST", "SALE PRICE", "SUPPLIER ID");
            System.out.printf("%15s %10s %15s %12s %12s\n", product_id, quantity, wholesale_cost, sale_price, supplier_id);
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
    public static void updateRecord(){

    }

    //Method will delete entry record
    public static void deleteRecord(){

    }


}
