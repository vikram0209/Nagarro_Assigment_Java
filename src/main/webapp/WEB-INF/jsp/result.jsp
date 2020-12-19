<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
<META HTTP-EQUIV="Expires" CONTENT="Sat, 01 Dec 2001 00:00:00 GMT">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT=no-cache">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
 <table><tr><td align="right" style="vertical-align:top;" width="30%"><a href="${pageContext.request.contextPath}/welcome/${username}"><font size="+2">Home</font></a></td>
   <td> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Account No:&nbsp;&nbsp;&nbsp;${result.getAccountNumber()}
    <br />
    <br />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Account Type:&nbsp;&nbsp;&nbsp;${result.getAccountType()}
    <br />
    <br />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Account Statements
    <br />
    <br />
  
    <table>
    <tr>
               <c:if test="${fn:length(result.getStmtList())>0}">
                
                 <td>Date</user>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 
                 <td>Amount</user>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 </c:if>
            </tr>
            <tr>------------------------------------------------------------------------------</tr>
        
        <c:forEach items="${result.getStmtList()}" var="user">
           
            <tr>
                
                
                <td>${user.getDatefield()}</user>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
                 <td>${user.getAmount()}</user>
                 <td>${result.getUsername()}</user>
            </tr>
        </c:forEach>
        
        <c:if test="${empty result.getStmtList()}">
        <tr><td>There is no Statement for the requested date or amount ! </td></tr>
        </c:if>
    </table>
    <% 
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    response.setDateHeader("Expires",-1);
    
    %>
    </td></tr>
    <table>
</body>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></body>
</html>
