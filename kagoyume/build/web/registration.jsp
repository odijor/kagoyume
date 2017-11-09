<%-- 
    Document   : registration
    Created on : 2017/10/23, 13:21:55
    Author     : sige
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserData"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>registration</title>
    </head>
    <%UserData ud=(UserData)session.getAttribute("ud");
    KagoyumeHelper helper = new KagoyumeHelper();
    boolean reinput = false;
    if(ud != null)reinput = true;%>
    <body>
        <%
        String URL ="/top.jsp";
        int loginID=-1;
        String name="";
        if((UserDataDTO)session.getAttribute("active") != null){
        UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
        loginID = active.getUserID();
        name = active.getName();}
        %>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h1>新規会員登録ページです</h1><br>
        以下の要素を全て入力してください。<br>
        <form action="RegistrationConfirm" method="POST">
            名前:<input type="text" name="name" value="<%if(reinput)out.print(ud.getName());%>"><br>
            パスワード:<input type="text" name="pass" value="<%if(reinput)out.print(ud.getPassword());%>"><br>
            メールアドレス:<input type="text" name="mail" value="<%if(reinput)out.print(ud.getMail());%>"><br>
            住所:<input type="text" name="address" value="<%if(reinput)out.print(ud.getAddress());%>"><br>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="登録確認画面へ">
        </form>
        <br><br><%=helper.home()%>
    </body>
</html>
