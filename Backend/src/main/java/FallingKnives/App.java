package FallingKnives;

import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws SQLException
    {
        Connection conn = null;
        // Get connection to the database
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC driver installed");
            //conn = DriverManager.getConnection("jdbc:postgresql:///postgres?cloudSqlInstance=adroit-nimbus-272020:us-central1:database&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=postgres&password=csb312");
            System.out.println("Congratulations");
        }
        catch (Exception e) {
            System.out.println("Connenction Failed");
            e.printStackTrace();
            //return;
        }

        menu();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char toDo = prompt(in);

        if (toDo == 'T') {
            Tester.TestMethod(conn);
        }
        else if (toDo == 'A') {
            // TODO: Launch Application;
        }

        System.out.println("Operation Completed");
    }

    static char prompt(BufferedReader in) {
        // The valid actions:
        String actions = "TA";

        // We repeat until a valid single-character option is selected        
        while (true) {
            System.out.print("[" + actions + "] :> ");
            String action;
            try {
                action = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (action.length() != 1)
                continue;
            if (actions.contains(action)) {
                return action.charAt(0);
            }
            System.out.println("Invalid Command");
        }
    }

    static void menu() {
        System.out.println("What action would you like to complete?");
        System.out.println("[T] Test SQL Statements");
        System.out.println("[A] Launch Application");
    }
}
