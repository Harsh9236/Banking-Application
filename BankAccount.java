import java.util.Random;
import java.util.Scanner;
import java.sql.*;

class LoginSection {

    static {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:accounts.db"); Statement s = connection.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                                 "account_number TEXT PRIMARY KEY, " +
                                 "password TEXT NOT NULL, " +
                                 "balance INTEGER NOT NULL, " +
                                 ");";

            s.execute(createTable);

        } catch (SQLException e) {
            System.err.println("Database creation error: " + e.getMessage());
        }
    }

    public static void displayLoginMenu() {
        System.out.println("1. Create account");
        System.out.println("2. Login");
        System.out.println("3. Delete account");
    }

    private static boolean doesAccountNumberExist(String accountNumber) {
        String checkTable = "SELECT account_number FROM accounts WHERE account_number = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:accounts.db"); PreparedStatement ps = connection.prepareStatement(checkTable)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking account number existence: " + e.getMessage());
            return false;
        }
    }

    static public void createAccount() {
        Random random = new Random();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        boolean accountNumberUnique = false;

        while (!accountNumberUnique) {
            for (int i = 0; i < 8; i++) {
                int digit = random.nextInt(10);
                sb1.append(digit);
            }

            String accountID = sb1.toString();

            if (!doesAccountNumberExist(accountID)) {
                    accountNumberUnique = true;
            }

            sb2 = sb1;
        }

        String accountID = sb2.toString();
        int initialBalance = 0;

        System.out.print("Enter 4 digit passkey: ");
        String passKey = scanner.nextLine();

        String insertSQL = "INSERT INTO accounts (account_number, passkey, balance) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:accounts.db");
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, accountID);
            pstmt.setString(2, passKey);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

            System.out.println("Your account has been created");
            System.out.println("Your account number: " + accountID);
        }

        catch (SQLException e) {
            System.err.println("Error creating account: " + e.getMessage());
        }
    }
}

    public static void login(String accountID, String password) {}

    public static void deleteAccount(String accountID, String password) {}
}
