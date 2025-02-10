import java.util.Scanner;

public class BankingApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LoginSection.displayLoginMenu();

        System.out.print("\n" + "Enter choice: ");
        int choice = scanner.nextInt();
        System.out.print("\n");

        switch(choice) {
            case 1:
                LoginSection.createAccount();
                break;

            case 2:
                LoginSection.login();
                break;

            case 3:
                LoginSection.deleteAccount();
                break;

            default:
                System.out.println("Invalid choice" + "\n");
        }
    }
}
