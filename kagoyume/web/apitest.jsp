<%-- 
    Document   : apitest
    Created on : 2017/10/24, 18:08:30
    Author     : sige
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="https://s.yimg.jp/images/yjdn/js/bakusoku-jsonp-v1-min.js"
    data-url="http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch"
    data-p-appid="dj00aiZpPXlzNklsSlpKQWpPUCZzPWNvbnN1bWVyc2VjcmV0Jng9OWI-"
    data-p-query= <%out.print("\""+request.getParameter("searchkey")+"\"");%> >
{{#ResultSet.0.Result}}
 {{#0}}
 <a href="{{Url}}"><img src="{{Image.Medium}}" alt="{{Name}}"></a>
 {{/0}}
 {{#1}}
 <a href="{{Url}}"><img src="{{Image.Medium}}" alt="{{Name}}"></a>
 {{/1}}
 {{#2}}
 <a href="{{Url}}"><img src="{{Image.Medium}}" alt="{{Name}}"></a>
 {{/2}}
{{/ResultSet.0.Result}}
</script>
    </body>
</html>
