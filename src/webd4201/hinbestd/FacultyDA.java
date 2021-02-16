package webd4201.hinbestd;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import webd4201.hinbestd.Exceptions.DuplicateException;
import webd4201.hinbestd.Exceptions.InvalidUserDataException;
import webd4201.hinbestd.Exceptions.NotFoundException;

/**
 * FacultyDA - this file is contains all of the data access methods, that
 * actually get/set data to the database. Note: that all the methods are static
 * this is because you do not really create FacultyDA objects (does not make
 * sense)
 *
 * @author Daniel Hinbest
 * @version 1.0 (16 February 2021)
 * @since 3.0
 */
public class FacultyDA {
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
     * faculty data attribute
     */
    static Faculty aFaculty;

    /**
     * The faculty ID number
     */
    static long id;
    /**
     * The faculty's password
     */
    static String password;
    /**
     * The faculty's first name
     */
    static String firstName;
    /**
     * The faculty's last name
     */
    static String lastName;
    /**
     * The faculty's email address
     */
    static String emailAddress;
    /**
     * The date the faculty last accessed the system
     */
    static Date lastAccess;
    /**
     * The date the faculty enrolled
     */
    static Date enrolDate;
    /**
     * Checks if the faculty has access to the system
     */
    static boolean enabled;
    /**
     * Checks the faculty's user type (All faculty are 'f')
     */
    static char type;
    /**
     * The faculty's school code
     */
    static String schoolCode;
    /**
     * The faculty's school name
     */
    static String schoolDescription;
    /**
     * The faculty's office location
     */
    static String office;
    /**
     * The faculty's phone extension
     */
    static int extension;
    
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
     * @param key the ID number of the faculty
     * @return a faculty with the ID passed
     * @throws NotFoundException thrown if there is no user with the ID provided
     */
    public static Faculty retrieve(long key) throws NotFoundException {
        aFaculty = null;
        
        try {
            PreparedStatement psSelect = aConnection.prepareStatement("SELECT users.id, password, first_name, "
                    + "last_name, email_address, last_access, enrol_date, enabled, type, "
                    + "school_code, school_description, office, extension "
                    + "FROM users, faculty WHERE users.id = faculty.id AND users.id = ?");
            
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
                schoolCode = rs.getString("school_code");
                schoolDescription = rs.getString("school_description");
                office = rs.getString("office");
                extension = rs.getInt("extension");
                
                try {
                    aFaculty = new Faculty(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, schoolCode, schoolDescription, office, extension);
                } catch (InvalidUserDataException iude) {
                    System.out.println(iude.getMessage());
                }
            } else {
                throw new NotFoundException("Failed to retrieve Faculty record. Faculty ID " + key + " does not exist.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return aFaculty;
    }
    
    /**
     * Creates a new faculty record into the database
     *
     * @param aFaculty the Faculty object to be added to the database
     * @return a boolean value to verify if the user was added successfully
     * @throws DuplicateException thrown if a user with the same ID exists
     * already
     * @throws java.security.NoSuchAlgorithmException thrown when a hashing
     * algorithm doesn't exist
     */
    public static boolean create(Faculty aFaculty) throws DuplicateException, NoSuchAlgorithmException {
        boolean inserted = false;
        
        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = new Date(aFaculty.getLastAccess().getTime());  // Converts java.util.Date to java.sql.Date
        enrolDate = new Date(aFaculty.getEnrolDate().getTime());    // Converts java.util.date to java.sql.Date
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        schoolCode = aFaculty.getSchoolCode();
        schoolDescription = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        extension = aFaculty.getExtension();
        
        try {
            retrieve(id);
            throw new DuplicateException("Failed to create Faculty record. Faculty ID " + id + " already exists.");
        } catch (NotFoundException e) {
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
                psUserInsert.execute();
                
                PreparedStatement psFacultyInsert = aConnection.prepareStatement("INSERT INTO faculty "
                        + "(id, school_code, school_description, office, extension) VALUES (?, ?, ?, ?, ?)");
            
                psFacultyInsert.setLong(1, id);
                psFacultyInsert.setString(2, schoolCode);
                psFacultyInsert.setString(3, schoolDescription);
                psFacultyInsert.setString(4, office);
                psFacultyInsert.setInt(5, extension);
                psFacultyInsert.execute();
            } catch (SQLException ee) {
                System.out.println(ee);
            }
        }
        return inserted;
    }
    
    /**
     * Removes a user from the database
     *
     * @param aFaculty the faculty that is being removed
     * @return the number of existing records in the database
     * @throws NotFoundException thrown if the user to be deleted was not found
     */
    public static int delete(Faculty aFaculty) throws NotFoundException {
        int records = 0;
        
        id = aFaculty.getId();
        
        try {
            PreparedStatement psUserDelete = aConnection.prepareStatement("DELETE FROM users WHERE id = ?");
            PreparedStatement psFacultyDelete = aConnection.prepareStatement("DELETE FROM faculty WHERE id = ?");

            psUserDelete.setLong(1, id);
            psFacultyDelete.setLong(1, id);

            Faculty.retrieve(id);

            records = psFacultyDelete.executeUpdate();
            records = psUserDelete.executeUpdate();
        } catch (NotFoundException e) {
            throw new NotFoundException("Faculty with ID " + id + " does not exist.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return records;
    }
    
    /**
     * Updates an existing faculty record with the faculty object passed
     *
     * @param aFaculty the updated faculty content
     * @return the number of records in the database
     * @throws NotFoundException thrown if no user with the provided content
     * exists
     * @throws java.security.NoSuchAlgorithmException if the hashing algorithm
     * doesn't exist
     */
    public static int update(Faculty aFaculty) throws NotFoundException, NoSuchAlgorithmException {
        int records = 0;
        
        try {
            PreparedStatement psUserUpdate = aConnection.prepareStatement("UPDATE users SET password = ?, "
                    + "first_name = ?, last_name = ?, email_address = ?, last_access = ?, enrol_date = ?, type = ?, enabled = ?"
                    + "WHERE id = ?");
            PreparedStatement psFacultyUpdate = aConnection.prepareStatement("UPDATE faculty SET school_code = ?, school_description = ?, office = ?, extension = ?"
                    + "WHERE id = ?");
            
            Faculty.retrieve(id);
            
            id = aFaculty.getId();
            password = aFaculty.getPassword();
            firstName = aFaculty.getFirstName();
            lastName = aFaculty.getLastName();
            emailAddress = aFaculty.getEmailAddress();
            lastAccess = new Date(aFaculty.getLastAccess().getTime());  // Converts java.util.date to java.sql.Date
            enrolDate = new Date(aFaculty.getEnrolDate().getTime());    // Converts java.util.date to java.sql.Date
            enabled = aFaculty.isEnabled();
            type = aFaculty.getType();
            schoolCode = aFaculty.getSchoolCode();
            schoolDescription = aFaculty.getSchoolDescription();
            office = aFaculty.getOffice();
            extension = aFaculty.getExtension();
            
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
            
            psFacultyUpdate.setString(1, schoolCode);
            psFacultyUpdate.setString(2, schoolDescription);
            psFacultyUpdate.setString(3, office);
            psFacultyUpdate.setInt(4, extension);
            psFacultyUpdate.setLong(5, id);
            psFacultyUpdate.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Faculty with ID " + id + " does not exist.");
        }
        
        return records;
    }
}
