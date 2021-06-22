/*
This makes a user object
 */

public class User{

    // These ranks can be used to determine how much someone can interface with the data base
    // may are may not be useful;
    private final int MASTER = 1;
    private final int EMPLOYEE = 2;
    private final int CUSTOMER = 3;

    //The information contained in user_information.csv
    private String firstName, lastName, email, location, userPassword;
    private int rank;

    //A constructor that can take direct input, will be useful for reading form CSV file
    public User(String firstName, String lastName, String email, String location, String userPassword, int rank){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        //It was decided that not having username as well would be easier for now
        //this.userName = userName;
        this.userPassword = userPassword;
        this.rank = rank;
    }
    //Getters
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getLocation() {return location; }
    //public String getUserName() {return userName;}
    public String getUserPassword() {return userPassword;}
    public int getRank() {return rank;}

    //Setters


    public void setFirstName(String firstName) {this.firstName = firstName; }
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setLocation(String location) {this.location = location;}
    //public void setUserName(String userName) {this.userName = userName;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
    public void setRank(int rank) {this.rank = rank;}

    @Override
    public String toString() {
        //first_name,last_name,email,location,password,rank
        return firstName + ","
            + lastName + ","
            + email + ","
            + location + ","
            + userPassword + ","
            + rank;
    }
}//FIN

