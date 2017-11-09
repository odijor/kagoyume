<%-- 
    Document   : buycomplete
    Created on : 2017/10/23, 13:17:43
    Author     : sige
--%>
<%@page import="main.UserDataDTO"
        import="main.KagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/buycomplete.jsp";
    int loginID=-1;
    String name="";
    if((UserDataDTO)session.getAttribute("active") != null){
    UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
    loginID = active.getUserID();
    name = active.getName();}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h2>購入しました</h2>
        <br><br><%=helper.home()%>
    </body>
</html>
