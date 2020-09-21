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

         PreparedStatement statement;
         ResultSet result = null;
         try
         {
               String startDate = req.getParameter("sdate");
               String endDate = req.getParameter("edate");
               String pchange = req.getParameter("pchange");
               float percentChange = Float.parseFloat(pchange);

               statement = c.prepareStatement("select dataid, sum((close-open)/nullif(open, 0))  as change, '" + startDate + "'  as startdate, '" + endDate + "' as enddate from market where date between '" + startDate + "' and '" + endDate + "' group by dataid having sum((close-open)/nullif(open, 0)) < " + percentChange + ";");

               result = statement.executeQuery();
               if(!result.next())
               {
                  System.out.println("That table doesn't exist");
               }
               ArrayList<String> output = new ArrayList<String>();
               do
               {
                  PreparedStatement ticker = c.prepareStatement("select ticker from relates where dataid = '" + result.getString("dataid") + "'");
                  ResultSet tickerResult = ticker.executeQuery();
                  tickerResult.next();
                  output.add(String.format("%-7s %-10s %-25s %-15s %-15s", tickerResult.getString("ticker"), result.getString("dataid"), result.getString("change"), result.getString("startdate"), result.getString("enddate")));
               }
               while(result.next());
               req.setAttribute("output", output);
               RequestDispatcher view = req.getRequestDispatcher("result.jsp");
               view.forward(req, resp);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
}