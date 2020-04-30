import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class App
{
    public static void main(String args[]) throws SQLException, IOException, java.lang.ClassNotFoundException
    {
         Connection c = null;
         try
         {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql:///postgres?cloudSqlInstance=adroit-nimbus-272020:us-central1:database&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=postgres&password=csb312");
         }
         catch (Exception e)
         {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
         }
         System.out.println("Opened database successfully");

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
               System.out.println("Please enter the ticker for the stock you want to perform calculations on");
               System.out.print("Ticker: ");
               String ticker = input.next();
               Float simpleAvg = calcMovingAvg(c, ticker);
               System.out.println(simpleAvg);
               // ArrayList<Float> upMov = new ArrayList<Float>();
               // ArrayList<Float> downMov = new ArrayList<Float>();

               // statement = c.prepareStatement("select dataid, sum((close-open)/nullif(open, 0))  as change, '" + startDate + "'  as startdate, '" + endDate + "' as enddate from market where date between '" + startDate + "' and '" + endDate + "' group by dataid having sum((close-open)/nullif(open, 0)) < " + percentChange + ";");

               // result = statement.executeQuery();
               // if(!result.next())
               // {
               //    System.out.println("That table doesn't exist");
               // }
               // PrintStream o = new PrintStream(new File("output.csv"));
               // PrintStream console = System.out;
               // System.setOut(o);
               // System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", "Ticker", "Data ID", "Change", "Start Date", "End Date"));
               // do
               // {
               //    PreparedStatement ticker = c.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "'");
               //    ResultSet tickerResult = ticker.executeQuery();
               //    tickerResult.next();
               //    System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", tickerResult.getString("ticker"), result.getString("dataid"), result.getString("change"), result.getString("startdate"), result.getString("enddate")));
               // }
               // while(result.next());

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

      //method to calculate moving average
      public static Float calcMovingAvg(Connection c, String ticker) throws SQLException, IOException, java.lang.ClassNotFoundException
      {
         Scanner input = new Scanner(System.in);
         System.out.print("Enter the start date for which you would like to calculate the moving average for the previous x days for (yyyy-mm-dd): ");
         String date = input.next();

         System.out.print("Enter the amount of days you would like to calculate the moving average for: ");
         while(!input.hasNextInt())
         {
            input.next();
            System.out.println("Incorrect input, try again");
         }
         int days = input.nextInt();
         
         PreparedStatement statement = c.prepareStatement("select myrank from prevcloseview where date = '" + date + "' and ticker = '" + ticker + "'");
         ResultSet result = statement.executeQuery();
         if(!result.next())
            System.out.println("That table doesn't exist");
         int rank = result.getInt("myrank");

         statement = c.prepareStatement("Select sum(close) as sm from prevcloseview where myrank between " + (rank - days - 1) + " and " + (rank - 1) + " and ticker = '" + ticker + "';");
         result = statement.executeQuery();
         if(!result.next())
            System.out.println("That table doesn't exist");
         Float sum = result.getFloat("sm");
         Float avg = sum / days;
         return avg;
      }

      //calculates RSI
      public static void calcRsi(Connection c, String ticker) throws SQLException, IOException, java.lang.ClassNotFoundException
      {
         Scanner input = new Scanner(System.in);
      }

      //calculates MACD
      public static void calcMacd(Connection c, String ticker) throws SQLException, IOException, java.lang.ClassNotFoundException
      {
         Scanner input = new Scanner(System.in);
      }
}