package FallingKnives;

import java.io.File;
import java.io.PrintStream;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import static spark.Spark.*;

public class App
{
    public static void main(String[] args) throws SQLException
    {
        Connection conn = null;
        // Get connection to the database
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:///postgres?cloudSqlInstance=adroit-nimbus-272020:us-central1:database&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=postgres&password=csb312");
        }
        catch (Exception e) {
            System.out.println("Connenction Failed");
            e.printStackTrace();
        }
        regularUI(conn);
    }

    public static void webUI(Connection conn)
    {
        // Set up a route for serving the main page
        get("/", (request, response) -> {

            return "";
        });
    }

    public static void regularUI(Connection conn) throws SQLException
    {
        menu();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char toDo = prompt(in);

        if (toDo == 'T') {
            Tester.TestMethod(conn);
        }
        else if (toDo == 'A') {
            PreparedStatement statement;
            ResultSet result = null;
            try
            {
                Scanner input = new Scanner(System.in);
                boolean flag = true;
                System.out.println("------------------------------");
                System.out.println("Welcome to the user interface");
                do
                {
                    System.out.println("Please enter the start date, end date, and percent change");
                    System.out.print("Start Date (mm-dd-yyyy): ");
                    String startDate = input.next();
                    System.out.print("End Date (mm-dd-yyyy): ");
                    String endDate = input.next();
                    System.out.print("Percent change: ");
                while(!input.hasNextFloat())
                {
                    input.next();
                    System.out.println("Incorrect input, try again");
                }
                float percentChange = input.nextFloat();

                statement = conn.prepareStatement("select dataid, sum((close-open)/nullif(open, 0))  as change, '" + startDate + "'  as startdate, '" + endDate + "' as enddate from market where date between '" + startDate + "' and '" + endDate + "' group by dataid having sum((close-open)/nullif(open, 0)) < " + percentChange + ";");

                result = statement.executeQuery();
                if(!result.next())
                {
                    System.out.println("That table doesn't exist");
                }
                // PrintStream o = new PrintStream(new File("output.csv"));
                // PrintStream console = System.out;
                // System.setOut(o);
                System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", "Ticker", "Data ID", "Change", "Start Date", "End Date"));
                do
                {
                    PreparedStatement ticker = conn.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "'");
                    ResultSet tickerResult = ticker.executeQuery();
                    tickerResult.next();
                    System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", tickerResult.getString("ticker"), result.getString("dataid"), result.getString("change"), result.getString("startdate"), result.getString("enddate")));
                }
                while(result.next());

                // System.setOut(console);
                System.out.println("Enter \"quit\" to quit or anything else to search again");
                String choice = input.next();
                if(choice.equals("quit"))
                {
                    flag = false;
                }
                }
                while(flag);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (toDo == 'C') {
            PrevCloseView.CreatePrevCloseView(conn);
        }

        conn.close();
        System.out.println("Operation Completed");
        return;
    }

    static char prompt(BufferedReader in) {
        // The valid actions:
        String actions = "TAC";

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
        System.out.println("\t[T] Test SQL Statements");
        System.out.println("\t[A] Launch Application");
        System.out.println("\t[C] Run Command Line Admin Application");
    }
}
