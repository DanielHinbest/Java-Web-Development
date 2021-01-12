/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     *
     * @param msg the detail message.
     */
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
