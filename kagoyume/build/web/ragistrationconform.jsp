<%-- 
    Document   : ragistrationconform
    Created on : 2017/10/23, 13:22:49
    Author     : sige
--%>
<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserData" 
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ragistrationconform</title>
    </head>
    <body>
        <%UserData ud=(UserData)session.getAttribute("ud");
        ArrayList<String> chkList = ud.chkproperties();
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
        <%if(chkList.size() == 0){%>
        <h1>以下の内容で登録します。よろしいですか?</h1>
        名前:<%=ud.getName()%>
        パスワード:<%=ud.getPassword()%>
        メールアドレス:<%=ud.getMail()%>
        住所:<%=ud.getAddress()%>
        <form action="RegistrationComplete" method="POST">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btmsumit" value="登録">
        </form>
        <%}else{%>
        <h1>以下の要素が未入力です。入力をお願いします。</h1>
        <%=ud.chkproperties() %>
        <%}%>
        <form action="Registration">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="re-entry" value="再記入">
        </form><br><br>
        <%=helper.home()%>
    </body>
</html>
