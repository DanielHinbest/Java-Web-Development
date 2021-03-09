package webd4201.hinbestd.Exceptions;

/**
 * Creates a new Exception for a record not found
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-19)
 * @since 2.0
 */
public class NotFoundException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>NotFoundException</code> without detail
     * message.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructs an instance of <code>NotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotFoundException(String msg) {
        super(msg);
    }
}
