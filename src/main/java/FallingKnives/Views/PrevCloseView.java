package FallingKnives;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PrevCloseView
{
    private static String rankTable = "SELECT Ticker, Date, Close, RANK() OVER (PARTITION BY Ticker ORDER BY Date DESC) MyRank FROM Market JOIN Relates ON Market.DataID = Relates.DataID";
    private static String prevRankTable = "SELECT Ticker, MyRank, MyRank + 1 AS PrevRank FROM (" + rankTable + ") AS Base";
    private static String combinedTable = "SELECT Base.Ticker, Date, Close, Base.MyRank, PrevRank FROM (" + rankTable + ") AS Base JOIN (" + prevRankTable + ") AS Prev ON Base.MyRank = Prev.MyRank AND Base.Ticker = Prev.Ticker";
    private static String combinedTable2 = "SELECT Base.Ticker, PrevRank, Close as PrevClose FROM (" + rankTable + ") AS Base JOIN (" + prevRankTable + ") AS Prev ON Base.MyRank = Prev.PrevRank AND Base.Ticker = Prev.Ticker";
    private static String prevCloseTable = "SELECT P.Ticker, Date, Close, PrevClose, MyRank FROM (" + combinedTable + ") AS C JOIN (" + combinedTable2 + ") AS P ON C.PrevRank = P.PrevRank AND C.Ticker = P.Ticker";

    private static String getQuery() {
        return prevCloseTable;
    }

    public static void CreatePrevCloseView(Connection conn) {
        try {
            String view = "CREATE OR REPLACE VIEW PrevCloseView AS " + getQuery();
            PreparedStatement update = conn.prepareStatement(view);
            update.executeUpdate();

            PreparedStatement test = conn.prepareStatement("SELECT * FROM PrevCloseView WHERE Ticker = \'AAPL\'");
            ResultSet rs = test.executeQuery();
    
            while (rs.next()) {
                System.out.println(String.format("%-10s %-15s %-30s %-30s %-10s", rs.getString("Ticker"), rs.getString("Date"), rs.getString("Close"), rs.getString("PrevClose"), rs.getString("MyRank")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DropPrevCloseView(Connection conn) {
        try {
            String view = "DROP VIEW PrevCloseView";
            PreparedStatement update = conn.prepareStatement(view);
            update.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}