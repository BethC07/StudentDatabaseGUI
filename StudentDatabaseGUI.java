/*
 * FileName: StudentDatabaseGUI.java
 * Author: Beth Carmichael
 * Date: 07/13/2018
 * Purpose: 
 *
 *
 */
package studentdatabasegui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class StudentDatabaseGUI {
    // Initialize JFrame
    private JFrame frame;
    // Initialize JTextField
    private JTextField userID, userName, userMajor;
    // Initialize JLabel
    private JLabel userIDLabel, userNameLabel, userMajorLabel, optionLabel, gradeLabel, creditLabel;
    // Initialize JButton
    private JButton process;
    // Initialize JRadioButtons
    private JComboBox databaseOptionBox;
    private String[] databaseOptions = {"Insert", "Delete", "Find", "Update"};
    private String[] grades = {"A", "B", "C", "D", "F"};
    private String[] credits = {"3", "6"};
    private String chosenOption1, chosenOption2, chosenOption3;
    Map<> students = new HashMap<>();
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 250;
    
    // The StudentDatabaseGUI() method will create the GUI showed to the user
    public StudentDatabaseGUI() {
        // Calling the createGUI class to create the components of the GUI
        createGUI();
    }
    
    private void createGUI() {
        frame = new JFrame("Project 4");
        // Setting the width and hight of the box
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // Setting the JLabel variables
        userIDLabel = new JLabel("Id:");
        userNameLabel = new JLabel("Name:");
        userMajorLabel = new JLabel("Major:");
        optionLabel = new JLabel("Choose Selection:");
        gradeLabel = new JLabel("Choose grade:");
        creditLabel = new JLabel("Choose credits:");
        
        // Setting the JTextField variables
        userID = new JTextField(10);
        userID.setMaximumSize(userID.getPreferredSize());
        userName = new JTextField(10);
        userName.setMaximumSize(userName.getPreferredSize());
        userMajor = new JTextField(10);
        userMajor.setMaximumSize(userMajor.getPreferredSize());
        
        // Setting the JCheckBox variable
        databaseOptionBox = new JComboBox(databaseOptions);
        // Action listeners to get the String item in the array
        databaseOptionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JComboBox comboBoxOptions1 = (JComboBox)ae.getSource();
                chosenOption1 = (String)comboBoxOptions1.getSelectedItem();
            }
        });
        
        // Setting the JButton variable
        process = new JButton("Process Request");
        // Compute actionListener to decide which function was chosen
        // and computing the correct result and efficiency
        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String studentID = userID.getText();
                String studentName = userName.getText();
                String studentMajor = userMajor.getText();
                Student studentMethods = new Student(studentName, studentMajor);
                System.out.println("Students: " + students);
                if(chosenOption1 == "Insert") {
                    if(checkStudent(studentID) == true) {
                        JOptionPane.showMessageDialog(null, "The user entered is already "
                                + "in the system. Please enter a new user.");
                    }
                    else {
                        students.put(studentID, studentName);
                        JOptionPane.showMessageDialog(null, "The user entered is now in the system.");
                    }
                }
                else if(chosenOption1 == "Delete") {
                    if(checkStudent(studentID) == true) {
                        students.remove(studentID);
                        JOptionPane.showMessageDialog(null, "The user entered has been removed from the system.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The user entered is not "
                                + "in the system to remove. Please enter a new user.");
                    }
                }
                else if(chosenOption1 == "Find") {
                    if(checkStudent(studentID) == true) {
                        JOptionPane.showMessageDialog(null, studentMethods.toString());;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The user entered is not "
                                + "in the system and could not be found. Please enter a new user.");
                    }
                }
                else if(chosenOption1 == "Update") {
                    if(checkStudent(studentID) == true) {
                        chosenOption2 = (String)JOptionPane.showInputDialog(null, "Choose credits:",
                            "", JOptionPane.QUESTION_MESSAGE, null, grades, grades);
                        chosenOption3 = (String)JOptionPane.showInputDialog(null, "Choose credits:",
                                "", JOptionPane.QUESTION_MESSAGE, null, credits, credits);
                        studentMethods.courseCompleted(chosenOption2, chosenOption3);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The user entered is not "
                                + "in the system and cannot be updated. Please enter a new user.");
                    }
                }
            }
        });

        
        // GridLayout was used to place the ojects in cells with
        // 6 rows, 2 columns, and hgaps of 10 and vgaps of 15
        JPanel panel = new JPanel(new GridLayout(5,2,15,30));
        panel.add(userIDLabel);
        panel.add(userID);
        panel.add(userNameLabel);
        panel.add(userName);
        panel.add(userMajorLabel);
        panel.add(userMajor);
        panel.add(optionLabel);
        panel.add(databaseOptionBox);
        panel.add(process);
        panel.add(new JLabel(""));;
        
        frame.add(panel);
        
        // Exiting the program once "X" button on the window is pressed
        // Once program is exited, the file "efficiency" is created
        // and the efficieny values of both the recursive and iterative
        // methods are added to the file, along with the count.
        // File needs to be a csv (commas seperated values) file
        // because the values are seperated by commas to ensure a graph can be made
        // and the file can be opened in Excel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public boolean checkStudent(String id) {
        if(students.containsKey(id)) {
            return true;
        }
        else {
            return false;
        }
    } 
    
    public static void main(String[] args) {
        new StudentDatabaseGUI();
    }
}
