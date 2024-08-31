import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MAX_ATTEMPTS = 5; // Maximum number of attempts allowed
    private static final int MIN_RANGE = 1; // Minimum range for random number
    private static final int MAX_RANGE = 100; // Maximum range for random number
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalRounds = 0; // Total rounds played
    private static int roundsWon = 0; // Total rounds won

    public static void main(String[] args) {
        boolean playAgain = true;
        
        while (playAgain) {
            playRound();
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");
        }

        // Display the final score
        System.out.println("Thanks for playing!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total rounds won: " + roundsWon);
        System.out.println("Your winning percentage: " + ((double) roundsWon / totalRounds * 100) + "%");
    }

    private static void playRound() {
        totalRounds++;
        int randomNumber = generateRandomNumber(MIN_RANGE, MAX_RANGE);
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Guess the number between " + MIN_RANGE + " and " + MAX_RANGE + "!");
        
        while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
            System.out.print("Enter your guess (Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "): ");
            int userGuess = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            attempts++;

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You've guessed the correct number: " + randomNumber);
                guessedCorrectly = true;
                roundsWon++;
            } else if (userGuess < randomNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry! You've used all attempts. The correct number was: " + randomNumber);
        }
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min; // Generate a number between min and max (inclusive)
    }
}
