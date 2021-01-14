package webd4201.hinbestd;

import java.util.Date;
import java.util.Vector;
import webd4201.hinbestd.Exceptions.*;

/**
 * Inherited from the User class, the Student class creates a new Student with unique attributes to go with the common attributes from User
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class Student extends User {
    /**
     * Class constant variable for the default program code
     */
    public static final String DEFAULT_PROGRAM_CODE = "UNDC";
    /**
     * Class constant variable for the default program description
     */
    public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    /**
     * Class constant variable for the default program year
     */
    public static final int DEFAULT_YEAR = 1;
    
    /**
     * Private instance variable for the program code
     */
    private String programCode;
    /**
     * Private instance variable for the program description
     */
    private String programDescription;
    /**
     * Private instance variable for the current year of study
     */
    private int year;
    /**
     * Private instance vector set with the marks
     */
    private Vector<Mark> marks; // Maybe ask about the vector's capacity

    /**
     * Constructor to create a new Student with all attributes set     
     * @param id the student ID number
     * @param password the student's password
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param emailAddress the student's email address
     * @param lastAccess the student's date of last access
     * @param enrolDate the student's enrollment date
     * @param enabled the student's enabled status
     * @param type the student's user type (Student)
     * @param programCode the program's 3-4 character code
     * @param programDescription the name of the program
     * @param year the student's current year of study
     * @param marks a vector of the student's marks
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public Student(long id, String password, String firstName, String lastName, 
            String emailAddress,  Date enrolDate, Date lastAccess, boolean enabled, 
            char type, String programCode, String programDescription, int year, Vector<Mark> marks) 
            throws InvalidUserDataException {
        
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        try {
            this.setProgramCode(programCode);
            this.setProgramDescription(programDescription);
            this.setYear(year);
            this.setMarks(marks);
        } catch (Exception e) {
            throw new InvalidUserDataException(e.getMessage());
        }
    }

    /**
     * Overloaded from the first parameterized constructor to create a new Student with no marks     
     * @param id the student ID number
     * @param password the student's password
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param emailAddress the student's email address
     * @param lastAccess the student's date of last access
     * @param enrolDate the student's enrollment date
     * @param enabled the student's enabled status
     * @param type the student's user type (Student)
     * @param programCode the program's 3-4 digit code
     * @param programDescription the name of the program
     * @param year the student's current year of study
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException 
     */
    public Student(long id, String password, String firstName, String lastName, 
            String emailAddress,  Date enrolDate, Date lastAccess, boolean enabled, 
            char type, String programCode, String programDescription, int year ) 
            throws InvalidUserDataException {
        this(id, password, firstName, lastName, emailAddress, enrolDate, lastAccess, enabled, type, programCode, programDescription, year, null);
    }
    
    /**
     * Default constructor to create a new Student with all default values
     * @throws webd4201.hinbestd.Exceptions.InvalidUserDataException
     */
    public Student() throws InvalidUserDataException{
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, 
                DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, 
                DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
    }
    
    /**
     * Returns the program code
     * @return the program code
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the program code
     * @param programCode the 3-4 character program code
     */
    public final void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Returns the program description
     * @return the program description
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Sets the program description
     * @param programDescription the name of the program
     */
    public final void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * Returns the student's year of study
     * @return the program year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the student's year of study
     * @param year the student's current year of study
     */
    public final void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the student's marks
     * @return the vector of marks
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    /**
     * Sets the student's marks
     * @param marks the vector of the student's marks
     */
    public final void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }
    
    /**
     * Returns the student's type to the display
     * @return the user type
     */
    @Override
    public String getTypeForDisplay() { 
        String output = super.getTypeForDisplay();
        return output.replaceAll("User", "Student");
    }

    /**
     * Overrides the toString function to output the contents for a student
     * @return the class contents in a string
     */
    @Override
    public String toString() {
        
        switch(year){
            case 1:
                return this.getTypeForDisplay() + " Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "st year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
            case 2:
                return this.getTypeForDisplay() + " Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "nd year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
            case 3:
                return this.getTypeForDisplay() + " Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "rd year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
            case 4:
                return this.getTypeForDisplay() + " Info for:"
                        + "\n\t" + this.getFirstName() + " " + this.getLastName() + " (" + this.getId() + ")"
                        + "\n\t" + "Currently in " + this.getYear() + "th year of " + this.getProgramDescription() + " (" + this.getProgramCode() + ")"
                        + "\n\t" + "Enrolled: " + this.getEnrolDate();
            default:
                return this.getTypeForDisplay() + " Info for:"
                        + "\n\t" + this.DEFAULT_FIRST_NAME + " " + this.DEFAULT_LAST_NAME + " (" + MINIMUM_ID_NUMBER + ")"
                        + "\n\t" + "Currently in " + this.DEFAULT_YEAR + "st year of " + this.DEFAULT_PROGRAM_DESCRIPTION + " (" + this.programCode + ")"
                        + "\n\t" + "Enrolled: " + new Date();
        }
    }
    
}
