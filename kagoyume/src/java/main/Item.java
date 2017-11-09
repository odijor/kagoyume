/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import net.arnx.jsonic.JSON;
/**
 *
 * @author sige
 */
@WebServlet(name = "Item", urlPatterns = {"/Item"})
public class Item extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
             HttpSession session = request.getSession();
             request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
             
             String Code = request.getParameter("Code");
             String jsondata = apiConect(Code);
             
             SearchDataBeans SDB = parse(jsondata);
             session.setAttribute("detailData", SDB);
             
             RequestDispatcher dispatch = request.getRequestDispatcher("/item.jsp");
            dispatch.forward(request, response);
             
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    /**
     * 詳細情報を検索する
     * @param searchkey Searchから商品コードを持ってくる
     * @return 商品のjsonp
     * @throws ServletException
     * @throws IOException 
     */
    private String apiConect(String searchkey)throws ServletException, IOException{
        
        
        URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPXlzNklsSlpKQWpPUCZzPWNvbnN1bWVyc2VjcmV0Jng9OWI-&itemcode="+searchkey+"&responsegroup=medium");
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
     * 商品の詳細情報を返す
     * @param jsonText 商品のjsonp
     * @return 詳細情報
     */
    private SearchDataBeans parse(String jsonText){
        Map<String, Map<String, Object>> json = JSON.decode(jsonText);
        SearchDataBeans SDB =new SearchDataBeans();
        if( !Integer.valueOf((String) json.get("ResultSet").get("firstResultPosition")).equals(0) ){
            Map<String, Object> result = ((Map<String, Object>)(
            (Map<String, Map<String, Object>>)json.get("ResultSet").get("0")
            ).get("Result").get(String.valueOf(0))
            );
            String Code = result.get("Code").toString();
            SDB.setCode(Code);
            
            String janCode = result.get("JanCode").toString();
            SDB.setJanCode(janCode);
            
            String resultData = result.get("Name").toString();
            SDB.setResultData(resultData);
            
            String detailData = result.get("Description").toString();
            SDB.setDetailData(detailData);
            
            String rate = ((Map<String,Object>)result.get("Review")).get("Rate").toString();
            SDB.setRate(rate);
            
            String imageData = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
            SDB.setImageData(imageData);
            
            String priceData = ((Map<String, Object>)result.get("Price")).get("_value").toString();
            SDB.setPriceData(priceData);
            
            
        }
        return SDB;
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
