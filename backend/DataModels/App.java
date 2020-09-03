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

               System.out.println("Enter the corresponding number to indicate the type of calculations you want to perform.");
               System.out.println("1: Simple Moving Average");
               System.out.println("2: Relative Strength Index (RSI)");
               System.out.println("3: Moving Average Convergence/Divergence (MACD)");
               while(!input.hasNextInt())
               {
                  input.next();
                  System.out.println("Incorrect input, try again");
               }
               int answer = input.nextInt();

               switch(answer)
               {
                  case 1:
                     Float simpleAvg = calcMovingAvg(c, ticker);
                     System.out.println(simpleAvg);
                     break;
                  case 2:
                     calcRsi(c, ticker);
                     break;
                  case 3:
                     calcMacd(c, ticker);
                     break;
                  default:
                     System.out.println("Invalid option.");
                     break;
               }

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

         boolean valid = false;
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
         
         int rank = 0;
         try
         {
            rank = result.getInt("myrank");
         }
         catch(Exception e)
         {
            System.out.println("The date you provided isn't valid");
         }

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

         PreparedStatement statement = c.prepareStatement("select dataid from relates where ticker = '" + ticker + "'");
         ResultSet result = statement.executeQuery();
         if(!result.next())
            System.out.println("That table doesn't exist");
         int dataid = result.getInt("dataid");

         System.out.print("Enter the date to calculate the RSI (yyyy-mm-dd): ");
         String date = input.next();
         statement = c.prepareStatement("select date from market where date = '" + date + "' and dataid = '" + dataid + "'");
         result = statement.executeQuery();
         if(!result.next())
            System.out.println("That table doesn't exist");
         java.sql.Date dateDB;
         try
         {
            dateDB = result.getDate("date");
         }
         catch(Exception e)
         {
            System.out.println("The date you entered is invalid.");
            return;
         }

         System.out.print("Enter the period for which you want the RSI to be calculated: ");
         while(!input.hasNextInt())
         {
            input.next();
            System.out.println("Incorrect input, try again");
         }
         int period = input.nextInt();

         ArrayList<Float> avgU = new ArrayList<Float>();
         ArrayList<Float> avgD = new ArrayList<Float>();

         statement = c.prepareStatement("select myrank from prevcloseview where date = '" + date + "' and ticker = '" + ticker + "'");
         result = statement.executeQuery();
         if(!result.next())
            System.out.println("That table doesn't exist");
         int rank = result.getInt("myrank");

         for(int i = rank + period; i > rank; i--)
         {
            statement = c.prepareStatement("select close - prevclose as diff from prevcloseview where myrank = '" + i + "' and ticker = '" + ticker + "'");
            result = statement.executeQuery();
            if(!result.next())
               System.out.println("That table doesn't exist");
            Float diff = result.getFloat("diff");
            if(diff > 0)
            {
               avgU.add(diff);
            }
            else if(diff < 0)
            {
               avgD.add(diff);
            }
         }

         Float totAvgU = 0.0f;
         int uCount = 0;
         Float totAvgD = 0.0f;
         int dCount = 0;
         for(int i = 0; i < avgU.size(); i++)
         {
            totAvgU += avgU.get(i);
            uCount++;
         }
         for(int i = 0; i < avgD.size(); i++)
         {
            totAvgD += avgD.get(i);
            dCount++;
         }
         Float sumU = totAvgU / uCount;
         Float sumD = Math.abs(totAvgD) / dCount;

         Float rs = sumU / sumD;
         Float rsi = 100 - (100 / (1 + rs));
         System.out.println(rsi);
      }

      //calculates MACD
      public static void calcMacd(Connection c, String ticker) throws SQLException, IOException, java.lang.ClassNotFoundException
      {
         Scanner input = new Scanner(System.in);
      }
}