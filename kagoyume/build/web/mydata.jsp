<%-- 
    Document   : mydata
    Created on : 2017/11/02, 0:49:10
    Author     : sige
--%>
<%@page import="main.KagoyumeHelper"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/top.jsp";
    int loginID=-1;
    String name="";
    UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
    loginID = active.getUserID();
    name = active.getName();%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mydata</title>
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h2>会員情報</h2> 
        氏名:<%=active.getName()%><br>
        パスワード:<%=active.getPassword()%><br>
        メールアドレス:<%=active.getMail()%><br>
        住所:<%=active.getAddress()%><br>
        総購入金額:<%=active.getTotal()%>円<br>
        登録日:<%=active.getNewDate()%><br>
        <br><br>
        <form action="Myhistory" method="post">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="購入履歴">
        </form><br>
        <form action="mydateupdate.jsp" method="post">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="更新">
        </form><br>
        <form action="Mydelete" method="post">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="削除">
        </form>
        <br><br><%=helper.home()%>
    </body>
</html>
