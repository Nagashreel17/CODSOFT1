import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private String pin;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private int maxAttempts = 3; // Maximum PIN attempts

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Authenticate the user by asking for the PIN
        if (!authenticate(scanner)) {
            System.out.println("Too many incorrect attempts. Exiting program.");
            return;
        }

        // Start ATM operations after successful authentication
        while (true) {
            System.out.println("------------------------");
            System.out.println("\nWelcome to the ATM ");
            System.out.println("------------------------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private boolean authenticate(Scanner scanner) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Enter your ATM PIN: ");
            String enteredPin = scanner.next();
            if (account.authenticate(enteredPin)) {
                System.out.println("Authentication successful!\n");
                return true;
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }
        return false;
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.deposit(amount);
            System.out.printf("Successfully deposited $%.2f. Your new balance is $%.2f.\n", amount, account.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            if (account.withdraw(amount)) {
                System.out.printf("Successfully withdrew $%.2f. Your new balance is $%.2f.\n", amount, account.getBalance());
            } else {
                System.out.println("Insufficient balance. Please try a smaller amount.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }
}

// Main class to run the ATM system
public class AtmInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.00, "1234");  // Initial balance of $500.00 and PIN set to "1234"
        ATM atm = new ATM(account);
        atm.start();
    }
}
