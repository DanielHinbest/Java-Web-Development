package webd4201.hinbestd;

import java.text.DecimalFormat;

/**
 * This class keeps record of the marks for each student
 * @author Daniel Hinbest
 * @version 1.0 (2021-01-07)
 * @since 1.0
 */
public class Mark {
    
    /**
     * Constant attributes
     */
    public final float MINIMUM_GPA = 0.0f;
    public final float MAXIMUM_GPA = 5.0f;
    public final DecimalFormat GPA = new DecimalFormat("#.##");
    
    /**
     * Instance variables
     */
    private String courseCode;
    private String courseName;
    private int result;
    private float gpaWeighting;

    
    
    /**
     * Returns the course code
     * @return {String}
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the course code
     * @param courseCode 
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Returns the course name
     * @return {String}
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name
     * @param courseName 
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the course result
     * @return {int}
     */
    public int getResult() {
        return result;
    }

    /**
     * Sets the course result
     * @param result 
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Returns the GPA weighting
     * @return {float}
     */
    public float getGpaWeighting() {
        return gpaWeighting;
    }

    /**
     * Sets the GPA weighting
     * @param gpaWeighting 
     */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }
    
}
