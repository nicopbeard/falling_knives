package FallingKnives;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        regularUI(conn);
    }

    public static void regularUI(Connection conn) throws SQLException
    {
        menu();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char toDo = prompt(in);

        if (toDo == 'P')
        {
            
            PreparedStatement statement;
            ResultSet result = null;
            try
            {
                Scanner input = new Scanner(System.in);
                boolean flag = true;
                System.out.println("Please enter the length of time (years) and percent change");
                System.out.print("Length of time: ");
                while(!input.hasNextInt())
                {
                    input.next();
                    System.out.println("Incorrect input, try again");
                }
                int time = input.nextInt();
                System.out.print("Percent change: ");
                while(!input.hasNextFloat())
                {
                    input.next();
                    System.out.println("Incorrect input, try again");
                }
                float percentChange = input.nextFloat();

                String startDate = "2010-01-01";
                String endDate = "2012-01-01";
                statement = conn.prepareStatement("select dataid, sum((close-open)/nullif(open, 0))  as change, '" + startDate + "'  as startdate, '" + endDate + "' as enddate from market where date between '" + startDate + "' and '" + endDate + "' group by dataid having sum((close-open)/nullif(open, 0)) < " + percentChange + ";");
                ArrayList<String> tickers = new ArrayList<String>();
                ArrayList<String> dataids = new ArrayList<String>();
                result = statement.executeQuery();
                if(!result.next())
                    System.out.println("That table doesn't exist");
                do
                {
                    PreparedStatement ticker = conn.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "';");
                    ResultSet tickerResult = ticker.executeQuery();
                    tickerResult.next();
                    tickers.add(tickerResult.getString("ticker"));
                    dataids.add(result.getString("dataid"));
                }
                while(result.next());
                for(int i = 0; i < dataids.size(); i++)
                {
                    String dataid = dataids.get(i);
                    // Float niChange = calcPercentChangeAnnual(dataid, startDate, endDate, "net_income", conn);
                    // Float revChange = calcPercentChangeAnnual(dataid, startDate, endDate, "revenue", conn);
                    // Float gpChange = calcPercentChangeAnnual(dataid, startDate, endDate, "gross_profit", conn);
                    // Float crChange = calcPercentChangeAnnual(dataid, startDate, endDate, "current_ratio", conn);
                    // Float opCashChange = calcPercentChangeAnnual(dataid, startDate, endDate, "operating_cash_flow", conn);
                    Float debtEquityChange = calcPercentChangeQuarterly(dataid, startDate, endDate, "debt_equity_ratio", conn);
                    // Float gpMarginChange = calcPercentChangeAnnual(dataid, startDate, endDate, "gross_profit_margin", conn);
                    // Float qrChange = calcPercentChangeAnnual(dataid, startDate, endDate, "quick_ratio", conn);
                    System.out.println(debtEquityChange);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (toDo == 'C') {
            PreparedStatement statement;
            ResultSet result = null;
            try
            {
                Scanner input = new Scanner(System.in);
                boolean flag = true;
                do
                {
                    System.out.println("Please enter the start date, end date, and percent change");
                    System.out.print("Start Date (yyyy-mm-dd): ");
                    String startDate = input.next();
                    System.out.print("End Date (yyyy-mm-dd): ");
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
                        System.out.println("That table doesn't exist");
                    // PrintStream o = new PrintStream(new File("output.csv"));
                    // PrintStream console = System.out;
                    // System.setOut(o);
                    System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", "Ticker", "Data ID", "Change", "Start Date", "End Date"));
                    ArrayList<FallingKnifeDataModel> fkList = new ArrayList<FallingKnifeDataModel>();
                    do
                    {
                        PreparedStatement ticker = conn.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "'");
                        ResultSet tickerResult = ticker.executeQuery();
                        tickerResult.next();
                        fkList.add(new FallingKnifeDataModel(tickerResult.getString("ticker"), result.getDate("startdate"), result.getDate("enddate"), result.getFloat("change")));
                        System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", tickerResult.getString("ticker"), result.getString("dataid"), result.getString("change"), result.getString("startdate"), result.getString("enddate")));
                    }
                    while(result.next());

                    // System.setOut(console);
                    System.out.println("Enter \"quit\" to quit or anything else to search again");
                    String choice = input.next();
                    if(choice.equals("quit"))
                        flag = false;
                }
                while(flag);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (toDo == 'I')
        {
            PreparedStatement statement;
            ResultSet result = null;
            try
            {
                File file = new File("/Users/nicobeard/Documents/falling-knives-capstone/Backend/src/main/java/FallingKnives/data.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = "";
                while((st = br.readLine()) != null)
                {
                    String [] starray = st.split(" ");
                    statement = conn.prepareStatement("select dataid from relates where ticker = '" + starray[1] + "';");
                    result = statement.executeQuery();
                    if(!result.next())
                        System.out.println("That table doesn't exist");
                    String dataid = result.getString("dataid");
                    String date = starray[0].substring(0, 4) + "-" + starray[0].substring(4, 6) + "-" + starray[0].substring(6,8);
                    String cli = starray[5];
                    Float cli2 = 0f;
                    if(!(cli.equals(".")))
                        cli2 = Float.parseFloat(cli);
                    String inv = starray[4];
                    Float inv2 = 0f;
                    if(!(inv.equals(".")))
                        inv2 = Float.parseFloat(inv);
                    String rev = starray[6];
                    Float rev2 = 0f;
                    if(!(rev.equals(".")))
                        rev2 = Float.parseFloat(rev);
                    String cogs = starray[3];
                    Float cogs2 = 0f;
                    if(!(cogs.equals(".")))
                        cogs2 = Float.parseFloat(cogs);
                    String ca = starray[2];
                    Float ca2 = 0f;
                    if(!(ca.equals(".")))
                        ca2 = Float.parseFloat(ca);
                    Float grossProfitMargin = (rev2 - cogs2) / rev2;
                    Float quickRatio = (ca2 - inv2) / cli2;
                    statement = conn.prepareStatement("update quarterly set gross_profit_margin = '" + grossProfitMargin + "', quick_ratio = '" + quickRatio + "' where dataid = '" + dataid + "' and report_date = '" + date + "';");
                    statement.executeUpdate();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        conn.close();
        System.out.println("Operation Completed");
    }

    static char prompt(BufferedReader in) {
        // The valid actions:
        String actions = "PCI";

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
        System.out.println("\t[P] Predict the chance a certain stock becomes a falling knife");
        System.out.println("\t[C] Calculate falling knives from database");
        System.out.println("\t[I] Insert data into database");
    }

    public static Float calcPercentChangeAnnualAnnual(String dataid, String startDate, String endDate, String metric, Connection conn) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        Float pChange = 0f;
        Float previousVal = 0f;
        Float currentVal = 0f;
        int size = Integer.parseInt(endDate.substring(0,4)) - Integer.parseInt(startDate.substring(0,4));
        int date = Integer.parseInt(startDate.substring(0,4));
        for(int j = 0; j < size; j++)
        {
            statement = conn.prepareStatement("select " + metric + " from annual where dataid = '" + dataid + "' and report_date = '" + (date + j) + "-12-31';");
            result = statement.executeQuery();
            if(!result.next())
            {
                currentVal = 0f;
                continue;
            }
            currentVal = result.getFloat(metric);
            if(previousVal != 0)
                pChange += ((currentVal - previousVal) / previousVal);
            previousVal = currentVal;
        }
        return pChange;
    }

    public static Float calcPercentChangeQuarterly(String dataid, String startDate, String endDate, String metric, Connection conn) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        Float pChange = 0f;
        Float previousVal = 0f;
        Float currentVal = 0f;
        int size = Integer.parseInt(endDate.substring(0,4)) - Integer.parseInt(startDate.substring(0,4));
        int year = Integer.parseInt(startDate.substring(0,4));
        String date = "";
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                switch(j)
                {
                    case 0:
                        date = (year + i) + "-03-31";
                        break;
                    case 1:
                        date = (year + i) + "-06-30";
                        break;
                    case 2:
                        date = (year + i) + "-09-30";
                        break;
                    case 3:
                        date = (year + i) + "-12-31";
                        break;
                }
                statement = conn.prepareStatement("select " + metric + " from annual where dataid = '" + dataid + "' and report_date = '" + date + "';");
                result = statement.executeQuery();
                if(!result.next())
                {
                    currentVal = 0f;
                    continue;
                }
                currentVal = result.getFloat(metric);
                if(previousVal != 0)
                    pChange += ((currentVal - previousVal) / previousVal);
                previousVal = currentVal;
            }
        }
        return pChange;
    }
}