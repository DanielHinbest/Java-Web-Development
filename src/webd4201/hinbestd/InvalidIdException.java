/**
 * 
 */
package webd4201.hinbestd;

/**
 * Creates a new exception for an invalid ID
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class InvalidIdException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor to create a new exception
     */
    public InvalidIdException(){
        
    }
    
    /**
     * Parameterized constructor for a new exception with a message
     * @param message 
     */
    public InvalidIdException(String message){
        super(message);
    }
}
