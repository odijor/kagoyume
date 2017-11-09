package main;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.net.URL;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import java.net.URLEncoder;

import net.arnx.jsonic.JSON;

/**
 *
 * @author sige
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            String searchkey=""; 
            if(request.getParameter("searchkey") == null){
                searchkey=(String)session.getAttribute("searchlog");
            }else{
                searchkey=request.getParameter("searchkey");
                if(searchkey.equals(""))throw new Exception("検索ワードを入力してください。");
                session.setAttribute("searchlog",searchkey);
            }
            searchkey=URLEncoder.encode(searchkey,"UTF-8");
            
            String jsondata = apiConect(searchkey);
            
            ArrayList<SearchDataBeans> resultList= parse(jsondata);
            
            SearchDataBeans searchCount = searchCount(jsondata);
            
            
            session.setAttribute("resultList",resultList);
            session.setAttribute("searchCount", searchCount);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("/search.jsp");
            dispatch.forward(request, response);

            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    /**
     * yahooapiに接続を行う
     * @param searchkey 検索キーワード 
     * @return jsonp
     * @throws ServletException
     * @throws IOException 
     */
    private String apiConect(String searchkey)throws ServletException, IOException{
        
        
        URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPXlzNklsSlpKQWpPUCZzPWNvbnN1bWVyc2VjcmV0Jng9OWI-&query="+searchkey);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setInstanceFollowRedirects(false);
 
        conn.connect();
 
        BufferedReader reader =
        new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        
        StringBuffer responseBuffer = new StringBuffer();
        //lineがnullになるまで読み込む
        while (true){
        String line = reader.readLine();
        //null対策
        if ( line == null ){
        break;
        }
        responseBuffer.append(line);
        }
        
        //クローズ処理
        reader.close();
        conn.disconnect();
 
        String response = responseBuffer.toString();
        
        return response;
        
        }
    
    /**
     * 検索結果をjavabeansに格納
     * @param jsonText
     * @return 検索結果をまとめたArrayList
     */
        private ArrayList<SearchDataBeans> parse(String jsonText){
        ArrayList<SearchDataBeans> resultList=new ArrayList<>();
        Map<String, Map<String, Object>> json = JSON.decode(jsonText);
        if( !Integer.valueOf((String) json.get("ResultSet").get("totalResultsAvailable")).equals(0) ){
        //apiの表示上限20
            for(int i=0;i<20;i++){
            SearchDataBeans SDB =new SearchDataBeans();
            Map<String, Object> result = ((Map<String, Object>)(
            (Map<String, Map<String, Object>>)json.get("ResultSet").get("0")
            ).get("Result").get(String.valueOf(i))
            );
            //結果をSearchDataBeansに格納
            String Code = result.get("Code").toString();
            SDB.setCode(Code);
            
            String resultData = result.get("Name").toString();
            SDB.setResultData(resultData);
            
            String imageData = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
            SDB.setImageData(imageData);
            
            String priceData = ((Map<String, Object>)result.get("Price")).get("_value").toString();
            SDB.setPriceData(priceData);
            
            resultList.add(SDB);
            }   
        }
        return resultList;
        }
        
        /**
         * 検索結果の件数を取得
         * @param jsonText
         * @return 表示件数と検索件数のjavabeansを返す
         */
        private SearchDataBeans searchCount(String jsonText){
            Map<String, Map<String, Object>> json = JSON.decode(jsonText);
            SearchDataBeans searchCount =new SearchDataBeans();
            searchCount.setTotalResultsAvailable(json.get("ResultSet").get("totalResultsAvailable"));
            searchCount.setTotalResultsReturned(json.get("ResultSet").get("totalResultsReturned"));
            return searchCount;
        }
        
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
