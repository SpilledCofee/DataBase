/*
    This class will be able to update the inventory csv by order input. It will e able to do this in two ways
        1. reading input from an order file
        2. getting individual orders from user input

        It will will call on the DataBase class and use it's methods to help update records
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class InventoryUpdator {
    private DataBase dataBase;
    private CustomerOrderDataBase orderDB;
    private String FILE;
    private Scanner in;
    //Theses might be useful if we wanted to make a
    //receipt of all the orders that went through and all the orders that failed
    private ArrayList<OrderItem> validOrders, failedOrders;
    //These two arrays are dublicates of the data bases' arrays
    private ArrayList<OrderItem> currentOrders;
    private ArrayList<EntryItem> inventory;
    private boolean product_idOK, stockOK;
    EntryItem item;

    public InventoryUpdator(){
        dataBase = new DataBase();
        try {
            dataBase.loadFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        inventory = dataBase.getRecordsArray();
    }

    //This will make a menu giving the user options to use either a file or place a single order
    public void displayMenu(){

    }

    /* This will load a file from the user
          -It calls on the CustomerOrderDataBase's load method.
          -The file name can be changed
          -There are no user prompts! :) (this keeps the code more flexible)
     */
    public void loadUserFile(String file){
        orderDB = new CustomerOrderDataBase(file);
        try {
            orderDB.loadFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        currentOrders = orderDB.getOrderArray();
        //TESTING
        //System.out.println(currentOrders.size());
        //System.out.println(currentOrders.toString());
    }

    //will call most of the other methods to validate that an order is valid
    //This will be able to access the data base and save the changes to the file....../XDm
    public void makeOrder(){

    }
    //This will check to see if there is a product with the same ID
    public boolean validateProductID( String product_id){
        item = dataBase.viewRecord(product_id);
        if(item == null){product_idOK = false;}
        else{product_idOK = true;}
        //TEST: System.out.println(item.toString());
        //TEST: System.out.println(product_idOK);
        return product_idOK;
    }

    //This will validate that there is enough items in stock to place the order
    public void validateStock(int quantity){
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
    }

    //This will create an interface with the user to upload their file
    public void massOrdering(){}

    //This will create an interface with the user to make a single order
    public void individualOrdering(){

    }
    //This will print an array's data, each with their own line.
    public void printArray(ArrayList array){
        for (int i = 0; i < array.size() ; i++) {
            System.out.println(array.get(i).toString());
        }
    }

}//FIN
