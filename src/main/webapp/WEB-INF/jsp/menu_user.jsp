<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT=no-cache"/>
<META HTTP-EQUIV="Expires" CONTENT="Sat, 01 Dec 2001 00:00:00 GMT"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> 
<script>
$(function() {
  $("#fromdate").datepicker({dateFormat:'dd.mm.yy'});
  $("#todate").datepicker({dateFormat:'dd.mm.yy'});
});
</script>
<script type="text/javascript">
    $(function () {
        $("#ddlp").change(function () {
            if ($(this).val() == "Y") {
                $("#dv1").show();
            } else {
               $("#dv1").hide();
                $("#dv2").show();
            }
        });
    });
    
    
  function  preventBack(){
    window.history.forward();
    
    }
    window.onunload=function(){
       null;
    }
    setTimeout("preventBack()",0);
</script>



</head>





 
<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<a href="${pageContext.request.contextPath}/welcome/ ${username}">Home</a> | &nbsp;
  <ul>  
    <p><b>Welcome User:</b>
      ${username}
</p>
<form action="/getAccountStatementUser" method="POST">
<label for="account">Please Select Account ID:</label>
<input type="number" name ="account" id="account" min="1" max="5" required>
<input type="hidden" name ="usename" id="username" value="${username}">

<br/>
<br/>
<input type="submit" value="Show Statements" />
</form>
 </ul>
 <li> <ul><h2 style="color: red;">
           <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h3></u>
      <form id="logoutForm" method="POST" action="${contextPath}/logout">
      </form>
      </ul>
</li>
</div>

</html>


