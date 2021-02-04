package webd4201.hinbestd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import webd4201.hinbestd.Exceptions.*;

/**
 * The user class, which is used to implement the CollegeInterface and is the
 * super class for different types of users (Faculty, Students)
 *
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
     * The parameterized constructor - accepts the instance variables and
     * assigns the data to the variables with the mutators
     *
     * @param id The ID number of the user
     * @param password The user's password
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param emailAddress The user's email address
     * @param lastAccess The last time the user signed in
     * @param enrolDate The date the user enrolled
     * @param enabled Sets the user's accessibility
     * @param type The user type (Student or Faculty)
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public User(long id, String password, String firstName, String lastName, String emailAddress,
            Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException {
        try {
            this.setId(id);
            this.setPassword(password);
            this.setFirstName(firstName);
            this.setLastName(lastName);
            this.setEmailAddress(emailAddress);
            this.setLastAccess(lastAccess);
            this.setEnrolDate(enrolDate);
            this.setEnabled(enabled);
            this.setType(type);
        } catch (Exception e) {
            throw new InvalidUserDataException(e.getMessage());
        }
    }

    /**
     * Default constructor - creates a new instance of the User class with the
     * default values from the constants
     *
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public User() throws InvalidUserDataException {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE);
    }

    /**
     * Returns the ID of the user
     *
     * @return the ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user ID
     *
     * @param id The user's ID number
     * @throws webd4201.hinbestd.Exceptions.InvalidIdException
     */
    public final void setId(long id) throws InvalidIdException {
        if (verifyID(id)) {
            this.id = id;
        } else {
            throw new InvalidIdException("The ID number must be between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER);
        }
    }

    /**
     * Returns the user's password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     *
     * @param password the user's password input
     * @throws webd4201.hinbestd.Exceptions.InvalidPasswordException
     * @throws java.security.NoSuchAlgorithmException
     */
    public final void setPassword(String password) throws InvalidPasswordException, NoSuchAlgorithmException {
        if (password.length() >= MINIMUM_PASSWORD_LENGTH && password.length() <= MAXIMUM_PASSWORD_LENGTH) {
            this.password = hashPassword(password);
        } else {
            throw new InvalidPasswordException("The password length must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH);
        }
    }

    /**
     * Returns the user's first name
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     *
     * @param firstName the user's first name
     * @throws webd4201.hinbestd.Exceptions.InvalidNameException
     */
    public final void setFirstName(String firstName) throws InvalidNameException {
        if (firstName.length() != 0) {  //firstName.equals("")
            this.firstName = firstName;
        } else {
            throw new InvalidNameException("The first name cannot be empty");
        }
    }

    /**
     * Returns the user's last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     *
     * @param lastName the user's last name
     * @throws webd4201.hinbestd.Exceptions.InvalidNameException
     */
    public final void setLastName(String lastName) throws InvalidNameException {
        if (lastName.length() != 0) {
            this.lastName = lastName;
        } else {
            throw new InvalidNameException("The last name cannot be empty");
        }
    }

    /**
     * Returns the user's email address
     *
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the user's email address
     *
     * @param emailAddress the user's email address
     */
    public final void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the user's last access date
     *
     * @return last access date
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Sets the user's last access date
     *
     * @param lastAccess the last date the user signed in
     */
    public final void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Gets the user's enrollment date
     *
     * @return the enrollment date
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    /**
     * Sets the user's enrollment date
     *
     * @param enrolDate the date the user enrolled
     */
    public final void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Returns the user's enabled status
     *
     * @return the enabled status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the user's enabled status
     *
     * @param enabled the user accessibility
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the user type
     *
     * @return the user type
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the user type
     *
     * @param type the user type (Student or faculty)
     */
    public final void setType(char type) {
        this.type = type;
    }

    /**
     * Base class function for returning the user type for the display
     *
     * @return the user type
     */
    @Override
    public String getTypeForDisplay() {
        return "User";
    }

    /**
     * Converts the user information into a string to output to the screen
     *
     * @return the User content as a String
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
    public void dump() {
        System.out.println(toString());
    }

    /**
     * Checks for a valid ID entry and returns true if the ID is valid, and
     * false if invalid
     *
     * @param id the user's ID number
     * @return the validity status
     */
    public static boolean verifyID(long id) {
        if (id < MINIMUM_ID_NUMBER || id > MAXIMUM_ID_NUMBER) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Converts the hashed string to a hexadecimal value
     * @param bytes the bytes to be converted
     * @return the hex string of the bytes
     */
    public static String decToHex(byte[] bytes) {
        String hex = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            //System.out.println(bytes[i] + " as hex is " + Integer.toHexString(bytes[i]));
            //System.out.println(bytes[i] + " as 2-digit hex is " + String.format("%02x", bytes[i]));
            sb.append(String.format("%02x", bytes[i]));
        }
        hex = sb.toString();
        return hex;
    }
    
    /**
     * Hashes the password into SHA1
     * @param thingToBeHashed the value that is being hashed
     * @return the hashed string
     * @throws NoSuchAlgorithmException if a hashing algorithm doesn't exist
     */
    public static String hashPassword(String thingToBeHashed) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(thingToBeHashed.getBytes());
        byte[] bytesofHashedString = md.digest();
        return decToHex(bytesofHashedString);
    }

}
