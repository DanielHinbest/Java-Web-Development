package webd4201.hinbestd.Exceptions;

/**
 * Creates a new Exception for an invalid password
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class InvalidPasswordException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>InvalidPasswordException</code> without
     * detail message.
     */
    public InvalidPasswordException() {
    }

    /**
     * Constructs an instance of <code>InvalidPasswordException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public InvalidPasswordException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>InvalidPasswordException</code> with a
     * throwable cause
     * @param cause what caused the exception
     */
    public InvalidPasswordException(Throwable cause){
        super(cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidPasswordException</code> with the 
     * message and the cause of the exception
     * @param msg the detail message
     * @param cause what caused the exception
     */
    public InvalidPasswordException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidPasswordException</code> with the
     * message, the cause, suppression, and a writable stack trace
     * @param msg the detail message
     * @param cause what caused the exception
     * @param enableSuppression enables/disables suppression
     * @param writableStackTrace  enables/disables writableStackTrace
     */
    public InvalidPasswordException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
    
}
