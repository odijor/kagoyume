<%-- 
    Document   : cart
    Created on : 2017/10/23, 13:18:31
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
                <th>削除</th>
            </tr>
            <!--arraylistの変数を取る為通常のfor文-->
            <%for(int i = 0; i < item.size(); i++){%><tr>
                <td><image src="<%=item.get(i).getImageData()%>"></td>
                <td><a href="Item?Code=<%=item.get(i).getCode()%>"><%=item.get(i).getResultData()%></a></td>
                <td><%=item.get(i).getPriceData()%>円</td>
                <td>☆<%=item.get(i).getRate()%></td>
                <td><%=item.get(i).getJanCode()%></td>
                <td><form action="Cart" method="post">
                        <input type="hidden" name="delete" value="<%=i%>">  
                        <input type="submit" name="btnSubmit" value="削除">
                </form></td>
            </tr>
            <%}%>
        
        </table>
            合計:<%=total%>円
        <form action="BuyConform" method="post">
            <input type="submit" name="btnSubmit" value="購入する">
        </form>
        <br><br><%=helper.home()%>
    </body>
</html>
