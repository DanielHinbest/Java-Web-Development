
package webd4201.hinbestd;

/**
 * Interface object for the College information. This stores the name, as well as the other general college information
 * @author Daniel Hinbest
 * @version 1.0 (2021-01-07)
 * @since 1.0
 */
public interface CollegeInterface {
    /**
     * Interface constant variables for various college information
     */
    final String COLLEGE_NAME = "Durham College";
    final String PHONE_NUMBER = "(905) 721-2000";
    final long MINIMUM_ID_NUMBER = 100000000;
    final long MAXIMUM_ID_NUMBER = 999999999;
    final byte MINIMUM_PASSWORD_LENGTH = 8;
    final byte MAXIMUM_PASSWORD_LENGTH = 20;
    
    /**
     * The function header for the classes to display the type of user
     * @return {String}
     */
    public String getTypeForDisplay();
}
