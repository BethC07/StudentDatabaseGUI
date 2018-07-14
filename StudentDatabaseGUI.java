package studentdb;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainGUI extends JPanel {

    // First, I initiate all the JFrame variables I need to make my GUI work
    // JFrame is the box that will appear
    public JFrame frame;
    // JTextField is an inputable field that can be editied
    public JTextField idInput, nameInput, majorInput;
    public JLabel idLabel, nameLabel, majorLabel, selectLabel;
	public JComboBox<String> selectList, gradeList;
	public JComboBox<Integer> creditList;
    public Map<String, Student> studentDB = new HashMap<>();
	
    // JButton is exactly that, a button
    // It can actually do something if it has a ActionListener
    public JButton processButton;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 250;
    
    public MainGUI() {
        // Calling the createPanel class to create the components in the box
        createPanel();
    }
    
    private void createPanel() {      
        frame = new JFrame("Project 4");
        // Setting the width and hight of the box
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        idLabel = new JLabel("Id:");
        nameLabel = new JLabel("Name:");
        majorLabel = new JLabel("Major:");
        selectLabel = new JLabel("Choose Selection:");
        
        // Setting the JTextField variables
        idInput = new JTextField(10);
        idInput.setMaximumSize(idInput.getPreferredSize());
        nameInput = new JTextField(30);
        nameInput.setMaximumSize(nameInput.getPreferredSize());
        majorInput = new JTextField(30);
        majorInput.setMaximumSize(majorInput.getPreferredSize());
        
        String[] selectOptions = {"Insert", "Delete", "Find", "Update"};
        selectList = new JComboBox<>(selectOptions);
        selectList.setSelectedIndex(0);
        
        String[] gradeOptions = {"A", "B", "C", "D", "F"};
        //gradeList = new JComboBox<>(gradeOptions);
        //gradeList.setSelectedIndex(0);
        
        Integer[] creditOptions = {3, 6};
        // = new JComboBox<>(creditOptions);
        //creditList.setSelectedIndex(0);
        
        // Setting the JButton variable
        processButton = new JButton("Process Request");

        // calculateWithdraw actionListener
        processButton.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
            	
            	String selectAction = selectList.getSelectedItem().toString();
            	String idText = idInput.getText();
            	String nameText = nameInput.getText();
            	String majorText = majorInput.getText();
            	
            	if(selectAction == "Insert") {
            		if(!idText.equals("") && !nameText.equals("") && !majorText.equals("")) {
            			if(!studentDB.containsKey(idText)) {
            				Student newStudent = new Student(nameText, majorText);
                			studentDB.put(idText, newStudent);
                			JOptionPane.showMessageDialog(null, "Student inserted into database");
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "Student ID already exists in database");
                		}
            			
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "Missing required information for insert");
            		}
                }
                else if(selectAction == "Delete") {
                	if(!idText.equals("")) {
	                	if(studentDB.containsKey(idText)) {
	            			studentDB.remove(idText);
	            			JOptionPane.showMessageDialog(null, "Student deleted from database");
	            		}
	            		else {
	            			JOptionPane.showMessageDialog(null, "Student ID not found in database");
	            		}
                	}
                	else {
            			JOptionPane.showMessageDialog(null, "Missing required information for delete");
            		}
                }
                else if(selectAction == "Find") {
                	
                	if(!idText.equals("")) {
                		
                		if(studentDB.containsKey(idText)) {
                			Student selectedStudent = studentDB.get(idText);
                			JOptionPane.showMessageDialog(null, selectedStudent.toString());
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "Student ID not found in database");
                		}
                	}
                	else {
            			JOptionPane.showMessageDialog(null, "Missing required information for find");
            		}
                }
                else if(selectAction == "Update") {
                	
                	if(!idText.equals("")) {
                		
                		if(studentDB.containsKey(idText)) {
                			Student selectedStudent = studentDB.get(idText);
                			
                			String gradeSelection = (String)JOptionPane.showInputDialog(null,"Choose grade", null, JOptionPane.INFORMATION_MESSAGE, null, gradeOptions, gradeOptions[0]);
                        	Integer creditSelection = (Integer)JOptionPane.showInputDialog(null,"Choose credits", null, JOptionPane.INFORMATION_MESSAGE, null, creditOptions, creditOptions[0]);
                        	
                        	if(gradeSelection != null && creditSelection != null) {
                            	selectedStudent.courseCompleted(gradeSelection, creditSelection);
                        	}
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "Student ID not found in database");
                		}
                	}
                	else {
            			JOptionPane.showMessageDialog(null, "Missing required information for update");
            		}
                }
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(6,5,20,15));
        mainPanel.add(idLabel);
        mainPanel.add(idInput);
        mainPanel.add(nameLabel);
        mainPanel.add(nameInput);
        mainPanel.add(majorLabel);
        mainPanel.add(majorInput);
        mainPanel.add(selectLabel);
        mainPanel.add(selectList);
        mainPanel.add(processButton);
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        
        frame.add(mainPanel);
        
        // Exiting the program once "X" button on the window is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

 public static void main(String[] args) {
     
	 new MainGUI();

    }   
}
