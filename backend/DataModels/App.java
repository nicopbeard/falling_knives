import java.sql.Connection;
import java.sql.DriverManager;

public class App
{
    public static void main(String args[])
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
     }
}