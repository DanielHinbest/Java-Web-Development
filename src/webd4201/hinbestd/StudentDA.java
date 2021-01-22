package webd4201.hinbestd;

import java.util.Vector;
import java.sql.*;
import java.text.SimpleDateFormat;
import webd4201.hinbestd.Exceptions.DuplicateException;
import webd4201.hinbestd.Exceptions.InvalidUserDataException;
import webd4201.hinbestd.Exceptions.NotFoundException;

/**
 * StudentDA - this file is contains all of the data access methods, that actually get/set data to the database. 
 * Note: that all the methods are static this is because you do not really create StudentDA objects (does not make sense)
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
    
    public static void initialize(Connection c)
    {
        try
        {
            aConnection = c;
            aStatement = aConnection.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public static void terminate()
    {
        try
        {
            aStatement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
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

        try
        {
            retrieve(id);
            throw (new DuplicateException("Problem with creating Student record. A student with the ID " + id + " already exists."));
        }
        catch (NotFoundException e)
        {
            try
            {
                PreparedStatement psUserInsert = aConnection.prepareStatement("INSERT INTO users (id, password, first_name, last_name, email_address, enabled, type, enrol_date, last_access) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement psStudentInsert = aConnection.prepareStatement("INSERT INTO students (id, program_code, program_description, year) VALUES "
                        + "(?, ?, ?, ?)");
                
                psUserInsert.setLong(1, id);
                psUserInsert.setString(2, password);
                psUserInsert.setString(3, firstName);
                psUserInsert.setString(4, lastName);
                psUserInsert.setString(4, emailAddress);
                psUserInsert.setBoolean(6, enabled);
//                psUserInsert.set                  //unsure for user type
                psUserInsert.setDate(8, enrolDate);
                psUserInsert.setDate(9, lastAccess);
                psUserInsert.execute();
                
                psStudentInsert.setLong(1, id);
                psStudentInsert.setString(2, programCode);
                psStudentInsert.setString(3, programDescription);
                psStudentInsert.setInt(4, year);
                psStudentInsert.execute();
            } catch (SQLException ee)
            {
                System.out.println(ee);
            }
        }
        return inserted;
    }
    
    public static Student retrieve(long key) throws NotFoundException
    {
        aStudent = null;

        String sqlQuery = "SELECT users.id, password, first_name, last_name, email_address, "
                + "last_access, enrol_date, enabled, type, program_code, program_description, year"
                + "FROM users, students WHERE users.id = students.id AND users.id = " + key;
        
        try
        {
            ResultSet rs = aStatement.executeQuery(sqlQuery);
            
            boolean gotIt = rs.next();
            if (gotIt)
            {
                id = rs.getLong("users.id");
                password = rs.getString("password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                emailAddress = rs.getString("email_address");
                lastAccess = rs.getDate("last_access");
                enrolDate = rs.getDate("enrol_date");
                enabled = rs.getBoolean("enabled");
//                type = rs.getBytes("type"); //not sure about this one
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = rs.getInt("year");
                
                try
                {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, 
                            lastAccess, enrolDate, enabled, 's', programCode, programDescription, year);
                } catch (InvalidUserDataException iude)
                {
                    System.out.println(iude);
                }
            }
            else
            {
                throw (new NotFoundException("Problem retreiving Student record. Student ID " + key + " does not exist"));
            }
            rs.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return aStudent;
    }
}
