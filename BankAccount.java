import java.util.Random;
import java.util.Scanner;
import java.sql.*;

class BankAccount {

    static {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:accounts.db");
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts ("
                                  + "account_number TEXT PRIMARY KEY, "
                                  + "passkey TEXT NOT NULL, "
                                  + "balance INTEGER NOT NULL, "
                                  + "created_at TEXT DEFAULT CURRENT_TIMESTAMP"
                                  + ");";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }


    static public void createAccount() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        String accountID = sb.toString();
        int initialBalance = 0;

        System.out.print("Enter 4 digit passkey: ");
        String passKey = scanner.nextLine();

        String insertSQL = "INSERT INTO accounts (account_number, passkey, balance) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:accounts.db");
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, accountID);
            pstmt.setInt(2, passKey);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

            System.out.println("Your account has been created");
            System.out.println("Your account number: " + accountID);
        }

        catch (SQLException e) {
            System.err.println("Error creating account: " + e.getMessage());
        }
    }

    static public void deleteAccount() {
        System.out.print("Enter your account number: ");
    }

    static public void depositMoney() {}

    static public void withdrawMoney() {}

    static public void checkBalance() {}

    static public void transferMoney() {}
}
