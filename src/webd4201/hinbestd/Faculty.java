/**
 * 
 */
package webd4201.hinbestd;

import java.util.Date;

/**
 * This class inherits from User to create a new faculty member with the necessary information
 * @author Daniel Hinbest
 * @version 1.0 (2021-01-07)
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
     * @param schoolCode
     * @param schoolDescription
     * @param office
     * @param extension
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
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String schoolCode, String schoolDescription, String office, int extension) {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.setSchoolCode(schoolCode);
        this.setSchoolDescription(schoolDescription);
        this.setOffice(office);
        this.setExtension(extension);
    }

    /**
     * Default constructor to create a new faculty member with the default faculty values and the default user values with the super class called
     */
    public Faculty() {
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
     * @param schoolCode 
     */
    public void setSchoolCode(String schoolCode) {
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
     * @param schoolDescription 
     */
    public void setSchoolDescription(String schoolDescription) {
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
     * @param office 
     */
    public void setOffice(String office) {
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
     * @param extension 
     */
    public void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Returns the faculty type to the display
     * @return the user type
     */
    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }
    
    /**
     * Returns the content of the faculty class as a string
     * @return the content in a string
     */
    @Override
    public String toString(){
        return this.getTypeForDisplay() + " " 
                + super.toString()
                + this.getSchoolDescription() + " (" + this.getSchoolCode() + ")"
                + "Office: " + this.getOffice()
                + PHONE_NUMBER + " x" + this.getExtension();
    }
}
