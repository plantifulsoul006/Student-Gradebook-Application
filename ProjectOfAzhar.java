import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class ProjectOfAzhar {
	public static Scanner in = new Scanner(System.in); 
	static ArrayList<String> studentNames = new ArrayList<>(); 
	static ArrayList<Integer> studentID = new ArrayList<>(); 
	static ArrayList<Integer> studentTestScores = new ArrayList<>(); 
	static ArrayList<Character> studentFinalGrades = new ArrayList<>(); 
	static boolean checkAddStudent = true;
	public static char GetStudentLetterGrade(int testScore) {
		char studentLetterGrade = ' ';
		if (testScore < 60) {
			studentLetterGrade = 'F';
		}else if (testScore < 70) {
			studentLetterGrade = 'D';
		}else if (testScore < 80) {
			studentLetterGrade = 'C';
		}else if (testScore < 90) {
			studentLetterGrade = 'B';
		}else {
			studentLetterGrade = 'A';
		}
		return studentLetterGrade;
	}
	public static void CalculateGrades() {
		for (int i = 0; i < studentTestScores.size(); i++) {
            studentFinalGrades.add(GetStudentLetterGrade(studentTestScores.get(i)));
        }
        System.out.println("Grades calculated.");
        System.out.println("Grade Details added.");
	}
	public static void ViewStatistics(List<Integer> scores) {
		int count = 0;
    	for (double i : scores) {
        	count++;
    	}
		System.out.println("Total Students: " + count);
		double sum = 0;
		for (double i : scores) {
			sum = sum + i;
		}
		double averageScore = (double) sum / count;
		System.out.println("Average Score: " + averageScore);
		double max = scores.get(0);
		for (double i : scores) {
			if (max < i) {
				max = i;
			}
		}
		System.out.println("Highest Score: " + max);
		double min = scores.get(0);
		for (double i : scores) {
			if (min > i) {
				min = i;
			}
		}
		System.out.println("Lowest Score: " + min);
	}
	public static void GenerateReports(int id) {
    int index = studentID.indexOf(id);
	if (index != -1) {
        System.out.println("Generating report for student with ID: " + id);
        System.out.println("Student Report");
        System.out.println("Name: " + studentNames.get(index));
        System.out.println("Student ID: " + id);
        System.out.println("Test Score: " + (double) studentTestScores.get(index));
        System.out.println("Final Grade: " + studentFinalGrades.get(index));
    } else {
        System.out.println("Student with ID " + id + " not found.");
    }
	}
	public static void ShowMenu() {
		System.out.println("Main Menu");
		System.out.println("1. Add Student");
		System.out.println("2. Manage Records");
		System.out.println("3. Calculate Grades");
		System.out.println("4. View Statistics");
		System.out.println("5. Generate Reports");
		System.out.println("6. Delete Students");
		System.out.println("7. Logout and exit");
		System.out.println("Enter your choice: ");			
	}
	public static void AddStudent() {
		while (true) {
			System.out.println("Enter student name: ");
			String nameOfStudent = in.nextLine();
			in.nextLine();
			System.out.println("Enter student ID: ");
			int idOfStudent = in.nextInt();
			int lengthOfID = (int) (Math.log10(idOfStudent) + 1);
			System.out.println("Enter test score: ");
			int testScoreOfStudent = in.nextInt();
			if (nameOfStudent.length() <= 50 && lengthOfID <= 9 && testScoreOfStudent <= 100 && testScoreOfStudent >= 0) {
				in.nextLine();
				studentNames.add(nameOfStudent);
				studentID.add(idOfStudent);
				studentTestScores.add(testScoreOfStudent);
				System.out.println("Student added successfully.");
				return;
			} else {
				System.out.println("The entered name or ID or test score do not match the expected format. Please try again!");
			}
		}
	}
	public static void CheckID() {
		while (true) {
			System.out.println("Enter student ID to edit: ");
			int inputID = in.nextInt();
			if (studentID.contains(inputID)) {
				System.out.println("Enter new test score: ");
				int sscore = in.nextInt();
				int newScore = sscore;
				int index = studentID.indexOf(inputID);
				studentTestScores.set(index, newScore);
				System.out.println("Student record updated successfully.");
				return;
			} else {
				System.out.println("Student with ID " + inputID + " is not found. Please try again.");
			}
		}
	}
	public static void DeleteStudent() {
		while (true) {
			System.out.println("Enter student ID to delete: ");
			int inputStudentID = in.nextInt();
			int indexOfStudent = studentID.indexOf(inputStudentID);
			if (indexOfStudent != -1) {
				studentNames.remove(indexOfStudent);
				studentID.remove(indexOfStudent);
				studentTestScores.remove(indexOfStudent);
				studentFinalGrades.remove(indexOfStudent);
				System.out.println("Student deleted successfully.");
				return;
			} else {
				System.out.println("Student with ID " + inputStudentID + " is not found. Please try again.");
			}
		}
	}
	public static void main(String args[]){
		boolean teacherIsLoggedIn = false;
		while (true) {
			if (!teacherIsLoggedIn) {
				System.out.println("Enter username: ");
				String usernameOfTeacher = in.nextLine();
				System.out.println("Enter password: ");
				String passwordOfTeacher = in.nextLine();
				
				if(usernameOfTeacher.equals("username") && passwordOfTeacher.equals("12345678")){
					teacherIsLoggedIn = true;
				} else {
					System.out.println("Your username or password is incorrect. Please try again.");
					continue;
				}
			}
			ShowMenu();
			int choiceFromMenu = in.nextInt();
			switch (choiceFromMenu) {
				case 1:
					AddStudent();
					break;
				case 2:
					CheckID();
					break;
				case 3:
					CalculateGrades();
					break;
				case 4:
					System.out.println("Statistics:");
					ViewStatistics(studentTestScores);
					break;
				case 5:
					System.out.println("Enter student ID to generate a report: ");
					int input = in.nextInt();
					GenerateReports(input);
					break;
				case 6:
					DeleteStudent();
					break;
				case 7:
					System.out.println("Exit program.");
					System.exit(0);
				default: 
					System.out.println("You entered wrong choice. Please try again.");
			}
		}
	}
}
