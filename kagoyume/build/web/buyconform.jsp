<%-- 
    Document   : buyconform
    Created on : 2017/10/23, 13:17:18
    Author     : sige
--%>
<%@page import="java.util.ArrayList"
        import="main.SearchDataBeans"
        import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ArrayList<SearchDataBeans> item = (ArrayList<SearchDataBeans>)session.getAttribute("usercart");
int total = (Integer)session.getAttribute("total");
    KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/top.jsp";
    int loginID=-1;
    String name="";
    if((UserDataDTO)session.getAttribute("active") != null){
    UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
    loginID = active.getUserID();
    name = active.getName();}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h2>詳細情報</h2>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>価格</th>
                <th>評価</th>
                <th>JanCode</th>
            </tr>
            <%for(SearchDataBeans i:item){%><tr>
                <td><image src="<%=i.getImageData()%>"></td>
                <td><%=i.getResultData()%></td>
                <td><%=i.getPriceData()%></td>
                <td><%=i.getRate()%></td>
                <td><%=i.getJanCode()%></td>
            </tr>
            <%}%>
        
        </table>
            <h3>合計:<%=total%>円</h3>
        <form action="BuyComplete" method="post">
            発送方法:
            <% for(int i = 1; i<=2; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%if(i==1)out.print("checked=\"checked\"");%>><%=helper.exTypenum(i)%><br>
            <% } %>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="上記の内容で購入する">
        </form>
            <br><br><%=helper.home()%>
    </body>
</html>
