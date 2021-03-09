package webd4201.hinbestd.Exceptions;

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
     * Creates a new instance of <code>InvalidIdException</code> without detail
     * message.
     */
    public InvalidIdException() {
    }

    /**
     * Constructs an instance of <code>InvalidIdException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public InvalidIdException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>InvalidIdException</code> with a
     * throwable cause
     * @param cause what caused the exception
     */
    public InvalidIdException(Throwable cause){
        super(cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidIdException</code> with the 
     * message and the cause of the exception
     * @param msg the detail message
     * @param cause what caused the exception
     */
    public InvalidIdException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidIdException</code> with the
     * message, the cause, suppression, and a writable stack trace
     * @param msg the detail message
     * @param cause what caused the exception
     * @param enableSuppression enables/disables suppression
     * @param writableStackTrace  enables/disables writableStackTrace
     */
    public InvalidIdException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
