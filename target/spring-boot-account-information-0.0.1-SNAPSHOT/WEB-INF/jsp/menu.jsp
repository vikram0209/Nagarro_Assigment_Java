<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
</script>

</head>





 
<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<a href="${pageContext.request.contextPath}/welcome">Home</a> | &nbsp;
  <ul>  
    <p><b>Welcome User:</b>
      ${username}
</p>
<form action="/getAccountStatement" method="POST">
<label for="account">Please Select Account ID:</label>
<input type="number" name ="account" id="account" min="1" max="5" required>
<span>Select options </span>
<select id="ddlp">
    <option value="starter" selected>please select</option>
    <option value="Y">Date wise </option>
    <option value="N">Amount wise</option>
</select>
<hr />
<c:if test="${username=='testadmin'}">
<br/>
<br/>
<br/>
<br/>

<div id="dv1" style="display: none">
<label for="fromdate">From Date:</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name ="fromdate" id="fromdate">
<label for="todate">To Date</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name ="todate" id="todate">
</div>
<div id="dv2" style="display: none">
<label for="fromamount">From Amount:</label>
<input type="number" name ="fromamount" id="fromamount">
<label for="toamount">To Amount:</label>
<input type="number" name ="toamount" id="toamount" >
</div>
<br/>
</c:if>
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


