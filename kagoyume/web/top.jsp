<%-- 
    Document   : top
    Created on : 2017/10/20, 16:45:45
    Author     : sige
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper" %>
<%
    HttpSession hs = request.getSession();
    KagoyumeHelper jh = KagoyumeHelper.getInstance();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Login" method="post">
            <input type="hidden" name="top">
            <input type="submit" value="ログイン">
        </form><br><br><br><br>
        <h1>かごゆめへようこそ!!</h1><br>
        キーワード検索:<form action="Search" method="GET">
            <input type="text" name="searchkey">
            <input type="submit" name="btnSubmit" value="検索">
        </form>
    </body>
</html>
