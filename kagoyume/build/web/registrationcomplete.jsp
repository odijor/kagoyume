<%-- 
    Document   : registrationcomplete
    Created on : 2017/10/23, 13:24:38
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
        <%UserDataDTO DTO=(UserDataDTO)request.getAttribute("DTO");
        KagoyumeHelper helper = KagoyumeHelper.getInstance();
        String URL ="/top.jsp";
        int loginID=-1;
        String name="";
        if((UserDataDTO)session.getAttribute("active") != null){
        UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
        loginID = active.getUserID();
        name = active.getName();}
        %>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h1>以下の内容で登録しました。</h1>
        名前:<%=DTO.getName()%>
        パスワード:<%=DTO.getPassword()%>
        メールアドレス:<%=DTO.getMail()%>
        住所:<%=DTO.getAddress()%>
        <br><br><%=helper.home()%>
    </body>
</html>
