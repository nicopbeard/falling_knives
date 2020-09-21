<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<%
List result = (List) request.getAttribute("output");
Iterator it = result.iterator();
out.println("Ticker&emsp;DataID&emsp;Change&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Start Date&emsp;End Date<br>");
while(it.hasNext()){
out.println(it.next()+"&emsp;<br>");
}
%>
</body>
</html>