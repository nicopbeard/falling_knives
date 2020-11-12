<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search Query Results</title>
        <style>
            h1 {
                text-align: center;
                color: white;
            }
            html {
                background-image: url('g.foolcdn.jpg');
                background-repeat: no-repeat;
                background-size: cover;
            }
            body {
                color: white;
            }
            p {
                margin-left: 80px;
                margin-right: 80px;
            }
        </style>
    </head>
<body>
    <h1>Search Query Results</h1>
    <p>
        The following results indicate how the given stock compares to other stock profiles that have fallen by the percent change indicated within the given period,
        with the overall similarity being the average of the metrics.<br>
        <%
        List<String> result = (List) request.getAttribute("output");
        Iterator it = result.iterator();
        while(it.hasNext())
            out.println(it.next() + "<br>");
        %>
    </p>
</body>
</html>