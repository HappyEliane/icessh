<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="javax.servlet.http.Cookie"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <script type="text/javascript">
    function userLogin(param1,param2,param3) {
    	Cookie cookie1=new Cookie("name", param1);
    	Cookie cookie2=new Cookie("org", param2);
    	Cookie cookie3=new Cookie("role", param3);
    	cookie1.setMaxAge(60*10);
    	cookie2.setMaxAge(60*10);
    	cookie3.setMaxAge(60*10);
    	alert(param1);
    	return "ok";
    }
    function readName()
    {
    }
   </script>
</head>
<body onload="userLogin();">
this is  a test!
</body>
</html>