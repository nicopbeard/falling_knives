package FallingKnives;

import java.sql.*;
import java.util.ArrayList;

public class Tester
{
    public static void TestMethod(Connection conn) {
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
}