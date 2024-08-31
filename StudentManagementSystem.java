import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent individual students
class IndividualStudent implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public IndividualStudent(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "IndividualStudent{" +
                "name='" + name + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

// Class to manage the collection of students
class StudentRecordsManager {
    private List<IndividualStudent> students;
    private final String dataFile = "students.dat";

    public StudentRecordsManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(IndividualStudent student) {
        students.add(student);
        saveStudents();
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
        saveStudents();
    }

    public IndividualStudent searchStudent(String rollNumber) {
        for (IndividualStudent student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (IndividualStudent student : students) {
                System.out.println(student);
            }
        }
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            students = (List<IndividualStudent>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found; it will be created on first save.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Main class to run the Student Management System
public class StudentManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentRecordsManager recordsManager = new StudentRecordsManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("----------------------------");
            System.out.println("\nStudent Management System");
            System.out.println("----------------------------");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    recordsManager.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter student's grade: ");
        String grade = scanner.nextLine();

        if (validateInput(name, rollNumber, grade)) {
            IndividualStudent student = new IndividualStudent(name, rollNumber, grade);
            recordsManager.addStudent(student);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Invalid input. All fields are required.");
        }
    }

    private static void removeStudent() {
        System.out.print("Enter roll number of student to remove: ");
        String rollNumber = scanner.nextLine();
        recordsManager.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter roll number of student to search: ");
        String rollNumber = scanner.nextLine();
        IndividualStudent student = recordsManager.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static boolean validateInput(String name, String rollNumber, String grade) {
        return !name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty();
    }
}
