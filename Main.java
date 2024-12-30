import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final HashMap<String, String> userDatabase = new HashMap<>();
    private static final HashMap<String, Double> accountBalances = new HashMap<>();

    public static void main(String[] args) {
        initializeDatabase();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the ATM Interface");

            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (authenticateUser(username, pin)) {
                System.out.println("\nLogin successful!");
                performOperations(scanner, username);
            } else {
                System.out.println("\nInvalid credentials. Exiting.");
            }
        }
    }

    private static void initializeDatabase() {
        userDatabase.put("user1", "1234");
        userDatabase.put("user2", "5678");
        accountBalances.put("user1", 5000.0);
        accountBalances.put("user2", 3000.0);
    }

    private static boolean authenticateUser(String username, String pin) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(pin);
    }

    private static void performOperations(Scanner scanner, String username) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> checkBalance(username);
                case 2 -> depositMoney(scanner, username);
                case 3 -> withdrawMoney(scanner, username);
                case 4 -> {
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance(String username) {
        System.out.println("Your current balance is: $" + accountBalances.get(username));
    }

    private static void depositMoney(Scanner scanner, String username) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            accountBalances.put(username, accountBalances.get(username) + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    private static void withdrawMoney(Scanner scanner, String username) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= accountBalances.get(username)) {
            accountBalances.put(username, accountBalances.get(username) - amount);
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }
}
