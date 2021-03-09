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
     * Class constant variable for the minimum GPA value
     */
    public static final float MINIMUM_GPA = 0.0f;
    /**
     * Class constant variable for the maximum GPA value
     */
    public static final float MAXIMUM_GPA = 5.0f;
    /**
     * Class constant variable for the decimal formatting for the GPA
     */
    public static final DecimalFormat GPA = new DecimalFormat("#.##");
    
    /**
     * Class instance variable for the course code
     */
    private String courseCode;
    /**
     * Class instance variable for the course name
     */
    private String courseName;
    /**
     * Class instance variable for the GPA result
     */
    private int result;
    /**
     * Class instance variable for the class's GPA weighting
     */
    private float gpaWeighting;

    /**
     * Creates a new course mark with code, name, result, and weighting
     * @param courseCode
     * @param courseName
     * @param result
     * @param gpaWeighting 
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting) {
        this.setCourseCode(courseCode);
        this.setCourseName(courseName);
        this.setResult(result);
        this.setGpaWeighting(gpaWeighting);
    }
    
    /**
     * Returns the course code
     * @return the course code
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
     * @return the course name
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
     * @return the result
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
     * @return the GPA weight
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
    
    /**
     * Converts the Mark class into a string to allow for output
     * @return the string contents
     */
    @Override
    public String toString(){
        return String.format("%-35s", this.getCourseCode(), "%-35s", this.getCourseName(), "%-35s", this.getResult(), "%-35s", this.getGpaWeighting());        
    }
}
