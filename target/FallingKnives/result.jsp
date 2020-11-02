<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search Query Results</title>
        <style>
            h1 {
                text-align: center;
                color: black;
            }
        </style>
    </head>
<body style="background-color: cyan;">
    <h1>Search Query Results</h1>
    <p style="margin-left:80px">
        DataID&emsp;&emsp;&emsp;Change<br>
        <%
        List<String> result = (List) request.getAttribute("output");
        for(String elements : result)
        {
            String [] array = elements.split(" ");
            out.println(array[0] + "&emsp;&emsp;" + array[1] + "<br>");
        }
        %>
    </p>
</body>
</html>