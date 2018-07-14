package studentdb;

import java.text.DecimalFormat;

public class Student {

	private String name;
	private String major;
	private int completedCredits;
	private double qualityPoints;
	
	public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.completedCredits = 0;
        this.qualityPoints = 0;
    }
	
	public void courseCompleted(String grade, int hours) {
		
		int gradeVal = 0;
		
		if(grade.equals("A")) {
			gradeVal = 4;
		}
		else if(grade.equals("B")) {
			gradeVal = 3;
		}
		else if(grade.equals("C")) {
			gradeVal = 2;
		}
		else if(grade.equals("D")) {
			gradeVal = 1;
		}
		else if(grade.equals("E")) {
			gradeVal = 0;
		}
		
		this.qualityPoints += gradeVal * hours;
		this.completedCredits += hours;
	}
	
	public String toString() {
		
		double GPA = 4.0;
		
		if(this.completedCredits != 0) {
			GPA = this.qualityPoints/this.completedCredits;
		}
		//format to one place
		DecimalFormat df = new DecimalFormat("#.0");
		
	    return "Name: " + this.name + "\nMajor: " + this.major + "\nGPA: " + df.format(GPA);
	} 
	
}
