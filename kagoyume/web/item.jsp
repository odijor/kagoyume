<%-- 
    Document   : item
    Created on : 2017/10/23, 13:18:45
    Author     : sige
--%>

<%@page import="main.SearchDataBeans"
        import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper"
        import="main.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%SearchDataBeans detail = (SearchDataBeans)session.getAttribute("detailData");
    KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/item.jsp";
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
        <title>DetailData</title>
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h1>詳細情報</h1>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>価格</th>
                <th>評価</th>
                <th>JanCode</th>
            </tr><tr>
                <td><image src="<%=detail.getImageData()%>"></td>
                <td><%=detail.getResultData()%></td>
                <td><%=detail.getPriceData()%>円</td>
                <td>☆<%=detail.getRate()%></td>
                <td><%=detail.getJanCode()%></td>
            </tr>
        </table><table border=1>
            <tr>
                <th>詳細情報</th>
            </tr><tr>
                <td><%=detail.getDetailData()%></td>
            </tr>
       
        </table>
            <form action="Add">
                <input type="hidden" name="code" value="<%=detail.getCode()%>">
                <input type="submit" value="カートに追加する">
            </form>
            <br><br><%=helper.home()%>
    </body>
</html>
