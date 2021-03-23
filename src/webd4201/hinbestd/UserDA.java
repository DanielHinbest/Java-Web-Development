package webd4201.hinbestd;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import webd4201.hinbestd.Exceptions.DuplicateException;
import webd4201.hinbestd.Exceptions.InvalidUserDataException;
import webd4201.hinbestd.Exceptions.NotFoundException;

/**
 *
 * @author Daniel
 */
public class UserDA {

    /**
     * The SQL date formatting
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Attribute to create a new connection
     */
    static Connection aConnection;
    /**
     * Attribute for a SQL statement
     */
    static Statement aStatement;
    /**
     * Student data attribute
     */
    static User aUser;

    /**
     * The student ID number
     */
    static long id;
    /**
     * The student's password
     */
    static String password;
    /**
     * The student's first name
     */
    static String firstName;
    /**
     * The student's last name
     */
    static String lastName;
    /**
     * The student's email address
     */
    static String emailAddress;
    /**
     * The date the student last accessed the system
     */
    static Date lastAccess;
    /**
     * The date the student enrolled
     */
    static Date enrolDate;
    /**
     * Checks if the student has access to the system
     */
    static boolean enabled;
    /**
     * Checks the student's user type (All students are 's')
     */
    static char type;

    /**
     * Creates a new database connection
     *
     * @param c the connection variable for the database
     */
    public static void initialize(Connection c) {
        try {
            aConnection = c;
            aStatement = aConnection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Closes the database connection
     */
    public static void terminate() {
        try {
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves the database records based on the ID number provided
     *
     * @param key the ID number of the student
     * @return a student with the ID passed
     * @throws NotFoundException thrown if there is no user with the ID provided
     */
    public static User retrieve(long key) throws NotFoundException {
        aUser = null;

        try {
            PreparedStatement psSelect = aConnection.prepareStatement("SELECT users.id, password, first_name, "
                    + "last_name, email_address, last_access, enrol_date, enabled, type, "
                    + "FROM users WHERE users.id = ?");

            psSelect.setLong(1, key);

            ResultSet rs = psSelect.executeQuery();

            boolean gotIt = rs.next();
            if (gotIt) {
                id = rs.getLong("id");
                password = rs.getString("password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                emailAddress = rs.getString("email_address");
                lastAccess = rs.getDate("last_access");
                enrolDate = rs.getDate("enrol_date");
                enabled = rs.getBoolean("enabled");
                type = rs.getString("type").charAt(0);

                try {
                    aUser = new User(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
                } catch (InvalidUserDataException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new NotFoundException("Failed to retrieve User record. User ID " + key + " does not exist.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return aUser;
    }

    /**
     * Creates a new student record into the database
     *
     * @param aUser the student object to be added to the database
     * @return a boolean value to verify if the user was added successfully
     * @throws DuplicateException thrown if a user with the same ID exists
     * already
     * @throws java.security.NoSuchAlgorithmException thrown when a hashing
     * algorithm doesn't exist
     */
    public static boolean create(User aUser) throws DuplicateException, NoSuchAlgorithmException {
        boolean inserted = false;

        id = aUser.getId();
        password = aUser.getPassword();
        firstName = aUser.getFirstName();
        lastName = aUser.getLastName();
        emailAddress = aUser.getEmailAddress();
        lastAccess = new Date(aUser.getLastAccess().getTime());  // Converts java.util.Date to java.sql.Date
        enrolDate = new Date(aUser.getEnrolDate().getTime());    // Converts java.util.date to java.sql.Date
        enabled = aUser.isEnabled();
        type = aUser.getType();

        try {
            PreparedStatement psUserInsert = aConnection.prepareStatement("INSERT INTO users (id, password, "
                    + "first_name, last_name, email_address, last_access, enrol_date, enabled, type) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            psUserInsert.setLong(1, id);
            psUserInsert.setString(2, password);
            psUserInsert.setString(3, firstName);
            psUserInsert.setString(4, lastName);
            psUserInsert.setString(5, emailAddress);
            psUserInsert.setDate(6, lastAccess);
            psUserInsert.setDate(7, enrolDate);
            psUserInsert.setBoolean(8, enabled);
            psUserInsert.setString(9, String.valueOf(type));

            inserted = (psUserInsert.executeUpdate() == 1);

        } catch (SQLException e) {
            System.out.println(e);
        }

        return inserted;
    }

    public static int delete(User aUser) throws NotFoundException {
        int records = 0;

        id = aUser.getId();

        try {
            PreparedStatement psUserDelete = aConnection.prepareStatement("DELETE FROM users WHERE id = ?");

            psUserDelete.setLong(1, id);

            User.retrieve(id);

            records = psUserDelete.executeUpdate();

        } catch (NotFoundException e) {
            throw new NotFoundException("Student with ID " + id + " does not exist.");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return records;
    }

    public static int update(User aUser) throws NotFoundException, NoSuchAlgorithmException {
        int records = 0;

        try {
            PreparedStatement psUserUpdate = aConnection.prepareStatement("UPDATE users SET password = ?, "
                    + "first_name = ?, last_name = ?, email_address = ?, last_access = ?, enrol_date = ?, type = ?, enabled = ?"
                    + "WHERE id = ?");
            
            User.retrieve(id);
            
            id = aUser.getId();
            password = aUser.getPassword();
            firstName = aUser.getFirstName();
            lastName = aUser.getLastName();
            emailAddress = aUser.getEmailAddress();
            lastAccess = new Date(aUser.getLastAccess().getTime());  // Converts java.util.date to java.sql.Date
            enrolDate = new Date(aUser.getEnrolDate().getTime());    // Converts java.util.date to java.sql.Date
            enabled = aUser.isEnabled();
            type = aUser.getType();
            
            psUserUpdate.setString(1, password);
            psUserUpdate.setString(2, firstName);
            psUserUpdate.setString(3, lastName);
            psUserUpdate.setString(4, emailAddress);
            psUserUpdate.setDate(5, lastAccess);
            psUserUpdate.setDate(6, enrolDate);
            psUserUpdate.setString(7, String.valueOf(type));    // Converts char to String
            psUserUpdate.setBoolean(8, enabled);
            psUserUpdate.setLong(9, id);
            psUserUpdate.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with ID " + id + " does not exist.");            
        }
        return records;
    }
}
