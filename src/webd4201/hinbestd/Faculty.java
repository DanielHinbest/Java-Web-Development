package webd4201.hinbestd;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Date;
import webd4201.hinbestd.Exceptions.*;

/**
 * This class inherits from User to create a new faculty member with the necessary information
 * @author Daniel Hinbest
 * @version 2.0 (2021-02-16)
 * @since 1.0
 */
public class Faculty extends User {
    /**
     * Class constant variable for the default school code
     */
    public static final String DEFAULT_SCHOOL_CODE = "SET";
    /**
     * Class constant variable for the default school description
     */
    public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    /**
     * Class constant variable for the default office location
     */
    public static final String DEFAULT_OFFICE = "H-140";
    /**
     * Class constant variable for the default phone extension
     */
    public static final int DEFAULT_PHONE_EXTENSION = 1234;
    
    
    /**
     * Class instance variable for the school code
     */
    private String schoolCode;
    /**
     * Class instance variable for the school description
     */
    private String schoolDescription;
    /**
     * Class instance variable for the office location
     */
    private String office;
    /**
     * Class instance variable for the phone extension
     */
    private int extension;

    /**
     * The parameterized constructor that creates a new Faculty member with the super-class parameter called to complete the inheritance of a user
     * @param schoolCode the faculty's 3-4 character school code
     * @param schoolDescription the faculty's school name
     * @param office the faculty's office location
     * @param extension the faculty's phone extension
     * @param id the faculty's ID number
     * @param password the faculty's password
     * @param firstName the faculty's first name
     * @param lastName the faculty's last name
     * @param emailAddress the faculty's email address
     * @param lastAccess the faculty's last access date
     * @param enrolDate the faculty's enrollment date
     * @param enabled the faculty's enabled status
     * @param type the faculty's user type (Faculty)
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public Faculty(long id, String password, String firstName, String lastName, 
            String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, 
            char type, String schoolCode, String schoolDescription, String office, int extension) 
            throws InvalidUserDataException{
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        try {
            this.setSchoolCode(schoolCode);
            this.setSchoolDescription(schoolDescription);
            this.setOffice(office);
            this.setExtension(extension);
        } catch (Exception e) {
            throw new InvalidUserDataException(e.getMessage());
        }
    }

    /**
     * Default constructor to create a new faculty member with the default faculty values and the default user values with the super class called
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public Faculty() throws InvalidUserDataException {
        super();
        this.setSchoolCode(DEFAULT_SCHOOL_CODE);
        this.setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        this.setOffice(DEFAULT_OFFICE);
        this.setExtension(DEFAULT_PHONE_EXTENSION);
    } 

    /**
     * Returns the school code
     * @return the school code
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * Sets the school code
     * @param schoolCode the faculty's 3-4 digit school code
     */
    public final void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * Returns the school description
     * @return the school description
     */
    public String getSchoolDescription() {
        return schoolDescription;
    }

    /**
     * Sets the school description
     * @param schoolDescription the name of the school
     */
    public final void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    /**
     * Returns the faculty office location
     * @return the office location
     */
    public String getOffice() {
        return office;
    }

    /**
     * Sets the faculty office location
     * @param office the faculty's office location
     */
    public final void setOffice(String office) {
        this.office = office;
    }

    /**
     * Returns the faculty's phone extension
     * @return the phone extension
     */
    public int getExtension() {
        return extension;
    }

    /**
     * Sets the faculty's phone extension
     * @param extension the faculty's phone extension
     */
    public final void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Returns the faculty type to the display
     * @return the user type
     */
    @Override
    public String getTypeForDisplay() { 
        String output = super.getTypeForDisplay();
        return output.replaceAll("User", "Faculty");
    }
    
    /**
     * Returns the content of the faculty class as a string
     * @return the content in a string
     */
    @Override
    public String toString(){
        return this.getTypeForDisplay() + " " 
                + super.toString()
                + "\n\t" + this.getSchoolDescription() + " (" + this.getSchoolCode() + ")"
                + "\n\tOffice: " + this.getOffice()
                + "\n\t" + PHONE_NUMBER + " x" + this.getExtension();
    }
    
    /**
     * Initializes a database connection from the faculty data access
     * @param c the variable to create a connection
     */
    public static void initialize(Connection c) {
        FacultyDA.initialize(c);
    }
    
    /**
     * Selects a faculty record with the provided ID number
     * @param id the ID number of the retrieved faculty record
     * @return the result set of the selected faculty
     * @throws NotFoundException thrown if no user with the set ID exists
     */
     public static Faculty retrieve(long id) throws NotFoundException {
        return FacultyDA.retrieve(id);
    }
    
    /**
     * Terminates the existing database connection
     */
    public static void terminate() {
        FacultyDA.terminate();
    }
    
    /**
     * Inserts a new record into the database with the provided content
     * @throws DuplicateException thrown when a record with the provided ID already exists
     * @throws java.security.NoSuchAlgorithmException thrown when a hashing algorithm doesn't exist
     */
    public void create() throws DuplicateException, NoSuchAlgorithmException {
        FacultyDA.create(this);
    }
    
    /**
     * Deletes an existing record from the database
     * @throws NotFoundException thrown when no record with the ID exists
     */
    public void delete() throws NotFoundException {
        FacultyDA.delete(this);
    }
    
    public void update() throws NotFoundException, NoSuchAlgorithmException {
        FacultyDA.update(this);
    }
}
