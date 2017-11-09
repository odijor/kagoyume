<%-- 
    Document   : mydeleteconplete
    Created on : 2017/10/23, 13:31:07
    Author     : sige
--%>
<%@page import="main.UserDataDTO"%>
<%@page import="main.KagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
    KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/top.jsp";
    int loginID=-1;
    String name="";
    if((UserDataDTO)session.getAttribute("active") != null){
    UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
    loginID = active.getUserID();
    name = active.getName();}
%>
        <h1>削除しました</h1>
        <form action="Login" method="post">
        <input type="hidden" name="URL" value="<%=URL%>">
        <input type="submit" value="ログアウト">
        </form><br>
    </body>
</html>
