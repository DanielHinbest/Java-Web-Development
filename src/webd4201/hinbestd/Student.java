package webd4201.hinbestd;

import java.util.Date;
import java.util.Vector;

/**
 * Inherited from the User class, the Student class creates a new Student with unique attributes to go with the common attributes from User
 * @author Daniel
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class Student extends User {
    /**
     * Class constants
     */
    public final String DEFAULT_PROGRAM_CODE = "UNDC";
    public final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    public final int DEFAULT_YEAR = 1;
    
    /**
     * Private instance variables
     */
    private String programCode;
    private String programDescription;
    private int year;
    private Vector<Mark> marks; // Maybe ask about the vector's capacity

    /**
     * Constructor to create a new Student with all attributes set
     * @param programCode
     * @param programDescription
     * @param year
     * @param marks
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
    public Student(String programCode, String programDescription, int year, Vector<Mark> marks, long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.setProgramCode(programCode);
        this.setProgramDescription(programDescription);
        this.setYear(year);
        this.setMarks(marks);
    }

    /**
     * Overloaded from the first parameterized constructor to create a new Student with no marks
     * @param programCode
     * @param programDescription
     * @param year
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
    public Student(String programCode, String programDescription, int year, long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.setProgramCode(programCode);
        this.setProgramDescription(programDescription);
        this.setYear(year);
        this.setMarks(marks);
    }
    
    /**
     * Default constructor to create a new Student with all default values
     */
    public Student(){
//        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_TYPE, DEFAULT_ENABLED_STATUS, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
        this.setId(DEFAULT_ID);
        this.setPassword(DEFAULT_PASSWORD);
        this.setFirstName(DEFAULT_FIRST_NAME);
        this.setLastName(DEFAULT_LAST_NAME);
        this.setEmailAddress(DEFAULT_EMAIL_ADDRESS);
        this.setLastAccess(new Date());
        this.setEnrolDate(new Date());
        this.setType(DEFAULT_TYPE);
        this.setEnabled(DEFAULT_ENABLED_STATUS);
        this.setProgramCode(DEFAULT_PROGRAM_CODE);
        this.setProgramDescription(programDescription);
        this.setYear(DEFAULT_YEAR);
    }
    
    /**
     * Returns the program code
     * @return {String}
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the program code
     * @param programCode 
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Returns the program description
     * @return {String}
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Sets the program description
     * @param programDescription 
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * Returns the student's year of study
     * @return {int}
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the student's year of study
     * @param year 
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the student's marks
     * @return {Vector}
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    /**
     * Sets the student's marks
     * @param marks 
     */
    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }

    /**
     * Overrides the toString function to output the contents for a student
     * @return {String}
     */
    @Override
    public String toString() {
        switch(year){
            case 1:
                return this.getTypeForDisplay() + " User Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "st year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
//                break;
            case 2:
                return this.getTypeForDisplay() + " User Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "nd year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
//                break;
            case 3:
                return this.getTypeForDisplay() + " User Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "rd year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
//                break;
            case 4:
                return this.getTypeForDisplay() + " User Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "th year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
//                break;
            default:
                return this.getTypeForDisplay() + " User Info for:"
                        + "\n\t" + this.DEFAULT_LAST_NAME + " " + this.DEFAULT_LAST_NAME + " (" + MINIMUM_ID_NUMBER + ")"
                        + "\n\t" + "Currently in " + this.DEFAULT_YEAR + "st year of " + this.DEFAULT_PROGRAM_DESCRIPTION + " (" + this.programCode + ")"
                        + "\n\t" + "Enrolled: " + new Date();
//                break;
        }
    }
    
}
