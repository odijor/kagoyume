<%-- 
    Document   : top
    Created on : 2017/10/20, 16:45:45
    Author     : sige
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserDataDTO"%>

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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>top</title>
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h2>かごゆめ!!</h2><br>
        商取引の無いECサイトです。<br>
        自由に買い物をして合計金額を確認してみましょう。<br>
        <h3>キーワード検索:</h3><form action="Search" method="GET">
            <input type="text" name="searchkey">
            <input type="submit" name="btnSubmit" value="検索">
        </form>
    </body>
</html>
