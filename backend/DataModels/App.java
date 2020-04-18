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
           c = DriverManager.getConnection("jdbc:postgresql://34.66.234.184:5432/postgres","postgres", "csb312");
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