package webd4201.hinbestd;

/**
 * Creates a new Exception for invalid user data
 * @author Daniel
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class InvalidUserDataException extends Exception {
    // Removes the warning from the Exception class
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor to create a new exception
     */
    public InvalidUserDataException(){
        
    }
    
    /**
     * Parameterized constructor for a new exception with a message
     * @param message 
     */
    public InvalidUserDataException(String message){
        
    }
}
