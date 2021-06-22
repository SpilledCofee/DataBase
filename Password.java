/*
Discription: This will create a password and see if the password is valid.
*****This doesn't have a main method and will need to be looped in the class you are using it to get a calid password.
***It can return if the password is a good one
*** See use of it in UserMaker
 */
import java.io.*;
import java.util.*;

public class Password {
    //Thses are vaiables tha will be used to check if the password is good
    private boolean valid;
    private boolean lengthOK, caseOK, digitOK, specialCharaterOK;
    private String password;
    private final int MIN_LENGTH = 8;
    private int length;
    private Scanner in = new Scanner(System.in);
    private String s = "----------------------------------------"; // separator

    /*Calling the class with call it to run all of its methods
    public Password(){
        printRules();
        inputPassword();
        checkPassword();
    }
     */

//Informs the user of how to create a valid password
    public void printRules(){
        System.out.println("Password:"
                + "\n - Must be at least " + MIN_LENGTH + "character long"
                + "\n - Must contain at least one uppercase letter"
                + "\n - Contains at least one lowercase letter"
                + "\n - Contains at least one special letter"
        );
    }
    public void inputPassword(){
        System.out.println("Please enter a password");
        password = in.nextLine();
        System.out.println("You've entered: " + password);
    }


    public void checkPassword(){
        checkLength();
        checkCase();
        checkDigit();
        checkSpecailChacter();
        valid = lengthOK && caseOK && digitOK && specialCharaterOK;
    }//end CheckPassword

    //Checks to see if the length is at least as large as the MIN_LENGTH
    private void checkLength(){
        length = password.length();
        if(length >= MIN_LENGTH){
            lengthOK = true;
        }
        else{
            lengthOK = false;
            System.out.println("**PASSWORD TOO SHORT! ");
            System.out.println("Must be at least " + MIN_LENGTH + "character long");
        }
    }//end checkLength

    //Checks to see if there is at least one uppercase letter
    //I can add a bit more here if we want to check for a lowercase letter as well
    private void checkCase(){
        for(int i = 0; i <password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                caseOK = true;
                break;
            }
            if (caseOK = false){
                System.out.println("Your password must contain at least one uppercase letter");
            }
        }

    }//end checkCase

    private void checkDigit(){
        for(int i = 0; i <password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                digitOK = true;
                break;
            }
        }
        if(digitOK == false){
            System.out.println("Your password must contain at least one number");
        }
    }//end checkDigit

    private void checkSpecailChacter(){
        for(int i = 0; i <password.length(); i++) {
            if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i))) {
                specialCharaterOK = true;
                break;
            }
        }
        if(specialCharaterOK == false){
            System.out.println("Your password must contain at least one special character");
        }
    }//end checkSpecialCharacter
    //This will allow other methods to use this class
    public String getPassword(){ return password;}
    public boolean getValid(){return valid;}
    public void setPassword(String password) {this.password = password; }
}//FIN