<%@page session="false"%>
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache" />
<META HTTP-EQUIV="Cache-Control" CONTENT=no-cache" />
<META HTTP-EQUIV="Expires" CONTENT="Sat, 01 Dec 2001 00:00:00 GMT" />

<script type="type/javascript">

window.history.forward();
function noBack(){
window.history.forward();


}
 




</script>




<title>Welcome</title>


</head>
<body onload="noBack(); onpageshow="if (event.persisted) noBack(); onunload="">
	<jsp:include page="menu_user.jsp" />
</body>
</html>