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
    private String FILE;
    private Scanner in;
    private ArrayList<OrderItem> array;
    private boolean product_idOK, stockOK;

    public InventoryUpdator(){
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
    //This will check to see if there is a product with the same ID
    public boolean validateProductID(){
        return product_idOK;
    }

    //This will validate that there is enough items in stock to place the order
    public boolean validateStock(){
        return stockOK;
    }

    //This will create an interface with the user to upload their file
    public void massOrdering(){}

    //This will create an interface with the user to make a single order
    public void individualOrdering(){

    }

}//FIN
