/*
This will use the user_information csv to check if the user exists via their email.
It makes an array of the csv file then checks to see if the email exists.
    -If the email doesn't exist it will ask if the user wants to try again with yes or no;
        If the user selects no it will exit the program;
    -if email exists it asks for the password.
        The user then has three attempts to get the password right before it will quit
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class LoginChecker {

    private Scanner in;
    private String USER_FILE = "user_information.csv";
    private ArrayList<User> userArray;
    private boolean emailOK, passOK, valid;
    private final int MAX_ATTEMPTS = 3;
    private User user;

    //This will run all the methods and make a new user with the info gathered
    public LoginChecker(){
        emailOK = passOK = valid= false;
        userArray = new ArrayList<>(2000);
        in = new Scanner(System.in);
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //This way you can auto run methods just by calling the LoginChecker class
        checkEmail();
        if(emailOK) {
            checkPassword();
        }
        if(emailOK && passOK){
            valid = true;
        }
    }

    public void loadFile() throws FileNotFoundException {
        try {
            Scanner fileReader = new Scanner(new FileInputStream(USER_FILE));
            //This will get past the first line that is just the titles of the colums and not data
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
    }

    public boolean checkEmail(){
        String tempEmail;
        System.out.println("Enter your email");
        tempEmail = in.nextLine();

        for (int i = 0; i < userArray.size(); i++) {
            if(tempEmail.equalsIgnoreCase(userArray.get(i).getEmail())){
                user = userArray.get(i);
                emailOK = true;
                break;
            }
        }
        if(emailOK == false){
            System.out.println("Email not found.");
            System.out.println("Would you like to try again? 'yes' or 'no'");
            String answer = in.nextLine();

            if(answer.contains("y")){
                checkEmail();
            }
            else{
                System.out.println("Ok. Good bye!");//I kept accidentally not realizing I chose no...
                System.exit(1);
            }
        }
        return emailOK;
    }
    //This checks the usserArray to see if a email exists. It isn't case sensitive,
    public boolean checkPassword() {
        int attempts = 0;
        System.out.println("Welcome back " + user.getFirstName() + "!");
        System.out.println("Enter you password:");
        boolean done = false;
        while ((attempts < MAX_ATTEMPTS)){
            String password = in.nextLine();
            if(password.equals(user.getUserPassword())){
                passOK = true;
                break;
            }
            else {
                System.out.println("Password is incorrect!");
            }
            attempts++;
            //System.out.println(attempts);
        }
        if(passOK == false){
            System.out.println("Sorry, you reached your maximum attempts has been reached.");
            System.exit(1);
        }
        return passOK;
    }
    public boolean getValid(){return valid;}
}//FIN
