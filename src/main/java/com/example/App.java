package com.example;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "app",
        urlPatterns = "/App"
)
public class App extends HttpServlet
{
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
    {
         Connection c = null;
         try
         {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql:///postgres?cloudSqlInstance=adroit-nimbus-272020:us-central1:database&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=postgres&password=csb312");
         }
         catch(Exception e)
         {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
         }
         System.out.println("Opened database successfully");

         try
         {
            Predict.runPrediction(c);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }

         // PreparedStatement statement;
         // ResultSet result = null;
         // try
         // {
         //       String ticker = req.getParameter("sdate");
         //       String time = req.getParameter("edate");
         //       int years = Integer.parseInt(time);
         //       String pchange = req.getParameter("pchange");
         //       float percentChange = Float.parseFloat(pchange);

         //       statement = c.prepareStatement("SELECT DISTINCT Market.DataID, Open, FutureOpen, StartDate, EndDate FROM Market JOIN (SELECT DISTINCT DataID, Open as FutureOpen, Date-? as StartDate, Date as EndDate FROM Market) Future ON Market.Date = Future.StartDate AND Market.DataID = Future.DataID WHERE Date < \'01/01/2019\' AND Date > \'01/01/2000\' AND Open <> 0 AND FutureOpen <> 0 AND (FutureOpen-Open)/abs(Open) < ? AND EXTRACT(Day FROM Date) = 1 AND EXTRACT(Month FROM Date) = 12;");
         //       statement.setInt(1, years * 365);
         //       statement.setFloat(2, percentChange);
         //       ArrayList<String> dataids = new ArrayList<String>();
         //       ArrayList<String> startDates = new ArrayList<String>();
         //       ArrayList<String> endDates = new ArrayList<String>();
         //       result = statement.executeQuery();
         //       if(!result.next())
         //          System.out.println("That table doesn't exist");
         //       do
         //       {
         //          startDates.add(result.getString("StartDate"));
         //          endDates.add(result.getString("EndDate"));
         //          dataids.add(result.getString("dataid"));
         //       }
         //       while(result.next());

         //       int niSize = 2 * dataids.size();
         //       int revSize = 2 * dataids.size();
         //       int retEarnSize = 2 * dataids.size();
         //       int crSize = 2 * dataids.size();
         //       int opCashSize = 2 * dataids.size();
         //       int deSize = 2 * dataids.size();
         //       int gpSize = 2 * dataids.size();
         //       int qrSize = 2 * dataids.size();
         //       Float niSum = 0f;
         //       Float revSum = 0f;
         //       Float retEarnSum = 0f;
         //       Float crSum = 0f;
         //       Float opCashSum = 0f;
         //       Float deSum = 0f;
         //       Float gpSum = 0f;
         //       Float qrSum = 0f;
         //       ArrayList<String> output = new ArrayList<String>();
         //       for(int i = 0; i < dataids.size(); i++)
         //       {
         //          String startDate = startDates.get(i);
         //          String endDate = endDates.get(i);
         //          String dataid = dataids.get(i);
         //          if(dataid.equals(ticker))
         //             continue;

         //          //stock the user inputted
         //          Float niChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "net_income", c);
         //          Float revChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "revenue", c);
         //          Float retEarnChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "retained_earnings", c);
         //          Float crChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "current_ratio", c);
         //          Float opCashChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "operating_cash_flow", c);
         //          Float debtEquityChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "debt_equity_ratio", c);
         //          Float gpMarginChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "gross_profit_margin", c);
         //          Float qrChangeStockAnnual = calcPercentChangeAnnual(ticker, startDate, endDate, "quick_ratio", c);
         //          Float niChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "net_income", c);
         //          Float revChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "revenue", c);
         //          Float retEarnChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "retained_earnings", c);
         //          Float crChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "current_ratio", c);
         //          Float opCashChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "operating_cash_flow", c);
         //          Float debtEquityChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "debt_equity_ratio", c);
         //          Float gpMarginChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "gross_profit_margin", c);
         //          Float qrChangeStockQuarter = calcPercentChangeQuarterly(ticker, startDate, endDate, "quick_ratio", c);

         //          //stock currently being analyzed
         //          Float niChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "net_income", c);
         //          Float niDiffAnnual = niChangeStockAnnual - niChangeAnnual;
         //          if(!(niDiffAnnual.isNaN()) && !(niDiffAnnual.isInfinite()))
         //             niSum += niDiffAnnual;
         //          else
         //             niSize--;
         //          Float revChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "revenue", c);
         //          Float revDiffAnnual = revChangeStockAnnual - revChangeAnnual;
         //          if(!(revDiffAnnual.isNaN()) && !(revDiffAnnual.isInfinite()))
         //             revSum += revDiffAnnual;
         //          else
         //             revSize--;
         //          Float retEarnChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "retained_earnings", c);
         //          Float retEarnDiffAnnual = retEarnChangeStockAnnual - retEarnChangeAnnual;
         //          if(!(retEarnDiffAnnual.isNaN()) && !(retEarnDiffAnnual.isInfinite()))
         //             retEarnSum += retEarnDiffAnnual;
         //          else
         //             retEarnSize--;
         //          Float crChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "current_ratio", c);
         //          Float crDiffAnnual = crChangeStockAnnual - crChangeAnnual;
         //          if(!(crDiffAnnual.isNaN()) && !(crDiffAnnual.isInfinite()))
         //             crSum += crDiffAnnual;
         //          else
         //             crSize--;
         //          Float opCashChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "operating_cash_flow", c);
         //          Float opCashDiffAnnual = opCashChangeStockAnnual - opCashChangeAnnual;
         //          if(!(opCashDiffAnnual.isNaN()) && !(opCashDiffAnnual.isInfinite()))
         //             opCashSum += opCashDiffAnnual;
         //          else
         //             opCashSize--;
         //          Float debtEquityChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "debt_equity_ratio", c);
         //          Float debtEquityDiffAnnual = debtEquityChangeStockAnnual - debtEquityChangeAnnual;
         //          if(!(debtEquityDiffAnnual.isNaN()) && !(debtEquityDiffAnnual.isInfinite()))
         //             deSum += debtEquityDiffAnnual;
         //          else
         //             deSize--;
         //          Float gpMarginChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "gross_profit_margin", c);
         //          Float gpMarginDiffAnnual = gpMarginChangeStockAnnual - gpMarginChangeAnnual;
         //          if(!(gpMarginDiffAnnual.isNaN()) && !(gpMarginDiffAnnual.isInfinite()))
         //             gpSum += gpMarginDiffAnnual;
         //          else
         //             gpSize--;
         //          Float qrChangeAnnual = calcPercentChangeAnnual(dataid, startDate, endDate, "quick_ratio", c);
         //          Float qrDiffAnnual = qrChangeStockAnnual - qrChangeAnnual;
         //          if(!(qrDiffAnnual.isNaN()) && !(qrDiffAnnual.isInfinite()))
         //             qrSum += qrDiffAnnual;
         //          else
         //             qrSize--;

         //          Float niChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "net_income", c);
         //          Float niDiffQuarter = niChangeStockQuarter - niChangeQuarter;
         //          if(!(niDiffQuarter.isNaN()) && !(niDiffQuarter.isInfinite()))
         //             niSum += niDiffQuarter;
         //          else
         //             niSize--;
         //          Float revChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "revenue", c);
         //          Float revDiffQuarter = revChangeStockQuarter - revChangeQuarter;
         //          if(!(revDiffQuarter.isNaN()) && !(revDiffQuarter.isInfinite()))
         //             revSum += revDiffQuarter;
         //          else
         //             revSize--;
         //          Float retEarnChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "retained_earnings", c);
         //          Float retEarnDiffQuarter = retEarnChangeStockQuarter - retEarnChangeQuarter;
         //          if(!(retEarnDiffQuarter.isNaN()) && !(retEarnDiffQuarter.isInfinite()))
         //             retEarnSum += retEarnDiffQuarter;
         //          else
         //             retEarnSize--;
         //          Float crChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "current_ratio", c);
         //          Float crDiffQuarter = crChangeStockQuarter - crChangeQuarter;
         //          if(!(crDiffQuarter.isNaN()) && !(crDiffQuarter.isInfinite()))
         //             crSum += crDiffQuarter;
         //          else
         //             crSize--;
         //          Float opCashChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "operating_cash_flow", c);
         //          Float opCashDiffQuarter = opCashChangeStockQuarter - opCashChangeQuarter;
         //          if(!(opCashDiffQuarter.isNaN()) && !(opCashDiffQuarter.isInfinite()))
         //             opCashSum += opCashDiffQuarter;
         //          else
         //             opCashSize--;
         //          Float debtEquityChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "debt_equity_ratio", c);
         //          Float debtEquityDiffQuarter = debtEquityChangeStockQuarter - debtEquityChangeQuarter;
         //          if(!(debtEquityDiffQuarter.isNaN()) && !(debtEquityDiffQuarter.isInfinite()))
         //             deSum += debtEquityDiffQuarter;
         //          else
         //             deSize--;
         //          Float gpMarginChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "gross_profit_margin", c);
         //          Float gpMarginDiffQuarter = gpMarginChangeStockQuarter - gpMarginChangeQuarter;
         //          if(!(gpMarginDiffQuarter.isNaN()) && !(gpMarginDiffQuarter.isInfinite()))
         //             gpSum += gpMarginDiffQuarter;
         //          else
         //             gpSize--;
         //          Float qrChangeQuarter = calcPercentChangeQuarterly(dataid, startDate, endDate, "quick_ratio", c);
         //          Float qrDiffQuarter = qrChangeStockQuarter - qrChangeQuarter;
         //          if(!(qrDiffQuarter.isNaN()) && !(qrDiffQuarter.isInfinite()))
         //             qrSum += qrDiffQuarter;
         //          else
         //             qrSize--;
         //       }
         //       output.add(ticker);
         //       Float niChange = niSum / niSize;
         //       output.add("Net Income: " + niChange);
         //       Float revChange = revSum / revSize;
         //       output.add("Revenue: " + revChange);
         //       Float retEarnChange = retEarnSum / retEarnSize;
         //       output.add("Retained Earnings: " + retEarnChange);
         //       Float crChange = crSum / crSize;
         //       output.add("Current Ratio: " + crChange);
         //       Float opCashChange = opCashSum / opCashSize;
         //       output.add("Operating Cash Flow: " + opCashChange);
         //       Float deChange = deSum / deSize;
         //       output.add("Debt Equity Ratio: " + deChange);
         //       Float gpChange = gpSum / gpSize;
         //       output.add("Gross Profit Margin: " + gpChange);
         //       Float qrChange = qrSum / qrSize;
         //       output.add("Quick Ratio: " + qrChange);
         //       Float totAvg = (niChange + revChange + retEarnChange + crChange + opCashChange + deChange + gpChange + qrChange) / 8;
         //       output.add("-------------------------------------");
         //       output.add("Overall Similarity: " + totAvg);
         //       req.setAttribute("output", output);
         //       RequestDispatcher view = req.getRequestDispatcher("result.jsp");
         //       view.forward(req, resp);
         // }
         // catch(Exception e)
         // {
         //    e.printStackTrace();
         // }
      }

   public static Float calcPercentChangeAnnual(String dataid, String startDate, String endDate, String metric, Connection c) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        statement = c.prepareStatement("SELECT DISTINCT Annual.DataID, sum((future_" + metric + "-" + metric + ")/abs(" + metric + ")) as change FROM Annual JOIN (SELECT DISTINCT DataID, " + metric + " as future_" + metric + ", report_date-365 as StartDate, report_date as EndDate FROM Annual) Future ON Annual.report_date = Future.StartDate AND Annual.DataID = Future.DataID WHERE Annual.dataid = '" + dataid + "' and report_date between '" + startDate + "' and '" + endDate + "' AND " + metric + " <> 0 AND future_" + metric + " <> 0 group by annual.dataid;");
        result = statement.executeQuery();
        Float pChange = 0f;
        if(!result.next())
            return pChange;
        pChange = result.getFloat("change");
        return pChange;
    }

    public static Float calcPercentChangeQuarterly(String dataid, String startDate, String endDate, String metric, Connection c) throws Exception
    {
        PreparedStatement statement;
        ResultSet result;
        statement = c.prepareStatement("SELECT DISTINCT Quarterly.DataID, sum((future_" + metric + "-" + metric + ")/abs(" + metric + ")) as change FROM Quarterly JOIN (SELECT DISTINCT DataID, " + metric + " as future_" + metric + ", report_date-365 as StartDate, report_date as EndDate FROM Quarterly) Future ON Quarterly.report_date = Future.StartDate AND Quarterly.DataID = Future.DataID WHERE Quarterly.dataid = '" + dataid + "' and report_date between '" + startDate + "' and '" + endDate + "' AND " + metric + " <> 0 AND future_" + metric + " <> 0 group by quarterly.dataid;");
        result = statement.executeQuery();
        Float pChange = 0f;
        if(!result.next())
            return pChange;
        pChange = result.getFloat("change");
        return pChange;
    }
}