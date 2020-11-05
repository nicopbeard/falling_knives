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
import java.util.stream.IntStream;

public class App
{
    public static void main(String[] args) throws Exception
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
        System.out.println("Bye!");
    }

    public static void regularUI(Connection conn) throws Exception
    {
        menu();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char toDo = prompt(in);

        if (toDo == 'P')
        {
            try {
                Predict.runPrediction(conn);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            /*
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

                statement = conn.prepareStatement("SELECT DISTINCT Market.DataID, Open, FutureOpen, StartDate, EndDate FROM Market JOIN (SELECT DISTINCT DataID, Open as FutureOpen, Date-? as StartDate, Date as EndDate FROM Market) Future ON Market.Date = Future.StartDate AND Market.DataID = Future.DataID WHERE Date < \'01/01/2019\' AND Date > \'01/01/2000\' AND Open <> 0 AND FutureOpen <> 0 AND (FutureOpen-Open)/Open < ? AND EXTRACT(Day FROM Date) = 1 AND EXTRACT(Month FROM Date) = 12;");
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
                
                int niSize = 2 * dataids.size();
                int revSize = 2 * dataids.size();
                int retEarnSize = 2 * dataids.size();
                int crSize = 2 * dataids.size();
                int opCashSize = 2 * dataids.size();
                int deSize = 2 * dataids.size();
                int gpSize = 2 * dataids.size();
                int qrSize = 2 * dataids.size();
                Float niSum = 0f;
                Float revSum = 0f;
                Float retEarnSum = 0f;
                Float crSum = 0f;
                Float opCashSum = 0f;
                Float deSum = 0f;
                Float gpSum = 0f;
                Float qrSum = 0f;
                for(int i = 0; i < dataids.size(); i++)
                {
                    String startDate = startDates.get(i);
                    String endDate = endDates.get(i);
                    String dataid = dataids.get(i);
                    if(dataid.equals(dataIdofTicker))
                        continue;

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
                    if(!(niDiffAnnual.isNaN()) && !(niDiffAnnual.isInfinite()))
                        niSum += niDiffAnnual;
                    else
                        niSize--;
                    Float revChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "revenue", conn);
                    Float revDiffAnnual = revChangeStockAnnual - revChangeAnnual;
                    if(!(revDiffAnnual.isNaN()) && !(revDiffAnnual.isInfinite()))
                        revSum += revDiffAnnual;
                    else
                        revSize--;
                    Float retEarnChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "retained_earnings", conn);
                    Float retEarnDiffAnnual = retEarnChangeStockAnnual - retEarnChangeAnnual;
                    if(!(retEarnDiffAnnual.isNaN()) && !(retEarnDiffAnnual.isInfinite()))
                        retEarnSum += retEarnDiffAnnual;
                    else
                        retEarnSize--;
                    Float crChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "current_ratio", conn);
                    Float crDiffAnnual = crChangeStockAnnual - crChangeAnnual;
                    if(!(crDiffAnnual.isNaN()) && !(crDiffAnnual.isInfinite()))
                        crSum += crDiffAnnual;
                    else
                        crSize--;
                    Float opCashChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "operating_cash_flow", conn);
                    Float opCashDiffAnnual = opCashChangeStockAnnual - opCashChangeAnnual;
                    if(!(opCashDiffAnnual.isNaN()) && !(opCashDiffAnnual.isInfinite()))
                        opCashSum += opCashDiffAnnual;
                    else
                        opCashSize--;
                    Float debtEquityChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "debt_equity_ratio", conn);
                    Float debtEquityDiffAnnual = debtEquityChangeStockAnnual - debtEquityChangeAnnual;
                    if(!(debtEquityDiffAnnual.isNaN()) && !(debtEquityDiffAnnual.isInfinite()))
                        deSum += debtEquityDiffAnnual;
                    else
                        deSize--;
                    Float gpMarginChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "gross_profit_margin", conn);
                    Float gpMarginDiffAnnual = gpMarginChangeStockAnnual - gpMarginChangeAnnual;
                    if(!(gpMarginDiffAnnual.isNaN()) && !(gpMarginDiffAnnual.isInfinite()))
                        gpSum += gpMarginDiffAnnual;
                    else
                        gpSize--;
                    Float qrChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "quick_ratio", conn);
                    Float qrDiffAnnual = qrChangeStockAnnual - qrChangeAnnual;
                    if(!(qrDiffAnnual.isNaN()) && !(qrDiffAnnual.isInfinite()))
                        qrSum += qrDiffAnnual;
                    else
                        qrSize--;

                    Float niChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "net_income", conn);
                    Float niDiffQuarter = niChangeStockQuarter - niChangeQuarter;
                    if(!(niDiffQuarter.isNaN()) && !(niDiffQuarter.isInfinite()))
                        niSum += niDiffQuarter;
                    else
                        niSize--;
                    Float revChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "revenue", conn);
                    Float revDiffQuarter = revChangeStockQuarter - revChangeQuarter;
                    if(!(revDiffQuarter.isNaN()) && !(revDiffQuarter.isInfinite()))
                        revSum += revDiffQuarter;
                    else
                        revSize--;
                    Float retEarnChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "retained_earnings", conn);
                    Float retEarnDiffQuarter = retEarnChangeStockQuarter - retEarnChangeQuarter;
                    if(!(retEarnDiffQuarter.isNaN()) && !(retEarnDiffQuarter.isInfinite()))
                        retEarnSum += retEarnDiffQuarter;
                    else
                        retEarnSize--;
                    Float crChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "current_ratio", conn);
                    Float crDiffQuarter = crChangeStockQuarter - crChangeQuarter;
                    if(!(crDiffQuarter.isNaN()) && !(crDiffQuarter.isInfinite()))
                        crSum += crDiffQuarter;
                    else
                        crSize--;
                    Float opCashChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "operating_cash_flow", conn);
                    Float opCashDiffQuarter = opCashChangeStockQuarter - opCashChangeQuarter;
                    if(!(opCashDiffQuarter.isNaN()) && !(opCashDiffQuarter.isInfinite()))
                        opCashSum += opCashDiffQuarter;
                    else
                        opCashSize--;
                    Float debtEquityChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "debt_equity_ratio", conn);
                    Float debtEquityDiffQuarter = debtEquityChangeStockQuarter - debtEquityChangeQuarter;
                    if(!(debtEquityDiffQuarter.isNaN()) && !(debtEquityDiffQuarter.isInfinite()))
                        deSum += debtEquityDiffQuarter;
                    else
                        deSize--;
                    Float gpMarginChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "gross_profit_margin", conn);
                    Float gpMarginDiffQuarter = gpMarginChangeStockQuarter - gpMarginChangeQuarter;
                    if(!(gpMarginDiffQuarter.isNaN()) && !(gpMarginDiffQuarter.isInfinite()))
                        gpSum += gpMarginDiffQuarter;
                    else
                        gpSize--;
                    Float qrChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "quick_ratio", conn);
                    Float qrDiffQuarter = qrChangeStockQuarter - qrChangeQuarter;
                    if(!(qrDiffQuarter.isNaN()) && !(qrDiffQuarter.isInfinite()))
                        qrSum += qrDiffQuarter;
                    else
                        qrSize--;
                }
                System.out.println(tickerAnalyzed);
                Float niChange = niSum / niSize;
                System.out.println("Net Income: " + niChange);
                Float revChange = revSum / revSize;
                System.out.println("Revenue: " + revChange);
                Float retEarnChange = retEarnSum / retEarnSize;
                System.out.println("Retained Earnings: " + retEarnChange);
                Float crChange = crSum / crSize;
                System.out.println("Current Ratio: " + crChange);
                Float opCashChange = opCashSum / opCashSize;
                System.out.println("Operating Cash Flow: " + opCashChange);
                Float deChange = deSum / deSize;
                System.out.println("Debt Equity Ratio: " + deChange);
                Float gpChange = gpSum / gpSize;
                System.out.println("Gross Profit Margin: " + gpChange);
                Float qrChange = qrSum / qrSize;
                System.out.println("Quick Ratio: " + qrChange);
                Float totAvg = (niChange + revChange + retEarnChange + crChange + opCashChange + deChange + gpChange + qrChange) / 8;
                System.out.println("-------------------------------------");
                System.out.println("Overall Similarity: " + totAvg);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            */
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
                        FallingKnifeDataModel fk = new FallingKnifeDataModel(id, ticker, result.getString("StartDate"), result.getString("EndDate"), result.getFloat("Open"), result.getFloat("FutureOpen"));
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
        statement = conn.prepareStatement("SELECT DISTINCT Annual.DataID, sum((future_" + metric + "-" + metric + ")/" + metric + ") as change FROM Annual JOIN (SELECT DISTINCT DataID, " + metric + " as future_" + metric + ", report_date-365 as StartDate, report_date as EndDate FROM Annual) Future ON Annual.report_date = Future.StartDate AND Annual.DataID = Future.DataID WHERE Annual.dataid = '" + dataid + "' and report_date between '" + startDate + "' and '" + endDate + "' AND " + metric + " <> 0 AND future_" + metric + " <> 0 group by annual.dataid;");
        result = statement.executeQuery();
        Float pChange = 0f;
        if(!result.next())
            return pChange;
        pChange = result.getFloat("change");
        return pChange;
    }

    public static Float calcPercentChangeQuarterly(String dataid, String startDate, String endDate, String metric, Connection conn) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        statement = conn.prepareStatement("SELECT DISTINCT Quarterly.DataID, sum((future_" + metric + "-" + metric + ")/" + metric + ") as change FROM Quarterly JOIN (SELECT DISTINCT DataID, " + metric + " as future_" + metric + ", report_date-365 as StartDate, report_date as EndDate FROM Quarterly) Future ON Quarterly.report_date = Future.StartDate AND Quarterly.DataID = Future.DataID WHERE Quarterly.dataid = '" + dataid + "' and report_date between '" + startDate + "' and '" + endDate + "' AND " + metric + " <> 0 AND future_" + metric + " <> 0 group by quarterly.dataid;");
        result = statement.executeQuery();
        Float pChange = 0f;
        if(!result.next())
            return pChange;
        pChange = result.getFloat("change");
        return pChange;
    }

}