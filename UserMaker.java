/*
This is an interface that can create a new employee or customer. It uses a rank system to determine if
the person using it has permission to create an employee
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class UserMaker {

    // These ranks can't be used to determine how much someone can interface with the data base
    // may are may not be useful;
    private final int MASTER = 1;
    private final int EMPLOYEE = 2;
    private final int CUSTOMER = 3;

    //These variables are validate that users are putting in good info
    //Probably not needed but there just in case.
    private boolean firstOK, lastOK, emailOK, locationOK;

    private String firstName, lastName, email, location, password;
    private Password pass;
    private int rank;
    private Scanner in;
    private String USER_FILE = "user_information.csv";
    private ArrayList<User> userArray;
    //making a new instance of this class will have it run though its methods
    public UserMaker(){
        userArray = new ArrayList<>(2000);
        in = new Scanner(System.in);
        rank = CUSTOMER;//default
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome!");
        chooseCreator();
    }

    //This will run all the methods that get valid pieces of info
    public void createNewUser(){
        printRules();
        firstName = createFirstName();
        lastName = createLastName();
        email = createEmail();
        //userName = createUserName();
        boolean passok = false;
        pass = new Password();
        while(passok == false) {
            pass.printRules();
            pass.inputPassword();
            pass.checkPassword();
            passok = pass.getValid();
        }
        password = pass.getPassword();
        location = createLocation();

        //The methods themselves should have enough checkers to make sure all pieces of info are good
        //But this is a final precausion just in case.
        //REFERENCE: first_name,last_name,email,location,user_name,password,rank
        if(firstOK && lastOK && emailOK && locationOK) {
            User user = new User(firstName, lastName, email, location, password, rank);
            userArray.add(user);

            try {
                saveFile();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            System.out.println("User: " + email + " has been added!");
            System.exit(1);
        }
        else{
            System.out.println("Sorry, something went wrong!");
        }
    }
    //Only masters are given access to this method and so they can choose to make a regular employee or another master
    private void chooseEmployeeType() {
        int authorityLVL = 0;
        String line = null;

        System.out.println("How much access do you want to give this new employee?");
        System.out.println("for limited access type 'a'.");
        System.out.println("For full access type 'b'.");
        while (authorityLVL == 0) {
            line = in.nextLine();
            if(line.contains("a")){
                authorityLVL = rank = EMPLOYEE;
            }
            else if(line.contains("b")){
                System.out.println("Are you sure you want to give this person full access to the data base?");
                System.out.println("'yes' or 'no'");
                String answer = in.nextLine();
                if(answer.contains("yes")){
                    authorityLVL = rank = MASTER;
                }
                else{
                    authorityLVL= rank = 2;
                    System.out.println("That's ok.");
                    System.out.println("Your new employee has only been given limited access for now.");
                    System.out.println("You can make up your mind later.");
                }
            }
            else if(line.contains("b")){
                authorityLVL = 2;
                System.out.println("you have given" + firstName + "limited access.");
             }
            else{
                System.out.println("Invalid answer: Type 'a' or 'b'");
            }
        }
    }
//This loads the user_information.csv File
    public void loadFile() throws FileNotFoundException {
        try {
            Scanner fileReader = new Scanner(new FileInputStream(USER_FILE));
            //This will get past the first line that is just the titles of the columns and not data
            String titles = fileReader.nextLine();
            //This loop will extrapulate the data from each line and create a user with the line's data
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                int end = line.indexOf(",", 0);
                String first_name = line.substring(0, end);
                int start = end + 1;
                end = line.indexOf(",", start);
                String last_name = line.substring(start, end);
                start = end + 1;
                end = line.indexOf(",", start);
                String e_mail = line.substring(start, end);
                start = end + 1;
                end = line.indexOf(",", start);
                String location = line.substring(start, end);
                start = end +1;
                end = line.indexOf(",", start);
                String password = line.substring(start, end);
                String tempRank = line.substring(end + 1);
                int rank = Integer.parseInt(tempRank);
                User user = new User(first_name, last_name, e_mail, location, password, rank);
                userArray.add(user);}
                fileReader.close();

            }catch (FileNotFoundException fileNotFoundException) { }
        //TESTING!!!!
        //System.out.println(userArray.get(0).getFirstName());
        //System.out.println(userArray.get(0).getEmail());
    }

    //This will take all the userArray in the array and save them back onto the CSV file
    public void saveFile() throws FileNotFoundException {
        try {
            PrintWriter out = new PrintWriter(USER_FILE);
            //This puts back the labels that the loadFile removed
            out.println("first_name,last_name,email,location,user_name,password,rank");
            int i = 0;

            while(i < userArray.size()){
                String saved = userArray.get(i).toString();
                out.println(saved);
                i++;
            }
            out.close();
            } catch(FileNotFoundException e) {}

    }

    //This prints out the rules for creating a new user
    public void printRules(){
        System.out.println("Please provide the following information:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("4. Zip code");
        System.out.println("5. A user name");
        System.out.println("6. A password");
    }

    //This will prompt user for their first name and verify that it has a capital first letter
    public String createFirstName(){
        String name = null;
        firstOK = false;
        System.out.println("First Name:");
        while(firstOK != true){
            name = in.nextLine();
            int min_nameLegnth = 2;
            if(Character.isUpperCase(name.charAt(0)) && name.length() >= min_nameLegnth) {
                for (int i = 1; i < name.length(); i++) {
                    if (!Character.isLetter(name.charAt(i))) {
                        break;
                    }
                    firstOK = true;
                }
            }
            else{
                System.out.println("Please enter a valid name. For example: Angel");
            }
        }
        return name;
    }
//Does basically the same thing as firstName
    public String createLastName(){
        String name = null;
        lastOK = false;
        System.out.println("Last Name:");
        while(lastOK != true){
            name = in.nextLine();
            int min_nameLegnth = 2;
            if(Character.isUpperCase(name.charAt(0)) && name.length() >= min_nameLegnth) {
                for (int i = 1; i < name.length(); i++) {
                    if (!Character.isLetter(name.charAt(i))) {
                        break;
                    }
                    lastOK = true;
                }
            }
            else{
                System.out.println("Please enter a valid name. For example: Gonzales or McDonald");
            }
        }
        return name;
    }
    //This will prompt user for their email and verify that it is valid
    //It only checks to see it there is an @ and . (minmal validation)
    public String createEmail(){
        String e = null;
        emailOK = false;
        System.out.println("Email:");
        while(emailOK == false){
            e = in.nextLine();
            if(e.contains("@") && e.contains(".")){
                emailOK = true;
            }
            else{
                System.out.println("**The email you entered is not valid");
                System.out.println("Please try again.");
            }
        }
        return e;
    }//end createEmail

    //This will prompt user for the user's zip code, valid if it has 5 digits
    public String createLocation(){
        String zip = null;
        int zipLength = 5;
        String  userInput;
        locationOK = false;
        boolean validLength = false;
        boolean isNumber = false;
        System.out.println("Please enter a your zip code.");
        while(locationOK == false){
            userInput = in.nextLine();
            if(userInput.length() == zipLength){
                validLength = true;
            }
            for (int i = 0; i < zipLength; i++) {
                if(!Character.isDigit(userInput.charAt(i))){
                    break;
                }
                else{
                    isNumber = true;
                }
            }
            if(validLength == true && isNumber == true){
                zip = userInput;
                locationOK = true;
            }
            else{
                System.out.println("**The zip code you entered is not valid");
                System.out.println("Please try again.");
            }

        }
        return zip;
    }//end createLocation

        /*
        -This method will check to see if the user who is trying to make a new employee/master is as Master user
        -It will allow the master user to assign the created user as either another master or an employee
         */
        public boolean verifyAuthority(){
            String line;
            User temp = null;
            boolean valid = false;
            System.out.println("Before you begin, we need to verify that you have permission.");
            LoginChecker login = new LoginChecker();
            int tempRank = login.getRank();
            if(login.getValid() && tempRank == MASTER){
                valid = true;
            }
            if(tempRank != MASTER){
                System.out.println("You do not have permission to create a new employee.");
                System.exit(1);
            }
            return valid;
        }//end verifyAuthority

        //This allowers the user to either create a new customer or employee/master
        public void chooseCreator(){
            System.out.println("If you are a new customer type in a.");
            System.out.println("If you are trying to create an account for a new employee type b.");
            String line;
            line = in.nextLine();
            if(line.contains("a")){
                createNewUser();
            }
            else if (line.contains("b")){
                if(verifyAuthority()) {
                    chooseEmployeeType();
                    createNewUser();
                }
            }
            else{
                    System.out.println("**That was not a valid answer");
                    chooseCreator();
            }
        }//end chooseCreator
}//FIN
