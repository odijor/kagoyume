<%-- 
    Document   : myhistory
    Created on : 2017/10/23, 13:28:28
    Author     : sige
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper" 
        import="java.util.ArrayList;"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%ArrayList<ArrayList> resultList=(ArrayList<ArrayList>)session.getAttribute("resultList");
        KagoyumeHelper helper =new KagoyumeHelper();%>
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
        <h2>購入履歴</h2>
        <table border=1>
            <tr>
                <th>商品コード</th>
                <th>発送方法</th>
                <th>購入日時</th>
            </tr>
                <%for(ArrayList<Object> i:resultList){%>
                <td><%=i.get(0)%></td>
                <td><%=helper.exTypenum((Integer)i.get(1))%></td>
                <td><%=i.get(2)%></td>
        </tr>
            <%}%>
       
        </table>
            <br><br><%=helper.home()%>
    </body>
</html>
