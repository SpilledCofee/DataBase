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
    private static Scanner console = new Scanner(System.in);   // This will receive data input from the keyboard of the user
    private ArrayList<EntryItem> records;
    private static String FILE_NAME = "inventory_team1.csv";

    public DataBase(){
        records = new ArrayList<>(400000);
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
            String input = console.next();
            if (input.contains("a")) {
                createRecord();
            }
            if (input.contains("b")) {
                lookUpRecord();
            }
            if (input.contains("c")) {
                updateRecord(console, records);
            }
            if (input.contains("d")) {
                deleteRecord();
            }
            if(input.contains("f")){
                quit = true;
            }
        }
        

    }


    // Method will create a new entry
    public static void createRecord(){

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
