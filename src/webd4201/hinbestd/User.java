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
     * The class constant for the default ID
     */
    public static final long DEFAULT_ID = 100123456L;
    /**
     * The class constant for the default password
     */
    public static final String DEFAULT_PASSWORD = "password";
    /**
     * The class constant for the minimum password length
     */
    public static final byte MINIMUM_PASSWORD_LENGTH = 8;
    /**
     * The class constant for the maximum password length
     */
    public static final byte MAXIMUM_PASSWORD_LENGTH = 40;
    /**
     * The class constant for the default first name
     */
    public static final String DEFAULT_FIRST_NAME = "John";
    /**
     * The class constant for the default last name
     */
    public static final String DEFAULT_LAST_NAME = "Doe";
    /**
     * The class constant for the default email address
     */
    public static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
    /**
     * The class constant for the default enabled status
     */
    public static final boolean DEFAULT_ENABLED_STATUS = true;
    /**
     * The class constant for the default user type
     */
    public static final char DEFAULT_TYPE = 's';
    /**
     * The class constant for the ID length
     */
    public static final byte ID_NUMBER_LENGTH = 9;
    /**
     * The class constant for the date format
     */
    public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
    
    /**
     * The instance variable for the user ID
     */
    private long id;
    /**
     * The instance variable for the user password
     */
    private String password;
    /**
     * The instance variable for the first name
     */
    private String firstName;
    /**
     * The instance variable for the last name
     */
    private String lastName;
    /**
     * The instance variable for the email address
     */
    private String emailAddress;
    /**
     * The instance variable for the last access date
     */
    private Date lastAccess;
    /**
     * The instance variable for the enrollment date
     */
    private Date enrolDate;
    /**
     * The instance variable for the user's enabled status
     */
    private boolean enabled;
    /**
     * The instance variable for the user type
     */
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
     * @return the ID
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
     * @return the password
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
     * @return the first name
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
     * @return the last name
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
     * @return email address
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
     * @return last access date
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
     * @return the enrollment date
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
     * @return the enabled status
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
     * @return the user type
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
     * @return the user type
     */
    @Override
    public String getTypeForDisplay(){
        return "User";
    }

    /**
     * Converts the user information into a string to output to the screen
     * @return the string
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
     * @return the validity status
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
