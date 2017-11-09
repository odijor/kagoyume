<%-- 
    Document   : error
    Created on : 2017/10/20, 16:48:51
    Author     : sige
--%>
<%@page import="main.KagoyumeHelper"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        <h1>エラーが発生しました。以下の要素を確認してください。</h1><br>
        <%=request.getAttribute("error")%><br><br>
        <%=KagoyumeHelper.getInstance().home()%>
        
    </body>
</html>
