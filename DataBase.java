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


public class DataBase {

    public DataBase{

    }


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
            while((line = br.readLine() != null)){
                String[] values =
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
    public static void updateRecord(){

    }

    //Method will delete entry record
    public static void deleteRecord(){

    }


}
