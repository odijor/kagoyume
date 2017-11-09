<%-- 
    Document   : search
    Created on : 2017/10/23, 13:25:25
    Author     : sige
--%>
<%@page import="main.SearchDataBeans"
        import="javax.servlet.http.HttpSession"
        import="main.KagoyumeHelper" 
        import="java.util.ArrayList;"
        import="main.UserDataDTO"
        import="java.net.URLDecoder"%>
<%ArrayList<SearchDataBeans> resultList = (ArrayList<SearchDataBeans>)session.getAttribute("resultList");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    KagoyumeHelper helper = KagoyumeHelper.getInstance();
    String URL ="/search.jsp";
    int loginID=-1;
    String name="";
    if((UserDataDTO)session.getAttribute("active") != null){
    UserDataDTO active = (UserDataDTO)session.getAttribute("active");
    
    loginID = active.getUserID();
    name = active.getName();}
    
    SearchDataBeans searchCount =(SearchDataBeans)session.getAttribute("searchCount");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果</title>
        
    </head>
    <body>
        <%=helper.loginCheck(URL,loginID,name)%>
        <h2><%=URLDecoder.decode((String)session.getAttribute("searchlog"), "UTF-8")%>の検索結果</h2>
        <table border=1>
            <%=helper.home()%>
            表示数<%=searchCount.getTotalResultsReturned()%>/該当件数<%=searchCount.getTotalResultsAvailable()%>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>価格</th>
            </tr>
                <%for(SearchDataBeans i:resultList){%>
                <td><image src="<%=i.getImageData()%>"></td>
                <td><a href="Item?Code=<%=i.getCode()%>"><%=i.getResultData()%></a></td>
                <td><%=i.getPriceData()%>円</td>
        </tr>
            <%}%>
       
        </table>
            <br><br><%=helper.home()%>
    </body>
</html>
