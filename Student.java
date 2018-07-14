/*
 * FileName: Student.java
 * Author: Beth Carmichael
 * Date: 07/13/2018
 * Purpose: 
 *
 *
 */
package studentdatabasegui;
import java.text.DecimalFormat;

public class Student {
    private String name, major;
    private int gradeValue, intHours, totalCreditsCompleted, totalQualityPoints;
    // GPA is calculated by dividing the total amount of grade points earned
    // by the total amount of credit hours attempted
    public double GPA;
    public static DecimalFormat oneDecimalPlace = new DecimalFormat(".#");
    
    // Constructor Student method
    public Student(String userName, String userMajor) {
        this.name = userName;
        this.major = userMajor;
        this.totalCreditsCompleted = 0;
        this.totalQualityPoints = 0;
        this.GPA = 4.0;
    }
    
    public void courseCompleted(String grade, String hours) {
        if(totalCreditsCompleted == 0 && totalQualityPoints == 0) {
            GPA = 4.0;
        }
        else {
            if(grade == "A") {
                gradeValue = 4;
            }
            else if(grade == "B") {
                gradeValue = 3;
            }
            else if(grade == "C") {
                gradeValue = 2;
            }
            else if(grade == "D") {
                gradeValue = 1;
            }
            else if(grade == "F") {
                gradeValue = 0;
            }
            
            intHours = Integer.parseInt(hours);
            this.totalCreditsCompleted += intHours;
            this.totalQualityPoints += gradeValue * intHours;
            this.GPA = (double)totalQualityPoints / (double)totalCreditsCompleted;
        }
    }
    
    @Override
    public String toString() {
        return "Student name: " + name + "\n Student Major: " + major
                + "\n Student's GPA: " + oneDecimalPlace.format(GPA);
    }
}
