import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for number of subjects with validation
        int numberOfSubjects = 0;
        while (true) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                numberOfSubjects = scanner.nextInt();
                if (numberOfSubjects > 0) break;
                else System.out.println("Number of subjects should be greater than 0.");
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }

        // Array to store marks for each subject
        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        // Input marks for each subject with validation
        for (int i = 0; i < numberOfSubjects; i++) {
            while (true) {
                System.out.print("Enter marks for subject " + (i + 1) + " (0-100): ");
                if (scanner.hasNextInt()) {
                    int mark = scanner.nextInt();
                    if (mark >= 0 && mark <= 100) {
                        marks[i] = mark;
                        totalMarks += mark;
                        break;
                    } else {
                        System.out.println("Please enter marks between 0 and 100.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // Clear invalid input
                }
            }
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Determine grade based on average percentage and give feedback
        char grade;
        String feedback;
        if (averagePercentage >= 90) {
            grade = 'A';
            feedback = "Excellent work! Keep it up!";
        } else if (averagePercentage >= 80) {
            grade = 'B';
            feedback = "Great job! A little more effort and you can reach the top!";
        } else if (averagePercentage >= 70) {
            grade = 'C';
            feedback = "Good work, but there's room for improvement.";
        } else if (averagePercentage >= 60) {
            grade = 'D';
            feedback = "You passed, but it's important to work harder.";
        } else {
            grade = 'F';
            feedback = "You did not pass. Don't get discouraged, seek help and try again!";
        }

        // Display the results with feedback
        System.out.println("\n---------- Results ----------");
        System.out.println();
        System.out.println("Total Marks Obtained: " + totalMarks + " out of " + (numberOfSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Final Grade: " + grade);
        System.out.println();
        System.out.println("Feedback: " + feedback);
        System.out.println("-----------------------------");

        scanner.close();
    }
}
