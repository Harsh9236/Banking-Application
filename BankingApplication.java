import java.util.Scanner;

public class BankingApplication {

    static void displayChoices() {
        System.out.println("1. Create account");
        System.out.println("2. Delete account");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Check balance");
        System.out.println("6. Transfer money");
        System.out.println("7. Exit");
    }

    public static void main(String[] args) {

        boolean loopSwitch = true;
        while(loopSwitch) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n" + "===== Banking Application =====" + "\n");
            displayChoices();

            System.out.print("\n" + "Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.print("\n");

            switch(choice) {
                case 1:
                    BankAccount.createAccount();
                    break;

                case 2:
                    BankAccount.deleteAccount();
                    break;

                case 3:
                    BankAccount.depositMoney();
                    break;

                case 4:
                    BankAccount.withdrawMoney();
                    break;

                case 5:
                    BankAccount.checkBalance();
                    break;

                case 6:
                    BankAccount.transferMoney();
                    break;

                case 7:
                    loopSwitch = false;
                    break;

                default:
                    System.out.println("Invalid choice" + "\n");
            }
        }
    }
}
