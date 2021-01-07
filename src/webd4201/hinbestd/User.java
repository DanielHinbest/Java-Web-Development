/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webd4201.hinbestd;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Daniel
 */
public class User implements CollegeInterface {
    public final long DEFAULT_ID = 100123456;
    public final String DEFAULT_PASSWORD = "password";
    public final byte MINIMUM_PASSWORD_LENGTH = 8;
    public final byte MAXIMUM_PASSWORD_LENGTH = 40;
    public final String DEFAULT_FIRST_NAME = "John";
    public final String DEFAULT_LAST_NAME = "Doe";
    public final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
    public final boolean DEFAULT_ENABLED_STATUS = true;
    public final char DEFAULT_TYPE = 's';
    public final byte ID_NUMBER_LENGTH = 9;
    public final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
    
    private long id;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date lastAccess;
    private Date enrolDate;
    private boolean enabled;
    private char type;

    public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) {
        this.setId(id);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmailAddress(emailAddress);
        this.setLastAccess(lastAccess);
        this.setEnrolDate(enrolDate);
        this.setEnabled(enabled);
        this.setType(type);
    }
    
    public User(){
        this.id = this.DEFAULT_ID;
        this.password = this.DEFAULT_PASSWORD;
        this.firstName = this.DEFAULT_FIRST_NAME;
        this.lastName = this.DEFAULT_LAST_NAME;
        this.emailAddress = this.DEFAULT_EMAIL_ADDRESS;
        this.lastAccess = Date.from(Instant.now());
        this.enrolDate = Date.from(Instant.now());
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }    
   
    @Override
    public String getTypeForDisplay(){
        return "";
    }

    @Override
    public String toString() {
        return "User Info for: " + this.getId()
                + "\n\tName: " + this.getFirstName() + " " + this.getLastName() + " (" + this.getEmailAddress() + ")"
                + "\n\tCreated on: " + this.getEnrolDate()
                + "\n\tLast access: " + this.getLastAccess();
    }

    public void dump(){
        System.out.println(toString());
    }
    
    public static boolean verifyID(long id){
        if (id < 100000000 || id > 100999999){
            return false;
        }
        else {
            return true;
        }
    }
}
