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
     *
     * @param msg the detail message.
     */
    public InvalidUserDataException(String msg) {
        super(msg);
    }
}
