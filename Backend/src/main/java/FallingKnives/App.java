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
                boolean flag = false;
                String tickerAnalyzed = "";
                String dataIdofTicker = "";
                do
                {
                    System.out.print("Please enter the ticker you want to be analyzed: ");
                    tickerAnalyzed = input.next();
                    statement = conn.prepareStatement("select dataid from relates where ticker = '" +  tickerAnalyzed + "';");
                    result = statement.executeQuery();
                    if(!result.next())
                        System.out.println("The ticker you entered doesn't exist in our database. Please enter a new one.");
                    else
                    {
                        dataIdofTicker = result.getString("dataid");
                        flag = true;
                    }
                } while(!flag);
                System.out.println("Please enter the length of time (years) and percent change.");
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

                statement = conn.prepareStatement("SELECT DISTINCT Market.DataID, Open, FutureOpen, StartDate, EndDate FROM Market JOIN (SELECT DISTINCT DataID, Open as FutureOpen, Date-? as StartDate, Date as EndDate FROM Market) Future ON Market.Date = Future.StartDate AND Market.DataID = Future.DataID WHERE Date < \'01/01/2019\' AND Open <> 0 AND FutureOpen <> 0 AND (FutureOpen-Open)/Open < ? AND EXTRACT(Day FROM Date) = 1 AND EXTRACT(Month FROM Date) = 12;");
                statement.setInt(1, time * 365);
                statement.setFloat(2, percentChange);
                ArrayList<String> dataids = new ArrayList<String>();
                ArrayList<String> startDates = new ArrayList<String>();
                ArrayList<String> endDates = new ArrayList<String>();
                result = statement.executeQuery();
                if(!result.next())
                    System.out.println("That table doesn't exist");
                do
                {
                    startDates.add(result.getString("StartDate"));
                    endDates.add(result.getString("EndDate"));
                    dataids.add(result.getString("dataid"));
                }
                while(result.next());
                
                Float sum = 0f;
                int size = dataids.size();
                for(int i = 0; i < dataids.size(); i++)
                {
                    String startDate = startDates.get(i);
                    String endDate = endDates.get(i);
                    int sumSize = 16;
                    Float diffSum = 0f;
                    String dataid = dataids.get(i);
                    if(dataid.equals(dataIdofTicker))
                    {
                        size--;
                        continue;
                    }

                    //stock the user inputted
                    Float niChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "net_income", conn);
                    Float revChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "revenue", conn);
                    Float retEarnChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "retained_earnings", conn);
                    Float crChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "current_ratio", conn);
                    Float opCashChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "operating_cash_flow", conn);
                    Float debtEquityChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "debt_equity_ratio", conn);
                    Float gpMarginChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "gross_profit_margin", conn);
                    Float qrChangeStockAnnual = calcPercentChangeAnnual(dataIdofTicker, startDate, endDate, "quick_ratio", conn);
                    Float niChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "net_income", conn);
                    Float revChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "revenue", conn);
                    Float retEarnChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "retained_earnings", conn);
                    Float crChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "current_ratio", conn);
                    Float opCashChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "operating_cash_flow", conn);
                    Float debtEquityChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "debt_equity_ratio", conn);
                    Float gpMarginChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "gross_profit_margin", conn);
                    Float qrChangeStockQuarter = calcPercentChangeQuarterly(dataIdofTicker, startDate, endDate, "quick_ratio", conn);

                    //stock currently being analyzed
                    Float niChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "net_income", conn);
                    Float niDiffAnnual = niChangeStockAnnual - niChangeAnnual;
                    if(!(niDiffAnnual.isNaN()))
                        diffSum += niDiffAnnual;
                    else
                        sumSize--;
                    Float revChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "revenue", conn);
                    Float revDiffAnnual = revChangeStockAnnual - revChangeAnnual;
                    if(!(revDiffAnnual.isNaN()))
                        diffSum += revDiffAnnual;
                    else
                        sumSize--;
                    Float retEarnChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "retained_earnings", conn);
                    Float retEarnDiffAnnual = retEarnChangeStockAnnual - retEarnChangeAnnual;
                    if(!(retEarnDiffAnnual.isNaN()))
                        diffSum += retEarnDiffAnnual;
                    else
                        sumSize--;
                    Float crChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "current_ratio", conn);
                    Float crDiffAnnual = crChangeStockAnnual - crChangeAnnual;
                    if(!(crDiffAnnual.isNaN()))
                        diffSum += crDiffAnnual;
                    else
                        sumSize--;
                    Float opCashChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "operating_cash_flow", conn);
                    Float opCashDiffAnnual = opCashChangeStockAnnual - opCashChangeAnnual;
                    if(!(opCashDiffAnnual.isNaN()))
                        diffSum += opCashDiffAnnual;
                    else
                        sumSize--;
                    Float debtEquityChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "debt_equity_ratio", conn);
                    Float debtEquityDiffAnnual = debtEquityChangeStockAnnual - debtEquityChangeAnnual;
                    if(!(debtEquityDiffAnnual.isNaN()))
                        diffSum += debtEquityDiffAnnual;
                    else
                        sumSize--;
                    Float gpMarginChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "gross_profit_margin", conn);
                    Float gpMarginDiffAnnual = gpMarginChangeStockAnnual - gpMarginChangeAnnual;
                    if(!(gpMarginDiffAnnual.isNaN()))
                        diffSum += gpMarginDiffAnnual;
                    else
                        sumSize--;
                    Float qrChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "quick_ratio", conn);
                    Float qrDiffAnnual = qrChangeStockAnnual - qrChangeAnnual;
                    if(!(qrDiffAnnual.isNaN()))
                        diffSum += qrDiffAnnual;
                    else
                        sumSize--;

                    Float niChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "net_income", conn);
                    Float niDiffQuarter = niChangeStockQuarter - niChangeQuarter;
                    if(!(niDiffQuarter.isNaN()))
                        diffSum += niDiffQuarter;
                    else
                        sumSize--;
                    Float revChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "revenue", conn);
                    Float revDiffQuarter = revChangeStockQuarter - revChangeQuarter;
                    if(!(revDiffQuarter.isNaN()))
                        diffSum += revDiffQuarter;
                    else
                        sumSize--;
                    Float retEarnChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "retained_earnings", conn);
                    Float retEarnDiffQuarter = retEarnChangeStockQuarter - retEarnChangeQuarter;
                    if(!(retEarnDiffQuarter.isNaN()))
                        diffSum += retEarnDiffQuarter;
                    else
                        sumSize--;
                    Float crChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "current_ratio", conn);
                    Float crDiffQuarter = crChangeStockQuarter - crChangeQuarter;
                    if(!(crDiffQuarter.isNaN()))
                        diffSum += crDiffQuarter;
                    else
                        sumSize--;
                    Float opCashChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "operating_cash_flow", conn);
                    Float opCashDiffQuarter = opCashChangeStockQuarter - opCashChangeQuarter;
                    if(!(opCashDiffQuarter.isNaN()))
                        diffSum += opCashDiffQuarter;
                    else
                        sumSize--;
                    Float debtEquityChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "debt_equity_ratio", conn);
                    Float debtEquityDiffQuarter = debtEquityChangeStockQuarter - debtEquityChangeQuarter;
                    if(!(debtEquityDiffQuarter.isNaN()))
                        diffSum += debtEquityDiffQuarter;
                    else
                        sumSize--;
                    Float gpMarginChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "gross_profit_margin", conn);
                    Float gpMarginDiffQuarter = gpMarginChangeStockQuarter - gpMarginChangeQuarter;
                    if(!(gpMarginDiffQuarter.isNaN()))
                        diffSum += gpMarginDiffQuarter;
                    else
                        sumSize--;
                    Float qrChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "quick_ratio", conn);
                    Float qrDiffQuarter = qrChangeStockQuarter - qrChangeQuarter;
                    if(!(qrDiffQuarter.isNaN()))
                        diffSum += qrDiffQuarter;
                    else
                        sumSize--;
                    
                    Float avgDiff = (diffSum / sumSize);
                    System.out.println(avgDiff);
                    if(!(diffSum.isNaN()))
                        sum += avgDiff;
                    else
                        size--;
                }
                Float totAvg = sum / size;
                System.out.println(totAvg);
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
                    System.out.println("Please enter the percent change of stocks to search for (decimal format)");
                    float percentChange = input.nextFloat();
                    System.out.println("Please enter the number of years to check the percent change");
                    float years = input.nextInt();
                    int days = (int)(365*years);
                    System.out.println("Running Calculations");
                    statement = conn.prepareStatement("SELECT DISTINCT Market.DataID, Open, FutureOpen, StartDate, EndDate FROM Market JOIN (SELECT DISTNCT DataID, Open as FutureOpen, Date-? as StartDate, Date as EndDate FROM Market) Future ON Market.Date = Future.StartDate AND Market.DataID = Future.DataID WHERE Date > \'01/01/2000\' AND Open <> 0 AND FutureOpen <> 0 AND (FutureOpen-Open)/Open < ?;");
                    statement.setInt(1, days);
                    statement.setFloat(2, percentChange);
                    result = statement.executeQuery();
                    ArrayList<FallingKnifeDataModel> fkList = new ArrayList<FallingKnifeDataModel>();
                    System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s", "DataID", "Ticker", "StartDate", "EndDate", "Open", "FutureOpen", "Change"));
                    while(result.next()) {
                        String id = result.getString("dataid");
                        PreparedStatement tickerQuery = conn.prepareStatement("select ticker from relates where dataid = '" + id + "';");
                        ResultSet tickerResult = tickerQuery.executeQuery();
                        if (!tickerResult.next()) {
                            continue;
                        }
                        String ticker = tickerResult.getString("Ticker");
                        FallingKnifeDataModel fk = new FallingKnifeDataModel(id, ticker, result.getDate("StartDate"), result.getDate("EndDate"), result.getFloat("Open"), result.getFloat("FutureOpen"));
                        System.out.println(String.format("%10s %10s %10s %10s %10f %10f %10f", fk.DataID, fk.Ticker, fk.StartDate, fk.EndDate, fk.Open, fk.FutureOpen, fk.Change));
                        fkList.add(fk);
                    }
                    System.out.println("\n" + fkList.size() + " Falling Knives found, printing results\n");

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

    public static Float calcPercentChangeAnnual(String dataid, String startDate, String endDate, String metric, Connection conn) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        Float pChange = 0f;
        Float previousVal = 0f;
        Float currentVal = 0f;
        int size = Integer.parseInt(endDate.substring(0,4)) - Integer.parseInt(startDate.substring(0,4));
        int date = Integer.parseInt(startDate.substring(0,4));
        for(int j = 0; j < size + 1; j++)
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
            {
                if(Math.signum(currentVal - previousVal) == -1 && Math.signum(previousVal) == -1)
                    pChange -= ((currentVal - previousVal) / previousVal);
                else
                    pChange += ((currentVal - previousVal) / previousVal);
            }
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
        for(int i = 0; i < size + 1; i++)
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
                {
                    if(Math.signum(currentVal - previousVal) == -1 && Math.signum(previousVal) == -1)
                        pChange -= ((currentVal - previousVal) / previousVal);
                    else
                        pChange += ((currentVal - previousVal) / previousVal);
                }
                previousVal = currentVal;
            }
        }
        return pChange;
    }
}