package webd4201.hinbestd;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import webd4201.hinbestd.Exceptions.DuplicateException;
import webd4201.hinbestd.Exceptions.InvalidUserDataException;
import webd4201.hinbestd.Exceptions.NotFoundException;

/**
 * StudentDA - this file is contains all of the data access methods, that
 * actually get/set data to the database.
 * Updated April 1 to use database transactions. All user-specific data now in 
 * UserDA.
 *
 * @author Daniel Hinbest
 * @version 3.0 (1 April 2021)
 * @since 2.0
 */
public class StudentDA {

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
    static Student aStudent;

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
     * The student's registered program code
     */
    static String programCode;
    /**
     * The student's program name
     */
    static String programDescription;
    /**
     * The student's year of study
     */
    static int year;

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
    public static Student retrieve(long key) throws NotFoundException {
        aStudent = null;

        try {
            PreparedStatement psSelect = aConnection.prepareStatement("SELECT id, "
                    + "program_code, program_description, year "
                    + "FROM students WHERE students.id = ?");

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
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = rs.getInt("year");

                try {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);
                } catch (InvalidUserDataException iude) {
                    System.out.println(iude.getMessage());
                }
            } else {
                throw new NotFoundException("Failed to retrieve Student record. Student ID " + key + " does not exist.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return aStudent;
    }

    /**
     * Creates a new student record into the database once input for UserDA
     * is successful.
     *
     * @param aStudent the student object to be added to the database
     * @return a boolean value to verify if the user was added successfully
     * @throws DuplicateException thrown if a user with the same ID exists
     * already
     * @throws java.security.NoSuchAlgorithmException thrown when a hashing
     * algorithm doesn't exist
     */
    public static boolean create(Student aStudent) throws DuplicateException, NoSuchAlgorithmException {
        boolean inserted = false;

        try {
            retrieve(id);
            throw new DuplicateException("Failed to create Student record. Student ID " + id + " already exists.");
        } catch (NotFoundException e) {
            try {
                aConnection.setAutoCommit(false);

                id = aStudent.getId();
                programCode = aStudent.getProgramCode();
                programDescription = aStudent.getProgramDescription();
                year = aStudent.getYear();

                if (UserDA.create(aStudent)) {
                    PreparedStatement psStudentInsert = aConnection.prepareStatement("INSERT INTO students "
                            + "(id, program_code, program_description, year) VALUES (?, ?, ?, ?)");

                    psStudentInsert.setLong(1, id);
                    psStudentInsert.setString(2, programCode);
                    psStudentInsert.setString(3, programDescription);
                    psStudentInsert.setInt(4, year);

                    if (psStudentInsert.executeUpdate() == 1) {
                        inserted = true;
                        aConnection.commit();
                    } else {
                        aConnection.rollback();
                    }
                    aConnection.setAutoCommit(true);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return inserted;
    }

    /**
     * Removes a user from the database
     *
     * @param aStudent the student that is being removed
     * @return the number of existing records in the database
     * @throws NotFoundException thrown if the user to be deleted was not found
     */
    public static int delete(Student aStudent) throws NotFoundException {
        int records = 0;

        id = aStudent.getId();

        try {
            PreparedStatement psStudentDelete = aConnection.prepareStatement("DELETE FROM students WHERE id = ?");

            psStudentDelete.setLong(1, id);

            Student.retrieve(id);

            records = psStudentDelete.executeUpdate();
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with ID " + id + " does not exist.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;
    }

    /**
     * Updates an existing student record with the student object passed
     *
     * @param aStudent the updated student content
     * @return the number of records in the database
     * @throws NotFoundException thrown if no user with the provided content
     * exists
     * @throws java.security.NoSuchAlgorithmException if the hashing algorithm
     * doesn't exist
     */
    public static int update(Student aStudent) throws NotFoundException, NoSuchAlgorithmException {
        int records = 0;

        try {
            PreparedStatement psStudentUpdate = aConnection.prepareStatement("UPDATE students SET program_code = ?, program_description = ?, year = ?"
                    + "WHERE id = ?");

            Student.retrieve(id);

            id = aStudent.getId();
            programCode = aStudent.getProgramCode();
            programDescription = aStudent.getProgramDescription();
            year = aStudent.getYear();

            psStudentUpdate.setString(1, programCode);
            psStudentUpdate.setString(2, programDescription);
            psStudentUpdate.setInt(3, year);
            psStudentUpdate.setLong(4, id);
            psStudentUpdate.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with ID " + id + " does not exist.");
        }
        return records;
    }

    /**
     * Authenticates the user from the database if the id/password combination
     * exists
     *
     * @param id The student ID
     * @param password the student's password
     * @return The student details
     * @throws NotFoundException thrown when a student does not exist
     * @throws java.security.NoSuchAlgorithmException thrown when an algorithm
     * doesn't exist
     */
    public static Student authenticate(long id, String password) throws NotFoundException, NoSuchAlgorithmException {
        aStudent = null;

        try {
            PreparedStatement psAuthenticate = aConnection.prepareStatement("SELECT * FROM users, students "
                    + "WHERE users.id = ? AND password = ?");

            psAuthenticate.setLong(1, id);
            psAuthenticate.setString(2, User.hashPassword(password));

            ResultSet rs = psAuthenticate.executeQuery();

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
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = rs.getInt("year");

                try {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);

                } catch (InvalidUserDataException iude) {
                    System.out.println(iude.getMessage());
                }

            } else {
                throw new NotFoundException("Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return aStudent;
    }

    /**
     * Checks if the user is currently logged in
     *
     * @param id The user ID
     * @param password The user password
     * @return return true of the login exists
     */
    public static boolean isExistingLogin(long id, String password) {

        boolean exists = true;

        try {
            PreparedStatement psLogin = aConnection.prepareStatement("SELECT * FROM users, students "
                    + "WHERE users.id = ? AND users.password = ?");

            psLogin.setLong(1, id);
            psLogin.setString(2, password);

            ResultSet rs = psLogin.executeQuery();

            exists = rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exists;
    }
}
