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
    public String name, major;
    public int totalHours, totalPoints;
    // GPA is calculated by dividing the total amount of grade points earned
    // by the total amount of credit hours attempted
    public double GPA;
    public static DecimalFormat oneDecimalPlace = new DecimalFormat(".#");
    
    // Constructor Student method
    public Student(String userName, String userMajor) {
        this.name = userName;
        this.major = userMajor;
        totalHours = 0;
        totalPoints = 0;
    }
    
    public void courseCompleted(String grade, String hours) {
        if(totalHours == 0 && totalPoints == 0) {
            GPA = 4.0;
        }
        else {
            if(grade == "A") {
            totalPoints += 4;
            }
            else if(grade == "B") {
                totalPoints += 3;
            }
            else if(grade == "C") {
                totalPoints += 2;
            }
            else if(grade == "D") {
                totalPoints += 1;
            }
            else if(grade == "F") {
                totalPoints += 0;
            }
            
            totalHours += Integer.parseInt(hours);
            GPA = (double)totalPoints / (double)totalHours;
        }
        System.out.println("Points: " + totalPoints + " Hours: " + totalHours);
        System.out.println("GPA: " + GPA);
    }
    
    @Override
    public String toString() {
        return "Student name: " + name + "\n Student Major: " + major
                + "\n Student's GPA: " + oneDecimalPlace.format(GPA);
    }
}
