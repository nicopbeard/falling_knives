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
import java.util.*; 
import javafx.util.Pair;

public class Predict {

    public static void runPrediction(Connection conn) throws Exception {
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
                // Get the users ticker
                System.out.print("Please enter the ticker you want to be analyzed: ");
                tickerAnalyzed = input.next();
                // Make sure the ticker is in our database
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
            // Get the users classification for a Falling Knife
            System.out.println("Please enter the length of time (years) and percent change.");
            System.out.print("Length of time: ");
            while(!input.hasNextInt())
            {
                input.next();
                System.out.println("Incorrect input, try again");
            }
            int time = input.nextInt();
            int days = time*365;
            System.out.print("Percent change: ");
            while(!input.hasNextFloat())
            {
                input.next();
                System.out.println("Incorrect input, try again");
            }
            float percentChange = input.nextFloat();

            // Get list of historical Falling Knives fitting users profile
            ArrayList<FallingKnifeDataModel> fkList = getHistoricalFallingKnives(days, percentChange, conn);
            // List of fields to check against
            ArrayList<String> fieldList = new ArrayList<String>(
                List.of("net_income", "revenue", "retained_earnings", "current_ratio", "operating_cash_flow", "debt_equity_ratio", "gross_profit_margin", "quick_ratio")
            );
            // Annual and Quarterly list used to calculate average percent changes
            ArrayList<Trio> annualCalcList = new ArrayList<Trio>();
            for (String field : fieldList) {
                annualCalcList.add(new Trio(field));
            }
            ArrayList<Trio> quarterlyCalcList = new ArrayList<Trio>();
            for (String field : fieldList) {
                quarterlyCalcList.add(new Trio(field));
            }

            // Calculate percent changes for each historical FK
            for (FallingKnifeDataModel fk : fkList) {
                // Info about current FK
                String startDate = fk.StartDate;
                String endDate = fk.EndDate;
                String dataid = fk.DataID;

                // Calc percent change for each field in annual and quarterly
                for (Trio t : annualCalcList) {
                    String field = t.Field;
                    Float annualChange = calcPercentChangeAnnual(dataid, startDate, endDate, field, days, conn);
                    if ((!annualChange.isNaN()) &&(!annualChange.isInfinite())) {
                        t.Sum += annualChange;
                        t.Count++;
                    }
                }
                for (Trio t : quarterlyCalcList) {
                    String field = t.Field;
                    Float quarterlyChange = calcPercentChangeQuarterly(dataid, startDate, endDate, field, conn);
                    if (!(quarterlyChange.isNaN()) && !(quarterlyChange.isInfinite())) {
                        t.Sum += quarterlyChange;
                        t.Count++;
                    }
                }
            }

            System.out.println(tickerAnalyzed);
            // Take the average for each field and print
            for (Trio t : annualCalcList) {
                String field = t.Field;
                // Calculate average change for historical FK
                float averageChange = t.Sum / t.Count;
                // Calculate percent change for current FK
                float requestedChange = 0; // TODO
                // Calcualte percent deviation
                float percentDeviation = ((requestedChange - averageChange) / averageChange) * 100;
                if (percentDeviation < 0) {
                    percentDeviation *= -1;
                }
                System.out.println(field + ": " + averageChange);
                System.out.println(field + ": " + percentDeviation + "%");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Float calcPercentChangeAnnual(String dataid, String startDate, String endDate, String metric, int days, Connection conn) throws Exception
    {
        PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT Annual.DataID, ((future_" + metric + "-" + metric + ")/" + metric + ") as change FROM Annual JOIN (SELECT DISTINCT DataID, " + metric + " as future_" + metric + ", report_date-" + days + " as StartDate, report_date as EndDate FROM Annual) Future ON Annual.report_date = Future.StartDate AND Annual.DataID = Future.DataID WHERE Annual.dataid = '" + dataid + "' and report_date between '" + startDate + "' and '" + endDate + "' AND " + metric + " <> 0 AND future_" + metric + " <> 0;");
        ResultSet result = statement.executeQuery();
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

    public static ArrayList<FallingKnifeDataModel> getHistoricalFallingKnives(int days, float percentChange, Connection conn) throws Exception {
        ArrayList<FallingKnifeDataModel> fkList = new ArrayList<FallingKnifeDataModel>();
        PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT Market.DataID, Open, FutureOpen, StartDate, EndDate FROM Market JOIN (SELECT DISTINCT DataID, Open as FutureOpen, Date-? as StartDate, Date as EndDate FROM Market) Future ON Market.Date = Future.StartDate AND Market.DataID = Future.DataID WHERE Date < \'01/01/2019\' AND Date > \'01/01/2000\' AND Open <> 0 AND FutureOpen <> 0 AND (FutureOpen-Open)/Open < ? AND EXTRACT(Day FROM Date) = 1 AND EXTRACT(Month FROM Date) = 12;");
        statement.setInt(1, days);
        statement.setFloat(2, percentChange);
        ResultSet result = statement.executeQuery();
        if(!result.next())
            System.out.println("That table doesn't exist");
        do
        {
            fkList.add(new FallingKnifeDataModel(result.getString("DataID"), "", result.getString("StartDate"), result.getString("EndDate"), result.getFloat("Open"), result.getFloat("FutureOpen")));
        }
        while(result.next());
        return fkList;
    }
}