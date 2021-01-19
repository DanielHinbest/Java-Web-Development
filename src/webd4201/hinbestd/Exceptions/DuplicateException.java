package webd4201.hinbestd.Exceptions;

/**
 * Creates a new Exception for a duplicate record
 * @author Daniel Hinbest
 * @version 1.0 (2020-01-19)
 * @since 2.0
 */
public class DuplicateException extends Exception {
    /**
     * Removes the warning from the Exception class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>DuplicateException</code> without detail
     * message.
     */
    public DuplicateException() {
        super();
    }

    /**
     * Constructs an instance of <code>DuplicateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateException(String msg) {
        super(msg);
    }
}
