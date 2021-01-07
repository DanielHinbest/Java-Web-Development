package webd4201.hinbestd;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 * The user class, which is used to implement the CollegeInterface and is the super class for different types of users (Faculty, Students)
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class User implements CollegeInterface {
    
    /**
     * Constant variables for the User class
     */
    public final long DEFAULT_ID = 100123456;
    public final String DEFAULT_PASSWORD = "password";
    public final byte MINIMUM_PASSWORD_LENGTH = 8;
    public final byte MAXIMUM_PASSWORD_LENGTH = 40;
    public final String DEFAULT_FIRST_NAME = "John";
    public final String DEFAULT_LAST_NAME = "Doe";
    public final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
    public final boolean DEFAULT_ENABLED_STATUS = true;
    public final char DEFAULT_TYPE = 's';
    public final byte ID_NUMBER_LENGTH = 9;
    public final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
    
    /**
     * The instance variables for the user class
     */
    private long id;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date lastAccess;
    private Date enrolDate;
    private boolean enabled;
    private char type;

    /**
     * The parameterized constructor - accepts the instance variables and assigns the data to the variables with the mutators
     * @param id
     * @param password
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param lastAccess
     * @param enrolDate
     * @param enabled
     * @param type 
     */
    public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) {
        this.setId(id);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmailAddress(emailAddress);
        this.setLastAccess(lastAccess);
        this.setEnrolDate(enrolDate);
        this.setEnabled(enabled);
        this.setType(type);
    }
    
    /**
     * Default constructor - creates a new instance of the User class with the default values from the constants
     */
    public User(){
        this.id = this.DEFAULT_ID;
        this.password = this.DEFAULT_PASSWORD;
        this.firstName = this.DEFAULT_FIRST_NAME;
        this.lastName = this.DEFAULT_LAST_NAME;
        this.emailAddress = this.DEFAULT_EMAIL_ADDRESS;
        this.lastAccess = Date.from(Instant.now());
        this.enrolDate = Date.from(Instant.now());
    }
    
    /**
     * Returns the ID of the user
     * @return {long}
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user ID
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the user's password
     * @return {String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's first name
     * @return {String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's last name
     * @return {String}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's email address
     * @return {String}
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the user's email address
     * @param emailAddress 
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the user's last access date
     * @return {Date}
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Sets the user's last access date
     * @param lastAccess 
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Gets the user's enrollment date
     * @return {Date}
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    /**
     * Sets the user's enrollment date
     * @param enrolDate 
     */
    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Returns the user's enabled status
     * @return {boolean}
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the user's enabled status
     * @param enabled 
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the user type
     * @return {char}
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the user type
     * @param type 
     */
    public void setType(char type) {
        this.type = type;
    }    
   
    /**
     * Base class function for returning the user type for the display
     * @return {String}
     */
    @Override
    public String getTypeForDisplay(){
        return "";
    }

    /**
     * Converts the user information into a string to output to the screen
     * @return {String}
     */
    @Override
    public String toString() {
        return "User Info for: " + this.getId()
                + "\n\tName: " + this.getFirstName() + " " + this.getLastName() + " (" + this.getEmailAddress() + ")"
                + "\n\tCreated on: " + this.getEnrolDate()
                + "\n\tLast access: " + this.getLastAccess();
    }

    /**
     * Dumps the user information for debugging
     */
    public void dump(){
        System.out.println(toString());
    }
    
    /**
     * Checks for a valid ID entry and returns true if the ID is valid, and false if invalid
     * @param id
     * @return {boolean}
     */
    public static boolean verifyID(long id){
        if (id < MINIMUM_ID_NUMBER || id > MAXIMUM_ID_NUMBER){
            return false;
        }
        else {
            return true;
        }
    }
}
