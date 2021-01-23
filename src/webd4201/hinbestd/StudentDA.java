package webd4201.hinbestd;

import java.util.Vector;
import java.sql.*;
import java.text.SimpleDateFormat;
import webd4201.hinbestd.Exceptions.DuplicateException;
import webd4201.hinbestd.Exceptions.InvalidUserDataException;
import webd4201.hinbestd.Exceptions.NotFoundException;

/**
 * StudentDA - this file is contains all of the data access methods, that
 * actually get/set data to the database. Note: that all the methods are static
 * this is because you do not really create StudentDA objects (does not make
 * sense)
 *
 * @author Daniel Hinbest
 * @version 1.0 (21 January 2021)
 * @since 2.0
 */
public class StudentDA {

    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    static Connection aConnection;
    static Statement aStatement;
    static Student aStudent;

    static long id;
    static String password;
    static String firstName;
    static String lastName;
    static String emailAddress;
    static Date lastAccess;
    static Date enrolDate;
    static boolean enabled;
    static char type;
    static String programCode;
    static String programDescription;
    static int year;
    //static Vector<Marks> marks;

    public static void initialize(Connection c) {
        try {
            aConnection = c;
            aStatement = aConnection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void terminate() {
        try {
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static Student retrieve(long key) throws NotFoundException
    {
        aStudent = null;
        
        try {
            PreparedStatement psSelect = aConnection.prepareStatement("SELECT users.id, password, first_name, "
                    + "last_name, email_address, last_access, enrol_date, enabled, type, "
                    + "program_code, program_description, year"
                    + "FROM users, students WHERE, users.id = students.id AND users.id = ?");
            
            psSelect.setLong(1, key);  // If fails, try id
            
            ResultSet rs = psSelect.executeQuery();
            
            boolean gotIt = rs.next();
            if (gotIt)
            {
                id = rs.getLong("id");  // add table name if fails
                password = rs.getString("password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                emailAddress = rs.getString("email_address");
                lastAccess = rs.getDate("lastAccess");
                enrolDate = rs.getDate("enrol_date");
                enabled = rs.getBoolean("enabled");
//                type = rs.getCharacterStream("type"); //unsure about char data type
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = rs.getInt("year");
//                marks = rs.getVector()                //unsure about the marks vector
                
                try
                {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);
                } catch (InvalidUserDataException iude)
                {
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
    
    public static boolean create(Student aStudent) throws DuplicateException
    {
        boolean inserted = false;

        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = (Date) aStudent.getLastAccess();
        enrolDate = (Date) aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
//        marks = aStudent.getMarks();

        try {
            retrieve(id);
            throw new DuplicateException("Failed to create Student record. Student ID " + id + " already exists.");
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
                psUserInsert.setInt(9, year);
                psUserInsert.execute();
                
                PreparedStatement psStudentInsert = aConnection.prepareStatement("INSERT INTO students "
                        + "(id, program_code, program_description, year) VALUES (?, ?, ?, ?)");
                
                psStudentInsert.setLong(1, id);
                psStudentInsert.setString(2, programCode);
                psStudentInsert.setString(3, programDescription);
                psStudentInsert.setInt(4, year);
                psUserInsert.execute();
            } catch (SQLException ee) {
                System.out.println(ee);
            }
        }
        return inserted;
    }
}
