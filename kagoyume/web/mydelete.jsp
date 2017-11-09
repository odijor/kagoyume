<%-- 
    Document   : mydelete
    Created on : 2017/10/23, 13:30:12
    Author     : sige
--%>
<%@page import="main.KagoyumeHelper"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mydelete</title>
    </head>
    <body>
        <%KagoyumeHelper helper = KagoyumeHelper.getInstance();
        String URL ="/top.jsp";
        int loginID=-1;
        String name="";
        UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
        loginID = active.getUserID();
        name = active.getName();%>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h1>このユーザーをマジで削除しますか?</h1>
        氏名:<%=active.getName()%><br>
        パスワード:<%=active.getPassword()%><br>
        メールアドレス:<%=active.getMail()%><br>
        住所:<%=active.getAddress()%><br>
        総購入金額:<%=active.getTotal()%><br>
        登録日:<%=active.getNewDate()%><br>
        <form action="Mydeleteresult" method="post">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="削除">
        </form><br>
        <form action="Mydata" method="post">
            <input type="submit" name="btnSubmit" value="戻る">
        </form><br>
        <br><br><%=helper.home()%>
    </body>
</html>
