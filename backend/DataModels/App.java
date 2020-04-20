import java.sql.*;
import java.io.*;

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

        Statement statement = c.createStatement();
        String query = "select * from company;";
        ResultSet result = null;
        try
        {
            result = statement.executeQuery(query);
            if(!result.next())
            {
               System.out.println("That table doesn't exist");
            }
            System.out.println(String.format("%-10s %-40s %-15s %-10s", "Ticker", "Name", "Public_Data", "Sector"));
            do
            {
               System.out.println(String.format("%-10s %-40s %-15s %-10s", result.getString("ticker"), result.getString("name"), result.getString("public_data"), result.getString("sector")));
            }
            while(result.next());
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
     }
}