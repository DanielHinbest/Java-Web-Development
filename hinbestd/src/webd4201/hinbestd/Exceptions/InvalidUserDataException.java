package webd4201.hinbestd.Exceptions;

/**
 * Creates a new Exception for invalid user data
 * @author Daniel
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class InvalidUserDataException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>InvalidUserDataException</code> without
     * detail message.
     */
    public InvalidUserDataException() {
    }

    /**
     * Constructs an instance of <code>InvalidUserDataException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public InvalidUserDataException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>InvalidIdException</code> with a
     * throwable cause
     * @param cause what caused the exception
     */
    public InvalidUserDataException(Throwable cause){
        super(cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidIdException</code> with the 
     * message and the cause of the exception
     * @param msg the detail message
     * @param cause what caused the exception
     */
    public InvalidUserDataException(String msg, Throwable cause){
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
    public InvalidUserDataException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
