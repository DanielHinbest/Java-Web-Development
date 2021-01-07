/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webd4201.hinbestd;

/**
 *
 * @author Daniel
 */
public interface CollegeInterface {
    String COLLEGE_NAME = "Durham College";
    String PHONE_NUMBER = "(905) 721-2000";
    long MINIMUM_ID_NUMBER = 100000000;
    long MAXIMUM_ID_NUMBER = 999999999;
    byte MINIMUM_PASSWORD_LENGTH = 8;
    byte MAXIMUM_PASSWORD_LENGTH = 20;
    
    public String getTypeForDisplay();
}
