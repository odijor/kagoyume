/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import base.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sige
 */
@WebServlet(name = "BuyComplete", urlPatterns = {"/BuyComplete"})
public class BuyComplete extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection con = null;
        PreparedStatement st = null;
        HttpSession session = request.getSession();
        
        
        
        ArrayList<SearchDataBeans> usercart =new ArrayList<>();
        int type = Integer.parseInt(request.getParameter("type"));
        usercart = (ArrayList<SearchDataBeans>)session.getAttribute("usercart");
        UserDataDTO DTO=(UserDataDTO)session.getAttribute("active");
        HashMap <Integer,ArrayList<SearchDataBeans>>userlog=(HashMap <Integer,ArrayList<SearchDataBeans>>)session.getAttribute("userList");
        
        try{
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            con = DBManager.getConnection();
            for(SearchDataBeans i:usercart){
            st =  con.prepareStatement("INSERT INTO buy_t(userID,type,itemcode,buyDate) VALUES(?,?,?,?)");
            st.setInt(1,DTO.getUserID());
            st.setInt(2, type);
            st.setString(3, i.getCode());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
            }
            
            st =  con.prepareStatement("UPDATE user_t set total=total+? where userID=?;");
            st.setInt(1, (Integer)session.getAttribute("total"));
            st.setInt(2, DTO.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
            
            DTO.setTotal(DTO.getTotal()+(Integer)session.getAttribute("total"));
            
            //空のArrayListに差し替えることでusercartの中身を除去(removeAllの場合usercartがnullという問題発生)
            ArrayList<SearchDataBeans> clear =new ArrayList<>();
            session.setAttribute("usercart",clear);
            if(userlog.containsKey(DTO.getUserID())){
                userlog.remove(DTO.getUserID());
                session.setAttribute("userList",userlog);
            }
            
            
            session.setAttribute("ac", (int) (Math.random() * 1000));
            request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }finally{
            if(con != null){
                con.close();
            }
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuyComplete.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuyComplete.class.getName()).log(Level.SEVERE, null, ex);
        }
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
