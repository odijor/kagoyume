<%-- 
    Document   : login
    Created on : 2017/10/23, 13:19:34
    Author     : sige
--%>

<%@page import="main.KagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%KagoyumeHelper helper = KagoyumeHelper.getInstance();%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>ログインページです。</h1><br>
            アカウントをお持ちの方はIDとパスワード入力してください。<br>
            初めての方は会員登録ページへお進みください。<br>
            ログイン<br>
            <form action="LoginResult" method="post">
                ID<input type="text" name="name" >
                パスワード<input type="password" name="pass">
                <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
                <input type="submit" name="btnSubmit" value="ログイン">
            </form><br>
            <form action="Registration" method="post">
                <input type="submit" name="btnSubmit" value="新規会員登録">
            </form>
            
            <br><br><%=helper.home()%>
    </body>
</html>
