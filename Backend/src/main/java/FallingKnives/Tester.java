package FallingKnives;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tester
{
    public static void BaseTestMethod(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Company");
            ResultSet rs = statement.executeQuery();
            ArrayList<CompanyDataModel> list = new ArrayList<CompanyDataModel>();
            while(rs.next()) {
                CompanyDataModel comp = new CompanyDataModel(rs.getString("Ticker"), rs.getString("Name"), rs.getString("Sector"));
                list.add(comp);
            }
            System.out.println(String.format("%-10s %-25s %-10s", "Ticker", "ComapnyName", "Sector"));
            for (int i = 0; i < list.size(); i++) {
                CompanyDataModel comp = list.get(i);
                System.out.println(String.format("%-10s %-25s %-10s", comp.Ticker, comp.Name, comp.Sector));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void TestMethod(Connection conn) {
        // Base Query
        //BaseTestMethod(conn);

        // Add Custom Query to test Below
        try {
            String query = "SELECT *, RANK() OVER (ORDER BY Date DESC) MyRank FROM Market JOIN Relates ON Market.DataID = Relates.DataID WHERE Ticker = \'AAPL\'";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                System.out.println(String.format("%-10s %-15s %-30s %-30s %-10s", rs.getString("Ticker"), rs.getString("Date"), rs.getString("Close"), "", rs.getString("MyRank")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}