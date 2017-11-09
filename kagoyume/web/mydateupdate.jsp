<%-- 
    Document   : mydateupdate
    Created on : 2017/10/23, 13:20:16
    Author     : sige
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserDataDTO"
        import="main.UserData"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%UserDataDTO active=(UserDataDTO)session.getAttribute("active");
        UserData ud = new UserData();
        ud.castFromDTO(active);
        KagoyumeHelper helper =new KagoyumeHelper();
        
        String URL ="/top.jsp";
        int loginID=-1;
        String name="";
    
        loginID = active.getUserID();
        name = active.getName();
        %>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h1>会員情報更新ページです</h1><br>
        <%if(request.getAttribute("updateOK") != null){out.print(request.getAttribute("updateOK"));}
        else if(request.getAttribute("updateNG") != null){out.print(request.getAttribute("updateNG"));}
        else{out.print("以下の要素を全て入力してください。");}%><br>
        <form action="MydataUpdate" method="POST">
            名前:<input type="text" name="name" value="<%=ud.getName()%>"><br>
            パスワード:<input type="text" name="pass" value="<%=ud.getPassword()%>"><br>
            メールアドレス:<input type="text" name="mail" value="<%=ud.getMail()%>"><br>
            住所:<input type="text" name="address" value="<%=ud.getAddress()%>"><br>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="更新">
        </form>
        <form action="Mydata" method="post">
            <input type="submit" name="btnSubmit" value="戻る">
        </form><br>
        <br><br><%=helper.home()%>
    </body>
</html>
