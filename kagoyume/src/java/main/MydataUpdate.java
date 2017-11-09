/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "MydataUpdate", urlPatterns = {"/MydataUpdate"})
public class MydataUpdate extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try{
            HttpSession session = request.getSession();
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            UserDataDTO before=(UserDataDTO)session.getAttribute("active");
            UserData ud =new UserData();
            ArrayList<String> chkList;
            
            //全て入力で更新、未入力があればmydataupdateに戻す
            
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("pass"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));
            
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO DTO = ud.castToDTO();
            
            DTO.setUserID(before.getUserID());
            
            //DBで一致するものがあるか確認
            UserDataDTO loginUser = UserDataDAO .getInstance().updatechk(DTO);
            //成功したらエラーページへ
            if(loginUser != null)throw new Exception("パスワードが危険です。再入力をお願いします。");
            
            chkList = ud.chkproperties();
            if(chkList.size() == 0){
            
            DTO.setNewDate(before.getNewDate());
            DTO.setTotal(before.getTotal());
            
            //データ更新
            UserDataDAO.getInstance().update(DTO);
            
            session.setAttribute("active", DTO);
            ud.castFromDTO(DTO);
            session.setAttribute("response",ud);
            String ok = "更新完了:更新箇所をご確認ください。";
            request.setAttribute("updateOK", ok);
            session.setAttribute("ac", (int) (Math.random() * 1000));
            request.getRequestDispatcher("/mydateupdate.jsp").forward(request, response);
            }else{
            String ng ="更新失敗:未入力箇所があります。"+chkList;
            request.setAttribute("updateNG", ng);
            session.setAttribute("ac", (int) (Math.random() * 1000));
            request.getRequestDispatcher("/mydateupdate.jsp").forward(request, response);
            }
            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
