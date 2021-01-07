/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webd4201.hinbestd;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Faculty extends User {
    public final String DEFAULT_SCHOOL_CODE = "SET";
    public final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    public final String DEFAULT_OFFICE = "H-140";
    public final int DEFAULT_PHONE_EXTENSION = 1234;
    
    private String schoolCode;
    private String schoolDescription;
    private String office;
    private int extension;

    public Faculty(String schoolCode, String schoolDescription, String office, int extension, long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.setSchoolCode(schoolCode);
        this.setSchoolDescription(schoolDescription);
        this.setOffice(office);
        this.setExtension(extension);
    }

    public Faculty() {
        super();
        this.setSchoolCode(DEFAULT_SCHOOL_CODE);
        this.setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        this.setOffice(DEFAULT_OFFICE);
        this.setExtension(DEFAULT_PHONE_EXTENSION);
    } 

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }
    
    @Override
    public String toString(){
        return this.getTypeForDisplay() + " " 
                + super.toString()
                + this.getSchoolDescription() + " (" + this.getSchoolCode() + ")"
                + "Office: " + this.getOffice()
                + PHONE_NUMBER + " x" + this.getExtension();
    }
}
