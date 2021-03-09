package webd4201.hinbestd.Exceptions;

/**
 * Creates a new Exception for an invalid name
 * @author Daniel
 * @version 1.0 (2020-01-07)
 * @since 1.0
 */
public class InvalidNameException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>InvalidNameException</code> without
     * detail message.
     */
    public InvalidNameException() {
    }

    /**
     * Constructs an instance of <code>InvalidNameException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidNameException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>InvalidNameException</code> with the
     * message and cause of the exception
     * @param cause what caused the exception
     */
    public InvalidNameException(Throwable cause){
        super(cause);
    }
    
    /**
     * Constructs an instance of <code>InvalidNameException</code> with the 
     * message and the cause of the exception
     * @param msg the detail message
     * @param cause what caused the exception
     */
    public InvalidNameException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public InvalidNameException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
