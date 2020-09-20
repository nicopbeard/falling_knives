import java.sql.*;
import java.io.*;
import java.util.Scanner;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App
{
    public static void main(String args[]) throws Exception
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

         String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();

         PreparedStatement statement;
         ResultSet result = null;
         try
         {
            Scanner input = new Scanner(System.in);
            boolean flag = true;
            System.out.println("------------------------------");
            System.out.println("Welcome to the user interface");
            do
            {
               System.out.println("Please enter the start date, end date, and percent change");
               System.out.print("Start Date: ");
               String startDate = input.next();
               System.out.print("End Date: ");
               String endDate = input.next();
               System.out.print("Percent change: ");
               while(!input.hasNextFloat())
               {
                  input.next();
                  System.out.println("Incorrect input, try again");
               }
               float percentChange = input.nextFloat();

               statement = c.prepareStatement("select dataid, sum((close-open)/nullif(open, 0))  as change, '" + startDate + "'  as startdate, '" + endDate + "' as enddate from market where date between '" + startDate + "' and '" + endDate + "' group by dataid having sum((close-open)/nullif(open, 0)) < " + percentChange + ";");

               result = statement.executeQuery();
               if(!result.next())
               {
                  System.out.println("That table doesn't exist");
               }
               // PrintStream o = new PrintStream(new File("output.csv"));
               // PrintStream console = System.out;
               // System.setOut(o);
               System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", "Ticker", "Data ID", "Change", "Start Date", "End Date"));
               do
               {
                  PreparedStatement ticker = c.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "'");
                  ResultSet tickerResult = ticker.executeQuery();
                  tickerResult.next();
                  System.out.println(String.format("%-7s %-10s %-25s %-15s %-15s", tickerResult.getString("ticker"), result.getString("dataid"), result.getString("change"), result.getString("startdate"), result.getString("enddate")));
               }
               while(result.next());

               // System.setOut(console);
               System.out.println("Enter \"quit\" to quit or anything else to search again");
               String choice = input.next();
               if(choice.equals("quit"))
               {
                  flag = false;
               }
            }
            while(flag);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
}